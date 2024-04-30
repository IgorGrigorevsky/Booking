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
<div>
    <span>Заполните информацию об отеле =)</span>
</div>

<form action="/createHotel" method="post">
    <div>
        <br>
        <%-- с помощью тега <label> мы ассоциируем <input> name--%>
        <%--используем атрибут for для ассоциации с помощью id - это необязательно, но желательно --->
        т.е в for мы ссылаемся на id нашего input'а--%>
        <label for="nameId">Название отеля:
            <input type="text" name="hotelName" id="nameId">
        </label>
            <span>Укажите название отеля, например: Метрополис</span>
        <br>
    </div>

    <div>
        <br>
        <label for="addressId">Адрес отеля:
            <input type="text" name="address" id="addressId">
        </label>
        <br>
        <span>Укажите адрес отеля</span>
        <br>
        <span>например: г. Москва, ул. Пятницкая, д. 3 (при наличии добавьте - корп. 1</span>
        <br>
    </div>

    <div>
        <br>
        <label for="emailId">Email:
            <input type="text" name="email" id="emailId">
        </label>
        <br>
        <span>Укажите электронную почту, например: java@gmail.com</span>
        <br>
    </div>

    <div>
        <br>
        <label for="phoneNumberId">Телефонный номер:
            <input type="text" name="phoneNumber" id="phoneNumberId">
        </label>
        <br>
        <span>Укажите телефонный номер, например: 79101234567 или 74953456789</span>
        <br>
    </div>

    <%--для создания выпадающего списка тег <select>--%>
    <%-- значение нам передастся с параметром, который мы указали в select. Например, так будет выглядеть ?role=USER
    т.е. берется role от тега <select>, а value - из option'а, который мы выбрали в выпадающем списке--%>
    <span>Укажите количество звезд отеля от 1 до 5</span>
    <br>
    <select name="stars" id="starsId">
        <c:forEach var="star" items="${requestScope.stars}">
            <option value="${star}">${star} </option>
        </c:forEach>
    </select>
    <br>
    <br>
    <%--кнопака для отправки формы на сервер по вышеуказанному url--%>
    <button type="submit">Отправить заявку</button>
</form>
</body>
</html>