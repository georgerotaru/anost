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
package ro.anost.servlets.fbevents;

import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.Version;
import com.restfb.exception.FacebookGraphException;
import com.restfb.types.Event;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.Time;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author George
 */
public class EventsMain extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        PreparedStatement pstmnt = null;
        String sqlUser = "anost";
        String sqlPasswd = "anost";
        String sqlUrl = "jdbc:derby://localhost:1527/anost_db;create=true";
        String driver = "org.apache.derby.jdbc.ClientDriver";
        try { 
            Class driverClass = Class.forName(driver);
            connection = DriverManager.getConnection(sqlUrl, sqlUser, sqlPasswd);
            statement = connection.createStatement();
            if (request.getParameter("fbevents_add") != null) {
                String eventId = request.getParameter("fbevents_id");
                String query = "SELECT * FROM FB_EVENT_DETAILS WHERE EVENT_ID='"+eventId+"'";
                resultSet = statement.executeQuery(query);
                Boolean resultSetHasRows = resultSet.next();
                if (resultSetHasRows) {
                    String eventName = resultSet.getString("NAME");
                    request.setAttribute("inDB", true);
                    request.setAttribute("message", "Event \""+eventName+"\" already in database!");
                    RequestDispatcher dispatcher = request.getRequestDispatcher("./fb/events/add_new.jsp");
                    dispatcher.forward(request, response);
                } else {
                    String fbId = "EAATyQwZCvEZB4BAOZA3ueFxZA6iIaHJ5ZClj6IqZBvYWj58IpZCVEMKIvO3wSdqdRbBh5RhwA4ZBT0IaJ4yM9piR0wODOBYi7jZCtopQ2gJ6q10jnoGepuPj42aJ5mZBZC68JaO2AzQL7rKj6LyYHgpBmlSQgNKB8ZCJo1T0hND8UkMIHgZDZD";
                    FacebookClient fbClient = new DefaultFacebookClient(fbId, Version.VERSION_2_11);
                    Event eventSearch = fbClient.fetchObject(eventId, Event.class);
                    Event eventSearchWithParam = fbClient.fetchObject(eventId, Event.class, Parameter.with("fields", "attending_count,interested_count"));
                    String searchCriteria = eventId+"/admins";
                    com.restfb.Connection<Event> eventAdmins = fbClient.fetchConnection(searchCriteria, Event.class);
                    LinkedList<String> eventDetails = new LinkedList<>();
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
                    
                    LinkedList<Double> eventCoordinates = new LinkedList<>();
                    try {
                        eventCoordinates.add(eventSearch.getPlace().getLocation().getLatitude());
                        eventCoordinates.add(eventSearch.getPlace().getLocation().getLongitude());
                    } catch (NullPointerException ex) {
                        eventCoordinates.add(0.0);
                        eventCoordinates.add(0.0);
                    }
                    
                    LinkedList<Date> eventDate = new LinkedList<>();
                    LinkedList<Time> eventTime = new LinkedList<>();    
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
                    HashMap<String, String> eventAdminList = new HashMap<>();
                    for (int i=0; i<eventAdmins.getData().size(); i++) {
                        userID = eventAdmins.getData().get(i).getId();
                        userDisplayName = eventAdmins.getData().get(i).getName();
                        eventAdminList.put(userID, userDisplayName);
                    }
                    
                    connection.setAutoCommit(false);
                    query = "INSERT INTO FB_EVENT_DETAILS(EVENT_ID, NAME, CITY, PLACE, COUNTRY, ATTENDING_COUNT, INTERESTED_COUNT, LATITUDE, LONGITUDE, START_DATE, START_TIME, END_DATE, END_TIME, URL, DESCRIPTION)"
                            + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                    pstmnt = connection.prepareStatement(query);
                    pstmnt.setString(1, eventId);
                    pstmnt.setString(2, eventDetails.get(0));
                    pstmnt.setString(3, eventDetails.get(1));
                    pstmnt.setString(4, eventDetails.get(2));
                    pstmnt.setString(5, eventDetails.get(3));
                    pstmnt.setInt(6, eventUsersAttending);
                    pstmnt.setLong(7, eventUsersInterested);
                    pstmnt.setDouble(8, eventCoordinates.get(0));
                    pstmnt.setDouble(9, eventCoordinates.get(1));
                    pstmnt.setDate(10, eventDate.get(0));
                    pstmnt.setTime(11, eventTime.get(0));
                    pstmnt.setDate(12, eventDate.get(1));
                    pstmnt.setTime(13, eventTime.get(1));
                    pstmnt.setString(14, eventDetails.get(4));
                    pstmnt.setString(15, eventDetails.get(5));
                    pstmnt.executeUpdate();
                    
                    for (Entry<String, String> entry : eventAdminList.entrySet()){
                        String key = entry.getKey();
                        String value = entry.getValue();
                        query = "INSERT INTO FB_EVENT_ADMINS VALUES (?,?)";
                        pstmnt = connection.prepareStatement(query);
                        pstmnt.setString(1, eventId);
                        pstmnt.setString(2, key);
                        pstmnt.executeUpdate();
                        query = "SELECT USER_ID FROM FB_USER_IDENTITY WHERE USER_ID='"+key+"'";
                        resultSet = statement.executeQuery(query);
                        resultSetHasRows = resultSet.next();
                        if (resultSetHasRows) {
                            query = "UPDATE FB_USER_IDENTITY SET USER_DISPLAY_NAME = ? WHERE USER_ID = ?";
                            pstmnt = connection.prepareStatement(query);
                            pstmnt.setString(1, value);
                            pstmnt.setString(2, key);
                            pstmnt.executeUpdate();
                        } else {
                            query = "INSERT INTO FB_USER_IDENTITY VALUES (?,?,?)";
                            String userURL = "https://www.facebook.com/"+key;
                            pstmnt = connection.prepareStatement(query);
                            pstmnt.setString(1, key);
                            pstmnt.setString(2, value);
                            pstmnt.setString(3, userURL);
                            pstmnt.executeUpdate();                            
                        }
                    }
                    
                    connection.commit();
                    connection.setAutoCommit(true);
                    request.setAttribute("inDB", true);
                    request.setAttribute("message", "Event \""+eventDetails.get(0)+"\" added to database!");
                    RequestDispatcher dispatcher = request.getRequestDispatcher("./fb/events/add_new.jsp");
                    dispatcher.forward(request, response);
                }
            } else if (request.getParameter("fbevents_all") != null) {
                request.setAttribute("queryDB", "SELECT EVENT_ID, NAME, CITY, PLACE, ATTENDING_COUNT, INTERESTED_COUNT, START_DATE, START_TIME, LAST_UPDATE, URL FROM FB_EVENT_DETAILS ORDER BY START_DATE DESC");
                RequestDispatcher dispatcher = request.getRequestDispatcher("./fb/events/display_events.jsp");
                dispatcher.forward(request, response);
            } else if (request.getParameter("fbevents_ongoing") != null) {
                request.setAttribute("queryDB", "SELECT EVENT_ID, NAME, CITY, PLACE, ATTENDING_COUNT, INTERESTED_COUNT, START_DATE, START_TIME, LAST_UPDATE, URL FROM FB_EVENT_DETAILS WHERE START_DATE >= CURRENT_DATE ORDER BY START_DATE ASC, ATTENDING_COUNT DESC");
                RequestDispatcher dispatcher = request.getRequestDispatcher("./fb/events/display_events.jsp");
                dispatcher.forward(request, response);
            } else if ("Delete".equals(request.getParameter("fbevents_delete"))) {
                String[] selectedCheckboxes = request.getParameterValues("events_checkbox");
                if (selectedCheckboxes != null) {
                    String query = "DELETE FROM FB_EVENT_DETAILS WHERE EVENT_ID=?";
                    pstmnt = connection.prepareStatement(query);
                    connection.setAutoCommit(false);
                    for(String parseEvents : selectedCheckboxes){
                        pstmnt.setString(1, parseEvents);
                        pstmnt.execute();
                    }
                    connection.commit();
                    connection.setAutoCommit(true);
                    request.setAttribute("inDB", true);
                    request.setAttribute("message", "Event(s) deleted from database!");
                    RequestDispatcher dispatcher = request.getRequestDispatcher("./fb/events/index.jsp");
                    dispatcher.forward(request, response);
                } else {
                    request.setAttribute("inDB", true);
                    request.setAttribute("message", "No event selected!");
                    RequestDispatcher dispatcher = request.getRequestDispatcher("./fb/events/index.jsp");
                    dispatcher.forward(request, response);
                }
            } else if (request.getParameter("event_details") != null) {
                request.setAttribute("eventID", request.getParameter("event_details"));
                RequestDispatcher dispatcher = request.getRequestDispatcher("./fb/events/details.jsp");
                dispatcher.forward(request, response);
             } else if (request.getParameter("fbevents_update") != null) {
                 String[] selectedCheckboxes = request.getParameterValues("events_checkbox");
                if (selectedCheckboxes != null) {
                    for(String parseEvents : selectedCheckboxes){
                        String fbId = "EAATyQwZCvEZB4BAOZA3ueFxZA6iIaHJ5ZClj6IqZBvYWj58IpZCVEMKIvO3wSdqdRbBh5RhwA4ZBT0IaJ4yM9piR0wODOBYi7jZCtopQ2gJ6q10jnoGepuPj42aJ5mZBZC68JaO2AzQL7rKj6LyYHgpBmlSQgNKB8ZCJo1T0hND8UkMIHgZDZD";
                        FacebookClient fbClient = new DefaultFacebookClient(fbId, Version.VERSION_2_11);
                        Event eventSearch = fbClient.fetchObject(parseEvents, Event.class);
                        Event eventSearchWithParam = fbClient.fetchObject(parseEvents, Event.class, Parameter.with("fields", "attending_count,interested_count"));
                        String searchCriteria = parseEvents+"/admins";
                        com.restfb.Connection<Event> eventAdmins = fbClient.fetchConnection(searchCriteria, Event.class);
                        LinkedList<String> eventDetails = new LinkedList<>();
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

                        LinkedList<Date> eventDate = new LinkedList<>();
                        LinkedList<Time> eventTime = new LinkedList<>();    
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
                        pstmnt = connection.prepareStatement(query);

                        pstmnt.setString(1, eventDetails.get(0));
                        pstmnt.setString(2, eventDetails.get(1));
                        pstmnt.setString(3, eventDetails.get(2));
                        pstmnt.setString(4, eventDetails.get(3));
                        pstmnt.setInt(5, eventUsersAttending);
                        pstmnt.setLong(6, eventUsersInterested);
                        pstmnt.setDouble(7, eventCoordinates.get(0));
                        pstmnt.setDouble(8, eventCoordinates.get(1));
                        pstmnt.setDate(9, eventDate.get(0));
                        pstmnt.setTime(10, eventTime.get(0));
                        pstmnt.setDate(11, eventDate.get(1));
                        pstmnt.setTime(12, eventTime.get(1));
                        
                        java.util.Date nowDateJave = new java.util.Date();
                        pstmnt.setTimestamp(13, new java.sql.Timestamp(nowDateJave.getTime()));
                        
                        pstmnt.setString(14, eventDetails.get(4));
                        pstmnt.executeUpdate();
                    }
                    connection.commit();
                    connection.setAutoCommit(true);
                    request.setAttribute("inDB", true);
                    request.setAttribute("message", "Event(s) updated");
                    RequestDispatcher dispatcher = request.getRequestDispatcher("./fb/events/index.jsp");
                    dispatcher.forward(request, response);
                } else {
                    request.setAttribute("inDB", true);
                    request.setAttribute("message", "No event selected!");
                    RequestDispatcher dispatcher = request.getRequestDispatcher("./fb/events/index.jsp");
                    dispatcher.forward(request, response);
                }
            }
        } catch(SQLException | ClassNotFoundException ex){
            Logger.getLogger(EventsMain.class.getName()).log(Level.SEVERE, null, ex);           
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(EventsMain.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("inDB", true);
            request.setAttribute("message", "Unsupported request. Object ID does not exist, cannot be loaded due to missing permissions or does not support this operation.<br/> Please try again using correct parameters!");
            RequestDispatcher dispatcher = request.getRequestDispatcher("./fb/events/index.jsp");
            dispatcher.forward(request, response);
        } catch (FacebookGraphException ex){
            Logger.getLogger(EventsMain.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("inDB", true);
            request.setAttribute("message", "Unsupported request. Object with ID '"+request.getParameter("fbevents_id")+"' does not exist, cannot be loaded due to missing permissions or does not support this operation.<br/> Please try again using correct parameters!");
            RequestDispatcher dispatcher = request.getRequestDispatcher("./fb/events/index.jsp");
            dispatcher.forward(request, response);
        }finally{
            if (resultSet != null){
                try{
                    resultSet.close();
                } catch (SQLException ex){
                    Logger.getLogger(EventsMain.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (statement != null){
                try{
                    statement.close();
                } catch (SQLException ex){
                    Logger.getLogger(EventsMain.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (pstmnt != null){
                try{
                    pstmnt.close();
                } catch (SQLException ex){
                    Logger.getLogger(EventsMain.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (connection != null){
                try{
                    connection.close();
                } catch(SQLException ex){
                    Logger.getLogger(EventsMain.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(EventsMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
