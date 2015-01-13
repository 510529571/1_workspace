package com.rest.action1.student;

import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

//@Results(@Result(name = "success", type = "redirectAction", params = {
//		"actionName", "student" }))
public class StudentAction extends ActionSupport implements ModelDriven<Object> {

	public String index() {
		return SUCCESS;
	}

	public String add() {
		return "add";
	}
	public String update() {
		return "update";
	}

	public String delete() {
		return "update";
	}


	public Object getModel() {
		// TODO Auto-generated method stub
		return null;
	}
}
