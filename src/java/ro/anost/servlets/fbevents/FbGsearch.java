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

package ro.anost.servlets.fbevents;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 */
public class FbGsearch extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url;
        String gsearchPgGoButton = request.getParameter("gsearchpg_go");

        if (gsearchPgGoButton != null || !"".equals(gsearchPgGoButton)){
            switch(gsearchPgGoButton){
                case "1": url = "https://www.facebook.com/search/str/"+request.getParameter("fieldtext1")+"/users-named";
                        response.sendRedirect(url);
                        break;
                case "2": url = "https://www.facebook.com/search/str/"+request.getParameter("fieldtext2.1")+"/users-named/"+request.getParameter("fieldtext2.2")+"/home-residents/intersect";
                        response.sendRedirect(url);
                        break;
                case "3": url = "https://www.facebook.com/search/str/"+request.getParameter("fieldtext3.1")+"/users-named/"+request.getParameter("fieldtext3.2")+"/residents/intersect";
                        response.sendRedirect(url);
                        break;
                case "4": url = "https://www.facebook.com/search/"+request.getParameter("fieldtext4.1")+"/home-residents/"+request.getParameter("fieldtext4.2")+"/residents/intersect";
                        response.sendRedirect(url);
                        break;
                case "5": url = "https://www.facebook.com/search/"+request.getParameter("fieldtext5")+"/pages-liked";
                        response.sendRedirect(url);
                        break;
                case "6": url = "https://www.facebook.com/search/"+request.getParameter("fieldtext6")+"/friends/pages-liked";
                        response.sendRedirect(url);
                        break;
                case "7": url = "https://www.facebook.com/search/"+request.getParameter("fieldtext7.1")+"/pages-liked/"+request.getParameter("fieldtext7.2")+"/pages-liked/intersect";
                        response.sendRedirect(url);
                        break;
                case "8": url = "https://www.facebook.com/search/str/"+request.getParameter("fieldtext8")+"/pages-named";
                        response.sendRedirect(url);
                        break;
                case "photos1": url = "https://www.facebook.com/search/"+request.getParameter("photos_field1")+"/photos-of";
                        response.sendRedirect(url);
                        break;
                case "photos2": url = "https://www.facebook.com/search/"+request.getParameter("photos_field2")+"/photos-commented";
                        response.sendRedirect(url);
                        break;
                case "photos3": url = "https://www.facebook.com/search/"+request.getParameter("photos_field3")+"/photos-liked";
                        response.sendRedirect(url);
                        break;
                case "photos4": url = "https://www.facebook.com/search/"+request.getParameter("photos_field4")+"/photos-tagged";
                        response.sendRedirect(url);
                        break;
                case "photos5": url = "https://www.facebook.com/search/"+request.getParameter("photos_field5")+"/photos-of/users-tagged";
                        response.sendRedirect(url);
                        break;
                case "photos6": url = "https://www.facebook.com/search/"+request.getParameter("photos_field6.1")+"/photos-commented/"+request.getParameter("photos_field6.2")+"/photos-by/intersect";
                        response.sendRedirect(url);
                        break;
                case "photos7": url = "https://www.facebook.com/search/"+request.getParameter("photos_field7.1")+"/photos-liked/"+request.getParameter("photos_field7.2")+"/photos-by/intersect";
                        response.sendRedirect(url);
                        break;
                case "photos8": url = "https://www.facebook.com/search/"+request.getParameter("photos_field8.1")+"/photos-commented/"+request.getParameter("photos_field8.2")+"/photos-commented/intersect";
                        response.sendRedirect(url);
                        break;
                case "photos9": url = "https://www.facebook.com/search/"+request.getParameter("photos_field9.1")+"/photos-commented/"+request.getParameter("photos_field9.2")+"/photos-commented/"+request.getParameter("photos_field9.3")+"/photos-commented/intersect";
                        response.sendRedirect(url);
                        break;
                case "photos10": url = "https://www.facebook.com/search/"+request.getParameter("photos_field10.1")+"/photos-liked/"+request.getParameter("photos_field10.2")+"/photos-commented/intersect";
                        response.sendRedirect(url);
                        break;
                case "photos11": url = "https://www.facebook.com/search/"+request.getParameter("photos_field11.1")+"/photos-by/"+request.getParameter("photos_field11.2")+"/photos-liked/intersect";
                        response.sendRedirect(url);
                        break;
                case "photos12": url = "https://www.facebook.com/search/"+request.getParameter("photos_field12")+"/photos-in";
                        response.sendRedirect(url);
                        break;
                case "photos13": url = "https://www.facebook.com/search/"+request.getParameter("photos_field13.1")+"/photos-of/"+request.getParameter("photos_field13.2")+"/photos-in/intersect";
                        response.sendRedirect(url);
                        break;
                case "events1": url = "https://www.facebook.com/search/str/"+request.getParameter("events_field1")+"/events-named";
                        response.sendRedirect(url);
                        break;
                case "events2": url = "https://www.facebook.com/search/"+request.getParameter("events_field2")+"/events-at";
                        response.sendRedirect(url);
                        break;
                case "events3": url = "https://www.facebook.com/search/str/"+request.getParameter("events_field3")+"/events-named/in-future/date/events/intersect";
                        response.sendRedirect(url);
                        break;
                case "events4": url = "https://www.facebook.com/search/str/"+request.getParameter("events_field4.1")+"/events-named/in-future/date/events/"+request.getParameter("events_field4.2")+"/events-at/intersect";
                        response.sendRedirect(url);
                        break;
                case "events5": url = "https://www.facebook.com/search/today/date/events";
                        response.sendRedirect(url);
                        break;
                case "events6": url = "https://www.facebook.com/search/tomorrow/date/events";
                        response.sendRedirect(url);
                        break;
                case "events7": url = "https://www.facebook.com/search/this-weekend/date/events";
                        response.sendRedirect(url);
                        break;
                case "events8": url = "https://www.facebook.com/search/this-week/date/events";
                        response.sendRedirect(url);
                        break;
                case "events9": url = "https://www.facebook.com/search/"+request.getParameter("events_field9")+"/events-created";
                        response.sendRedirect(url);
                        break;
                case "events10": url = "https://www.facebook.com/search/"+request.getParameter("events_field10")+"/events-created/in-future/date/events/intersect";
                        response.sendRedirect(url);
                        break;
                case "events11": url = "https://www.facebook.com/search/"+request.getParameter("events_field11")+"/events";
                        response.sendRedirect(url);
                        break;
                case "events12": url = "https://www.facebook.com/search/"+request.getParameter("events_field12")+"/events-joined/in-past/date/events/intersect";
                        response.sendRedirect(url);
                        break;
                case "events13": url = "https://www.facebook.com/search/"+request.getParameter("events_field13")+"/events-joined/in-future/date/events/intersect";
                        response.sendRedirect(url);
                        break;
                case "events14": url = "https://www.facebook.com/search/"+request.getParameter("events_field14.1")+"/events/"+request.getParameter("events_field14.2")+"/events/intersect";
                        response.sendRedirect(url);
                        break;
                case "events15": url = "https://www.facebook.com/search/str/"+request.getParameter("events_field15")+"/events-named/today/date/events/intersect";
                        response.sendRedirect(url);
                        break;
                case "events16": url = "https://www.facebook.com/search/str/"+request.getParameter("events_field16.1")+"/events-named/"+request.getParameter("events_field16.2")+"/events-at/today/date/events/intersect";
                        response.sendRedirect(url);
                        break;
                case "events17": url = "https://www.facebook.com/search/str/"+request.getParameter("events_field17")+"/events-named/tomorrow/date/events/intersect";
                        response.sendRedirect(url);
                        break;
                case "events18": url = "https://www.facebook.com/search/str/"+request.getParameter("events_field18.1")+"/events-named/"+request.getParameter("events_field18.2")+"/events-at/tomorrow/date/events/intersect";
                        response.sendRedirect(url);
                        break;
                case "events19": url = "https://www.facebook.com/search/str/"+request.getParameter("events_field19")+"/events-named/this-weekend/date/events/intersect";
                        response.sendRedirect(url);
                        break;
                case "events20": url = "https://www.facebook.com/search/str/"+request.getParameter("events_field20.1")+"/events-named/"+request.getParameter("events_field20.2")+"/events-at/this-weekend/date/events/intersect";
                        response.sendRedirect(url);
                        break;
                case "events21": url = "https://www.facebook.com/search/str/"+request.getParameter("events_field21")+"/events-named/this-week/date/events/intersect";
                        response.sendRedirect(url);
                        break;
                case "events22": url = "https://www.facebook.com/search/str/"+request.getParameter("events_field22.1")+"/events-named/"+request.getParameter("events_field22.2")+"/events-at/this-week/date/events/intersect";
                        response.sendRedirect(url);
                        break;
                case "friendship1": url = "https://www.facebook.com/friendship/"+request.getParameter("friendship_field1.1")+"/"+request.getParameter("friendship_field1.2");
                        response.sendRedirect(url);
                        break;
                case "friendship2": url = "https://www.facebook.com/search/"+request.getParameter("friendship_field2.1")+"/friends/"+request.getParameter("friendship_field2.2")+"/friends/intersect";
                        response.sendRedirect(url);
                        break;
                case "friendship3": url = "https://www.facebook.com/search/"+request.getParameter("friendship_field3.1")+"/friends/"+request.getParameter("friendship_field3.2")+"/friends/"+request.getParameter("friendship_field3.3")+"/friends/intersect";
                        response.sendRedirect(url);
                        break;
                case "friendship4": url = "https://www.facebook.com/search/"+request.getParameter("friendship_field4.1")+"/friends/"+request.getParameter("friendship_field4.2")+"/friends/"+request.getParameter("friendship_field4.3")+"/friends/"+request.getParameter("friendship_field4.4")+"/friends/intersect";
                        response.sendRedirect(url);
                        break;
                case "friendship5": url = "https://www.facebook.com/browse/mutual_friends/?uid="+request.getParameter("friendship_field5.1")+"&node="+request.getParameter("friendship_field5.2");
                        response.sendRedirect(url);
                        break;
                default: ;
            }
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
