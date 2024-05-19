<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 06.05.2024
  Time: 14:53
  To change this template use File | Settings | File Templates.
--%>
<body>
<html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--берем из сессии sessionScope нашего пользователя - если не равно null, то отображаем форму--%>
<div>
    <div id="logout">
        <c:if test="${not empty sessionScope.personInfo}">
            <form action="/logout" method="post">
                <button type="submit">Logout</button>
            </form>
        </c:if>
    </div>

    <div id="needBook">
        <c:if test="${not empty sessionScope.isClient}">
            <form action="/needToBook" method="get">
                <button type="submit">Need to book</button>
            </form>
        </c:if>
    </div>

    <div id="deleteUser">
        <c:if test="${not empty sessionScope.personInfo && empty sessionScope.isClient}">
            <form action="/deleteUser" method="post">
                <button type="submit">Delete account</button>
            </form>
        </c:if>
    </div>

    <div id="createHotel">
        <c:if test="${not empty sessionScope.personInfo && not empty sessionScope.isClient}">
            <form action="/createHotel" method="get">
                <button type="submit">Create hotel</button>
            </form>
        </c:if>
    </div>

    <div id="locale">
        <form action="/locale" method="post">
            <button type="submit" name="lang" value="ru_RU">RU</button>
            <button type="submit" name="lang" value="en_US">EN</button>
        </form>
    </div>

    <fmt:setLocale
            value="${sessionScope.lang != null ? sessionScope.lang : (param.lang != null ? param.lang : 'en_US')}"/>
    <fmt:setBundle basename="translation"/>
</div>