<%@ page contentType="text/html; charset=GBK" %>
<html>
<head>
    <title></title>
    <%--<script src="js/11.js"></script>--%>
</head>
<%
    System.out.println("�����¼ҳ��");
    Thread.sleep(5000);
    System.out.println("�������");
%>
<body>
<form action="/Login.do">
    ${errorMsg}<br/>
    �û�����<input name="username"/>
    ���룺<input name="password"/>
    <input type="submit" name="�ύ"/>
</form>
</body>
</html>