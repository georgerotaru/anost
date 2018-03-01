<%-- 
    Document   : display_events
    Created on : Feb 20, 2018, 2:22:48 PM
    Author     :  George
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
        <link rel="icon" type="image/png" href="/anost/util/pictures/logo.png">
        <title>anost - fb.. events - Display events</title>
    </head>
    <body>
        <%--set db credentials--%>
        <sql:setDataSource
            var="myDB"
            driver="org.apache.derby.jdbc.ClientDriver"
            user="anost"
            password="anost"
            url="jdbc:derby://localhost:1527/anost_db;create=true"/>
        <%--set db request for events--%>
        <sql:query dataSource="${myDB}" var="events">
            ${fn:replace(queryDB, '&gt;', '>')}
        </sql:query>
        <div class="gradient_menu">
            <%@include file="/WEB-INF/jspf/menu.jspf" %>
            <%--display events form--%>
            <form action="${pageContext.request.contextPath}/EventsMain" method="GET">
                <%--page options--%>
                <table class="tablecenteredwithroundborder" id="noborder">
                    <tr><td><a href="/anost/fb/events/add_new.jsp"><input type="button" class="button" value="Add new event"></a></td>
                        <td><input type="submit" class="button" name="fbevents_ongoing" value="Display ongoing events"></td>
                        <td><input type="submit" class="button" name="fbevents_all" value="Display events"></td>
                        <td><input type="submit" class="button" name="fbevents_update" value="Update"></td>   
                        <td><input type="submit" class="button" name="fbevents_delete" value="Delete"></td>
                        <td><input type="reset" class="button" value="Cancel"></td></tr>
                </table>
                <br/>
                <%--display events--%>
                <table class="tableforlistings">
                    <tr><th></th><th>Event name</th><th>City</th><th>Place</th><th>Attending no.</th><th>Interested no.</th><th>Start date/ time</th><th>End date/ time</th><th>URL</th><th>Update time</th></tr>
                    <% int counter = 0;
                    String trEvenCssClass = null; %>
                    <c:forEach var="row" items="${events.rows}">
                        <% if (counter % 2 == 0 ) {
                            trEvenCssClass = "evenrow";
                            counter++;
                        } else {
                            trEvenCssClass = null;
                            counter++;
                        }
                         %>
                    <tr class="<%out.print(trEvenCssClass);%>">
                            <td><input type="checkbox" name="events_checkbox" value="${row.EVENT_ID}"></td>
                            <td class="nostyle"><button name="event_details" type="submit" title="click for details" value="${row.EVENT_ID}"><c:out value="${row.NAME}"/></button></td>
                            <td><c:out value="${row.CITY}"/></td>
                            <td><c:out value="${row.PLACE}"/></td>
                            <td><c:out value="${row.ATTENDING_COUNT}"/></td>
                            <td><c:out value="${row.INTERESTED_COUNT}"/></td>
                            <td><fmt:formatDate pattern = "dd.MM.yyyy" value="${row.START_DATE}"/> <fmt:formatDate pattern = "HH:mm" value="${row.START_TIME}"/></td>
                            <td><fmt:formatDate pattern = "dd.MM.yyyy" value="${row.END_DATE}"/> <fmt:formatDate pattern = "HH:mm" value="${row.END_TIME}"/></td>
                            <td><a href="${row.URL}" target="_blank" title="Event Facebook Page"><img src="/anost/util/pictures/facebook-radius-transparent-logo.png" alt="facebook logo wich takes you to facebook event page" style="width: 3vh; height: 3vh"></a></td>
                            <td><fmt:formatDate pattern = "dd.MM.yyyy HH:mm" value="${row.LAST_UPDATE}"/></td>
                        </tr>
                    </c:forEach>
                </table>
            </form>
        </div>
    <%@include file="/WEB-INF/jspf/footer.jspf" %>
    </body>
</html>