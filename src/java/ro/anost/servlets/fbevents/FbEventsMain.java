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

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ro.anost.utils.fb.AddEventDetails;
import ro.anost.utils.fb.COpReport;
import ro.anost.utils.fb.FbAppCredentials;
import ro.anost.utils.fb.UpdateEventDetails;
import ro.anost.utils.fb.UserDetails;

/**
 *
 */
public class FbEventsMain extends HttpServlet {

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
        //Connection connection = null;
        //Statement statement = null;
        //ResultSet resultSet = null;
        //PreparedStatement ppstm = null;
        
        String currentFbUser = (String) request.getSession().getAttribute("fbcurrentuser");
        String accTkn = (String) request.getSession().getAttribute("fbcurrenttk");
        
        if (request.getParameter("fbevents_add") != null){
            if (currentFbUser == null || "".equals(currentFbUser)){
                FbAppCredentials fbAppCredentials = new FbAppCredentials();
                request.setAttribute("fbauthurl", fbAppCredentials.getLoginDialog());
                request.setAttribute("login", false);
                //request.setAttribute("fbevents_add", null);
                RequestDispatcher dispatcher = request.getRequestDispatcher("./fb/events/index.jsp");
                dispatcher.forward(request, response);
            } else {
               response.sendRedirect("./fb/events/add_new.jsp");
            }
        }else if (request.getParameter("fbeventsadd_go") != null){
            String fbEventId = request.getParameter("fbeventadd_id");
            if (fbEventId == null || "".equals(fbEventId)) {
                System.out.println("User "+currentFbUser+" tried to implement an event with no ID!");
                request.setAttribute("message", "No event ID was introduced. Please try again!");
                RequestDispatcher dispatcher = request.getRequestDispatcher("./fb/events/add_new.jsp");
                dispatcher.forward(request, response);
            } else {
                AddEventDetails addEvent = new AddEventDetails(fbEventId, accTkn);
                request.setAttribute("message", addEvent.getMessage());
                RequestDispatcher dispatcher = request.getRequestDispatcher("./fb/events/add_new.jsp");
                dispatcher.forward(request, response);
            }
        }else if (request.getParameter("fbevents_all") != null) {
            request.setAttribute("queryDB", "SELECT EVENT_ID, NAME, CITY, PLACE, ATTENDING_COUNT, INTERESTED_COUNT, START_DATE, START_TIME, END_DATE, END_TIME, LAST_UPDATE, URL FROM FB_EVENT_DETAILS ORDER BY START_DATE DESC");
            RequestDispatcher dispatcher = request.getRequestDispatcher("./fb/events/display_events.jsp");
            dispatcher.forward(request, response);
        }else if (request.getParameter("fbevents_ongoing") != null) {
            request.setAttribute("queryDB", "SELECT EVENT_ID, NAME, CITY, PLACE, ATTENDING_COUNT, INTERESTED_COUNT, START_DATE, START_TIME, END_DATE, END_TIME, LAST_UPDATE, URL FROM FB_EVENT_DETAILS WHERE (START_DATE >= CURRENT_DATE) or (END_DATE >= CURRENT_DATE) ORDER BY START_DATE ASC, ATTENDING_COUNT DESC");
            RequestDispatcher dispatcher = request.getRequestDispatcher("./fb/events/display_events.jsp");
            dispatcher.forward(request, response);
        }else if (request.getParameter("event_details") != null) {
            request.setAttribute("eventID", request.getParameter("event_details"));
            RequestDispatcher dispatcher = request.getRequestDispatcher("./fb/events/details.jsp");
            dispatcher.forward(request, response);
        }else if (request.getParameter("detailspg_admin") != null) {
            String eventAdminId = request.getParameter("detailspg_admin");
            /*UserDetails user = new UserDetails(eventAdminId, accTkn);
            request.setAttribute("adminPictureUrl", user.getPictureUrl());
            request.setAttribute("message", user.getMessage());*/
            request.setAttribute("adminId", eventAdminId);
            RequestDispatcher dispatcher = request.getRequestDispatcher("./fb/users/event_admins.jsp");
            dispatcher.forward(request, response);
        }else if (request.getParameter("fbevents_copreport") != null) {
            String[] selectedCheckboxes = request.getParameterValues("events_checkbox");
            String copOption = request.getParameter("COpReport");
            if ("".equals(copOption) || selectedCheckboxes == null) {
                request.setAttribute("message", "Wrong selection! Please be sure to check an event and select YES or NO option!");
                RequestDispatcher dispatcher = request.getRequestDispatcher("./fb/events/index.jsp");
                dispatcher.forward(request, response);
            } else {
                COpReport reportEvent = new COpReport(selectedCheckboxes, copOption);
                request.setAttribute("message", reportEvent.getMessage());
                RequestDispatcher dispatcher = request.getRequestDispatcher("./fb/events/index.jsp");
                dispatcher.forward(request, response);
            }
                /*String query = "UPDATE FB_EVENT_REPORTSTATUS SET REPORT_STATUS=?, LAST_UPDATE=? WHERE EVENT_ID=?";
                ppstm = connection.prepareStatement(query);
                connection.setAutoCommit(false);
                for(String parseEvents : selectedCheckboxes){
                    java.util.Date nowDateJava = new java.util.Date();
                    ppstm = connection.prepareStatement(query);
                    ppstm.setString(1, copOption);
                    ppstm.setTimestamp(2, new java.sql.Timestamp(nowDateJava.getTime()));
                    ppstm.setString(3, parseEvents);
                    ppstm.executeUpdate();
                }
                connection.commit();
                connection.setAutoCommit(true);
                request.setAttribute("inDB", true);
                request.setAttribute("message", "Event(s) marked as being "+copOption+" for COp!");
                RequestDispatcher dispatcher = request.getRequestDispatcher("./fb/events/index.jsp");
                dispatcher.forward(request, response);*/
        /*try { 
            connection = JDBCConnectionManager.getJDBCConnection();
            statement = connection.createStatement();
            FacebookClient client = new DefaultFacebookClient(Version.VERSION_2_11);
            FacebookClient fbClient = new DefaultFacebookClient(accTkn, Version.VERSION_2_11);
            if (request.getParameter("fbevents_add") != null || !"".equals(request.getParameter("fbevents_add"))) {
                String eventId = request.getParameter("fbevents_id");
                String query = "SELECT EVENT_ID, NAME FROM FB_EVENT_DETAILS WHERE EVENT_ID='"+eventId+"'";
                resultSet = statement.executeQuery(query);
                Boolean resultSetHasRows = resultSet.next();
                if (resultSetHasRows) {
                    String eventName = resultSet.getString("NAME");
                    request.setAttribute("inDB", true);
                    request.setAttribute("message", "<div style=\"color: #FF0000\">Event \""+eventName+"\" already in database!</div>");
                    RequestDispatcher dispatcher = request.getRequestDispatcher("./fb/events/add_new.jsp");
                    dispatcher.forward(request, response);
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
                        request.setAttribute("inDB", true);
                        request.setAttribute("message", "<div style=\"color: #1C0DCF\">Event \""+eventDetails.get(0)+"\" added to database!</div>");
                        RequestDispatcher dispatcher = request.getRequestDispatcher("./fb/events/add_new.jsp");
                        dispatcher.forward(request, response);
                    }catch(NullPointerException ex){
                        Logger.getLogger(FbEventsMain.class.getName()).log(Level.SEVERE, null, ex);
                        System.out.println("Problem inserting event. Most probably 'fbClient' is null - only known exception is NullPointerException.");
                        request.setAttribute("inDB", true);
                        request.setAttribute("message", "There was a problem with getting an access token from Facebook.<br/> Please re-open browser and try again.<br/> If problem persists, please contact administrator.");
                        RequestDispatcher dispatcher = request.getRequestDispatcher("./fb/events/index.jsp");
                        dispatcher.forward(request, response);
                    }
                }
            } else if (request.getParameter("fbevents_all") != null) {
                request.setAttribute("queryDB", "SELECT EVENT_ID, NAME, CITY, PLACE, ATTENDING_COUNT, INTERESTED_COUNT, START_DATE, START_TIME, END_DATE, END_TIME, LAST_UPDATE, URL FROM FB_EVENT_DETAILS ORDER BY START_DATE DESC");
                RequestDispatcher dispatcher = request.getRequestDispatcher("./fb/events/display_events.jsp");
                dispatcher.forward(request, response);
            } else if (request.getParameter("fbevents_ongoing") != null) {
                request.setAttribute("queryDB", "SELECT EVENT_ID, NAME, CITY, PLACE, ATTENDING_COUNT, INTERESTED_COUNT, START_DATE, START_TIME, END_DATE, END_TIME, LAST_UPDATE, URL FROM FB_EVENT_DETAILS WHERE (START_DATE >= CURRENT_DATE) or (END_DATE >= CURRENT_DATE) ORDER BY START_DATE ASC, ATTENDING_COUNT DESC");
                RequestDispatcher dispatcher = request.getRequestDispatcher("./fb/events/display_events.jsp");
                dispatcher.forward(request, response);
            /*} else if ("Delete".equals(request.getParameter("fbevents_delete"))) {
                String[] selectedCheckboxes = request.getParameterValues("events_checkbox");
                if (selectedCheckboxes != null) {
                    String query = "DELETE FROM FB_EVENT_DETAILS WHERE EVENT_ID=?";
                    ppstm = connection.prepareStatement(query);
                    connection.setAutoCommit(false);
                    for(String parseEvents : selectedCheckboxes){
                        ppstm.setString(1, parseEvents);
                        ppstm.execute();
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
            }else if (request.getParameter("fbevents_copreport") != null) {
                String[] selectedCheckboxes = request.getParameterValues("events_checkbox");
                String copOption = request.getParameter("COpReport");
                if (!"COp".equals(copOption) && selectedCheckboxes != null) {
                    String query = "UPDATE FB_EVENT_REPORTSTATUS SET REPORT_STATUS=?, LAST_UPDATE=? WHERE EVENT_ID=?";
                    ppstm = connection.prepareStatement(query);
                    connection.setAutoCommit(false);
                    for(String parseEvents : selectedCheckboxes){
                        java.util.Date nowDateJava = new java.util.Date();
                        ppstm = connection.prepareStatement(query);
                        ppstm.setString(1, copOption);
                        ppstm.setTimestamp(2, new java.sql.Timestamp(nowDateJava.getTime()));
                        ppstm.setString(3, parseEvents);
                        ppstm.executeUpdate();
                    }
                    connection.commit();
                    connection.setAutoCommit(true);
                    request.setAttribute("inDB", true);
                    request.setAttribute("message", "Event(s) marked as being "+copOption+" for COp!");
                    RequestDispatcher dispatcher = request.getRequestDispatcher("./fb/events/index.jsp");
                    dispatcher.forward(request, response);
                } else {
                    request.setAttribute("inDB", true);
                    request.setAttribute("message", "Wrong selection! Please be sure to check an event and select YES or NO option!");
                    RequestDispatcher dispatcher = request.getRequestDispatcher("./fb/events/index.jsp");
                    dispatcher.forward(request, response);
                }*/
        } else if (request.getParameter("fbevents_update") != null) {
            String[] selectedCheckboxes = request.getParameterValues("events_checkbox");
            if (selectedCheckboxes == null){
                request.setAttribute("message", "Wrong selection! Please be sure to check one ore more events to update!");
                RequestDispatcher dispatcher = request.getRequestDispatcher("./fb/events/index.jsp");
                dispatcher.forward(request, response);      
            } else {
                UpdateEventDetails updateEvent = new UpdateEventDetails(selectedCheckboxes, accTkn);
                request.setAttribute("message", updateEvent.getMessage());
                RequestDispatcher dispatcher = request.getRequestDispatcher("./fb/events/index.jsp");
                dispatcher.forward(request, response);  
            }
        }
        
             /*} else if (request.getParameter("fbevents_update") != null) {
                String[] selectedCheckboxes = request.getParameterValues("events_checkbox");
                if (selectedCheckboxes != null) {
                    for(String parseEvents : selectedCheckboxes){
                        try{
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
                        }catch(NullPointerException ex){
                            Logger.getLogger(FbEventsMain.class.getName()).log(Level.SEVERE, null, ex);
                            System.out.println("Problem updating event. Most probably 'fbClient' is null - only known exception is NullPointerException.");
                            request.setAttribute("inDB", true);
                            request.setAttribute("message", "There was a problem with getting an access token from Facebook.<br/> Please re-open browser and try again.<br/> If problem persists, please contact administrator.");
                            RequestDispatcher dispatcher = request.getRequestDispatcher("./fb/events/index.jsp");
                            dispatcher.forward(request, response);
                        }
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
            }else if (request.getParameter("detailspg_admin") != null) {
            String eventAdminId = request.getParameter("detailspg_admin");
            request.setAttribute("adminId", eventAdminId);
            RequestDispatcher dispatcher = request.getRequestDispatcher("./fb/users/event_admins.jsp");
            dispatcher.forward(request, response);
            }
        } catch(SQLException ex){
            Logger.getLogger(FbEventsMain.class.getName()).log(Level.SEVERE, null, ex);           
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(FbEventsMain.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("inDB", true);
            request.setAttribute("message", "Unsupported request. Object ID does not exist, cannot be loaded due to missing permissions or does not support this operation.<br/> Please try again using correct parameters!");
            RequestDispatcher dispatcher = request.getRequestDispatcher("./fb/events/index.jsp");
            dispatcher.forward(request, response);
        } catch (FacebookGraphException ex){
            Logger.getLogger(FbEventsMain.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("inDB", true);
            request.setAttribute("message", "Unsupported request. Object with ID '"+request.getParameter("fbevents_id")+"' does not exist, cannot be loaded due to missing permissions or does not support this operation.<br/> Please try again using correct parameters!");
            RequestDispatcher dispatcher = request.getRequestDispatcher("./fb/events/index.jsp");
            dispatcher.forward(request, response);
        }finally{
            JDBCConnectionManager.closeJDBCConnection();
        }*/
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
            Logger.getLogger(FbEventsMain.class.getName()).log(Level.SEVERE, null, ex);
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