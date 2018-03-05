<%-- 
    Document   : about
    Created on : Mar 4, 2018, 8:30:42 PM
    Author     : George <mrgeorge.ro @ gmail.com>
--%>

<%@page import="java.util.Date"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="/WEB-INF/jspf/header.jspf" %>
    </head>
    <body>
        <div class="mainscreen">
            <%@include file="/WEB-INF/jspf/menu.jspf" %>
            <br/>
            <table class="abouttable">
                <tr><td>FB user (if logged in)</td><td>${fbcurrentuser}</td></tr>
                <tr><td>FB API user id</td><td>${fbcurrentuserid}</td></tr>
                <tr><td>FB token expire date</td><td><fmt:formatDate value="${fbcurrenttkexp}" pattern="dd.MM.yyyy HH:mm:ss"/></td></tr>
                <tr><td>client IP address</td><td>${pageContext.request.remoteAddr}</td></tr>
                <tr><td>client session creation time</td><td><%= new Date(session.getCreationTime()) %></td></tr>
            </table>
        </div>
        <%@include file="/WEB-INF/jspf/footer.jspf" %>
    </body>
</html>
