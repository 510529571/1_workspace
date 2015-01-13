<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>c标签测试</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

	</head>

	<body>
		<c:set var="name" value="胡寒伟">
		</c:set>
		<c:out value="${name}"></c:out>

		<c:forEach var="i" begin="1" end="10" step="1">
			<c:out value="${i}"></c:out>
		</c:forEach>
		<c:set var="i" value="10"></c:set>
		<c:choose>
			<c:when test="${i<10}">
		小于10
		</c:when>
			<c:when test="${i==10}">
		等于10
		</c:when>
			<c:when test="${i>10}">
		等于10
		</c:when>
		</c:choose>

		<c:if test="${i==10}">
		i等于10
		</c:if>

		<c:catch var="exception"></c:catch>
	</body>
</html>
