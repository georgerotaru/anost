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
        <sql:query dataSource="${myDB}" var="events">
            SELECT * FROM EVENT_DETAILS WHERE ID='<c:out value="${eventID}"/>'
        </sql:query>
        <div class="gradient_menu">
            <%@include file="/WEB-INF/jspf/menu.jspf" %>
            <form action="${pageContext.request.contextPath}/EventsMain" method="GET">
                <table class="tablecenteredwithroundborder" id="noborder">
                    <tr><td><a href="/anost/fb/events/add_new.jsp"><input type="button" class="button" value="Add new event"></a></td>
                        <td><input type="submit" class="button" name="fbevents_ongoing" value="Display ongoing events"></td>
                        <td><input type="submit" class="button" name="fbevents_all" value="Display events"></td>
                        
                        <td><input type="submit" class="button" name="fbevents_delete" value="Delete Event"></td>
                        <td><input type="submit" class="button" name="fbevents_update" value="Update Event"></td>
                        
                        <td><input type="reset" class="button" value="Cancel"></td></tr>
                </table>
                <br/>
                
            </form>
        </div>
    <%@include file="/WEB-INF/jspf/footer.jspf" %>
    </body>
</html>
