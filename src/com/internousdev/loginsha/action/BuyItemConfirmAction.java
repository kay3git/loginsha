package com.internousdev.loginsha.action;

import java.sql.SQLException;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.loginsha.dao.BuyItemCompleteDAO;
import com.opensymphony.xwork2.ActionSupport;

public class BuyItemConfirmAction extends ActionSupport implements SessionAware{

	public Map<String,Object> session;

	private BuyItemCompleteDAO buyItemCompleteDAO = new BuyItemCompleteDAO();

	/**
	 * 商品購入情報登録処理
	 *
	 * @author internous
	 */
	public String execute() throws SQLException {
//購入完了処理用DAOにセッション情報をセット
		buyItemCompleteDAO.buyItemInfo(
				//商品ID
				session.get("id").toString(),
				//購入トータル価格
				session.get("total_price").toString(),
				//購入個数
				session.get("count").toString(),
				//ユーザID
				session.get("login_user_id").toString(),
				//支払い方法
				session.get("pay").toString());

		String result = SUCCESS;
		return result;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
}
