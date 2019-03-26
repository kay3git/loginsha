package com.internousdev.myecsite.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.internousdev.myecsite.dto.MyPageDTO;
import com.internousdev.myecsite.util.DBConnector;

/**
 * @author internousdev
 *
 */
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

	/*	★★★★ getMyPageUserInfoメソッド（購入履歴取得）★★★★
	 * 「商品ID」と「ユーザID」を受け取り、「該当ユーザが該当商品を購入した履歴」の全件分を
	 * セットしたmyPageDTOオブジェクトを戻り値として返す*/

	public ArrayList<MyPageDTO> getMyPageUserInfo(String item_transaction_id, String user_master_id) throws SQLException{
		//MyPageDTO型のArrayListオブジェクトとしてmyPageDTOを宣言
		ArrayList<MyPageDTO> myPageDTO = new ArrayList<MyPageDTO>();

		///////////////// ＳＱＬの内容 //////////////////////////////////////////////////////////////////////////////
		//「user_buy_item_transaction」テーブルをベースに「item_info_transaction」テーブルからも値を引っ張る
		//（結合キーは「商品ID」）
		//・検索条件は「user_buy_item_transaction」テーブルの「商品ID」「ユーザID」
		//・「レコード作成日時」の降順
		///////////////// ＳＱＬの内容 //////////////////////////////////////////////////////////////////////////////
		String sql = "SELECT ubit.id, iit.item_name, ubit.total_price, ubit.total_count, ubit.pay, ubit.insert_date "
				+ "FROM user_buy_item_transaction ubit LEFT JOIN item_info_transaction iit "
				+ "ON ubit.item_transaction_id = iit.id where ubit.item_transaction_id  = ? AND ubit.user_master_id  = ? "
				+ "ORDER BY insert_date DESC";

		try{
			//
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, item_transaction_id);
			preparedStatement.setString(2, user_master_id);

			ResultSet resultSet = preparedStatement.executeQuery();

			///////////// resultSetの1件ごとに以下の処理を行う //////////////////////////////////
			//MyPageDTO型オブジェクトdtoに、結果セットの
			//「商品ID」「商品名」「購入金額」「購入個数」「支払い方法」「日時」をセット
			while(resultSet.next()){
				MyPageDTO dto = new MyPageDTO();
				dto.setId(resultSet.getString("id"));
				dto.setItemName(resultSet.getString("item_name"));
				dto.setTotalPrice(resultSet.getString("total_price"));
				dto.setTotalCount(resultSet.getString("total_count"));
				dto.setPayment(resultSet.getString("pay"));
				dto.setInsert_date(resultSet.getString("insert_date"));
			//MyPageDTO型のArrayListオブジェクトmyPageDTOにdtoにセットした1件分の値を追加
				myPageDTO.add(dto);
			}
			//////////////////////////////////////////////////////////////////////////////////////

		}catch(Exception e){
			e.printStackTrace();
		}finally{
			connection.close();
		}

		//購入履歴の全件分をセットしたmyPageDTOオブジェクトを戻り値として返す
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

	/*	★★★★ buyItemHistoryDeleteメソッド（購入履歴削除）★★★★
	 * 「商品ID」と「ユーザID」を受け取り、「該当ユーザが該当商品を購入した履歴」の全件分を削除*/


	public int buyItemHistoryDelete(String item_transaction_id, String user_master_id) throws SQLException {

		String sql = "DELETE FROM user_buy_item_transaction where item_transaction_id  = ? AND user_master_id  = ?";

		PreparedStatement preparedStatement;
		int result =0;
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, item_transaction_id);
			preparedStatement.setString(2, user_master_id);

			//削除件数を変数resultに格納
			result = preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
		//戻り値として削除件数を返す
		return result;
	}
}
