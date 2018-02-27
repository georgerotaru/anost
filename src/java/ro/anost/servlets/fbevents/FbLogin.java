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

import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.FacebookClient.AccessToken;
import com.restfb.Parameter;
import com.restfb.Version;
import com.restfb.scope.FacebookPermissions;
import com.restfb.scope.ScopeBuilder;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author George
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

        ScopeBuilder scopeBuilder = new ScopeBuilder();
        scopeBuilder.addPermission(FacebookPermissions.EMAIL);
        
        FacebookClient client = new DefaultFacebookClient(Version.VERSION_2_11);
        String appId = "1392269750899694";
        String appSecret = "10c7f2a99320ad4bb60ad879feab9622";
        String redirectUrl = "http://localhost:8080/anost/";
        Parameter additionalParameters = Parameter.with("state", "");
        //https://www.facebook.com/dialog/oauth?client_id=1392269750899694&redirect_uri=http://localhost:8080/anost&state=
        String loginDialogUrlString = client.getLoginDialogUrl(appId, redirectUrl, scopeBuilder, additionalParameters);

        
//String fbsigninUrl = "https://www.facebook.com/dialog/oauth?client_id=1392269750899694&redirect_uri=http://localhost:8080/anost/fb/events&scope=email&state=";
//String fbIdConfirmUrl = "https://graph.facebook.com/v2.11/oauth/access_token?client_id=1392269750899694&redirect_uri=http://localhost:8080/anost/fb/events&client_secret=10c7f2a99320ad4bb60ad879feab9622&code=AQCTv7CDpz_jbVOVRTUnlXtusLZLnFvFsWf408mREvWSXt3SAAkvklbAtZqSEB4PFbH4-HlJCH2LROCpEtvgZQ7WOCUWEZ5Eq7So9aN6NdSLOEvCARYWitoNSf4TzB6a7_KSKiBdwI1ubCfkpgmcH8VklddlvWW6Oo9o6eYauBoK2iSNuoVFj2FITuhfv7K-reAqz5qN_3d_3TkfWiK65UhZHc5kSfXE2zY2wFUdEeL7QXLYUPDpvorlrZQ7Ec_0VESAn-QBYkSSdoYhcYiywcvK2_Q_h5-xnFmMDZf02hUxO8O4GngcnCuqBONR0AyQUS0b0AKNHG1gSEQ9sRZQkdYM#_=_";
        
        //AccessToken accessToken = client.obtainUserAccessToken(appId, appSecret, redirectUrl, verifCode);
        //String accTkn = accessToken.getAccessToken();
        //request.getSession().setAttribute("tok", accTkn);
        
        //https://github.com/subhakant0/Facebook-login-restfb/tree/master/src/com/demo/facebook
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("./util/login.jsp");
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
        processRequest(request, response);
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
