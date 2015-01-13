<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/WEB-INF/tld/test.tld" prefix="my"%>

<jsp:useBean id="userVo1" class="test.UserVo" scope="request">
	<jsp:setProperty name="userVo1" property="name" value="Hackiller" />
	<jsp:setProperty name="userVo1" property="password" value="123" />
</jsp:useBean>

<jsp:useBean id="userVo2" class="test.UserVo" scope="request">
	<jsp:setProperty name="userVo2" property="name" value="YangYang" />
	<jsp:setProperty name="userVo2" property="password" value="456" />
</jsp:useBean>

<%
	List list = new ArrayList();
	list.add(userVo1);
	list.add(userVo2);
	pageContext.setAttribute("voList", list);
%>

<html>
	<head>
	</head>

    <body>
    <h2 align="center">
        测试我写的标签.
    </h2>
    <hr>

    <h2>
        自定义迭代标签：
    </h2>
    <table>
        <tr>
            <td>
                姓名
            </td>
            <td>
                密码
            </td>
        </tr>
        <my:forEach collection="voList" id="uservo">
            <tr>
                <td>
                        ${uservo.name}
                    <!-- <my:getProperty name="uservo" property="name" />-->
                </td>
                <td>
                        ${uservo.password}
                    <!-- <my:getProperty name="uservo" property="password" />-->
                </td>
            </tr>
        </my:forEach>
    </table>
    <%--hhw:problem table标签后面的内容不显示了，如果把我写的my:forEach标签去掉就可以正常显示
    这是为什么呢？
    --%>
    <br/>
    234234234234

    <h2>
        表达式调用类的静态方法：
    </h2>
    2+5=${my:add(2,5)}
    </body>
</html>
