<%--
  Created by IntelliJ IDEA.
  User: hanwei
  Date: 14-2-18
  Time: ����10:36
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=GBK" language="java" %>
<%
    String reqMsg=request.getParameter("reqMsg");
    request.setAttribute("reqMsg",reqMsg);
%>
<html>
<head>
    <title></title>
</head>
<body>
����ҳ��

�����ص�
<form name="form1" action="http://localhost:8081/account/AddTradeAccoReturnAction.do" method="post">
    <input name="reqMsg" type="text" value='${reqMsg}'>
    ����:<input name="password" type="text" value="">
    <input type="submit" name="" value="ȷ��"/>
</form>

�����ص�
<form name="form1" action="http://localhost:8081/account/BankCardModifyReturnAction.do" method="post">
    <input name="reqMsg" type="text" value='${reqMsg}'>
    ����:<input name="password" type="text" value="">
    <input type="submit" name="" value="ȷ��"/>
</form>

ǩԼ�ص�
<form name="form2"  action="http://localhost:8081/account/ProtocolSignReturnAction.do" method="post">
    <input name="reqMsg" type="text" value='${reqMsg}'>
    ����:<input name="password" type="text" value="">
    <input type="submit" name="" value="ȷ��"/>
</form>
</body>
</html>