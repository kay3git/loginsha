package com.internousdev.loginsha.action;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

public class LogoutAction extends ActionSupport implements SessionAware{

	/**
	 * Session情報
	 */
	public Map<String,Object> session;

	/**
	 * ログイン画面遷移処理
	 */
	public String execute(){

		session.clear();
		return SUCCESS;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

}
