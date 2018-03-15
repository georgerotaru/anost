/*
 * The MIT License
 *
 * Copyright 2018 admin.
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
import com.restfb.exception.FacebookOAuthException;
import com.restfb.types.Event;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;
import java.sql.Time;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;
import ro.anost.jdbcconn.JDBCConnectionManager;

/**
 *
 */
public class AddEventDetails {

    private String eventId;
    private String accTkn;
    private String message;
    private Statement statement = null;
    private ResultSet resultSet = null;
    private PreparedStatement ppstm = null;
    private Connection connection = null;

    public AddEventDetails(String eventId, String accTkn) throws SQLException {
        try{
            FacebookClient client = new DefaultFacebookClient(Version.VERSION_2_11);
            FacebookClient fbClient = new DefaultFacebookClient(accTkn, Version.VERSION_2_11);
            try{
                connection = JDBCConnectionManager.getJDBCConnection();
                statement = connection.createStatement();
                String query = "SELECT EVENT_ID, NAME FROM FB_EVENT_DETAILS WHERE EVENT_ID='"+eventId+"'";
                resultSet = statement.executeQuery(query);
                Boolean resultSetHasRows = resultSet.next();
                if (resultSetHasRows) {
                    String eventName = resultSet.getString("NAME");
                    this.message = "<div style=\"color: #FF0000\">Event <i>"+eventName+"</i> already in database!</div>";
                } else {
                    try{
                        Event eventSearch = fbClient.fetchObject(eventId, Event.class);
                        Event eventSearchWithParam = fbClient.fetchObject(eventId, Event.class, Parameter.with("fields", "attending_count,interested_count"));

                        String searchCriteria = eventId+"/admins";
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

                        eventDetails.add("https://www.facebook.com/events/"+eventId);

                        Integer eventUsersAttending = eventSearchWithParam.getAttendingCount();
                        Long eventUsersInterested = eventSearchWithParam.getInterestedCount();

                        List<Double> eventCoordinates = new LinkedList<>();
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

                        String userID = "";
                        String userDisplayName = "";
                        Map<String, String> eventAdminList = new HashMap<>();
                        for (int i=0; i<eventAdmins.getData().size(); i++) {
                            userID = eventAdmins.getData().get(i).getId();
                            userDisplayName = eventAdmins.getData().get(i).getName();
                            eventAdminList.put(userID, userDisplayName);
                        }

                        connection.setAutoCommit(false);
                        query = "INSERT INTO FB_EVENT_DETAILS(EVENT_ID, NAME, CITY, PLACE, COUNTRY, ATTENDING_COUNT, INTERESTED_COUNT, LATITUDE, LONGITUDE, START_DATE, START_TIME, END_DATE, END_TIME, URL, DESCRIPTION)"
                                + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                        ppstm = connection.prepareStatement(query);
                        ppstm.setString(1, eventId);
                        ppstm.setString(2, eventDetails.get(0));
                        this.message = "<div style=\"color: #FF0000\">Event <i>"+eventDetails.get(0)+"</i> added to database!</div>";
                        ppstm.setString(3, eventDetails.get(1));
                        ppstm.setString(4, eventDetails.get(2));
                        ppstm.setString(5, eventDetails.get(3));
                        ppstm.setInt(6, eventUsersAttending);
                        ppstm.setLong(7, eventUsersInterested);
                        ppstm.setDouble(8, eventCoordinates.get(0));
                        ppstm.setDouble(9, eventCoordinates.get(1));
                        ppstm.setDate(10, eventDate.get(0));
                        ppstm.setTime(11, eventTime.get(0));
                        ppstm.setDate(12, eventDate.get(1));
                        ppstm.setTime(13, eventTime.get(1));
                        ppstm.setString(14, eventDetails.get(4));
                        ppstm.setString(15, eventDetails.get(5));
                        ppstm.executeUpdate();

                        for (Entry<String, String> entry : eventAdminList.entrySet()){
                            String key = entry.getKey();
                            String value = entry.getValue();
                            query = "INSERT INTO FB_EVENT_ADMINS VALUES (?,?)";
                            ppstm = connection.prepareStatement(query);
                            ppstm.setString(1, eventId);
                            ppstm.setString(2, key);
                            ppstm.executeUpdate();
                            query = "SELECT USER_ID FROM FB_USER_IDENTITY WHERE USER_ID='"+key+"'";
                            resultSet = statement.executeQuery(query);
                            resultSetHasRows = resultSet.next();
                            if (resultSetHasRows) {
                                query = "UPDATE FB_USER_IDENTITY SET USER_DISPLAY_NAME = ? WHERE USER_ID = ?";
                                ppstm = connection.prepareStatement(query);
                                ppstm.setString(1, value);
                                ppstm.setString(2, key);
                                ppstm.executeUpdate();
                            } else {
                                query = "INSERT INTO FB_USER_IDENTITY VALUES (?,?,?)";
                                String userURL = "https://www.facebook.com/"+key;
                                ppstm = connection.prepareStatement(query);
                                ppstm.setString(1, key);
                                ppstm.setString(2, value);
                                ppstm.setString(3, userURL);
                                ppstm.executeUpdate();                            
                            }
                        }
                        
                        connection.commit();
                        connection.setAutoCommit(true);
                    } catch (FacebookOAuthException | SQLException ex) {
                        System.out.println("Could not connect event object or insert event or admin details.");
                        this.message = "Could not connect event object or insert event or admin details.";
                        Logger.getLogger(AddEventDetails.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            } catch (SQLException ex) {
                System.out.println("Could not query database for event id.");
                this.message = "Could not query database for event id.";
                Logger.getLogger(AddEventDetails.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (FacebookOAuthException ex) {
            System.out.println("Could not create fbClient Object. Check API version or access token.");
            this.message = "Could not create fbClient Object. Check API version or access token.";
            Logger.getLogger(AddEventDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getMessage() {
        return message;
    }
    
}
