<%-- 
    Document   : fblogin
    Created on : Feb 26, 2018, 11:41:28 AM
    Author     : George
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Facebook login</title>
    </head>
    <body>
        <h1>Login to Facebook</h1>
        <form action="/fb/events/index.jsp" method="GET">
            <input type="text" name="fbLoginStatus">
            <input type="button" name="test_button" value="GO">
        </form>
    </body>
</html>
