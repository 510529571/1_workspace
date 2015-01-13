<%--
  Created by IntelliJ IDEA.
  User: hanwei
  Date: 14-4-17
  Time: 上午11:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<
<html>
<head>
    <title></title>
</head>
<body>
<%--<%=request.getParameter("name")%>      //直接请求jsp带参数时，可以xss--%>
<%--<%=request.getSession().getAttribute("name")%>   //可以xss--%>
<script type="">
    <%--var a=<%=request.getSession().getAttribute("name")%>;    //可以xss，测试脚本【alert(123);】,--%>
    <%--var a='<%=request.getSession().getAttribute("name")%>'  //可以xss，测试脚本【\'';alert(123);'】，避免方法java中escapeJavaScript处理--%>
</script>
<style>
    #a1{ background-url : "javascript:alert(css)"; }        /*暂时未发现可以xss*/
</style>
<div style="name:javascript:alert(css);">Selection</div>
<form action="../XssServlet" method="post">
    <input type="text" name="name" value="<%=request.getSession().getAttribute("name")%>">
    <%--
    可以xss
    测试脚本【"/><script>alert(document.cookie)</script><!--】
    或【<script>alert(document.cookie)</script><!--】
    或【"onclick="alert(document.cookie)】
    避免方法java中escapeHtml处理
    --%>
    <input type="submit">
</form>
<%request.getSession().setAttribute("name",null);%>
</body>
</html>