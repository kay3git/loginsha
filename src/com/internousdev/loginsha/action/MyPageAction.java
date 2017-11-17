package com.internousdev.loginsha.action;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.loginsha.dao.MyPageDAO;
import com.internousdev.loginsha.dto.MyPageDTO;
import com.opensymphony.xwork2.ActionSupport;

public class MyPageAction extends ActionSupport implements SessionAware{

	public Map<String, Object> session;

	private MyPageDAO myPageDAO = new MyPageDAO();

	//MyPageDTO型のArrayListであるmyPageListを宣言
	public ArrayList<MyPageDTO> myPageList = new ArrayList<MyPageDTO>();

	//削除フラグを宣言
	private String deleteFlg;

	private String message;


	public String execute() throws SQLException{
		//セッションのキーとして「id」がなければ
		//＝セッションに商品idが格納されていなければ戻り値にERRORをセット
		if (!session.containsKey("id")){
			return ERROR;
		}

		//deleteFlgがnullなら以下の処理を行う
		if (deleteFlg == null){
			//変数「item_transaction_id」「user_master_id」にそれぞれ
			//セッションの「商品ID」「ユーザID」をセット
			String item_transaction_id = session.get("id").toString();
			String user_master_id = session.get("login_user_id").toString();
			//
			myPageList = myPageDAO.getMyPageUserInfo(item_transaction_id,user_master_id);
			//MyPageDTO型のイテレータオブジェクトiteratorをmyPageListのイテレータとして定義
			Iterator<MyPageDTO> iterator =myPageList.iterator();
			//iteratorに次のエレメントがなければmyPageListにnullをセット
			if(!(iterator.hasNext())){
				myPageList = null;
			}
		//deleteFlgがnull以外かつ「1」ならdeleteメソッドを実行
		}else if(deleteFlg.equals("1")){
			delete();
		}

		//戻り値にSUCCESSをセット
		String result = SUCCESS;
		//戻り値を返す
		return result;
	}

	public void delete() throws SQLException{
		String item_transaction_id = session.get("id").toString();
		String user_master_id = session.get("login_user_id").toString();

		int res = myPageDAO.buyItemHistoryDelete(item_transaction_id,user_master_id);

		if(res>0){
			myPageList = null;
			setMessage("商品情報を正しく削除しました。");
		}else if(res==0){
			setMessage("商品情報の削除に失敗しました。");
		}
	}

	public String getDeleteFlg() {
		return deleteFlg;
	}

	public void setDeleteFlg(String deleteFlg) {
		this.deleteFlg = deleteFlg;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
}
