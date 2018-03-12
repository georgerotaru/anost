<%-- 
    Document   : rdrfb
    Created on : Mar 10, 2018, 2:47:02 PM
    Author     : George
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
    </head>
    <body>
        <jsp:useBean id="ppbean" scope="page" class="ro.anost.utils.fb.FbSearch" />
        <jsp:setProperty name="ppbean" property="fbName" />
        
        <c:redirect url="https://www.facebook.com/search/str/${ppbean.fbName}/users-named"/>
    </body>
</html>
