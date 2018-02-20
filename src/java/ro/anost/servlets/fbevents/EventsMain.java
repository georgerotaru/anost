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
import java.util.LinkedList;
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
                String query = "SELECT * FROM EVENT_DETAILS WHERE ID='"+eventId+"'";
                resultSet = statement.executeQuery(query);
                Boolean resultSetHasRows = resultSet.next();
                if (resultSetHasRows) {
                    String eventName = resultSet.getString("NAME");
                    request.setAttribute("inDB", true);
                    request.setAttribute("message", "Event \""+eventName+"\" already in database!");
                    RequestDispatcher dispatcher = request.getRequestDispatcher("./fb/events/add_new.jsp");
                    dispatcher.forward(request, response);
                } else {
                    String fbId = "EAATyQwZCvEZB4BAKAl08cdtV49fZBF7K3V6b4QBPBLudluFfA8wR9bMeNfg9tAvlSyU7oVQ8ZBjwz9AZBcUBG4Keid041c5TZC1SqJvxMafW2lkHB8SuWZChJ8WS4s81rzwADXmBoOSe6yZAErgThpMfrN5hV42khmfMILZADkeJL7g3mwRQupQuvHOZANRbX7dTUZD";
                    FacebookClient fbClient = new DefaultFacebookClient(fbId, Version.VERSION_2_11);
                    Event eventSearch = fbClient.fetchObject(eventId, Event.class);
                    Event eventSearchWithParam = fbClient.fetchObject(eventId, Event.class, Parameter.with("fields", "attending_count,interested_count"));
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
                    String eventUrl = "https://www.facebook.com/events/"+eventId;
                    eventDetails.add(eventUrl);
                    
                    Integer eventUsersAttending = eventSearchWithParam.getAttendingCount();
                    Long eventUsersInterested = eventSearchWithParam.getInterestedCount();
                    
                    LinkedList<Double> eventCoordinates = new LinkedList<>();
                    try {
                        eventCoordinates.add(eventSearch.getPlace().getLocation().getLatitude());
                        eventCoordinates.add(eventSearch.getPlace().getLocation().getLongitude()); 
                    } catch (NullPointerException ex) {
                        eventCoordinates.add(null);
                        eventCoordinates.add(null);
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

                    connection.setAutoCommit(false);
                    query = "INSERT INTO EVENT_DETAILS(ID, NAME, CITY, PLACE, COUNTRY, ATTENDING_COUNT, INTERESTED_COUNT, LATITUDE, LONGITUDE, START_DATE, START_TIME, END_DATE, END_TIME, URL)"
                            + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
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
                    pstmnt.setString(14, eventUrl);
                    pstmnt.executeUpdate();
                    connection.commit();
                    connection.setAutoCommit(true);
                    request.setAttribute("inDB", true);
                    request.setAttribute("message", "Event \""+eventDetails.get(0)+"\" added to database!");
                    RequestDispatcher dispatcher = request.getRequestDispatcher("./fb/events/index.jsp");
                    dispatcher.forward(request, response);
                }
            } else if (request.getParameter("fbevents_all") != null) {
                //RequestDispatcher dispatcher = request.getRequestDispatcher("./fb/events/display_events.jsp");
                response.sendRedirect("./fb/events/display_events.jsp");
            }
        } catch(SQLException | ClassNotFoundException ex){
            Logger.getLogger(EventsMain.class.getName()).log(Level.SEVERE, null, ex);           
        } finally{
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
            if (connection != null){
                try{
                    connection.close();
                } catch(SQLException ex){
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
