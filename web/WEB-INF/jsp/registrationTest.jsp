<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 29.04.2024
  Time: 15:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
  <%--    задаем каждый из выпадающих элементов из списка с помощью тега <option>--%>
  <option value="USER"> USER</option>
  <option value="ADMIN"> ADMIN</option>
</select>
<br>

<%--name - ключ нашего параметра, value - значение нашего параметра--%>
<input type="radio" name="gender" value="MALE"> Male
<br>
<input type="radio" name="gender" value="FEMALE"> Female
<br>

<%--кнопака для отправки формы на сервер по вышеуказанному url--%>
<button type="submit"> Send</button>

</body>
</html>
