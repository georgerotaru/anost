<%-- 
    Document   : index
    Created on : Feb 19, 2018, 5:13:28 PM
    Author     : George
--%>

<%@page import="com.restfb.FacebookClient.AccessToken"%>
<%@page import="com.restfb.Parameter"%>
<%@page import="com.restfb.Version"%>
<%@page import="com.restfb.DefaultFacebookClient"%>
<%@page import="com.restfb.FacebookClient"%>
<%@page import="com.restfb.scope.FacebookPermissions"%>
<%@page import="com.restfb.scope.ScopeBuilder"%>
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
        <h1>Logged in?</h1>
        <%--<c:choose>
            <c:when test="${loggedin == true}">
                loggedin = ${loggedin}
            </c:when>
            <c:otherwise>
                <c:redirect url="https://www.facebook.com/dialog/oauth?client_id=1392269750899694&redirect_uri=http://localhost:8080/anost/FbLogin"/>
            </c:otherwise>
        </c:choose>--%>
        
    </body>
</html>
