<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 19.05.2024
  Time: 18:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%@ include file="addHeaders.jsp" %>

<h1> Список заявок на бронирование </h1>

<c:forEach var="book" items="${requestScope.bookList}">

    <li>
        <br> {Бронирование:
        <button onclick="window.location.href = '/confirmBooking?id=${book.getId()}'"><fmt:message key="page.logout.confirm.booking"/></button>
        <br> ${book.toString()}
        </a>
        <br>
        </form>
    </li>
</c:forEach>
</body>
</html>
