package com.internousdev.myecsite.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.internousdev.myecsite.dto.LoginDTO;
import com.internousdev.myecsite.util.DBConnector;

public class LoginDAO {
	//DBConnectorのインスタンスdbを作成
	private DBConnector dbConnector = new DBConnector();
	//DBConnectorからの戻り値（接続できていればその接続、できていなければnull）をConnection型の変数connectionにセット
	private Connection connection = dbConnector.getConnection();
	//LoginDTOのインスタンスloginDTOを生成
	private LoginDTO loginDTO = new LoginDTO();

	/**
	 * ログインユーザ情報取得メソッド
	 *
	 * @param loginUserId
	 * @param loginPassword
	 * @return LoginDTO
	 */
	//******************************** getLoginUserInfoメソッド ********************************
	//login.jspで入力されたIDとパスワードでDBを検索し
	//１．該当レコードの「login_id」「login_pass」「user_name」をDTOオブジェクトに格納
	//２．該当レコードの「login_id」がnullでなければloginDTOのloginFlgにtrueを格納
	public LoginDTO getLoginUserInfo(String loginUserId, String loginPassword){
		//ユーザ名とパスワードをプレースホルダにしたSQL文（文字列）を変数sqlにセット
		String sql = "SELECT * FROM login_user_transaction where login_id = ? AND login_pass = ?";

		try{
			//prepareStatementメソッドを用いてPreparedStatementインターフェースのオブジェクトpsを作成
			//（引数はsqlにセットされたSQL文）
			//PreparedStatementオブジェクトはパラメータ付のSQLを実行するために使われる
			//事前にコンパイルが行われているので高速で実行できる
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			//PreparedStatementオブジェクトのパラメータに値をセット
			//PreparedStatementインターフェースで用意されているsetStringメソッドを使用
			preparedStatement.setString(1,loginUserId);
			preparedStatement.setString(2,loginPassword);
			//SQLを実行し結果セットをresultSetに格納
			ResultSet resultSet = preparedStatement.executeQuery();

			if(resultSet.next()){
				//DBから取得した「login_id」「login_pass」「user_name」をDTOオブジェクトに格納
				loginDTO.setLoginId(resultSet.getString("login_id"));
				loginDTO.setLoginPassword(resultSet.getString("login_pass"));
				loginDTO.setUserName(resultSet.getString("user_name"));
				//DBから取得したlogin_idがnullで」なければloginDTOのloginFlgにtrueを格納
				if(!(resultSet.getString("login_id").equals(null))){
					loginDTO.setLoginFlg(true);
				}
			}

		}catch(Exception e){
			e.printStackTrace();
		}
		//結果の入ったDTOオブジェクトを返す
		return loginDTO;
	}
	//******* getLoginDTOメソッド
	//どこで使われているか今のところ不明
	public LoginDTO getLoginDTO(){
		return loginDTO;
	}
}
