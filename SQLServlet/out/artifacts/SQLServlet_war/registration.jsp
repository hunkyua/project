<%@ page import="sqlcmd.command.db.CreateUser" %>
<%--
  Created by IntelliJ IDEA.
  User: Hunky
  Date: 17.11.2015
  Time: 13:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<link rel="stylesheet" type="text/css" href="css/connectpage_style.css" media="all">
<head>
  <title>createuser</title>
</head>
<body>
<table border="1" align="center">
  <form action="createuser" method="post">
    <tr>
      <td>DBname</td>
      <td><input name ='db_name' type="text"/></td>
    </tr>

    <tr>
      <td>Username</td>
      <td><input name ='db_user' type="text"/></td>
    </tr>
    <tr>
      <td>Password</td>
      <td><input name ='db_password' type="password"/></td>
    </tr>
    <tr>
      <td></td>
      <td align="center"><input type="submit" value="create"/></td>
    </tr>
  </form>
</table>

<p id="error"><%=CreateUser.error%></p>
<p><a href="index.jsp">Back</a></p>
<p>Copyright 2015 Hunkbl4:)</p>
</body>
</html>
