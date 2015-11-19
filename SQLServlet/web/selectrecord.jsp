<%@ page import="sqlcmd.command.crud.SelectRecord" %>

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
  <title>selectrecord</title>
</head>
<body>
<table border="1" align="center" cellpadding="5">
  <form action="selectrecord" method="post">
    <tr>
      <td>Name of table</td>
      <td><input name ='table_name' type="text"/></td>
    </tr>
    <tr>
      <td>Select</td>
      <td><input name ='select' type="text" value="*"/></td>
    </tr>
    <tr>
      <td></td>
      <td><input type="submit" value="select"/></td>
    </tr>
  </form>
</table>


<table border="1" align="center" cellpadding="5">
  <tr>
     <c:forEach items="${list}" var="line" >
       ${line}
     </c:forEach>
  </tr>
</table>


<p id="error">${doesNotExist}</p>
<p><a href="/connect.jsp" style="color: crimson">${error}</a></p>
<%@include file="footer.jsp" %>
</body>
</html>
