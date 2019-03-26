package com.internousdev.myecsite.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.internousdev.myecsite.dto.BuyItemDTO;
import com.internousdev.myecsite.util.DBConnector;


public class BuyItemDAO {

	private DBConnector dbConnector = new DBConnector();

	private Connection connection = dbConnector.getConnection();

//	private BuyItemDTO buyItemDTO = new BuyItemDTO();

	/**
	 * 商品情報取得メソッド
	 *
	 * @return BuyItemDTO
	 */

	/*	★★★★ getBuyItemInfoメソッド（商品情報取得）★★★★
	 * item_info_transactionテーブルから全件分の「商品ID」「商品名」「価格」を受け取り
	 * それらをセットしたbuyItemDTOオブジェクトを戻り値として返す*/

	public ArrayList<BuyItemDTO> getBuyItemInfo(){
		//BuyItemDTO型のArrayListオブジェクトとしてbuyItemDTOを宣言
		ArrayList<BuyItemDTO> buyItemDTO = new ArrayList<BuyItemDTO>();

		String sql = "SELECT id, item_name, item_price FROM	item_info_transaction";

		try{
			PreparedStatement preparedStatement =
				connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();

			///////////// resultSetの1件ごとに以下の処理を行う //////////////////////////////////
			//BuyItemDTO型オブジェクトdtoに、結果セットの
			//「商品ID」「商品名」「価格」をセット
			while(resultSet.next()){
				BuyItemDTO dto = new BuyItemDTO();
				dto.setId(resultSet.getInt("id"));
				dto.setItemName(resultSet.getString("item_name"));
				dto.setItemPrice(resultSet.getString("item_price"));
				//BuyItemDTO型のArrayListオブジェクトbuyItemDTOに、dtoにセットした1件分の値を追加
				buyItemDTO.add(dto);			}
		}catch(Exception e){
			e.printStackTrace();
		}

		return buyItemDTO;
	}

	//どこからも呼び出されてない？
//	public ArrayList<BuyItemDTO> getBuyItemDTO(){
//		return buyItemDTO;
//	}
}
