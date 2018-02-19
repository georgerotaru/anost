<%-- 
    Document   : fb/events_add_new
    Created on : Feb 19, 2018, 11:38:35 PM
    Author     : George
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="../../css/button.css">
        <title>anost - fb.. events - Add new event</title>
    </head>
    <body>
        <div class="gradient_menu">
            <%@include file="../../WEB-INF/jspf/menu.jspf" %>
            <form action="${pageContext.request.contextPath}/???" method="GET">
                <table class="tablecenteredwithroundborder" id="noborder" width="40%">
                    <tr><td><input type="submit" class="button" name="fbeventspg_ongoing" value="Display ongoing events"></td>
                        <td><input type="submit" class="button" name="fbeventspg_all" value="Display all events"></td>
                        <td><input type="reset" class="button" name="fbeventspg_cancel" value="Cancel"></td></tr>
                </table>
            </form>
        </div>
    </body>
</html>