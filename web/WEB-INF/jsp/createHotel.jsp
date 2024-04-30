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
<br>
<span>Заполните информацию об отеле =)</span>
<br>
<br>
<form action="/createHotel" method="post">
<%-- с помощью тега <label> мы ассоциируем <input> name--%>
<%--используем атрибут for для ассоциации с помощью id - это необязательно, но желательно --->
т.е в for мы ссылаемся на id нашего input'а--%>
<label for="nameId">Название отеля:
    <input type="text" name="hotelName" id="nameId">
</label>
<br>
<br>

<label for="addressId">Адрес отеля:
    <input type="text" name="address" id="addressId">
</label>
<br>
<br>

<label for="emailId">Email:
    <input type="text" name="email" id="emailId">
</label>
<br>
<br>

<label for="phoneNumberId">Телефонный номер:
    <input type="text" name="phone_number" id="phoneNumberId">
</label>
<br>
<br>
<%--для создания выпадающего списка тег <select>--%>
<%-- значение нам передастся с параметром, который мы указали в select. Например, так будет выглядеть ?role=USER
т.е. берется role от тега <select>, а value - из option'а, который мы выбрали в выпадающем списке--%>
<span>Укажите количество звезд отеля</span>
<select name="stars" id="starsId">
    <c:forEach var="star" items="${requestScope.stars}">
        <option value="${star}">${star} </option>
    </c:forEach>
</select>
<br>
<br>
<%--кнопака для отправки формы на сервер по вышеуказанному url--%>
<button type="submit"> Отправить заявку</button>
</form>
</body>
</html>