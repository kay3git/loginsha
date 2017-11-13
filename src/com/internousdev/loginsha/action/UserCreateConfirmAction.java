package com.internousdev.loginsha.action;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

public class UserCreateConfirmAction extends ActionSupport implements SessionAware{

	private String loginUserId;

	private String loginPassword;

	private String userName;

	public Map<String,Object> session;

	private String result;

	private String errorMessage;

	/**
	 * 入力情報格納処理
	 */
	public String execute(){

		result = SUCCESS;

		if(!(loginUserId.equals("")) && !(loginPassword.equals("")) && !(userName.equals(""))){
			session.put("loginUserId",loginUserId);
			session.put("loginPassword",loginPassword);
			session.put("userName",userName);
		}else {
			setErrorMessage("未入力の項目があります。");
			result = ERROR;
		}
		return result;
	}



	/**
	 * @return loginUserId
	 */
	public String getLoginUserId() {
		return loginUserId;
	}



	/**
	 * @param loginUserId セットする loginUserId
	 */
	public void setLoginUserId(String loginUserId) {
		this.loginUserId = loginUserId;
	}



	/**
	 * @return loginPassword
	 */
	public String getLoginPassword() {
		return loginPassword;
	}



	/**
	 * @param loginPassword セットする loginPassword
	 */
	public void setLoginPassword(String loginPassword) {
		this.loginPassword = loginPassword;
	}



	/**
	 * @return userName
	 */
	public String getUserName() {
		return userName;
	}



	/**
	 * @param userName セットする userName
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}



	/**
	 * @return errorMessage
	 */
	public String getErrorMessage() {
		return errorMessage;
	}



	/**
	 * @param errorMessage セットする errorMessage
	 */
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}



	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
}
