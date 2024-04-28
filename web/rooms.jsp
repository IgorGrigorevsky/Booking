<%@ page import="dao.DaoRoom" %>
<%@ page import="entity.Room" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 26.04.2024
  Time: 14:16
  To change this template use File | Settings | File Templates.
--%>

<%--первая строчка - директива. Их всего 3. Директива page - служит для установки каких-то мета-данных и значений.--%>
<%--В данном случае она  устанавливает нам кодировку для html-странички. Когда Jasper транслирует эту страничку,--%>
<%--он возьмет отсюда Content-type и установит его в наш response. --%>
<%--В случае language'а - планировали, что использоваться JSP странички будут не только в Java, --%>
<%--поэтому и добавили данный аттрибут.--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="index.html"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    List<Room> all = DaoRoom.getInstance().findAll();
    for (Room room : all) {
        out.write(String.format(" значение id номера: %d |" +
                                " значение id отеля: %d |" +
                                " количество кроватей: %d |" +
                                " этаж: %d |" +
                                " наличие завтрака:" +
                                " значение id класса комнаты: %d |" +
                                " цена: %d", room.getId(), room.getHotelId(), room.getBedsCount(),
                room.getFloor(), room.getClassId(), room.getPrice()));
    }
%>
</body>
</html>

для "declaration'ов"
<%!
    public  void jspInit() {
        System.out.println("Hello, world");
    }
%>
