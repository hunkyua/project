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
  <title>Registration</title>
</head>
<body>
<table border="1" align="center">
  <form action="registration" method="post">
    <tr>
      <td>DBname</td>
      <td><input name ='dbName' type="text"/></td>
    </tr>

    <tr>
      <td>Username</td>
      <td><input name ='user' type="text"/></td>
    </tr>
    <tr>
      <td>Password</td>
      <td><input name ='password' type="password"/></td>
    </tr>
    <tr>
      <td></td>
      <td align="center"><input type="submit" value="registration"/></td>
    </tr>
  </form>
</table>

<p style="color: crimson" id="message">${message}</p>
<p><a href="index.jsp">Back</a></p>
<p>Copyright 2015 Hunkbl4:)</p>
</body>
</html>
