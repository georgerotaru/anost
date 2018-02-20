<%-- 
    Document   : fb/events_add_new
    Created on : Feb 19, 2018, 11:38:35 PM
    Author     : George
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="/anost/css/button.css">
        <title>anost - fb.. events - Add new event</title>
    </head>
    <body>
        <div class="gradient_menu">
            <%@include file="/WEB-INF/jspf/menu.jspf" %>
            <form action="${pageContext.request.contextPath}/EventsMain" method="GET">
                <table class="tablecenteredwithroundborder" id="noborder" width="40%">
                    <tr><td><input type="submit" class="button" name="fbevents_ongoing" value="Display ongoing events"></td>
                        <td><input type="submit" class="button" name="fbevents_all" value="Display all events"></td>
                        <td><input type="reset" class="button" value="Cancel"></td></tr>
                </table>
                <br/>
                <table align="center">
                    <tr><td><b>INSERT EVENT ID</b></td>
                        <td><input type="number" name="fbevents_id" style="width: 300px; text-align: center"></td>
                        <td><input type="submit" name="fbevents_add" value="GO"</td></tr>
                </table>
                <c:choose>
                <c:when test="${inDB == true}">
                    <br/>
                    <table  class="tablecenteredwithroundborder" id="noborder">
                        <tr><td style="text-align: center"><c:out value="${message}"/></td></tr>
                        <tr><td style="text-align: center">To update its details, push button below</td></tr>
                        <tr><td style="text-align: center; word-wrap: break-word; word-break: normal"><input type="submit" name="fbevents_updatesg" value="UPDATE"></td></tr>
                    </table>
                </c:when>
                </c:choose>
            </form>
        </div>
    </body>
</html>