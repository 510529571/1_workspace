<%--
  Created by IntelliJ IDEA.
  User: hanwei
  Date: 14-9-16
  Time: 上午10:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
	<script src="1.js"></script>
</head>
<body>
<% System.out.println(request);
System.out.println(request.getAttribute("type"));
%>
欢迎你：${user}${type}
</body>
</html>