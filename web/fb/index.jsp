<%-- 
    Document   : index_
    Created on : Mar 3, 2018, 1:45:21 PM
    Author     : George <mrgeorge.ro @ gmail.com>
--%>

<%@page import="ro.anost.utils.fb.FbAppCredentials"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="/WEB-INF/jspf/header.jspf" %>
    </head>
    <body>
        <div class="mainscreen">
            <%@include file="/WEB-INF/jspf/menu.jspf" %>
            <br/>
            <c:if test="${fbcurrentuser == null || fbcurrentuser == ''}">
                <div class="customSocialBtn">
                    <p style="font-size: 17px">Please click the button bellow to login using your Facebook account</p>
                    <%
                        FbAppCredentials fbAppCredentials = new FbAppCredentials();
                        request.setAttribute("fbauthurl", fbAppCredentials.getLoginDialog());
                        %>
                    <a href="${fbauthurl}"><button id="facebookbutton">Sign in with Facebook</button></a><br/>
                </div>
            </c:if>
        </div>
        <%@include file="/WEB-INF/jspf/footer.jspf" %>
    </body>
</html>