<%--
  Created by IntelliJ IDEA.
  User: hanwei
  Date: 14-2-18
  Time: 上午10:36
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
银行页面

开户回调
<form name="form1" action="http://localhost:8081/account/AddTradeAccoReturnAction.do" method="post">
    <input name="reqMsg" type="text" value='${reqMsg}'>
    密码:<input name="password" type="text" value="">
    <input type="submit" name="" value="确认"/>
</form>

换卡回调
<form name="form1" action="http://localhost:8081/account/BankCardModifyReturnAction.do" method="post">
    <input name="reqMsg" type="text" value='${reqMsg}'>
    密码:<input name="password" type="text" value="">
    <input type="submit" name="" value="确认"/>
</form>

签约回调
<form name="form2"  action="http://localhost:8081/account/ProtocolSignReturnAction.do" method="post">
    <input name="reqMsg" type="text" value='${reqMsg}'>
    密码:<input name="password" type="text" value="">
    <input type="submit" name="" value="确认"/>
</form>
</body>
</html>