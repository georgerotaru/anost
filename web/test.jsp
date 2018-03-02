<%-- 
    Document   : test
    Created on : Mar 2, 2018, 10:43:50 AM
    Author     : admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Facebook events list</h1>
        <table border="1" cellpadding="5" cellspacing="1">
            <tr>
                <th>ID</th>
                <th>NAME</th>
                <th>CITY</th>
                <th>PLACE</th>
                <th>ATTENDING no</th>
                <th>INTERESTED no</th>
                <th>START DATE</th>
                <th>START TIME</th>
                <th>END DATE</th>
                <th>END TIME</th>
                <th>URL</th>
                <th>LAST UPDATE</th>
            </tr>
            <c:forEach items="${fbEventsList}" var="event">
                <tr>
                    <td>${event.eventId}</td>
                    <td>${event.eventName}</td>
                    <td>${event.eventCity}</td>
                    <td>${event.eventPlace}</td>
                    <td>${event.eventAttendingCount}</td>
                    <td>${event.eventInterestedCount}</td>
                    <td>${event.eventStartDate}</td>
                    <td>${event.eventStartTime}</td>
                    <td>${event.eventEndDate}</td>
                    <td>${event.eventEndTime}</td>
                    <td>URL</td>
                    <td>${event.eventTimestamp}</td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
