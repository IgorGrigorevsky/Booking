<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 28.04.2024
  Time: 14:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Имеющиеся комнаты</h1>
<ul>
    // проверяем пустая ли коллекция
    <c:if test="${not empty requestScope.list}">

        // проходимся циклом по коллекции
        <c:forEach var="room" items="${requestScope.list}">
            <li>
                // в нижний регистр, затем в верхний регистр
                    ${fn:toLowerCase(room.toString())}
                    ${fn:toUpperCase(room.toString())}
            </li>
        </c:forEach>
    </c:if>
</ul>
</body>
</html>
