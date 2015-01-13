@echo off
echo 启动模拟发送
title 模拟发送

set domain=.

set classpath=%classpath%;%domain%\target\classes;
set classpath=%classpath%;%domain%\lib\commons-collections.jar;%domain%\lib\commons-httpclient-2.0-rc2.jar;
set classpath=%classpath%;%domain%\lib\commons-logging.jar;%domain%\lib\commons-pool.jar;
set classpath=%classpath%;%domain%\lib\httpclientcontrib.jar;%domain%\lib\icbc.jar;
set classpath=%classpath%;%domain%\lib\InfosecCrypto_Java1_02_JDK14.jar;
set classpath=%classpath%;%domain%\lib\jaxen-core.jar;%domain%\lib\jaxen-jdom.jar;%domain%\lib\javax.servlet.jar;
set classpath=%classpath%;%domain%\lib\jdom.jar;%domain%\lib\org.mortbay.jetty.jar;
set classpath=%classpath%;%domain%\lib\org.mortbay.jmx.jar;%domain%\lib\saxpath.jar;
set classpath=%classpath%;%domain%\lib\tools.jar;%domain%\lib\dom4j-1.6.1.jar;


cd .


java com.sinitek.newtrade.pay.icbc.common.ICBCUtils

cmd