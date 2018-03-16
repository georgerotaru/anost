/*
 * The MIT License
 *
 * Copyright 2018 George.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package ro.anost.utils.fb;

import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.Version;
import com.restfb.exception.FacebookGraphException;
import com.restfb.exception.FacebookOAuthException;
import com.restfb.types.Event;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import ro.anost.jdbcconn.JDBCConnectionManager;

/**
 *
 */
public class UpdateEventDetails {
    private String[] checkBoxes;
    private String accTkn;
    private String message;
    private Statement statement = null;
    private ResultSet resultSet = null;
    private PreparedStatement ppstm = null;
    private Connection connection = null;

    public UpdateEventDetails(String[] checkBoxes, String accTkn) {
        try{
            FacebookClient client = new DefaultFacebookClient(Version.VERSION_2_11);
            FacebookClient fbClient = new DefaultFacebookClient(accTkn, Version.VERSION_2_11);
            try{
                connection = JDBCConnectionManager.getJDBCConnection();
                statement = connection.createStatement();
                for(String parseEvents : checkBoxes){
                    Event eventSearch = fbClient.fetchObject(parseEvents, Event.class);
                    Event eventSearchWithParam = fbClient.fetchObject(parseEvents, Event.class, Parameter.with("fields", "attending_count,interested_count"));
                    String searchCriteria = parseEvents+"/admins";
                    com.restfb.Connection<Event> eventAdmins = fbClient.fetchConnection(searchCriteria, Event.class);
                    List<String> eventDetails = new LinkedList<>();
                    try {
                        eventDetails.add(eventSearch.getName());
                    } catch (NullPointerException ex) {
                        eventDetails.add(null);
                    }
                    try {
                        eventDetails.add(eventSearch.getPlace().getLocation().getCity());
                    } catch (NullPointerException ex) {
                        eventDetails.add(null);
                    }
                    try {
                        eventDetails.add(eventSearch.getPlace().getName());
                    } catch (NullPointerException ex) {
                        eventDetails.add(null);
                    }
                    try {
                        eventDetails.add(eventSearch.getPlace().getLocation().getCountry());
                    } catch (NullPointerException ex) {
                        eventDetails.add(null);
                    }

                    Integer eventUsersAttending = eventSearchWithParam.getAttendingCount();
                    Long eventUsersInterested = eventSearchWithParam.getInterestedCount();

                    LinkedList<Double> eventCoordinates = new LinkedList<>();
                    try {
                        eventCoordinates.add(eventSearch.getPlace().getLocation().getLatitude());
                        eventCoordinates.add(eventSearch.getPlace().getLocation().getLongitude());
                    } catch (NullPointerException ex) {
                        eventCoordinates.add(0.0);
                        eventCoordinates.add(0.0);
                    }

                    List<Date> eventDate = new LinkedList<>();
                    List<Time> eventTime = new LinkedList<>();    
                    try {
                        java.sql.Date date = new java.sql.Date(eventSearch.getStartTime().getTime());
                        eventDate.add(date);
                    } catch (NullPointerException ex) {
                        eventDate.add(null);
                    }
                    try {
                        java.sql.Time time = new java.sql.Time(eventSearch.getStartTime().getTime());
                        eventTime.add(time);
                    } catch (NullPointerException ex) {
                        eventTime.add(null);
                    }
                    try {
                        java.sql.Date date = new java.sql.Date(eventSearch.getEndTime().getTime());
                        eventDate.add(date);
                    } catch (NullPointerException ex) {
                        eventDate.add(null);
                    }
                    try {
                        java.sql.Time time = new java.sql.Time(eventSearch.getEndTime().getTime());
                        eventTime.add(time);
                    } catch (NullPointerException ex) {
                        eventTime.add(null);
                    }
                    try {
                        eventDetails.add(eventSearch.getDescription());
                    } catch (NullPointerException ex) {
                        eventDetails.add(null);
                    }

                    connection.setAutoCommit(false);
                    String query = "UPDATE FB_EVENT_DETAILS SET NAME=?, CITY=?, PLACE=?, COUNTRY=?, ATTENDING_COUNT=?, INTERESTED_COUNT=?, LATITUDE=?, LONGITUDE=?, START_DATE=?, START_TIME=?, END_DATE=?, END_TIME=?, LAST_UPDATE=?, DESCRIPTION=? WHERE EVENT_ID='"+
                            parseEvents+"'";
                    ppstm = connection.prepareStatement(query);

                    ppstm.setString(1, eventDetails.get(0));
                    ppstm.setString(2, eventDetails.get(1));
                    ppstm.setString(3, eventDetails.get(2));
                    ppstm.setString(4, eventDetails.get(3));
                    ppstm.setInt(5, eventUsersAttending);
                    ppstm.setLong(6, eventUsersInterested);
                    ppstm.setDouble(7, eventCoordinates.get(0));
                    ppstm.setDouble(8, eventCoordinates.get(1));
                    ppstm.setDate(9, eventDate.get(0));
                    ppstm.setTime(10, eventTime.get(0));
                    ppstm.setDate(11, eventDate.get(1));
                    ppstm.setTime(12, eventTime.get(1));

                    java.util.Date nowDateJave = new java.util.Date();
                    ppstm.setTimestamp(13, new java.sql.Timestamp(nowDateJave.getTime()));

                    ppstm.setString(14, eventDetails.get(4));
                    ppstm.executeUpdate();
                }
                connection.commit();
                System.out.println("Event(s) updated.");
                connection.setAutoCommit(true);
                JDBCConnectionManager.closeJDBCConnection();
                this.message = "Event(s) updated!";
            } catch (SQLException ex) {
                System.out.println("Event(s) update failed. Please check logs.");
                this.message = "Event(s) update failed. If problem persists, please contact the administrator.";
                Logger.getLogger(UpdateEventDetails.class.getName()).log(Level.SEVERE, null, ex);
                JDBCConnectionManager.closeJDBCConnection();
            } catch (FacebookGraphException ex) {
                System.out.println("Could not update event details. Most common problems are that event was deleted, the user does not have the credentials to see it or is not logged in with his Facebook account.");
                this.message = "The event(s) could not be updated.<br/>Make sure you are logged in with your Facebook or if the event was not deleted from Facebook or if you have the credentials to acccess it!";
                Logger.getLogger(AddEventDetails.class.getName()).log(Level.SEVERE, null, ex);
                JDBCConnectionManager.closeJDBCConnection();
            }
        }catch(FacebookOAuthException ex){
            System.out.println("Could not create fbClient Object. Check API version or access token.");
            this.message = "Could not connect with Facebook. If problem persists, please contact the administrator.";
            Logger.getLogger(AddEventDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getMessage() {
        return message;
    }
    
}
