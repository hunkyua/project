<%--
  Created by IntelliJ IDEA.
  User: Hunky
  Date: 12.11.2015
  Time: 13:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<link rel="stylesheet" type="text/css" href="css/index_style.css" media="all">
<head>
    <title>Menu</title>
</head>
<body>
<h1>Menu: Please choice action!!!</h1>
<a href="/createtable.jsp">1: Create table</a><br>
<a href="/deletetable.jsp">2: Delete table</a><br>
<a href="/insertrecord.jsp">3: Insert record in table</a><br>
<a href="/updaterecord.jsp">4: Update record in table</a><br>
<a href="/deleterecord.jsp">5: Delete record in table</a><br>
<a href="/selectrecord.jsp">6: Select record in table</a><br>
<a href="/showtables.jsp">7: Show all tables in database</a><br>
<a href="/tablesize.jsp">8: Show size tables in database</a><br>
<a href="/index.jsp">9: Exit</a>
<%@include file="footer.jsp" %>
</body>
</html>
