<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 02.05.2024
  Time: 17:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- добавляем данный тег, у нас появляется возможность использовать другие теги,
в том числе и интернационализацию--%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<%@ include file="addHeaders.jsp" %>
<%--все формы с логином и паролем обязаны передаваться методом POST, т.к. по умолчанию формы
 передаются методом GET. Мы не должны передавать пароли и логины в URL (через ?), они должны
  быть в теле нашего запроса, чтобы в последующем их можно было зашифровать, например с помощью https--%>
<form action="${pageContext.request.contextPath}/login" method="post">
    <br>
    <%-- Было - <span style="color: blue"> Пора взять в руки записную книжку с паролем ;) </span>--%>
    <span style="color: blue"> <fmt:message key="page.login.start"/> </span>
    <br>
    <%-- Было - <label for="emailId">Email--%>
    <%--    <fmt:message bundle=""> - нужен чтобы брать значение по ключу из разных bundle'ов--%>
    <label for="emailId"><fmt:message key="page.login.email"/>:

        <%--добавляя строку value="${param.email}" - если введены неверные логин и пароль,
         после переброски на данную страницу, пользователь н ебудет повторно вводить email, мы
          его возьмем из параметров--%>
        <input type="text" name="email" id="emailId" value="${param.email}" required>
    </label>
    <br>
    <%-- Было - <label for="passwordId">Password--%>
    <label for="passwordId"> <fmt:message key="page.login.password"/>:
        <input type="password" name="password" id="passwordId" required>
    </label>

    <%-- Было - <button type="Submit">Отправить</button>--%>
    <button type="Submit"><fmt:message key="page.login.submit.button"/></button>
    <br>
    <br>
    <br>
    <%-- Было - <span style="color: blue">   Все таки нужно зарегистрироваться, жми сюда =)</span>--%>
    <span style="color: blue"> <fmt:message key="page.login.start1"/> </span>
    <a href="http://localhost:8080/createPersonInfo">
        <%-- Было - <button type="button">Register</button>--%>
        <button type="button"><fmt:message key="page.login.register.button"/></button>
    </a>

    <%--    добавляем для неверного логирования--%>

    <c:if test="${param.error != null}">
        <%-- Было - <div style="color: crimson"> email or password is not correct!</div>--%>
        <div style="color: crimson"><fmt:message key="page.login.error"/></div>
    </c:if>
</form>

</body>
</html>
