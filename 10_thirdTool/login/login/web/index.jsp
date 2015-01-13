<%@ page contentType="text/html; charset=GBK" %>
<html>
<head>
    <title></title>
    <%--<script src="js/11.js"></script>--%>
</head>
<%
    System.out.println("请求登录页面");
    Thread.sleep(5000);
    System.out.println("请求结束");
%>
<body>
<form action="/Login.do">
    ${errorMsg}<br/>
    用户名：<input name="username"/>
    密码：<input name="password"/>
    <input type="submit" name="提交"/>
</form>
</body>
</html>