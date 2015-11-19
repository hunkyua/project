<%--
  Created by IntelliJ IDEA.
  User: Hunky
  Date: 12.11.2015
  Time: 13:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<link rel="stylesheet" type="text/css" href="css/style.css" media="all">
<head>
  <title>deleterecord</title>
</head>
<body>
<table border="1" align="center">
  <form action="deleterecord" method="post">
    <tr>
      <td>Name of table</td>
      <td><input name ='table_name' type="text"/></td>
    </tr>
    <tr>
      <td>Id</td>
      <td><input name ='id' type="number" value="0"/></td>
    </tr>
    <tr>
      <td></td>
      <td align="center"><input type="submit" value="delete"/></td>
    </tr>
  </form>
</table>
<p id="error">${doesNotExist}</p>
<p id="error"><a href="/connect.jsp">${error}</a></p>
<%@include file="footer.jsp" %>
</body>
</html>
