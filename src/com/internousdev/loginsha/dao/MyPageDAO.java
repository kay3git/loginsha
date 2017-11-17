package com.internousdev.loginsha.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.internousdev.loginsha.dto.MyPageDTO;
import com.internousdev.loginsha.util.DBConnector;

public class MyPageDAO {
	private DBConnector dbConnector = new DBConnector();

	private Connection connection = dbConnector.getConnection();

	/**
	 * 購入履歴取得
	 *
	 * @param item_transaction_id
	 * @param user_master_id
	 * @return
	 * @throws SQLException
	 */

	public ArrayList<MyPageDTO> getMyPageUserInfo(String item_transaction_id, String user_master_id) throws SQLException{
		//MyPageDTO型のArrayListオブジェクトとしてmyPageDTOを宣言
		ArrayList<MyPageDTO> myPageDTO = new ArrayList<MyPageDTO>();

		String sql = "SELECT ubit.id, iit.item_name, ubit.total_price, ubit.total_count, ubit.pay, ubit.insert_date "
				+ "FROM user_buy_item_transaction ubit LEFT JOIN item_info_transaction iit "
				+ "ON ubit.item_transaction_id = iit.id where ubit.item_transaction_id  = ? AND ubit.user_master_id  = ? "
				+ "ORDER BY insert_date DESC";

		try{
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, item_transaction_id);
			preparedStatement.setString(2, user_master_id);

			ResultSet resultSet = preparedStatement.executeQuery();

			//MyPageDTO型オブジェクトdtoに、結果セットの
			//「商品ID」「商品名」「購入金額」「購入個数」「支払い方法」「日時」を1件ずつセット
			while(resultSet.next()){
				MyPageDTO dto = new MyPageDTO();
				dto.setId(resultSet.getString("id"));
				dto.setItemName(resultSet.getString("item_name"));
				dto.setTotalPrice(resultSet.getString("total_price"));
				dto.setTotalCount(resultSet.getString("total_count"));
				dto.setPayment(resultSet.getString("pay"));
				dto.setInsert_date(resultSet.getString("insert_date"));
				//
				myPageDTO.add(dto);
			}


		}catch(Exception e){
			e.printStackTrace();
		}finally{
			connection.close();
		}

		return myPageDTO;
	}


	/**
	 * 購入履歴削除
	 *
	 * @param item_transaction_id
	 * @param user_master_id
	 * @return
	 * @throws SQLException
	 */

	public int buyItemHistoryDelete(String item_transaction_id, String user_master_id) throws SQLException {

		String sql = "DELETE FROM user_buy_item_transaction where item_transaction_id  = ? AND user_master_id  = ?";

		PreparedStatement preparedStatement;
		int result =0;
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, item_transaction_id);
			preparedStatement.setString(2, user_master_id);

			result = preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}

		return result;
	}
}
