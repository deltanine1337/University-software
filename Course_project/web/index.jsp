<%--
  Created by IntelliJ IDEA.
  User: Глеб
  Date: 07.04.2020
  Time: 15:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Главное меню</title>
    <link rel="stylesheet" href="styleForMain.css">
  </head>
  <body>
  <h3>Выберите таблицу, с которой необходима работа:</h3>
    <p><a href="/showTable?table=developer"><input name="developerButton" type="button" value="Разработчики"></a></p>
    <p><a href="/showTable?table=softwareType"><input name="softwareTypeButton" type="button" value="Вид ПО"></a></p>
    <p><a href="/showTable?table=software"><input name="softwareButton" type="button" value="Программное обеспечение"></a></p>
    <p><a href="/showTable?table=spec"><input name="specButton" type="button" value="Характеристика ПК"></a></p>
    <p><a href="/showTable?table=discipline"><input name="disciplineButton" type="button" value="Дисциплина"></a></p>
    <p><a href="/showTable?table=specSoftware"><input name="specSoftwareButton" type="button" value="Характеристика ПК &amp;&amp; ПО"></a></p>
    <p><a href="/showTable?table=softwareDiscipline"><input name="disciplineSoftwareButton" type="button" value="Дисциплина &amp;&amp; ПО"></a></p>
  </body>
</html>
