package com.internousdev.loginsha.action;

import java.sql.SQLException;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.loginsha.dao.UserCreateCompleteDAO;
import com.opensymphony.xwork2.ActionSupport;

public class UserCreateCompleteAction extends ActionSupport implements SessionAware{

	private String loginUserId;

	private String loginPassword;

	private String userName;

	public Map<String,Object> session;

	private String result;

	private UserCreateCompleteDAO userCreateCompleteDAO = new UserCreateCompleteDAO();

	/**
	 * ユーザー情報登録処理
	 */

	public String execute() throws SQLException{

		userCreateCompleteDAO.createUser(session.get("loginUserId").toString(),
				session.get("loginPassword").toString(),
				session.get("userName").toString());

		result = SUCCESS;

		return result;
	}

///////////////////カプセル化///////////////
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

//////////////セッション//////////////////////
	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}



}
