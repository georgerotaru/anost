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

import com.restfb.FacebookClient;
import com.restfb.exception.FacebookOAuthException;
import com.restfb.types.User;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import utils.fb.AccTkn;
import utils.fb.CreateFbClient;
import utils.fb.FbAppCredentials;

/**
 * This servlet processes Facebook login redirect and creates a FacebookClient object
 * and a User object.
 * It stores it in session variables along with user FB name and ID.
 */
public class FbLogin extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        RequestDispatcher dispatcher = request.getRequestDispatcher("/fb/events/index.jsp");
        try{
            String verifCode = request.getParameter("code");
            try{
                AccTkn accTkn = new AccTkn();
                accTkn.setFbAccessToken(verifCode);                
            }catch(NullPointerException ex){
                System.out.println("Problem getting an access token from Facebook - only known exception is NullPointerException.");
                Logger.getLogger(FbLogin.class.getName()).log(Level.SEVERE, null, ex);
            }
        }catch(NullPointerException ex){
            System.out.println("Never received Facebook parameter 'code' - only known exception is NullPointerException.");
            Logger.getLogger(FbLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
        try{
            FacebookClient fbClient = CreateFbClient.getFbClient();  
            //request.getSession().setAttribute("fbcurrentclient", fbClient);
            try{
                User user = fbClient.fetchObject("me", User.class);
                String currentFbUser = user.getName();
                request.getSession().setAttribute("fbcurrentuser", currentFbUser);
                String userId = user.getId();
                request.getSession().setAttribute("fbcurrentuserid", userId);
                System.out.println("Logged in FB user "+currentFbUser+"(id "+userId+") from IP "+request.getRemoteAddr()+" at "+LocalDateTime.now());
            }catch(NullPointerException ex){
                System.out.println("Could not create an User object. Investigate logs further.");
                Logger.getLogger(FbLogin.class.getName()).log(Level.SEVERE, null, ex);
            }catch(FacebookOAuthException ex){
                
            }
        }catch(NullPointerException ex){
            System.out.println("Could not aquire 'fbClient'. Investigate logs further - only known exception is NullPointerException.");
            Logger.getLogger(FbLogin.class.getName()).log(Level.SEVERE, null, ex);
            dispatcher.forward(request, response);
        }
        
        request.getSession().setAttribute("fbcurrenttkexp", AccTkn.getTknExpDate());
        request.getSession().setAttribute("fbcurrenttk", AccTkn.getFbAccessToken());
        
        AccTkn.resetCredentials();
        
        dispatcher.forward(request, response);
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
        processRequest(request, response);
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
        return "Handles redirect from Facebook user login page and creates a FacebookClient object and a User object.";
    }// </editor-fold>

}
