<%-- 
    Document   : details_
    Created on : Mar 3, 2018, 2:09:16 PM
    Author     : George <mrgeorge.ro @ gmail.com>
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="/WEB-INF/jspf/header.jspf" %>
    </head>
    <body>
        <%--set db credentials--%>
        <sql:setDataSource
            var="myDB"
            driver="org.apache.derby.jdbc.ClientDriver"
            user="anost"
            password="anost"
            url="jdbc:derby://localhost:1527/anost_db;create=true"/>
        <%--set db request for event details--%>
        <sql:query dataSource="${myDB}" var="event">
            SELECT * FROM FB_EVENT_DETAILS WHERE EVENT_ID='<c:out value="${eventID}"/>'
        </sql:query>
        <%--set db request for admin details--%>
        <sql:query dataSource="${myDB}" var="admins">
            SELECT FB_USER_IDENTITY.USER_DISPLAY_NAME FROM FB_USER_IDENTITY JOIN FB_EVENT_ADMINS ON FB_EVENT_ADMINS.USER_ID=FB_USER_IDENTITY.USER_ID WHERE FB_EVENT_ADMINS.EVENT_ID ='<c:out value="${eventID}"/>'
        </sql:query>
        
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
                
            <%--display event details from db--%>
            <c:forEach var="row" items="${event.rows}">
            <table class="noboredcentertable" width="95%">
                <tr><td width="60%" style="border-style: none">
                        <table class="intabledetails">
                            <tr><td style="width: 30%"><b><label>Facebook event ID</label></b></td><td><a href="${row.URL}" target="_blank" title="Event Facebook Page"><c:out value="${row.EVENT_ID}"/></a></td></tr>
                            <tr><td></td></tr>
                            <tr><td><b><label>Event name</label></b></td><td style="color: #ff0000"><c:out value="${row.NAME}"/></td></tr>
                            <tr><td></td></tr>
                            <tr><td><b><label>City</label></b></td><td><c:out value="${row.CITY}"/></td></tr>
                            <tr><td></td></tr>
                            <tr><td><b><label>Place</label></b></td><td><c:out value="${row.PLACE}"/></td></tr>
                            <tr><td></td></tr>
                            <tr><td><b><label>Country</label></b></td><td><c:out value="${row.COUNTRY}"/></td></tr>
                            <tr><td></td></tr>
                            <tr><td colspan="2">
                            <table class="intabledetails">
                                <tr><td style="padding-right: 10px"><b><label>latitude</label></b></td><td style="padding-left: 10px"><b><label>longitude</label></b></td></tr>
                                <tr><td style="padding-right: 10px"><c:out value="${row.LATITUDE}"/></td><td style="padding-left: 10px"><c:out value="${row.LONGITUDE}"/></td></tr>
                            </table >
                                </td></tr>
                            <tr><td style="width: 40vh"><b><label>People attending</label></b></td><td style="width: 40vh"><c:out value="${row.ATTENDING_COUNT}"/></td></tr>
                            <tr><td></td></tr>
                            <tr><td><b><label>People interested</label></b></td><td><c:out value="${row.INTERESTED_COUNT}"/></td></tr>
                            <tr><td></td></tr>
                            <tr><td><b><label>Start date</label></b></td><td><fmt:formatDate pattern = "dd.MM.yyyy" value="${row.START_DATE}"/> <fmt:formatDate pattern = "HH:mm" value="${row.START_TIME}"/></td></tr>
                            <tr><td></td></tr>
                            <tr><td><b><label>End date</label></b></td><td><fmt:formatDate pattern = "dd.MM.yyyy" value="${row.END_DATE}"/> <fmt:formatDate pattern = "HH:mm" value="${row.END_TIME}"/></td></tr>
                            <tr><td></td></tr>
                            <tr><td><b><label>Last details update</label></b></td><td><fmt:formatDate pattern = "dd.MM.yyyy HH:mm" value="${row.LAST_UPDATE}"/></td></tr>
                            <tr><td></td></tr>
                            <tr><td style="vertical-align: top"><b><label>Event created by</label></b></td>
                                <%--display event admins from db--%>
                                <td>
                                    <table width="100%">
                                        <c:forEach var="adminrow" items="${admins.rows}">
                                            <tr><td style="text-align: center"><c:out value="${adminrow.USER_DISPLAY_NAME}"/></td></tr>
                                        </c:forEach>
                                    </table>
                                </td></tr>
                        </table>
                    </td>
                    <td style="text-align: center; border-style: none;" width="40%"><b><label>EVENT DESCRIPTION</label></b><br/>
                        <textarea rows="30" style="width: 98%; background: inherit; resize: none" readonly><c:out value="${row.DESCRIPTION}"/></textarea>
                    </td></tr>
            </table>
            </c:forEach>
        </div>
        <%@include file="/WEB-INF/jspf/footer.jspf" %>
    </body>
</html>
