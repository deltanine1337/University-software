<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Глеб
  Date: 13.04.2020
  Time: 23:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Удаление...</title>
    <link rel="stylesheet" href="styles.css">
<c:choose>
    <c:when test="${param.table=='developer'}">
        <meta http-equiv="refresh" content="1; url=/showTable?table=developer">
    </c:when>
    <c:when test="${param.table=='discipline'}">
        <meta http-equiv="refresh" content="1; url=/showTable?table=discipline">
    </c:when>
    <c:when test="${param.table=='software'}">
        <meta http-equiv="refresh" content="1; url=/showTable?table=software">
    </c:when>
    <c:when test="${param.table=='softwareDiscipline'}">
        <meta http-equiv="refresh" content="1; url=/showTable?table=softwareDiscipline">
    </c:when>
    <c:when test="${param.table=='spec'}">
        <meta http-equiv="refresh" content="1; url=/showTable?table=spec">
    </c:when>
    <c:when test="${param.table=='specSoftware'}">
        <meta http-equiv="refresh" content="1; url=/showTable?table=specSoftware">
    </c:when>
    <c:when test="${param.table=='softwareType'}">
        <meta http-equiv="refresh" content="1; url=/showTable?table=softwareType">
    </c:when>
</c:choose>

</head>
<body>
    <h3>Происходит удаление...</h3>
</body>
</html>
