<%@ page import="java.net.Socket" %>
<%@ page import="java.io.OutputStream" %>
<%@ page import="java.io.InputStream" %>
<%@ page import="java.io.ByteArrayOutputStream" %>
<%--
  Created by IntelliJ IDEA.
  User: hanwei
  Date: 14-11-25
  Time: 上午11:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    //hhw:task
    try{
    Socket socket = new Socket("10.88.134.193", 10001);
    socket.setSoTimeout(10000);


    OutputStream outs = socket.getOutputStream();
    InputStream in = socket.getInputStream();
    outs.write("请求数据".getBytes("gbk"));


    //字符数组长度
    int bufferlen = 100;
    //声明数组
    byte[] buff = new byte[bufferlen];
    //保存客户端发送报文
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    int receivedLen;

    while ((receivedLen = in.read(buff, 0, bufferlen)) == bufferlen) {
        baos.write(buff, 0, receivedLen);
    }
    if (receivedLen > 0 && receivedLen < bufferlen) {
        baos.write(buff, 0, receivedLen);
    }
    System.out.println(baos.toString("gbk"));

    out.close();
    in.close();
    socket.close();
    }catch (Exception e){
        e.printStackTrace();
    }
    System.out.println("请求结束，能正常处理读取超时");
%>
<html>
<head>
    <title></title>
</head>
<body>

</body>
</html>