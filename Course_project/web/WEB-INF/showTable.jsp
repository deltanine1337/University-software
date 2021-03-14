<%--
  Created by IntelliJ IDEA.
  User: Глеб
  Date: 07.04.2020
  Time: 20:50
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="dao.*, Tables.*" %>
<html>
<head>
    <title>Show table</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
<p><a href="/"><input name="backButton" type="button" value="В главное меню"></a></p>
<p></p>
<c:choose>
    <c:when test="${param.table=='developer'}">
        <table>
            <tr><th>ID</th><th>Название разработчика</th><th>Действие</th></tr>
            <c:forEach var="developer" items="${developers}">
                <tr><td>${developer.developerId}</td>
                    <td>${developer.name}</td>
                    <td>
                        <a href="/changeForm?table=developer&id=${developer.developerId}"><input name="changeDeveloperButton" type="button" value="C"></a>
                        <a href="/deleteForm?table=developer&id=${developer.developerId}"><input name="deleteDeveloperButton" type="button" value="-"></a>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <p><a href="/insertForm?table=developer"><input name="insertDeveloperButton" type="button" value="Добавить"></a></p>
    </c:when>
    <c:when test="${param.table=='softwareType'}">
        <table>
            <tr><th>ID</th><th>Название типа ПО</th><th>Действие</th></tr>
            <c:forEach var="softwareType" items="${softwareTypes}">
                <tr><td>${softwareType.typeId}</td>
                    <td>${softwareType.name}</td>
                    <td>
                        <a href="/changeForm?table=softwareType&id=${softwareType.typeId}"><input name="changesoftwareTypeButton" type="button" value="C"></a>
                        <a href="/deleteForm?table=softwareType&id=${softwareType.typeId}"><input name="deletesoftwareTypeButton" type="button" value="-"></a>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <p><a href="/insertForm?table=softwareType"><input name="insertsoftwareTypeButton" type="button" value="Добавить"></a></p>
    </c:when>
    <c:when test="${param.table=='software'}">
        <table>
            <tr><th>ID</th><th>Название ПО</th><th>Версия ПО</th><th>Количество лицензий</th><th>Дата поступления</th><th>Тип ПО</th><th>Разработчик</th><th>Действие</th></tr>
            <c:forEach var="software" items="${software}">
                <tr><td>${software.softwareId}</td>
                    <td>${software.name}</td>
                    <td>${software.version}</td>
                    <td>${software.numOfLic}</td>
                    <td>${software.admissionDate}</td>
                    <td>${SoftwareTypeDAO.getSoftwareTypeById(software.typeId).getName()}</td>
                    <td>${DeveloperDAO.getDeveloperById(software.developerId).getName()}</td>
                    <td>
                        <a href="/changeForm?table=software&id=${software.softwareId}"><input name="changeSoftwareButton" type="button" value="C"></a>
                        <a href="/deleteForm?table=software&id=${software.softwareId}"><input name="deleteSoftwareButton" type="button" value="-"></a>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <p><a href="/insertForm?table=software"><input name="insertSoftwareButton" type="button" value="Добавить"></a></p>
    </c:when>
    <c:when test="${param.table=='spec'}">
        <table>
            <tr><th>ID</th><th>Название характеристики</th><th>Действие</th></tr>
            <c:forEach var="spec" items="${specs}">
                <tr><td>${spec.specId}</td>
                    <td>${spec.name}</td>
                    <td>
                        <a href="/changeForm?table=spec&id=${spec.specId}"><input name="changeSpecButton" type="button" value="C"></a>
                        <a href="/deleteForm?table=spec&id=${spec.specId}"><input name="deleteSpecButton" type="button" value="-"></a>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <p><a href="/insertForm?table=spec"><input name="insertSpecButton" type="button" value="Добавить"></a></p>
    </c:when>
    <c:when test="${param.table=='discipline'}">
        <table>
            <tr><th>ID</th><th>Название дисциплины</th><th>Действие</th></tr>
            <c:forEach var="discipline" items="${disciplines}">
                <tr><td>${discipline.disciplineId}</td>
                    <td>${discipline.name}</td>
                    <td>
                        <a href="/changeForm?table=discipline&id=${discipline.disciplineId}"><input name="changeDisciplineButton" type="button" value="C"></a>
                        <a href="/deleteForm?table=discipline&id=${discipline.disciplineId}"><input name="deleteDisciplineButton" type="button" value="-"></a>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <p><a href="/insertForm?table=discipline"><input name="insertDisciplineButton" type="button" value="Добавить"></a></p>
    </c:when>
    <c:when test="${param.table=='specSoftware'}">
        <table>
            <tr><th>Характеристика</th><th>Программное обеспечение</th><th>Значение</th><th>Действие</th></tr>
            <c:forEach var="specSoftware" items="${specSoftwares}">
                <tr><td>${SpecDAO.getSpecById(specSoftware.specId).getName()}</td>
                    <td>${SoftwareDAO.getSoftwareById(specSoftware.softwareId).getName()}</td>
                    <td>${Specs_SoftwareDAO.withoutNullAfterDot(specSoftware.value)}</td>
                    <td>
                        <a href="/changeForm?table=specSoftware&id1=${specSoftware.specId}&id2=${specSoftware.softwareId}"><input name="changeSpecSoftwareButton" type="button" value="C"></a>
                        <a href="/deleteForm?table=specSoftware&id1=${specSoftware.specId}&id2=${specSoftware.softwareId}"><input name="deleteSpecSoftwareButton" type="button" value="-"></a>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <p><a href="/insertForm?table=specSoftware"><input name="insertSpecSoftwareButton" type="button" value="Добавить"></a></p>
    </c:when>
    <c:when test="${param.table=='softwareDiscipline'}">
        <table>
            <tr><th>Программное обеспечение</th><th>Дисциплина</th></tr>
            <c:forEach var="softwareDiscipline" items="${softwareDiscipline}">
                <tr><td>${SoftwareDAO.getSoftwareById(softwareDiscipline.softwareId).getName()}</td>
                    <td>${DisciplineDAO.getDisciplinerById(softwareDiscipline.disciplineId).getName()}</td>
                    <td>
                        <a href="/deleteForm?table=softwareDiscipline&id1=${softwareDiscipline.softwareId}&id2=${softwareDiscipline.disciplineId}"><input name="deleteSoftwareDisciplineButton" type="button" value="-"></a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    <p><a href="/insertForm?table=softwareDiscipline"><input name="insertSoftwareDisciplineButton" type="button" value="Добавить"></a></p>
    </c:when>
    <c:otherwise>
        <h1>Таблицы не существует.</h1>
    </c:otherwise>
</c:choose>

</body>
</html>
