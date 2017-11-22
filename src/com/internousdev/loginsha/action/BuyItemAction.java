package com.internousdev.loginsha.action;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

public class BuyItemAction extends ActionSupport implements SessionAware {

	 //アイテム購入個数
	private int stock;

	 //支払い方法
	private String pay;

	//アイテム情報を格納
	public Map<String, Object>  session;

	//処理結果
	private String result;

	/**
	 * 商品情報取得メソッド
	 *
	 * @author internous
	 */
	public String execute() {
		result = SUCCESS;

		//buyItem.jspの在庫（stock）で選択された値をセッションのキー"count"の値としてセット
		session.put("count", stock);
		//セッションにセットされたキー名"count""buyItem_price"を数値型にし変数にセット
		int intCount = Integer.parseInt(session.get("count").toString());
		int intPrice = Integer.parseInt(session.get("buyItem_price").toString());

		//セッションのキー"total_price"の値として変数に入った個数と値段をかけたものをセット
		session.put("total_price", intCount * intPrice);
		//支払い方法をあらわす文字列変数paymentを宣言
		String payment;

		//buyItem.jspの支払い方法（pay）で選択された値によって
		//変数paymentを経由しセッションのキー"pay"の値として「現金払い」か「クレジットカード」をセット
		if(pay.equals("1")) {
			payment = "現金払い";
			session.put("pay", payment);
		} else {
			payment = "クレジットカード";
			session.put("pay", payment);
		}
		return result;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public String getPay() {
		return pay;
	}

	public void setPay(String pay) {
		this.pay = pay;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
}
