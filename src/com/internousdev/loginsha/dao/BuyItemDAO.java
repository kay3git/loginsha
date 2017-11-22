package com.internousdev.loginsha.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.internousdev.loginsha.dto.BuyItemDTO;
import com.internousdev.loginsha.util.DBConnector;


public class BuyItemDAO {

	private DBConnector dbConnector = new DBConnector();

	private Connection connection = dbConnector.getConnection();

	private BuyItemDTO buyItemDTO = new BuyItemDTO();

	/**
	 * 商品情報取得メソッド
	 *
	 * @return BuyItemDTO
	 */

	/*	★★★★ getBuyItemInfoメソッド（商品情報取得）★★★★
	 * item_info_transactionテーブルから「商品ID」「商品名」「価格」を受け取り
	 * それらをセットしたbuyItemDTOオブジェクトを戻り値として返す*/

	//引数を受け取らずitem_info_transactionから条件指定せずselectしているので
	//商品が1件しか登録されていないことが前提（たぶん）
	public BuyItemDTO getBuyItemInfo(){
		String sql = "SELECT id, item_name, item_price FROM	item_info_transaction";

		try{
			PreparedStatement preparedStatement =
				connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();

			if(resultSet.next()){
				buyItemDTO.setId(resultSet.getInt("id"));
				buyItemDTO.setItemName(resultSet.getString("item_name"));
				buyItemDTO.setItemPrice(resultSet.getString("item_price"));
			}
		}catch(Exception e){
			e.printStackTrace();
		}

		return buyItemDTO;
	}
	public BuyItemDTO getBuyItemDTO(){
		return buyItemDTO;
	}
}
