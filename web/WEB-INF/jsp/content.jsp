<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 26.04.2024
  Time: 15:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="header.jsp" %>
    <div>

        <%-- тег <span> - тег для текстовых элементов--%>
        <span>Content. Русский </span>

        <%-- тег <p> - обозначает абзац--%>
        <p> Size: ${requestScope.list.size()}</p>

        <%-- Лучше не делать get(0), т.к. это не NullSave, если коллекция будет пустой,
        то будет NullPointException--%>
        <%-- Лучше использовать второй способ - т.е. мы мыожем обращаться к элементам коллекции,
        будто обращаемся к элементам массива.--%>
        <%-- (!) но - если коллекция поддерживает доступ по index'у--%>
        <p> Id: ${requestScope.list.get(0).id}</p>
        <%-- Это уже NullSave, если объекта не будет, то он не отобразится и будет пустое значение--%>
        <p> Id2: ${requestScope.list[1].id}</p>
        <p> Map Id: ${sessionScope.listMap.get[1]}</p>
        <p> JSESSION Id: ${cookie["JSESSIONID"]}, unique identificator</p>
        <p> Header: ${header["Cookie"]}</p>
        <p> Param id: ${param.id}</p>
        <p> Param test: ${param.test}</p>


    </div>
    <%@ include file="footer.jsp" %>
</head>
<body>

</body>
</html>
