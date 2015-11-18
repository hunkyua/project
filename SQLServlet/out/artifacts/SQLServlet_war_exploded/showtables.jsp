<%@ page import="sqlcmd.command.table.TableNames" %>
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
    <title>showtables</title>
</head>
<body>
<table border="1" align="center">

  <form action="showtables" method="get">
    <tr>
      <td>Name of tables:</td>
      <td><p align="center"><%=TableNames.toString(TableNames.tables)%></p></td>
    </tr>
    <tr>
      <td></td>
      <td align="center"><input type="submit" value="Show tables"/></td>
    </tr>
  </form>
</table>



<p id="error"><%=TableNames.doesNotExist%></p>
<p id="error"><a href="/connect.jsp"><span class="error"><%=TableNames.error%></span></a> </p>
<%@include file="footer.jsp" %>
</body>
</html>
