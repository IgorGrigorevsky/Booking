<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 14.05.2024
  Time: 10:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- добавляем данный тег, у нас появляется возможность использовать другие теги,
в том числе и интернационализацию--%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<%@ include file="addHeaders.jsp" %>


<form action="${pageContext.request.contextPath}/createBooking" method="post">
    <label for="dateFromId"> <fmt:message key="page.login.dateFrom"/>:
        <input type="text" name="dateFrom" id="dateFromId" required>
    </label>
    <br>
    <label for="dateToId"> <fmt:message key="page.login.dateTo"/>:
        <input type="text" name="dateTo" id="dateToId" required>
    </label>
    <button type="Submit"><fmt:message key="page.login.submit.button"/></button>
</form>
</body>
</html>
