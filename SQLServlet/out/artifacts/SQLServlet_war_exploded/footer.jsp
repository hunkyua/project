<%@ page import="sqlcmd.ClearAllData" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" type="text/css" href="css/style.css" media="all">
<p><a href="/menu.jsp">Menu</a><br></p>
<p><%ClearAllData.clear();%></p>
<p align="center">Copyright 2015 Hunkbl4:)</p>