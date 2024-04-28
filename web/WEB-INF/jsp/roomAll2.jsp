<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 28.04.2024
  Time: 14:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1> Список комнат </h1>

<c:forEach var="room" items="${requestScope.roomsList}">

    <li>
            <%--                   ${room.toString()}--%>
        <br> {Номер отеля:
        <a href="${pageContext.request.contextPath}/getRoomById2?roomId=${room.getId()}"> | значение id
            отеля: ${room.getId()} |"</a>
    </li>
</c:forEach>
</body>
</html>
