<%-- 
    Document   : index
    Created on : Feb 19, 2018, 5:13:28 PM
    Author     : George
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="icon" type="image/png" href="/anost/util/pictures/logo.png">
        <title>Test FB Login</title>
    </head>
    <body>
        <h1>Login to Facebook</h1>
        <form action="${pageContext.request.contextPath}/FbLogin}" method="GET">
            <input type="submit" name="indexpg_gobutton"/>
        </form>
    </body>
</html>
