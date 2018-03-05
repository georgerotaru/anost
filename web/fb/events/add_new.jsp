<%-- 
    Document   : test
    Created on : Mar 3, 2018, 9:46:44 AM
    Author     : George <mrgeorge.ro @ gmail.com>
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <c:if test="${fbcurrentuser == null || fbcurrentuser == ''}">
            <c:redirect url="/fb/events"/>
        </c:if>
        <%@include file="/WEB-INF/jspf/header.jspf" %>
    </head>
    <body>
        <div class="mainscreen">
            <%@include file="/WEB-INF/jspf/menu.jspf" %>
            <form action="${pageContext.request.contextPath}/FbEventsMain" method="GET">
                <%--page options--%>
                <table class="noboredcentertable">
                    <tr><td><input type="submit" class="submenu" name="fbevents_ongoing" value="Display ongoing events"></td>
                        <td><input type="submit" class="submenu" name="fbevents_all" value="Display events"></td>
                        <td><input type="reset" class="submenu" value="Cancel"></td></tr>
                </table>
                <br/>
                <%--display insert form--%>
                <table align="center">
                    <tr><td><b><label>INSERT EVENT ID</label></b></td>
                        <td><input type="number" name="fbevents_id" style="width: 300px; text-align: center"></td>
                        <td><input type="submit" name="fbevents_add" value="GO"</td></tr>
                </table>
            </form>
            <c:choose>
                <c:when test="${inDB == true}">
                    <br/>
                    <div style="text-align: center">
                        ${message}
                    </div>
                </c:when>
            </c:choose>
        <br/>
        </div>
        <%@include file="/WEB-INF/jspf/footer.jspf" %>
    </body>
</html>
