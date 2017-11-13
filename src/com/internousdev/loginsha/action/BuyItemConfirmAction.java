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

		buyItemCompleteDAO.buyItemInfo(
				session.get("id").toString(),
				session.get("login_user_id").toString(),
				session.get("buyItem_price").toString(),
				session.get("count").toString(),
				session.get("pay").toString());

		String result = SUCCESS;
		return result;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
}
