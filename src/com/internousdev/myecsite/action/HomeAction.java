package com.internousdev.myecsite.action;

import com.opensymphony.xwork2.ActionSupport;

public class HomeAction extends ActionSupport{
	/**
	 * ログインボタン押下後に実行
	 * （ログイン後の画面へ遷移する）
	 * @return SUCCESS
	 */
	public String execute(){
		return SUCCESS;
	}
}
