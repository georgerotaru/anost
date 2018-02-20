<%-- 
    Document   : fb/events_index
    Created on : Feb 19, 2018, 5:36:44 PM
    Author     : George
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="/anost/css/button.css">
        <title>anost - fb.. events</title>
    </head>
    <body>
        <div class="gradient_menu">
            <%@include file="/WEB-INF/jspf/menu.jspf" %>
            <form action="${pageContext.request.contextPath}/EventsMain" method="GET">
                <table class="tablecenteredwithroundborder" id="noborder" width="40%">
                    <tr><td><a href="/anost/fb/events/add_new.jsp"><input type="button" class="button" value="Add new event"></a></td>
                        <td><input type="submit" class="button" name="fbevents_ongoing" value="Display ongoing events"></td>
                        <td><input type="submit" class="button" name="fbevents_all" value="Display all events"></td>
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
        </div>
    </body>
</html>
