<%-- 
    Document   : gsearch
    Created on : Mar 7, 2018, 7:15:49 PM
    Author     : George
--%>

<%@page import="ro.anost.utils.fb.FbAppCredentials"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="/WEB-INF/jspf/header.jspf" %>
    </head>
    <body>
        <div class="mainscreen">
            <%@include file="/WEB-INF/jspf/menu.jspf" %>
            <form action="${pageContext.request.contextPath}/FbSearchPgOption" method="GET">
                <%--page options--%>
                <table class="noboredcentertable">
                    <tr>
                        <td><input type="submit" name="gsearcgpg_option" class="submenu" value="People"></td>
                        <td><input type="submit" name="gsearcgpg_option" class="submenu" value="Photos"></td>
                    </tr>
                </table>
            </form>
            <br/>
            <c:if test="${fbcurrentuser == null || fbcurrentuser == ''}">
                    <div class="customSocialBtn">
                    <p style="font-size: 17px">Please click the button bellow to login using your Facebook account</p>
                    <%
                        FbAppCredentials fbAppCredentials = new FbAppCredentials();
                        request.setAttribute("fbauthurl", fbAppCredentials.getLoginDialog());
                    %>
                    <a href="${fbauthurl}"><button id="facebookbutton">Sign in with Facebook</button></a><br/>
                </div>
            </c:if>
            <br/>
            <div class="searchpage">
                <c:choose>
                    <c:when test="${gsearcgpgoption == 'People'}">
                        <form action="${pageContext.request.contextPath}/FbGsearch" target="_blank">
                            <fieldset>
                                <legend><b>People named "<i style="color: #990000">name</i>"</b></legend>
                                <table width="100%">
                                    <tr>
                                        <td>
                                            https://www.facebook.com/search/str/<input type="text" name="fieldtext1" placeholder="name" style="color: #990000"/>/users-named
                                        </td>
                                        <td>
                                            <button type="submit" name="gsearchpg_go" value="1">GO</button>
                                        </td>
                                    </tr>
                                </table>
                            </fieldset>
                        </form>
                        <br/>
                        <form action="${pageContext.request.contextPath}/FbGsearch" target="_blank">
                            <fieldset>
                                <legend><b>Users named "<i style="color: #990000">name</i>"</b></legend>
                                <table width="100%">
                                    <tr>
                                        <td>
                                            https://www.facebook.com/search/str/<input type="text" name="fieldtext2" placeholder="name" style="color: #990000"/>/discovery-users
                                        </td>
                                        <td>
                                            <button type="submit" name="gsearchpg_go" value="2">GO</button>
                                        </td>
                                    </tr>
                                </table>
                            </fieldset>
                        </form>
                        <br/>
                        <form action="${pageContext.request.contextPath}/FbGsearch" target="_blank">
                            <fieldset>
                                <legend><b>People from "<i style="color: #339966">town</i>" named "<i style="color: #990000">name</i>"</b></legend>
                                <table width="100%">
                                    <tr>
                                        <td>
                                            https://www.facebook.com/search/str/<input type="text" name="fieldtext3.1" placeholder="name" style="color: #990000"/>/users-named/<input type="number" name="fieldtext3.2" placeholder="city id" style="color: #339966"/>/home-residents/intersect
                                        </td>
                                        <td>
                                            <button type="submit" name="gsearchpg_go" value="3">GO</button>
                                        </td>
                                    </tr>
                                </table>
                            </fieldset>
                        </form>
                    </c:when>
                </c:choose>
            </div>
            <br/>
        </div>
        <%@include file="/WEB-INF/jspf/footer.jspf" %>
    </body>
</html>
