<%@ page import="sqlcmd.JDBCConnector" %>
<%@ page import="sqlcmd.command.table.TableSize" %>
<%--
  Created by IntelliJ IDEA.
  User: Hunky
  Date: 12.11.2015
  Time: 13:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<link rel="stylesheet" type="text/css" href="css/style.css" media="all">

<head>
  <title>tablesize</title>
</head>
<body>
<table border="1" align="center">

  <form action="tablesize" method="get">
    <tr>
      <td>Table name:</td>
      <td><input name ='name_table' type="text"/></td>
    </tr>
    <tr>
      <td></td>
      <td><input type="submit" value="select"/></td>
    </tr>
  </form>
</table>
<p><%=TableSize.size%></p>


<p id="error"><%=TableSize.doesNotExist%></p>
<p id="error"><a href="/connect.jsp"><span class="error"><%=TableSize.error%></span></a> </p>
<%@include file="footer.jsp" %>
</body>
</html>