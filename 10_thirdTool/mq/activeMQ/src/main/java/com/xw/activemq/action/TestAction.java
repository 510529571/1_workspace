package com.xw.activemq.action;

import com.opensymphony.xwork2.ActionSupport;
import com.xw.activemq.MessageSender;
import com.xw.activemq.pojo.User;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * Author: XW
 * Date Time: 2014/6/26 14:34
 * Desc：
 */
public class TestAction extends ActionSupport {
	private static final long serialVersionUID = -5597407684677020499L;
	private final Logger log = Logger.getLogger(TestAction.class);
	private MessageSender messageSender;
	private String msg;

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public void setMessageSender(MessageSender messageSender) {
		this.messageSender = messageSender;
	}

	public void test() throws IOException {
		PrintWriter out = ServletActionContext.getResponse().getWriter();
		if (log.isInfoEnabled()) {
			log.info("收到的msg：" + msg);
		}
		out.print(msg);
		User user = new User();
		user.setId(2);
		user.setUserName(msg);
		user.setGender(msg);
		messageSender.sendMsg(user);
	}
}
