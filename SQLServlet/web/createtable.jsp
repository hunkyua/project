<%--
  Created by IntelliJ IDEA.
  User: Hunky
  Date: 12.11.2015
  Time: 13:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<link rel="stylesheet" type="text/css" href="css/style.css" media="all">

<head>
    <title>CreateTable</title>
</head>
<body>
<table border="1" align="center">

  <form action="createTable" method="get">
    <tr>
      <td>Name of table:</td>
      <td><input name ='create_table' type="text"/></td>
    </tr>
    <tr>
      <td></td>
      <td align="center"><input type="submit" value="create"/></td>
    </tr>
  </form>

</table>

<p id="error">${doesNotExist}</p>
<p><a href="/connect.jsp" style="color: crimson">${error}</a></p>
<%@include file="footer.jsp" %>
</body>
</html>
