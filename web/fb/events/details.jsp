<%-- 
    Document   : details
    Created on : Feb 21, 2018, 3:07:40 PM
    Author     : George
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="/anost/css/button.css">
        <title>anost - fb.. events - Display details</title>
    </head>
    <body>
        <sql:setDataSource
            var="myDB"
            driver="org.apache.derby.jdbc.ClientDriver"
            user="anost"
            password="anost"
            url="jdbc:derby://localhost:1527/anost_db;create=true"/>
        <sql:query dataSource="${myDB}" var="event">
            SELECT * FROM FB_EVENT_DETAILS WHERE EVENT_ID='<c:out value="${eventID}"/>'
        </sql:query>
        <sql:query dataSource="${myDB}" var="admins">
            SELECT FB_USER_IDENTITY.USER_DISPLAY_NAME FROM FB_USER_IDENTITY JOIN FB_EVENT_ADMINS ON FB_EVENT_ADMINS.USER_ID=FB_USER_IDENTITY.USER_ID WHERE FB_EVENT_ADMINS.EVENT_ID ='<c:out value="${eventID}"/>'
        </sql:query>
        <div class="gradient_menu">
            <%@include file="/WEB-INF/jspf/menu.jspf" %>
            <form action="${pageContext.request.contextPath}/EventsMain" method="GET">
                <table class="tablecenteredwithroundborder" id="noborder">
                    <tr><td><a href="/anost/fb/events/add_new.jsp"><input type="button" class="button" value="Add new event"></a></td>
                        <td><input type="submit" class="button" name="fbevents_ongoing" value="Display ongoing events"></td>
                        <td><input type="submit" class="button" name="fbevents_all" value="Display events"></td>
                        <td><input type="submit" class="button" name="fbevents_update" value="Update Event"></td>
                </table>
                <br/>
            </form>
            <c:forEach var="row" items="${event.rows}">
            <table class="tableforlistings" width="95%">
                <tr><td width="60%" style="border-style: none">
                        <table class="intabledetails">
                            <tr><td style="width: 30%"><b>Facebook event ID</b></td><td><a href="${row.URL}" target="_blank" title="Event Facebook Page"><c:out value="${row.EVENT_ID}"/></a></td></tr>
                            <tr><td></td></tr>
                            <tr><td><b>Event name</b></td><td><c:out value="${row.NAME}"/></td></tr>
                            <tr><td></td></tr>
                            <tr><td><b>City</b></td><td><c:out value="${row.CITY}"/></td></tr>
                            <tr><td></td></tr>
                            <tr><td><b>Place</b></td><td><c:out value="${row.PLACE}"/></td></tr>
                            <tr><td></td></tr>
                            <tr><td><b>Country</b></td><td><c:out value="${row.COUNTRY}"/></td></tr>
                            <tr><td></td></tr>
                            <tr><td colspan="2">
                            <table class="intabledetails">
                                <tr><td><b>latitude</b></td><td><b>longitude</b></td></tr>
                                <tr><td><c:out value="${row.LATITUDE}"/></td><td><c:out value="${row.LONGITUDE}"/></td></tr>
                            </table >
                                </td></tr>
                            <tr><td style="width: 40vh"><b>People attending</b></td><td style="width: 40vh"><c:out value="${row.ATTENDING_COUNT}"/></td></tr>
                            <tr><td></td></tr>
                            <tr><td><b>People interested</b></td><td><c:out value="${row.INTERESTED_COUNT}"/></td></tr>
                            <tr><td></td></tr>
                            <tr><td><b>Start date</b></td><td><fmt:formatDate pattern = "dd.MM.yyyy" value="${row.START_DATE}"/> <fmt:formatDate pattern = "HH:mm" value="${row.START_TIME}"/></td></tr>
                            <tr><td></td></tr>
                            <tr><td><b>End date</b></td><td><fmt:formatDate pattern = "dd.MM.yyyy" value="${row.END_DATE}"/> <fmt:formatDate pattern = "HH:mm" value="${row.END_TIME}"/></td></tr>
                            <tr><td></td></tr>
                            <tr><td><b>Last details update</b></td><td><fmt:formatDate pattern = "dd.MM.yyyy HH:mm" value="${row.LAST_UPDATE}"/></td></tr>
                            <tr><td></td></tr>
                            <tr><td style="vertical-align: top"><b>Event created by</b></td>
                                <td>
                                    <table width="100%">
                                        <c:forEach var="adminrow" items="${admins.rows}">
                                            <tr><td style="text-align: center"><c:out value="${adminrow.USER_DISPLAY_NAME}"/></td></tr>
                                        </c:forEach>
                                    </table>
                                </td></tr>
                        </table>
                    </td>
                    <td style="text-align: center; border-style: none;" width="40%"><b>EVENT DESCRIPTION</b><br/>
                        <textarea rows="30" style="width: 98%; background: inherit; resize: none" readonly><c:out value="${row.DESCRIPTION}"/></textarea>
                    </td></tr>
            </table>
            </c:forEach>
        </div>
    <%@include file="/WEB-INF/jspf/footer.jspf" %>
    </body>
</html>
