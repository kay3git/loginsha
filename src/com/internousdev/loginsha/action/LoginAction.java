package com.internousdev.loginsha.action;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.loginsha.dao.BuyItemDAO;
import com.internousdev.loginsha.dao.LoginDAO;
import com.internousdev.loginsha.dto.BuyItemDTO;
import com.internousdev.loginsha.dto.LoginDTO;
import com.opensymphony.xwork2.ActionSupport;

/**
 * ログイン認証処理
 * Login.jspからログインID、ログインパスワードを受け取り
 * DBへ問い合わせを行います。
 *
 * @author internous
 * @param loginUserId
 * @param loginPassword
 *
 * @return result
 */
public class LoginAction extends ActionSupport implements SessionAware{

	private String loginUserId;
	private String loginPassword;
//処理結果格納用変数result
	private String result;

//セッション使用///////////////////////
	public Map<String, Object> session;
///////////////////////////////////////

//ログイン情報取得DAO
	private LoginDAO loginDAO = new LoginDAO();
//ログイン情報格納DTO
	private LoginDTO loginDTO = new LoginDTO();

//アイテム情報取得DAO
	private BuyItemDAO buyItemDAO = new BuyItemDAO();


//実行メソッド
	public String execute() {
//戻り値の初期値にERRORをセット
		result = ERROR;

//******************************** getLoginUserInfoメソッド ********************************
//login.jspで入力されたIDとパスワードでDBを検索し
//１．該当レコードの「login_id」「login_pass」「user_name」をDTOオブジェクトに格納
//２．該当レコードの「login_id」がnullでなければloginDTOのloginFlgにtrueを格納
		loginDTO = loginDAO.getLoginUserInfo(loginUserId, loginPassword);
//*****************************************************************************************
		//セッションの「loginUser」キーのvalueとしてloginDTOを格納
		session.put("loginUser", loginDTO);

		//sessionに格納された、DBから取得した値をセットしたloginDTOオブジェクトを
		//LoginDTO型にキャストしたものからloginFlgを取り出し、それがtrueなら戻り値にSUCCESSをセット
		//つまり→「入力値で検索した結果同じ値がDBにあれば」
		if(((LoginDTO) session.get("loginUser")).getLoginFlg()) {
			result = SUCCESS;

			//item_info_transactionテーブルから無条件で検索した結果（1件が前提）を
			//buyItemDTOに代入
			BuyItemDTO buyItemDTO = buyItemDAO.getBuyItemInfo();
			//sessionに以下をセット
			//*** loginDTOの「ユーザID」
			//*** buyItemDTOの「商品ID」「商品名」「商品価格」をセット
			session.put("login_user_id",loginDTO.getLoginId());
			session.put("id", buyItemDTO.getId());
			session.put("buyItem_name", buyItemDTO.getItemName());
			session.put("buyItem_price", buyItemDTO.getItemPrice());

			return result;
		}

		return result;
	}

	public String getLoginUserId() {
		return loginUserId;
	}

	public void setLoginUserId(String loginUserId) {
		this.loginUserId = loginUserId;
	}

	public String getLoginPassword() {
		return loginPassword;
	}

	public void setLoginPassword(String loginPassword) {
		this.loginPassword = loginPassword;
	}
	//セッション使用///////////////////////
	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	///////////////////////////////////////
	}
}
