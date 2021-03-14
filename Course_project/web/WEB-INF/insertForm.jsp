<%--
  Created by IntelliJ IDEA.
  User: Глеб
  Date: 11.04.2020
  Time: 23:43
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Insert new</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
<c:choose>
    <c:when test="${param.table=='developer'}">
        <form method="post">
            <p>Название разработчика:<input maxlength="20" name="devName" type="text"></p>
            <p><a href="/showTable?table=developer"><input name="addButton" type="submit" value="Добавить"></a></p>
        </form>
    </c:when>
    <c:when test="${param.table=='softwareType'}">
        <form method="post">
            <p>Название вида ПО:<input maxlength="35" name="softwareTypeName" type="text"></p>
            <p><a href="/showTable?table=softwareType"><input name="addButton" type="submit" value="Добавить"></a></p>
        </form>
    </c:when>
    <c:when test="${param.table=='discipline'}">
        <form method="post">
            <p>Название дисциплины:<input maxlength="40" name="discName" type="text"></p>
            <p><a href="/showTable?table=discipline"><input name="addButton" type="submit" value="Добавить"></a></p>
        </form>
    </c:when>
    <c:when test="${param.table=='spec'}">
        <form method="post">
            <p>Название характеристики:<input maxlength="30" name="specName" type="text"></p>
            <p><a href="/showTable?table=spec"><input name="addButton" type="submit" value="Добавить"></a></p>
        </form>
    </c:when>
    <c:when test="${param.table=='softwareDiscipline'}">
        <form method="post">
            <p>Название ПО:<select id="softwareSelector" name="softwareSelector" size="1">
                <c:forEach var="software" items="${softwares}">
                    <option>${software.name}</option>
                </c:forEach>
            </select></p>
            <p>Название дисциплины:<select id="disciplineSelector" name="disciplineSelector" size="1">
                <c:forEach var="discipline" items="${disciplines}">
                    <option>${discipline.name}</option>
                </c:forEach>
            </select></p>
            <p><a href="/showTable?table=softwareDiscipline"><input name="addButton" type="submit" value="Добавить"></a></p>
        </form>
    </c:when>
    <c:when test="${param.table=='specSoftware'}">
        <form method="post">
            <p>Название характеристики:<select id="specSelector" name="specSelector" size="1">
                <c:forEach var="spec" items="${specs}">
                    <option>${spec.name}</option>
                </c:forEach>
            </select></p>
            <p>Название ПО:<select id="softwareSelector1" name="softwareSelector1" size="1">
                <c:forEach var="software" items="${softwares}">
                    <option>${software.name}</option>
                </c:forEach>
            </select></p>
            <p>Значение характеристики:<input name="sValue" type="text"></p>
            <p><a href="/showTable?table=softwareDiscipline"><input name="addButton" type="submit" value="Добавить"></a></p>
        </form>
    </c:when>
    <c:when test="${param.table=='software'}">
        <form method="post">
            <p>Название ПО:<input maxlength="30" name="softwareName" type="text"></p>
            <p>Версия ПО:<input maxlength="15" name="version" type="text"></p>
            <p>Количество лицензий:<input maxlength="3" name="numOfLic" type="number"></p>
            <p>Дата поступления на кафедру:<input name="admDate" type="date"></p>
            <p>Тип ПО:<select id="typeSelector" name="typeSelector" size="1">
                <c:forEach var="softwareType" items="${softwareTypes}">
                    <option>${softwareType.name}</option>
                </c:forEach>
            </select></p>
            <p>Название разработчика:<select id="devSelector" name="devSelector" size="1">
                <c:forEach var="developer" items="${developers}">
                    <option>${developer.name}</option>
                </c:forEach>
            </select></p>
            <p><a href="/showTable?table=software"><input name="addButton" type="submit" value="Добавить"></a></p>
        </form>
    </c:when>
    <c:otherwise>
        <h1>Something goes wrong</h1>
    </c:otherwise>
</c:choose>
</body>
</html>
