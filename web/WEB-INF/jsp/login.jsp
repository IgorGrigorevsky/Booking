<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 02.05.2024
  Time: 17:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<%--все формы с логином и паролем обязаны передаваться методом POST, т.к. по умолчанию формы
 передаются методом GET. Мы не должны передавать пароли и логины в URL (через ?), они должны
  быть в теле нашего запроса, чтобы в последующем их можно было зашифровать, например с помощью https--%>
<form action="${pageContext.request.contextPath}/login" method="post">
    <br>
    <span style="color: blue"> Пора взять в руки записную книжку с паролем ;) </span>
    <br>
    <label for="emailId">Email

        <%--добавляя строку value="${param.email}" - если введены неверные логин и пароль,
         после переброски на данную страницу, пользователь н ебудет повторно вводить email, мы
          его возьмем из параметров--%>
        <input type="text" name="email" id="emailId" value="${param.email}" required>
    </label>
    <br>
    <label for="passwordId">Password
        <input type="password" name="password" id="passwordId" required>
    </label>

    <button type="Submit">Отправить</button>
    <br>
    <br>
    <br>
<span style="color: blue">   Все таки нужно зарегистрироваться, жми сюда =)</span>
    <a href="http://localhost:8080/">
        <button type="button">Register</button>
    </a>

    <%--    добавляем для неверного логирования--%>

    <c:if test="${param.error != null}">
        <div style="color: crimson"> email or password is not correct!</div>
    </c:if>
</form>

</body>
</html>
