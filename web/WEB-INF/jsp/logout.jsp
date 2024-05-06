<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 06.05.2024
  Time: 14:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--берем из сессии sessionScope нашего пользователя - если не равно null, то отображаем форму--%>
<div>
    <c:if test="${not empty sessionScope.personInfo}">
        <form action="/logout" method="post">
            <button type="submit">Logout</button>
        </form>
    </c:if>
</div>
