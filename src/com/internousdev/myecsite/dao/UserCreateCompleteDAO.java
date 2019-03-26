package com.internousdev.myecsite.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.internousdev.myecsite.util.DBConnector;
import com.internousdev.myecsite.util.DateUtil;

public class UserCreateCompleteDAO {

	private DBConnector dbConnector = new DBConnector();

	private Connection connection = dbConnector.getConnection();

	private DateUtil dateUtil = new DateUtil();

	private String sql ="INSERT INTO login_user_transaction(login_id, login_pass, user_name, insert_date) VALUES(?,?,?,?)";

	/*	★★★★ createUserメソッド（）★★★★
	 * 「ユーザID」「パスワード」「ユーザ名」を引数として受け取り、dateUtilで現在日時を取得し
	 * 「login_user_transaction」テーブルの「login_id」「login_pass」「user_name」「insert_date」にINSERTする */

	public void createUser(String loginUserId, String loginUserPassword, String userName) throws SQLException{

		try{
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, loginUserId);
			preparedStatement.setString(2, loginUserPassword);
			preparedStatement.setString(3, userName);
			preparedStatement.setString(4, dateUtil.getDate());

			preparedStatement.execute();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			connection.close();
		}
	}
}
