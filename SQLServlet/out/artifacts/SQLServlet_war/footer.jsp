<%@ page import="org.springframework.context.support.ClassPathXmlApplicationContext" %>
<%@ page import="sqlcmd.ClearAllData" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" type="text/css" href="css/style.css" media="all">
<p><a href="/menu.jsp">Menu</a><br></p>
<p id="footer">Copyright 2015 Hunkbl4:)</p>

<%ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"config.xml"});%>
<%ClearAllData clearAllData = (ClearAllData) context.getBean("clear");%>
<p><%clearAllData.clear();%></p>