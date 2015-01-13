<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<%@ taglib uri="/WEB-INF/tld/test.tld" prefix="h"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>My JSP 'math.jsp' starting page</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

	</head>
	<!-- 请求jsp页面，并且传递参数的时候，要在param里面获取 -->
	<body>
		<h:if test="${param.method!=null&&param.value!=null}">
			<h:math method="${param.method}" value="${param.value}"
				angle="${param.angle}" pattern="${param.pattern}" />车速方法名：  
			<input type="text" name="method" value="${param.method}"> 
			参数值： 
			<input type="text" name="value" value="${param.value}"> 
			是否为角度：
			<input type="checkbox" name="angle" value="true" param.angle="="
				true="" checked="checked"> 
			格式化参数： 
			<input name="pattern" value="${param.pattern}">
			<input type="submit" value="计算">

		</h:if>

	</body>
</html>
