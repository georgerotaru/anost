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
        <datalist id="locationid">
            <option value="106076642756874">Romania</option>
            <option value="106088726089413">Alba-Iulia</option>
            <option value="1551290241820516">Alba county</option>
        </datalist>
        <div class="mainscreen">
            <%@include file="/WEB-INF/jspf/menu.jspf" %>
            <form action="${pageContext.request.contextPath}/FbSearchPgOption" method="GET">
                <%--page options--%>
                <table class="noboredcentertable">
                    <tr>
                        <td><input type="submit" name="gsearcgpg_option" class="submenu" value="People/Pages"></td>
                        <td><input type="submit" name="gsearcgpg_option" class="submenu" value="Friendship"></td>
                        <td><input type="submit" name="gsearcgpg_option" class="submenu" value="Photos"></td>
                        <td><input type="submit" name="gsearcgpg_option" class="submenu" value="Events"></td>
                    </tr>
                </table>
            </form>
            <br/>
            <div class="searchpage">
                <c:choose>
                    <c:when test="${gsearcgpgoption == 'People/Pages'}">
                        <form action="${pageContext.request.contextPath}/FbGsearch" target="_blank">
                            <fieldset style="border-width: 1px">
                                <legend><b>People named "<i style="color: #990000">name</i>"</b></legend>
                                <table width="100%">
                                    <tr>
                                        <td>
                                            https://www.facebook.com/search/str/<input type="text" name="fieldtext1" placeholder="name" style="color: #990000"/>/users-named
                                        </td>
                                        <td width="40px">
                                            <button type="submit" name="gsearchpg_go" value="1">GO</button>
                                        </td>
                                    </tr>
                                </table>
                            </fieldset>
                        </form>
                        <br/>
                        <form action="${pageContext.request.contextPath}/FbGsearch" target="_blank">
                            <fieldset style="border-width: 1px">
                                <legend><b>People named "<i style="color: #990000">name</i>" from "<i style="color: #339966">location</i>" </b></legend>
                                <table width="100%">
                                    <tr>
                                        <td>
                                            https://www.facebook.com/search/str/<input type="text" name="fieldtext2.1" placeholder="name" style="color: #990000"/>/users-named/<input type="number" name="fieldtext2.2" placeholder="location id" style="color: #339966"/><%--<input list="locationid" name="fieldtext2.2" placeholder="location id" style="color: #339966">--%>/home-residents/intersect
                                        </td>
                                        <td width="40px">
                                            <button type="submit" name="gsearchpg_go" value="2">GO</button>
                                        </td>
                                    </tr>
                                </table>
                            </fieldset>
                        </form>
                        <br/>
                        <form action="${pageContext.request.contextPath}/FbGsearch" target="_blank">
                            <fieldset style="border-width: 1px">
                                <legend><b>People named "<i style="color: #990000">name</i>" who are "<i style="color: #339966">location</i>" residents</b></legend>
                                <table width="100%">
                                    <tr>
                                        <td>
                                            https://www.facebook.com/search/str/<input type="text" name="fieldtext3.1" placeholder="name" style="color: #990000"/>/users-named/<input type="number" name="fieldtext3.2" placeholder="location id" style="color: #339966"/>/residents/intersect
                                        </td>
                                        <td width="40px">
                                            <button type="submit" name="gsearchpg_go" value="3">GO</button>
                                        </td>
                                    </tr>
                                </table>
                            </fieldset>
                        </form>
                        <br/>
                        <form action="${pageContext.request.contextPath}/FbGsearch" target="_blank">
                            <fieldset style="border-width: 1px">
                                <legend><b>People from "<i style="color: #339966">location</i>" who are "<i style="color: #339966">location</i>" residents</b></legend>
                                <table width="100%">
                                    <tr>
                                        <td>
                                            https://www.facebook.com/search/<input type="number" name="fieldtext4.1" placeholder="location id" style="color: #339966"/>/home-residents/<input type="number" name="fieldtext4.2" placeholder="location id" style="color: #339966"/>/residents/intersect
                                        </td>
                                        <td width="40px">
                                            <button type="submit" name="gsearchpg_go" value="4">GO</button>
                                        </td>
                                    </tr>
                                </table>
                            </fieldset>
                        </form>
                        <br/>
                        <form action="${pageContext.request.contextPath}/FbGsearch" target="_blank">
                            <fieldset style="border-width: 1px">
                                <legend><b>Pages liked by "<i style="color: #2980B9">user</i>"</b></legend>
                                <table width="100%">
                                    <tr>
                                        <td>
                                            https://www.facebook.com/search/<input type="number" name="fieldtext5" placeholder="profile id" style="color: #2980B9"/>/pages-liked
                                        </td>
                                        <td width="40px">
                                            <button type="submit" name="gsearchpg_go" value="5">GO</button>
                                        </td>
                                    </tr>
                                </table>
                            </fieldset>
                        </form>
                        <br/>
                        <form action="${pageContext.request.contextPath}/FbGsearch" target="_blank">
                            <fieldset style="border-width: 1px">
                                <legend><b>Favorite pages of "<i style="color: #2980B9">user</i>"'s friends</b></legend>
                                <table width="100%">
                                    <tr>
                                        <td>
                                            https://www.facebook.com/search/<input type="number" name="fieldtext6" placeholder="profile id" style="color: #2980B9"/>/friends/pages-liked
                                        </td>
                                        <td width="40px">
                                            <button type="submit" name="gsearchpg_go" value="6">GO</button>
                                        </td>
                                    </tr>
                                </table>
                            </fieldset>
                        </form>
                        <br/>
                        <form action="${pageContext.request.contextPath}/FbGsearch" target="_blank">
                            <fieldset style="border-width: 1px">
                                <legend><b>Pages liked by "<i style="color: #2980B9">user</i>" and "<i style="color: #2980B9">user</i>"</b></legend>
                                <table width="100%">
                                    <tr>
                                        <td>
                                            https://www.facebook.com/search/<input type="number" name="fieldtext7.1" placeholder="profile id" style="color: #2980B9"/>/pages-liked/<input type="number" name="fieldtext7.2" placeholder="profile id" style="color: #2980B9"/>/pages-liked/intersect
                                        </td>
                                        <td width="40px">
                                            <button type="submit" name="gsearchpg_go" value="7">GO</button>
                                        </td>
                                    </tr>
                                </table>
                            </fieldset>
                        </form>
                        <br/>
                        <form action="${pageContext.request.contextPath}/FbGsearch" target="_blank">
                            <fieldset style="border-width: 1px">
                                <legend><b>Pages named "<i style="color: #990000">name</i>"</b></legend>
                                <table width="100%">
                                    <tr>
                                        <td>
                                            https://www.facebook.com/search/str/<input type="text" name="fieldtext8" placeholder="name" style="color: #990000"/>/pages-named
                                        </td>
                                        <td width="40px">
                                            <button type="submit" name="gsearchpg_go" value="8">GO</button>
                                        </td>
                                    </tr>
                                </table>
                            </fieldset>
                        </form>
                    </c:when>
                    <c:when test="${gsearcgpgoption == 'Photos'}">
                        <form action="${pageContext.request.contextPath}/FbGsearch" target="_blank">
                            <fieldset style="border-width: 1px">
                                <legend><b>Photos of "<i style="color: #2980B9">user</i>"</b></legend>
                                <table width="100%">
                                    <tr>
                                        <td>
                                            https://www.facebook.com/search/<input type="number" name="photos_field1" placeholder="profile id" style="color: #2980B9"/>/photos-of
                                        </td>
                                        <td width="40px">
                                            <button type="submit" name="gsearchpg_go" value="photos1">GO</button>
                                        </td>
                                    </tr>
                                </table>
                            </fieldset>
                        </form>
                        <br/>
                        <form action="${pageContext.request.contextPath}/FbGsearch" target="_blank">
                            <fieldset style="border-width: 1px">
                                <legend><b>Photos commented on by "<i style="color: #2980B9">user</i>"</b></legend>
                                <table width="100%">
                                    <tr>
                                        <td>
                                            https://www.facebook.com/search/<input type="number" name="photos_field2" placeholder="profile id" style="color: #2980B9"/>/photos-commented
                                        </td>
                                        <td width="40px">
                                            <button type="submit" name="gsearchpg_go" value="photos2">GO</button>
                                        </td>
                                    </tr>
                                </table>
                            </fieldset>
                        </form>
                        <br/>
                        <form action="${pageContext.request.contextPath}/FbGsearch" target="_blank">
                            <fieldset style="border-width: 1px">
                                <legend><b>Photos liked by "<i style="color: #2980B9">user</i>"</b></legend>
                                <table width="100%">
                                    <tr>
                                        <td>
                                            https://www.facebook.com/search/<input type="number" name="photos_field3" placeholder="profile id" style="color: #2980B9"/>/photos-liked
                                        </td>
                                        <td width="40px">
                                            <button type="submit" name="gsearchpg_go" value="photos3">GO</button>
                                        </td>
                                    </tr>
                                </table>
                            </fieldset>
                        </form>
                        <br/>
                        <form action="${pageContext.request.contextPath}/FbGsearch" target="_blank">
                            <fieldset style="border-width: 1px">
                                <legend><b>Photos tagged with "<i style="color: #2980B9">user</i>"</b></legend>
                                <table width="100%">
                                    <tr>
                                        <td>
                                            https://www.facebook.com/search/<input type="number" name="photos_field4" placeholder="profile id" style="color: #2980B9"/>/photos-tagged
                                        </td>
                                        <td width="40px">
                                            <button type="submit" name="gsearchpg_go" value="photos4">GO</button>
                                        </td>
                                    </tr>
                                </table>
                            </fieldset>
                        </form>
                        <br/>
                        <form action="${pageContext.request.contextPath}/FbGsearch" target="_blank">
                            <fieldset style="border-width: 1px">
                                <legend><b>People tagged in photos of "<i style="color: #2980B9">user</i>"</b></legend>
                                <table width="100%">
                                    <tr>
                                        <td>
                                            https://www.facebook.com/search/<input type="number" name="photos_field5" placeholder="profile id" style="color: #2980B9"/>/photos-of/users-tagged
                                        </td>
                                        <td width="40px">
                                            <button type="submit" name="gsearchpg_go" value="photos5">GO</button>
                                        </td>
                                    </tr>
                                </table>
                            </fieldset>
                        </form>
                        <br/>
                        <form action="${pageContext.request.contextPath}/FbGsearch" target="_blank">
                            <fieldset style="border-width: 1px">
                                <legend><b>Comments by "<i style="color: #2980B9">user</i>" on photos by "<i style="color: #2980B9">user</i>"</b></legend>
                                <table width="100%">
                                    <tr>
                                        <td>
                                            https://www.facebook.com/search/<input type="number" name="photos_field6.1" placeholder="profile id" style="color: #2980B9"/>/photos-commented/<input type="number" name="photos_field6.2" placeholder="profile id" style="color: #2980B9"/>/photos-by/intersect
                                        </td>
                                        <td width="40px">
                                            <button type="submit" name="gsearchpg_go" value="photos6">GO</button>
                                        </td>
                                    </tr>
                                </table>
                            </fieldset>
                        </form>
                        <br/>
                        <form action="${pageContext.request.contextPath}/FbGsearch" target="_blank">
                            <fieldset style="border-width: 1px">
                                <legend><b>Likes by "<i style="color: #2980B9">user</i>" on photos by "<i style="color: #2980B9">user</i>"</b></legend>
                                <table width="100%">
                                    <tr>
                                        <td>
                                            https://www.facebook.com/search/<input type="number" name="photos_field7.1" placeholder="profile id" style="color: #2980B9"/>/photos-liked/<input type="number" name="photos_field7.2" placeholder="profile id" style="color: #2980B9"/>/photos-by/intersect
                                        </td>
                                        <td width="40px">
                                            <button type="submit" name="gsearchpg_go" value="photos7">GO</button>
                                        </td>
                                    </tr>
                                </table>
                            </fieldset>
                        </form>
                        <br/>
                        <form action="${pageContext.request.contextPath}/FbGsearch" target="_blank">
                            <fieldset style="border-width: 1px">
                                <legend><b>Photos commented on by "<i style="color: #2980B9">user</i>" and "<i style="color: #2980B9">user</i>"</b></legend>
                                <table width="100%">
                                    <tr>
                                        <td>
                                            https://www.facebook.com/search/<input type="number" name="photos_field8.1" placeholder="profile id" style="color: #2980B9"/>/photos-commented/<input type="number" name="photos_field8.2" placeholder="profile id" style="color: #2980B9"/>/photos-commented/intersect
                                        </td>
                                        <td width="40px">
                                            <button type="submit" name="gsearchpg_go" value="photos8">GO</button>
                                        </td>
                                    </tr>
                                </table>
                            </fieldset>
                        </form>
                        <br/>
                        <form action="${pageContext.request.contextPath}/FbGsearch" target="_blank">
                            <fieldset style="border-width: 1px">
                                <legend><b>Photos commented on by "<i style="color: #2980B9">user</i>" and "<i style="color: #2980B9">user</i>" and "<i style="color: #2980B9">user</i>"</b></legend>
                                <table width="100%">
                                    <tr>
                                        <td>
                                            https://www.facebook.com/search/<input type="number" name="photos_field9.1" placeholder="profile id" style="color: #2980B9"/>/photos-commented/<input type="number" name="photos_field9.2" placeholder="profile id" style="color: #2980B9"/>/photos-commented/<input type="number" name="photos_field9.3" placeholder="profile id" style="color: #2980B9"/>/photos-commented/intersect
                                        </td>
                                        <td width="40px">
                                            <button type="submit" name="gsearchpg_go" value="photos9">GO</button>
                                        </td>
                                    </tr>
                                </table>
                            </fieldset>
                        </form>
                        <br/>
                        <form action="${pageContext.request.contextPath}/FbGsearch" target="_blank">
                            <fieldset style="border-width: 1px">
                                <legend><b>Photos liked by "<i style="color: #2980B9">user</i>" and commented on by "<i style="color: #2980B9">user</i>"</b></legend>
                                <table width="100%">
                                    <tr>
                                        <td>
                                            https://www.facebook.com/search/<input type="number" name="photos_field10.1" placeholder="profile id" style="color: #2980B9"/>/photos-liked/<input type="number" name="photos_field10.2" placeholder="profile id" style="color: #2980B9"/>/photos-commented/intersect
                                        </td>
                                        <td width="40px">
                                            <button type="submit" name="gsearchpg_go" value="photos10">GO</button>
                                        </td>
                                    </tr>
                                </table>
                            </fieldset>
                        </form>
                        <br/>
                        <form action="${pageContext.request.contextPath}/FbGsearch" target="_blank">
                            <fieldset style="border-width: 1px">
                                <legend><b>Photos by "<i style="color: #2980B9">user</i>" liked by "<i style="color: #2980B9">user</i>"</b></legend>
                                <table width="100%">
                                    <tr>
                                        <td>
                                            https://www.facebook.com/search/<input type="number" name="photos_field11.1" placeholder="profile id" style="color: #2980B9"/>/photos-by/<input type="number" name="photos_field11.2" placeholder="profile id" style="color: #2980B9"/>/photos-liked/intersect
                                        </td>
                                        <td width="40px">
                                            <button type="submit" name="gsearchpg_go" value="photos11">GO</button>
                                        </td>
                                    </tr>
                                </table>
                            </fieldset>
                        </form>
                        <br/>
                        <form action="${pageContext.request.contextPath}/FbGsearch" target="_blank">
                            <fieldset style="border-width: 1px">
                                <legend><b>Photos taken at "<i style="color: #339966">location</i>"</b></legend>
                                <table width="100%">
                                    <tr>
                                        <td>
                                            https://www.facebook.com/search/<input type="number" name="photos_field12" placeholder="location id" style="color: #339966"/>/photos-in
                                        </td>
                                        <td width="40px">
                                            <button type="submit" name="gsearchpg_go" value="photos12">GO</button>
                                        </td>
                                    </tr>
                                </table>
                            </fieldset>
                        </form>
                        <br/>
                        <form action="${pageContext.request.contextPath}/FbGsearch" target="_blank">
                            <fieldset style="border-width: 1px">
                                <legend><b>Photos of "<i style="color: #2980B9">user</i>" taken at "<i style="color: #339966">location</i>"</b></legend>
                                <table width="100%">
                                    <tr>
                                        <td>
                                            https://www.facebook.com/search/<input type="number" name="photos_field13.1" placeholder="profile id" style="color: #2980B9"/>/photos-of/<input type="number" name="photos_field13.2" placeholder="location id" style="color: #339966"/>/photos-in/intersect
                                        </td>
                                        <td width="40px">
                                            <button type="submit" name="gsearchpg_go" value="photos13">GO</button>
                                        </td>
                                    </tr>
                                </table>
                            </fieldset>
                        </form>
                    </c:when>
                    <c:when test="${gsearcgpgoption == 'Events'}">
                        <form action="${pageContext.request.contextPath}/FbGsearch" target="_blank">
                            <fieldset style="border-width: 1px">
                                <legend><b>Events named "<i style="color: #990000">name</i>"</b></legend>
                                <table width="100%">
                                    <tr>
                                        <td>
                                            https://www.facebook.com/search/str/<input type="text" name="events_field1" placeholder="name" style="color: #990000"/>/events-named
                                        </td>
                                        <td width="40px">
                                            <button type="submit" name="gsearchpg_go" value="events1">GO</button>
                                        </td>
                                    </tr>
                                </table>
                            </fieldset>
                        </form>
                        <br/>
                        <form action="${pageContext.request.contextPath}/FbGsearch" target="_blank">
                            <fieldset style="border-width: 1px">
                                <legend><b>Events in "<i style="color: #339966">location</i>"</b></legend>
                                <table width="100%">
                                    <tr>
                                        <td>
                                            https://www.facebook.com/search/<input type="number" name="events_field2" placeholder="location id" style="color: #339966"/>/events-at
                                        </td>
                                        <td width="40px">
                                            <button type="submit" name="gsearchpg_go" value="events2">GO</button>
                                        </td>
                                    </tr>
                                </table>
                            </fieldset>
                        </form>
                        <br/>
                        <form action="${pageContext.request.contextPath}/FbGsearch" target="_blank">
                            <fieldset style="border-width: 1px">
                                <legend><b>Events named "<i style="color: #990000">name</i>" that are future</b></legend>
                                <table width="100%">
                                    <tr>
                                        <td>
                                            https://www.facebook.com/search/str/<input type="text" name="events_field3" placeholder="name" style="color: #990000"/>/events-named/in-future/date/events/intersect
                                        </td>
                                        <td width="40px">
                                            <button type="submit" name="gsearchpg_go" value="events3">GO</button>
                                        </td>
                                    </tr>
                                </table>
                            </fieldset>
                        </form>
                        <br/>
                        <form action="${pageContext.request.contextPath}/FbGsearch" target="_blank">
                            <fieldset style="border-width: 1px">
                                <legend><b>Events named "<i style="color: #990000">name</i>" in "<i style="color: #339966">location</i>" that are future</b></legend>
                                <table width="100%">
                                    <tr>
                                        <td>
                                            https://www.facebook.com/search/str/<input type="text" name="events_field4.1" placeholder="name" style="color: #990000"/>/events-named/in-future/date/events/<input type="number" name="events_field4.2" placeholder="location id" style="color: #339966"/>/events-at/intersect
                                        </td>
                                        <td width="40px">
                                            <button type="submit" name="gsearchpg_go" value="events4">GO</button>
                                        </td>
                                    </tr>
                                </table>
                            </fieldset>
                        </form>
                        <br/>
                        <form action="${pageContext.request.contextPath}/FbGsearch" target="_blank">
                            <fieldset style="border-width: 1px">
                                <legend><b>Events today</b></legend>
                                <table width="100%">
                                    <tr>
                                        <td>
                                            https://www.facebook.com/search/today/date/events
                                        </td>
                                        <td width="40px">
                                            <button type="submit" name="gsearchpg_go" value="events5">GO</button>
                                        </td>
                                    </tr>
                                </table>
                            </fieldset>
                        </form>
                        <br/>
                        <form action="${pageContext.request.contextPath}/FbGsearch" target="_blank">
                            <fieldset style="border-width: 1px">
                                <legend><b>Events named "<i style="color: #990000">name</i>" today</b></legend>
                                <table width="100%">
                                    <tr>
                                        <td>
                                            https://www.facebook.com/search/str/<input type="text" name="events_field15" placeholder="name" style="color: #990000"/>/events-named/today/date/events/intersect
                                        </td>
                                        <td width="40px">
                                            <button type="submit" name="gsearchpg_go" value="events15">GO</button>
                                        </td>
                                    </tr>
                                </table>
                            </fieldset>
                        </form>
                        <br/>
                        <form action="${pageContext.request.contextPath}/FbGsearch" target="_blank">
                            <fieldset style="border-width: 1px">
                                <legend><b>Events named "<i style="color: #990000">name</i>" in "<i style="color: #339966">location</i>" today</b></legend>
                                <table width="100%">
                                    <tr>
                                        <td>
                                            https://www.facebook.com/search/str/<input type="text" name="events_field16.1" placeholder="name" style="color: #990000"/>/events-named/<input type="number" name="events_field16.2" placeholder="location id" style="color: #339966"/>/events-at/today/date/events/intersect
                                        </td>
                                        <td width="40px">
                                            <button type="submit" name="gsearchpg_go" value="events16">GO</button>
                                        </td>
                                    </tr>
                                </table>
                            </fieldset>
                        </form>
                        <br/>
                        <form action="${pageContext.request.contextPath}/FbGsearch" target="_blank">
                            <fieldset style="border-width: 1px">
                                <legend><b>Events tomorrow</b></legend>
                                <table width="100%">
                                    <tr>
                                        <td>
                                            https://www.facebook.com/search/tomorrow/date/events
                                        </td>
                                        <td width="40px">
                                            <button type="submit" name="gsearchpg_go" value="events6">GO</button>
                                        </td>
                                    </tr>
                                </table>
                            </fieldset>
                        </form>
                        <br/>
                        <form action="${pageContext.request.contextPath}/FbGsearch" target="_blank">
                            <fieldset style="border-width: 1px">
                                <legend><b>Events named "<i style="color: #990000">name</i>" tomorrow</b></legend>
                                <table width="100%">
                                    <tr>
                                        <td>
                                            https://www.facebook.com/search/str/<input type="text" name="events_field17" placeholder="name" style="color: #990000"/>/events-named/tomorrow/date/events/intersect
                                        </td>
                                        <td width="40px">
                                            <button type="submit" name="gsearchpg_go" value="events17">GO</button>
                                        </td>
                                    </tr>
                                </table>
                            </fieldset>
                        </form>
                        <br/>
                        <form action="${pageContext.request.contextPath}/FbGsearch" target="_blank">
                            <fieldset style="border-width: 1px">
                                <legend><b>Events named "<i style="color: #990000">name</i>" in "<i style="color: #339966">location</i>" tomorrow</b></legend>
                                <table width="100%">
                                    <tr>
                                        <td>
                                            https://www.facebook.com/search/str/<input type="text" name="events_field18.1" placeholder="name" style="color: #990000"/>/events-named/<input type="number" name="events_field18.2" placeholder="location id" style="color: #339966"/>/events-at/tomorrow/date/events/intersect
                                        </td>
                                        <td width="40px">
                                            <button type="submit" name="gsearchpg_go" value="events18">GO</button>
                                        </td>
                                    </tr>
                                </table>
                            </fieldset>
                        </form>
                        <br/>
                        <form action="${pageContext.request.contextPath}/FbGsearch" target="_blank">
                            <fieldset style="border-width: 1px">
                                <legend><b>Events this weekend</b></legend>
                                <table width="100%">
                                    <tr>
                                        <td>
                                            https://www.facebook.com/search/this-weekend/date/events
                                        </td>
                                        <td width="40px">
                                            <button type="submit" name="gsearchpg_go" value="events7">GO</button>
                                        </td>
                                    </tr>
                                </table>
                            </fieldset>
                        </form>
                        <br/>
                        <form action="${pageContext.request.contextPath}/FbGsearch" target="_blank">
                            <fieldset style="border-width: 1px">
                                <legend><b>Events named "<i style="color: #990000">name</i>" this weekend</b></legend>
                                <table width="100%">
                                    <tr>
                                        <td>
                                            https://www.facebook.com/search/str/<input type="text" name="events_field19" placeholder="name" style="color: #990000"/>/events-named/this-weekend/date/events/intersect
                                        </td>
                                        <td width="40px">
                                            <button type="submit" name="gsearchpg_go" value="events19">GO</button>
                                        </td>
                                    </tr>
                                </table>
                            </fieldset>
                        </form>
                        <br/>
                        <form action="${pageContext.request.contextPath}/FbGsearch" target="_blank">
                            <fieldset style="border-width: 1px">
                                <legend><b>Events named "<i style="color: #990000">name</i>" in "<i style="color: #339966">location</i>" this weekend</b></legend>
                                <table width="100%">
                                    <tr>
                                        <td>
                                            https://www.facebook.com/search/str/<input type="text" name="events_field20.1" placeholder="name" style="color: #990000"/>/events-named/<input type="number" name="events_field20.2" placeholder="location id" style="color: #339966"/>/events-at/this-weekend/date/events/intersect
                                        </td>
                                        <td width="40px">
                                            <button type="submit" name="gsearchpg_go" value="events20">GO</button>
                                        </td>
                                    </tr>
                                </table>
                            </fieldset>
                        </form>
                        <br/>
                        <form action="${pageContext.request.contextPath}/FbGsearch" target="_blank">
                            <fieldset style="border-width: 1px">
                                <legend><b>Events this week</b></legend>
                                <table width="100%">
                                    <tr>
                                        <td>
                                            https://www.facebook.com/search/this-week/date/events
                                        </td>
                                        <td width="40px">
                                            <button type="submit" name="gsearchpg_go" value="events8">GO</button>
                                        </td>
                                    </tr>
                                </table>
                            </fieldset>
                        </form>
                        <br/>
                        <form action="${pageContext.request.contextPath}/FbGsearch" target="_blank">
                            <fieldset style="border-width: 1px">
                                <legend><b>Events named "<i style="color: #990000">name</i>" this week</b></legend>
                                <table width="100%">
                                    <tr>
                                        <td>
                                            https://www.facebook.com/search/str/<input type="text" name="events_field21" placeholder="name" style="color: #990000"/>/events-named/this-week/date/events/intersect
                                        </td>
                                        <td width="40px">
                                            <button type="submit" name="gsearchpg_go" value="events21">GO</button>
                                        </td>
                                    </tr>
                                </table>
                            </fieldset>
                        </form>
                        <br/>
                        <form action="${pageContext.request.contextPath}/FbGsearch" target="_blank">
                            <fieldset style="border-width: 1px">
                                <legend><b>Events named "<i style="color: #990000">name</i>" in "<i style="color: #339966">location</i>" this week</b></legend>
                                <table width="100%">
                                    <tr>
                                        <td>
                                            https://www.facebook.com/search/str/<input type="text" name="events_field22.1" placeholder="name" style="color: #990000"/>/events-named/<input type="number" name="events_field22.2" placeholder="location id" style="color: #339966"/>/events-at/this-week/date/events/intersect
                                        </td>
                                        <td width="40px">
                                            <button type="submit" name="gsearchpg_go" value="events22">GO</button>
                                        </td>
                                    </tr>
                                </table>
                            </fieldset>
                        </form>
                        <br/>
                        <form action="${pageContext.request.contextPath}/FbGsearch" target="_blank">
                            <fieldset style="border-width: 1px">
                                <legend><b>Events hosted by "<i style="color: #2980B9">user</i>"</b></legend>
                                <table width="100%">
                                    <tr>
                                        <td>
                                            https://www.facebook.com/search/<input type="number" name="events_field9" placeholder="profile id" style="color: #2980B9"/>/events-created
                                        </td>
                                        <td width="40px">
                                            <button type="submit" name="gsearchpg_go" value="events9">GO</button>
                                        </td>
                                    </tr>
                                </table>
                            </fieldset>
                        </form>
                        <br/>
                        <form action="${pageContext.request.contextPath}/FbGsearch" target="_blank">
                            <fieldset style="border-width: 1px">
                                <legend><b>Events hosted by "<i style="color: #2980B9">user</i>" that are future</b></legend>
                                <table width="100%">
                                    <tr>
                                        <td>
                                            https://www.facebook.com/search/<input type="number" name="events_field10" placeholder="profile id" style="color: #2980B9"/>/events-created/in-future/date/events/intersect
                                        </td>
                                        <td width="40px">
                                            <button type="submit" name="gsearchpg_go" value="events10">GO</button>
                                        </td>
                                    </tr>
                                </table>
                            </fieldset>
                        </form>
                        <br/>
                        <form action="${pageContext.request.contextPath}/FbGsearch" target="_blank">
                            <fieldset style="border-width: 1px">
                                <legend><b>Events at which "<i style="color: #2980B9">user</i>" was invited</b></legend>
                                <table width="100%">
                                    <tr>
                                        <td>
                                            https://www.facebook.com/search/<input type="number" name="events_field11" placeholder="profile id" style="color: #2980B9"/>/events
                                        </td>
                                        <td width="40px">
                                            <button type="submit" name="gsearchpg_go" value="events11">GO</button>
                                        </td>
                                    </tr>
                                </table>
                            </fieldset>
                        </form>
                        <br/>
                        <form action="${pageContext.request.contextPath}/FbGsearch" target="_blank">
                            <fieldset style="border-width: 1px">
                                <legend><b>Events that were attended by "<i style="color: #2980B9">user</i>"</b></legend>
                                <table width="100%">
                                    <tr>
                                        <td>
                                            https://www.facebook.com/search/<input type="number" name="events_field12" placeholder="profile id" style="color: #2980B9"/>/events-joined/in-past/date/events/intersect
                                        </td>
                                        <td width="40px">
                                            <button type="submit" name="gsearchpg_go" value="events12">GO</button>
                                        </td>
                                    </tr>
                                </table>
                            </fieldset>
                        </form>
                        <br/>
                        <form action="${pageContext.request.contextPath}/FbGsearch" target="_blank">
                            <fieldset style="border-width: 1px">
                                <legend><b>Events that will be attended by "<i style="color: #2980B9">user</i>"</b></legend>
                                <table width="100%">
                                    <tr>
                                        <td>
                                            https://www.facebook.com/search/<input type="number" name="events_field13" placeholder="profile id" style="color: #2980B9"/>/events-joined/in-future/date/events/intersect
                                        </td>
                                        <td width="40px">
                                            <button type="submit" name="gsearchpg_go" value="events13">GO</button>
                                        </td>
                                    </tr>
                                </table>
                            </fieldset>
                        </form>
                        <br/>
                        <form action="${pageContext.request.contextPath}/FbGsearch" target="_blank">
                            <fieldset style="border-width: 1px">
                                <legend><b>Common events between "<i style="color: #2980B9">user</i>" and "<i style="color: #2980B9">user</i>"</b></legend>
                                <table width="100%">
                                    <tr>
                                        <td>
                                            https://www.facebook.com/search/<input type="number" name="events_field14.1" placeholder="profile id" style="color: #2980B9"/>/events/<input type="number" name="events_field14.2" placeholder="profile id" style="color: #2980B9"/>/events/intersect
                                        </td>
                                        <td width="40px">
                                            <button type="submit" name="gsearchpg_go" value="events14">GO</button>
                                        </td>
                                    </tr>
                                </table>
                            </fieldset>
                        </form>
                    </c:when>
                    <c:when test="${gsearcgpgoption == 'Friendship'}">
                        <form action="${pageContext.request.contextPath}/FbGsearch" target="_blank">
                            <fieldset style="border-width: 1px">
                                <legend><b>Friendship between "<i style="color: #2980B9">user</i>" and "<i style="color: #2980B9">user</i>"</b></legend>
                                <table width="100%">
                                    <tr>
                                        <td>
                                            https://www.facebook.com/friendship/<input type="number" name="friendship_field1.1" placeholder="profile id" style="color: #2980B9"/>/<input type="number" name="friendship_field1.2" placeholder="profile id" style="color: #2980B9"/>
                                        </td>
                                        <td width="40px">
                                            <button type="submit" name="gsearchpg_go" value="friendship1">GO</button>
                                        </td>
                                    </tr>
                                </table>
                            </fieldset>
                        </form>
                        <br/>
                        <form action="${pageContext.request.contextPath}/FbGsearch" target="_blank">
                            <fieldset style="border-width: 1px">
                                <legend><b>Mutual friend list of "<i style="color: #2980B9">user</i>" and "<i style="color: #2980B9">user</i>"</b></legend>
                                <table width="100%">
                                    <tr>
                                        <td>
                                            https://www.facebook.com/browse/mutual_friends/?uid=<input type="number" name="friendship_field5.1" placeholder="profile id" style="color: #2980B9"/>&node=<input type="number" name="friendship_field5.2" placeholder="profile id" style="color: #2980B9"/>
                                        </td>
                                        <td width="40px">
                                            <button type="submit" name="gsearchpg_go" value="friendship5">GO</button>
                                        </td>
                                    </tr>
                                </table>
                            </fieldset>
                        </form>
                        <br/>
                        <form action="${pageContext.request.contextPath}/FbGsearch" target="_blank">
                            <fieldset style="border-width: 1px">
                                <legend><b>Common friends of "<i style="color: #2980B9">user</i>" and "<i style="color: #2980B9">user</i>"</b></legend>
                                <table width="100%">
                                    <tr>
                                        <td>
                                            https://www.facebook.com/search/<input type="number" name="friendship_field2.1" placeholder="profile id" style="color: #2980B9"/>/friends/<input type="number" name="friendship_field2.2" placeholder="profile id" style="color: #2980B9"/>/friends
                                        </td>
                                        <td width="40px">
                                            <button type="submit" name="gsearchpg_go" value="friendship2">GO</button>
                                        </td>
                                    </tr>
                                </table>
                            </fieldset>
                        </form>
                        <br/>
                        <form action="${pageContext.request.contextPath}/FbGsearch" target="_blank">
                            <fieldset style="border-width: 1px">
                                <legend><b>Common friends of "<i style="color: #2980B9">user</i>", "<i style="color: #2980B9">user</i>" and "<i style="color: #2980B9">user</i>"</b></legend>
                                <table width="100%">
                                    <tr>
                                        <td>
                                            https://www.facebook.com/search/<input type="number" name="friendship_field3.1" placeholder="profile id" style="color: #2980B9"/>/friends/<input type="number" name="friendship_field3.2" placeholder="profile id" style="color: #2980B9"/>/friends/<input type="number" name="friendship_field3.3" placeholder="profile id" style="color: #2980B9"/>/friends/intersect
                                        </td>
                                        <td width="40px">
                                            <button type="submit" name="gsearchpg_go" value="friendship3">GO</button>
                                        </td>
                                    </tr>
                                </table>
                            </fieldset>
                        </form>
                        <br/>
                        <form action="${pageContext.request.contextPath}/FbGsearch" target="_blank">
                            <fieldset style="border-width: 1px">
                                <legend><b>Common friends of "<i style="color: #2980B9">user</i>", "<i style="color: #2980B9">user</i>", "<i style="color: #2980B9">user</i>" and "<i style="color: #2980B9">user</i>"</b></legend>
                                <table width="100%">
                                    <tr>
                                        <td>
                                            https://www.facebook.com/search/<input type="number" name="friendship_field4.1" placeholder="profile id" style="color: #2980B9"/>/friends/<input type="number" name="friendship_field4.2" placeholder="profile id" style="color: #2980B9"/>/friends/<input type="number" name="friendship_field4.3" placeholder="profile id" style="color: #2980B9"/>/friends/<input type="number" name="friendship_field4.4" placeholder="profile id" style="color: #2980B9"/>/friends/intersect
                                        </td>
                                        <td width="40px">
                                            <button type="submit" name="gsearchpg_go" value="friendship4">GO</button>
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
