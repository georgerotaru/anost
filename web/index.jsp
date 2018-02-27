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
        <h1>Index</h1>
        <c:choose>
            <c:when test="${sessionScope.fbLoginTest == null}">
                <c:redirect url="/util/fblogin.jsp" />
            </c:when>
            <c:otherwise>
                <jsp:useBean id="testfbconnection" scope="session" class="ro.anost.util.FbLoginTest" />
                <jsp:setProperty name="testfbconnection" property="fbLoginTest" />
                <jsp:getProperty name="testfbconnection" property="fbLoginTest" />
            </c:otherwise>
        </c:choose>
        <c:if test="">
            
        </c:if>
    </body>
</html>
