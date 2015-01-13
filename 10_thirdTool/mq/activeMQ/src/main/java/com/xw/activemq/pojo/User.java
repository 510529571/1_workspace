package com.xw.activemq.pojo;

import java.io.Serializable;

/**
 * Author: XW
 * Date Time: 2014/6/25 17:02
 * Desc：
 */
public class User implements Serializable {
	private static final long serialVersionUID = 888569260328396607L;

	private int id;
	private String userName;
	private String gender;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
}
