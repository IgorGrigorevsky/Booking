<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 30.04.2024
  Time: 19:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<form action="/createPersonInfo" method="post">
    <span>Пора рассказать немного о себе..=)</span>
    <div>
        <br>
        <%-- с помощью тега <label> мы ассоциируем <input> name--%>
        <%--используем атрибут for для ассоциации с помощью id - это необязательно, но желательно --->
        т.е в for мы ссылаемся на id нашего input'а--%>
        <label for="nameId">Укажите Full name:
            <input type="text" name="fullName" id="nameId">
        </label>
        <span>например: Петр Петрович"</span>
    </div>
    <br>
    <div>
        <br>
        <label for="phoneNumberId">Укажите телефонный номер:
            <input type="text" name="phoneNumber" id="phoneNumberId">
        </label>
        <br>
        <span>например: 89101234567 </span>
    </div>
    <br>
    <div>
        <br>
        <label for="emailId">Укажите email:
            <input type="text" name="email" id="emailId">
        </label>
        <span>например: java@gmail.com</span>
        <br>
    </div>
    <br>
    <%--для создания выпадающего списка тег <select>--%>
    <%-- значение нам передастся с параметром, который мы указали в select. Например, так будет выглядеть ?role=USER
    т.е. берется role от тега <select>, а value - из option'а, который мы выбрали в выпадающем списке--%>

    <%--кнопака для отправки формы на сервер по вышеуказанному url--%>
    <button type="submit">Отправить форму</button>
</form>
</body>
</html>