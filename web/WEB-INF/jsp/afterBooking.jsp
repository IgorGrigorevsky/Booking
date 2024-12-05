<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 17.05.2024
  Time: 17:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="addHeaders.jsp" %>
<html>
<head>
    <title> Ваша заявка на бронирование отправлена =) </title>
</head>
<body>
<span>Пожалуйста, ожидайте, когда с вами свяжется менеджер отеля для подтверждения бронирования и оплаты =)</span>

<form action="${pageContext.request.contextPath}/afterBooking" method="get">
    <span> Для возврата к списку номеров, нажмите на кнопку
        <button type="submit"> OK </button>
    </span>
</form>
</body>
</html>
