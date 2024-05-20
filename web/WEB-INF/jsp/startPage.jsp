<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 29.04.2024
  Time: 16:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="addHeaders.jsp" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<span>Здравствуйте, дорогой гость =)!</span>
<br>
<br>
<span>Если вы не зарегистрированы, пожалуйста, пройдите процедуру регистрации</span>
<br>

<form action="/login">
    <button type="submit">Вхоод</button>
</form>

<form action="${pageContext.request.contextPath}/createPersonInfo">
    <button type="submit">Регистрация</button>
</form>
<br>
<br>
<br>
<span>Для регистрации отеля в системе и сотрудничества с нами -> кнопочка ниже =)</span>
<form action="${pageContext.request.contextPath}/createHotel">
    <button type="submit">Регистрация отеля</button>
</form>
</body>
</html>
