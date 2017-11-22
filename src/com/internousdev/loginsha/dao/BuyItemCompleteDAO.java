package com.internousdev.loginsha.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.internousdev.loginsha.util.DBConnector;
import com.internousdev.loginsha.util.DateUtil;


public class BuyItemCompleteDAO {

	private DBConnector dbConnector = new DBConnector();

	private Connection connection = dbConnector.getConnection();

	private DateUtil dateUtil = new DateUtil();

	private String sql = "INSERT INTO user_buy_item_transaction(item_transaction_id,total_price,total_count,user_master_id,pay,insert_date) VALUES(?,?,?,?,?,?)";

	/**
	 * 商品購入情報登録メソッド
	 *
	 * @param item_transaction_id
	 * @param user_master_id
	 * @param total_price
	 * @param total_count
	 * @param pay
	 * @throws SQLException
	 */

	/*	★★★★ buyItemInfoメソッド（）★★★★
	 * 「商品ID」「購入金額」「購入個数」「ユーザID」「支払い方法」を引数として受け取り、
	 * 引数をuser_buy_item_transactionテーブルの「item_transaction_id」「total_price」「total_count」「user_master_id」、
	 * dateUtilで現在日時を取得し「insert_date」にセット後、INSERするT*/

	public void buyItemInfo(String item_transaction_id, String total_price, String total_count, String user_master_id, String pay) throws SQLException{

		try{
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, item_transaction_id);
			preparedStatement.setString(2, total_price);
			preparedStatement.setString(3, total_count);
			preparedStatement.setString(4, user_master_id);
			preparedStatement.setString(5, pay);
			preparedStatement.setString(6, dateUtil.getDate());

			preparedStatement.execute();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			connection.close();
		}
	}
}
