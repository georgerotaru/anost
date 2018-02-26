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
        <link rel="icon" type="image/png" href="/anost/util/pictures/logo.png">
        <title>anost - fb.. events - Add new event</title>
    </head>
    <body>
        <div class="gradient_menu">
            <%@include file="/WEB-INF/jspf/menu.jspf" %>
            <%--insert event id form--%>
            <form action="${pageContext.request.contextPath}/EventsMain" method="GET">
                <%--page options--%>
                <table class="tablecenteredwithroundborder" id="noborder">
                    <tr><td><input type="submit" class="button" name="fbevents_ongoing" value="Display ongoing events"></td>
                        <td><input type="submit" class="button" name="fbevents_all" value="Display events"></td>
                        <td><input type="reset" class="button" value="Cancel"></td></tr>
                </table>
                <br/>
                <%--display insert form--%>
                <table align="center">
                    <tr><td><b>INSERT EVENT ID</b></td>
                        <td><input type="number" name="fbevents_id" style="width: 300px; text-align: center"></td>
                        <td><input type="submit" name="fbevents_add" value="GO"</td></tr>
                </table>
            </form>
            <%--display message from servlet--%>
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