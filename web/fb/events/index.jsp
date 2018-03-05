<%-- 
    Document   : index_
    Created on : Mar 3, 2018, 1:45:21 PM
    Author     : George <mrgeorge.ro @ gmail.com>
--%>

<%@page import="utils.fb.FbAppCredentials"%>
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
            <form action="${pageContext.request.contextPath}/FbEventsMain" method="GET">
                <%--page options--%>
                <table class="noboredcentertable">
                    <tr>
                        <td><a href="/anost/fb/events/add_new.jsp"><input type="button" class="submenu" value="Add new event"></a></td>
                        <td><input type="submit" class="submenu" name="fbevents_ongoing" value="Display ongoing events"></td>
                        <td><input type="submit" class="submenu" name="fbevents_all" value="Display events"></td>
                    </tr>
                </table>
            </form>
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
            <c:if test="${inDB == true}">
                <br/>
                <div style="margin: auto; text-align: center; color: #ff0000; width: 65%">
                    ${message}
                </div>
                <br/>
            </c:if>
        </div>
        <%@include file="/WEB-INF/jspf/footer.jspf" %>
    </body>
</html>