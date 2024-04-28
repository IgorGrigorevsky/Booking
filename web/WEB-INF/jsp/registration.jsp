<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 28.04.2024
  Time: 15:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<form action="/registration" method="post"></form>
<%-- с помощью тега <label> мы ассоциируем <input> name--%>
<%--используем атрибут for для ассоциации с помощью id - это необязательно, но желательно --->
т.е в for мы ссылаемся на id нашего input'а--%>
<label for="nameId">Name:
    <input type="text" name="userName" id="nameId">
</label>
<br>

<label for="birthdayId">Birthday:
    <input type="date" name="birthday" id="birthdayId">
</label>
<br>

<label for="emailId">Email:
    <input type="text" name="email" id="emailId">
</label>
<br>

<label for="passwordId">Password:
    <input type="password" name="password" id="passwordId">
</label>
<br>

<%--для создания выпадающего списка тег <select>--%>
<%-- значение нам передастся с параметром, который мы указали в select. Например, так будет выглядеть ?role=USER
т.е. берется role от тега <select>, а value - из option'а, который мы выбрали в выпадающем списке--%>
<select name="role" id="roleId">
    <c:forEach var="role" items="${requestScope.roles}">
        <option value="${role}"> ${role}</option>
    </c:forEach>
</select>
<br>

<c:forEach var="gender" items="${requestScope.gender}">
    <input type="radio" name="gender" value="${gender}"> ${gender}
</c:forEach>
<br>

<%--кнопака для отправки формы на сервер по вышеуказанному url--%>
<button type="submit"> Send</button>

</body>
</html>
