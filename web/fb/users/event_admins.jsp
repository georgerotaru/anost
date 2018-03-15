<%-- 
    Document   : event_admins
    Created on : Mar 14, 2018, 9:48:47 AM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <%--set db request for user details--%>
        <sql:query dataSource="${myDB}" var="userdetails">
            SELECT USER_DISPLAY_NAME, USER_URL FROM FB_USER_IDENTITY WHERE USER_ID='<c:out value="${adminId}"/>'
        </sql:query>
        <sql:query dataSource="${myDB}" var="admin4events">
            SELECT FB_EVENT_DETAILS.EVENT_ID, FB_EVENT_DETAILS.NAME, FB_EVENT_DETAILS.CITY, FB_EVENT_DETAILS.PLACE, FB_EVENT_DETAILS.ATTENDING_COUNT, FB_EVENT_DETAILS.START_DATE, FB_EVENT_DETAILS.END_DATE FROM FB_EVENT_DETAILS JOIN FB_EVENT_ADMINS ON FB_EVENT_DETAILS.EVENT_ID=FB_EVENT_ADMINS.EVENT_ID WHERE FB_EVENT_ADMINS.USER_ID='<c:out value="${adminId}"/>' ORDER BY START_DATE DESC
        </sql:query>
        <div class="mainscreen">
            <%@include file="/WEB-INF/jspf/menu.jspf" %>
            <form action="${pageContext.request.contextPath}/FbEventsMain" method="GET">
                <%--page options--%>
                <table class="noboredcentertable">
                    <tr>
                        <td><input type="submit" class="submenu" name="fbevents_add" value="Add new event"></td>
                        <td><input type="submit" class="submenu" name="fbevents_ongoing" value="Display ongoing events"></td>
                        <td><input type="submit" class="submenu" name="fbevents_all" value="Display events"></td>
                    </tr>
                </table>
            </form>
            <br/>
            <table class="noboredcentertable" width="95%">
                <tr><td width="60%" style="border-style: none">
                        <table class="intabledetails">
                            <tr>
                                <td><b><label style="font-size: 20px">Admin name</label></b></td>
                                <td style="text-align: left">
                                    <c:forEach var="userRow" items="${userdetails.rows}">
                                        <a href="${userRow.USER_URL}" target="_blank" title="User Facebook Page" style="font-size: 20px"><c:out value="${userRow.USER_DISPLAY_NAME}"/></a>
                                    </c:forEach>
                                </td>
                            </tr>
                            <tr><td colspan="2"><br/></td></tr>
                            <tr><td colspan="2" style="text-align: left"><b>EVENTS FOUND IN DATABASE:</b><br/></td></tr>
                            <tr><td colspan="2"><br/></td></tr>
                            <tr><td colspan="2">
                                    <c:forEach var="eventRow" items="${admin4events.rows}">
                                        <form name="Event details" action="${pageContext.request.contextPath}/FbEventsMain" method="GET">
                                            <table style="border-style: solid; border-color: #000000; border-width: 1px">
                                                <tr>
                                                    <td style="width: 120px"><b><label>Event name</label></b></td>
                                                    <td style="width: 450px">
                                                        <div class="nostyle">
                                                            <button name="event_details" type="submit" title="click for details" value="${eventRow.EVENT_ID}" style="color: #ff0000"><c:out value="${eventRow.NAME}"/></button>
                                                        </div>
                                                    </td>
                                                </tr>
                                                <tr>
                                                <td><b><label>Event city</label></b></td>
                                                <td>
                                                    <c:out value="${eventRow.CITY}"/>
                                                </td>
                                                </tr>
                                                <tr>
                                                    <td><b><label>Event place</label></b></td>
                                                    <td>
                                                        <c:out value="${eventRow.PLACE}"/>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td><b><label>Attending no.</label></b></td>
                                                    <td>
                                                        <c:out value="${eventRow.ATTENDING_COUNT}"/>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td><b><label>Start date</label></b></td>
                                                    <td>
                                                        <fmt:formatDate pattern = "dd.MM.yyyy" value="${eventRow.START_DATE}"/>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td><b><label>End date</label></b></td>
                                                    <td>
                                                        <fmt:formatDate pattern = "dd.MM.yyyy" value="${eventRow.END_DATE}"/>
                                                    </td>
                                                </tr>
                                                <tr><td style="vertical-align: top"><b><label>Event created by</label></b></td>
                                                <%--display event admins from db--%>
                                                <td>
                                                    <table width="100%" class="nostyle">
                                                        <sql:query dataSource="${myDB}" var="admins">
                                                            SELECT FB_USER_IDENTITY.USER_DISPLAY_NAME, FB_USER_IDENTITY.USER_ID FROM FB_USER_IDENTITY JOIN FB_EVENT_ADMINS ON FB_EVENT_ADMINS.USER_ID=FB_USER_IDENTITY.USER_ID WHERE FB_EVENT_ADMINS.EVENT_ID ='<c:out value="${eventRow.EVENT_ID}"/>'
                                                        </sql:query>
                                                        <c:forEach var="adminrow" items="${admins.rows}">
                                                            <tr><td><%--<button type="submit"  title="click for details" name="detailspg_admin" value="${adminrow.USER_ID}">${adminrow.USER_DISPLAY_NAME}</button>--%>${adminrow.USER_DISPLAY_NAME}</td></tr>
                                                        </c:forEach>
                                                    </table>
                                                </td></tr>
                                            </table>
                                            <br/>
                                        </form>                                    
                                    </c:forEach>
                            </td></tr>
                        </table>
                    </td>
                    <td style="text-align: center; vertical-align: top" width="40%"><b><label>USER PICTURE</label></b><br/>
                        <br/>
                        <img src="${adminPictureUrl}" width="350" height="350" alt="facebook profile image of user"/>
                        <br/>
                        ${message}
                    </td>
                </tr>
            </table>
        </div>
        <%@include file="/WEB-INF/jspf/footer.jspf" %>
    </body>
</html>
