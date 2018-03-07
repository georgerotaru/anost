<%-- 
    Document   : gsearch
    Created on : Mar 7, 2018, 7:15:49 PM
    Author     : George
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
            <form action="${pageContext.request.contextPath}/FbGsearch" method="GET">
                <%--page options--%>
                <table class="noboredcentertable">
                    <tr>
                        <td><input type="submit" name="gsearcgpg_input" class="submenu" value="People ~ Pages"></td>
                        <td><input type="submit" class="submenu" name="fbevents_ongoing" value="Photos"></td>
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
            <br/>
            <c:choose>
                <c:when test="${gsearcgpg == 'People ~ Pages'}">
                    <form action="${pageContext.request.contextPath}/FbGsearch" method="GET" target="_blank">
                        <table class="tableforfbsearch" style="border-style: solid">
                            <tr>
                                <td>
                                    <dl>
                                        <dt><b>People named <i style="color: #990000">name</i></b></dt>
                                        <dd>https://www.facebook.com/search/str/<input type="text" name="gsearchpp" style="width: 150px; text-align: center; color: #990000" placeholder="name">/users-named<a target="_blank"></dd>
                                    </dl>                
                                </td>
                                <td><input type="submit" name="gsearchpg_go" value="GO"></td>
                            </tr>
                            <tr>
                                <td>
                                    <dl>
                                        <dt><b>Users named <i style="color: #990000">name</i></b></dt>
                                        <dd>https://www.facebook.com/search/str/<input type="text" name="gsearchpp" style="width: 150px; text-align: center; color: #990000" placeholder="name">/discovery-users<a target="_blank"></dd>
                                    </dl>                
                                </td>
                                <td><input type="submit" name="gsearchpg_go" value="GO"></td>
                            </tr>
                            <tr>
                                <td>
                                    <c:set var="test" value=""/>
                                </td>
                            </tr>
                        </table>
                    </form>
                </c:when>
            </c:choose>
    </body>
</html>
