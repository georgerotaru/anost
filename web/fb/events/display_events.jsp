<%-- 
    Document   : display_events_
    Created on : Mar 3, 2018, 2:29:26 PM
    Author     : George <mrgeorge.ro @ gmail.com>
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <%--set db request for events--%>
        
        <sql:query dataSource="${myDB}" var="events">
            ${fn:replace(queryDB, '&gt;', '>')}
        </sql:query>
        
        <div class="mainscreen">
            <%@include file="/WEB-INF/jspf/menu.jspf" %>
            <%--display events form--%>
            <form action="${pageContext.request.contextPath}/FbEventsMain" method="GET">
                <%--page options--%>
                <table class="noboredcentertable">
                    <tr>
                        <td><input type="submit" class="submenu" name="fbevents_add" value="Add new event"></td>
                        <td><input type="submit" class="submenu" name="fbevents_ongoing" value="Display ongoing events"></td>
                        <td><input type="submit" class="submenu" name="fbevents_all" value="Display events"></td>
                        <td><input type="submit" class="submenu" name="fbevents_update" value="Update"></td>   
                        <%--<td><input type="submit" class="submenu" name="fbevents_delete" value="Delete"></td>--%>
                        <td><input type="reset" class="submenu" value="Cancel"></td>
                    </tr>
                </table>
                <br/>
                <div class="copreport">
                    <table>
                        <tr>
                            <td><img src="/anost/util/pictures/new2.png" width="30" height="30" alt="picture in which the word new apears on red background"/></td>
                            <td>
                                <select name="COpReport">
                                    <option value="">COp</option>
                                    <option value="SENT">YES</option>
                                    <option value="NOT_SENT">NO</option>
                                </select>                                
                            </td>
                            <td>
                                <input type="submit" value="SET" name="fbevents_copreport" />                               
                            </td>
                        </tr>
                    </table>
                </div>
                <br/>
                <%--display events--%>
                <table class="tableforlistings">
                    <tr><th></th><th><label style="padding-right: 5px">Event name</label></th><th style="padding-left: 5px"><label>City</label></th><th><label>Place</label></th><th style="padding-right: 5px"><label>Attending no.</label></th><th style="padding-left: 5px"><label>Interested no.</label></th><th style="padding-right: 5px"><label>Start</label></th><th style="padding-left: 5px"><label>End</label></th><th><label>URL</label></th><th><label>Update time</label></th></tr>
                    <c:forEach var="row" items="${events.rows}">
                        <tr>
                            <td ><input type="checkbox" name="events_checkbox" value="${row.EVENT_ID}"></td>
                            <td class="nostyle" style="padding-right: 5px">
                                <sql:query dataSource="${myDB}" var="copReports">
                                    SELECT REPORT_STATUS FROM FB_EVENT_REPORTSTATUS WHERE EVENT_ID='${row.EVENT_ID}'
                                </sql:query>
                                <c:forEach var="statusrow" items="${copReports.rows}">
                                    <c:if test="${statusrow.REPORT_STATUS == 'SENT'}">
                                        <img src="/anost/util/pictures/check1.png" width="17" height="17" alt="image of a check mark meaning that this event was reported"/>
                                    </c:if>
                                </c:forEach>
                                <button name="event_details" type="submit" title="click for details" value="${row.EVENT_ID}"><c:out value="${row.NAME}"/></button>
                            </td>
                            <td style="padding-left: 5px"><c:out value="${row.CITY}"/></td>
                            <td><c:out value="${row.PLACE}"/></td>
                            <td style="padding-right: 5px"><c:out value="${row.ATTENDING_COUNT}"/></td>
                            <td style="padding-left: 5px"><c:out value="${row.INTERESTED_COUNT}"/></td>
                            <td style="padding-right: 5px"><fmt:formatDate pattern = "dd.MM.yyyy" value="${row.START_DATE}"/> <fmt:formatDate pattern = "HH:mm" value="${row.START_TIME}"/></td>
                            <td style="padding-left: 5px"><fmt:formatDate pattern = "dd.MM.yyyy" value="${row.END_DATE}"/> <fmt:formatDate pattern = "HH:mm" value="${row.END_TIME}"/></td>
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
