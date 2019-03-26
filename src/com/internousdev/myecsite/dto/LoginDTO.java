package com.internousdev.myecsite.dto;

public class LoginDTO {

	private String loginId;

	private String loginPassword;

	private String userName;

	private boolean loginFlg = false;

	/**
	 * @return loginId
	 */
	public String getLoginId() {
		return loginId;
	}

	/**
	 * @param loginId セットする loginId
	 */
	public void setLoginId(String loginId) {
		this.loginId = loginId;
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
	 * @return loginFlg
	 */
	public boolean getLoginFlg() {
		return loginFlg;
	}

	/**
	 * @param loginFlg セットする loginFlg
	 */
	public void setLoginFlg(boolean loginFlg) {
		this.loginFlg = loginFlg;
	}



}
