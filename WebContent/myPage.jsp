<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
	<head>
		<meta charset="utf-8">
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta http-equiv="Content-Style-Type" content="text/css" />
		<meta http-equiv="Content-Script-Type" content="text/javascript" />
		<meta http-equiv="imagetoolbar" content="no" />
		<meta name="description" content="" />
		<meta name="keywords" content="" />
			<title>MyPage画面</title>
		<style type="text/css">
		/* ========TAG LAYOUT======== */
			body {
			   margin:0;
			   padding:0;
			   line-height:1.6;
			   letter-spacing:1px;
			   font-family:Verdana, Helvetica, sans-serif;
			   font-size:12px;
			   color:#333;
			   background:#fff;
			}

			table {
				text-align:center;
				margin:0 auto;
			}

		/* ========ID LAYOUT======== */
			#top {
			   width:780px;
			   margin:30px auto;
			   border:1px solid #333;
			}

			#header {
			   width: 100%;
			   height: 80px;
			   background-color: black;
			}

			#main {
			   width: 100%;
			   height: 500px;
			   text-align: center;
			}

			#footer {
				width: 100%;
				height: 80px;
				background-color: black;
				clear:both;
			}

			#text-right {
				display: inline-block;
				text-align: right;
			}
		</style>
	</head>
	<body>
		<div id="header">
		 	<div id="pr">
			</div>
		</div>
		<div id="main">
			<div id="top">
				<p>MyPage</p>
			</div>
			<div>
			<!-- 【s:ifタグ】test属性に指定された式を評価し成立した場合の記述が出力対象  -->
				<!-- ■□ myPageListに値が入っていない場合 □■ -->
				<s:if test="myPageList == null">
					<h3>ご購入情報はありません。</h3>
				</s:if>
				<!-- ■□ myPageListに値が入っていて削除メッセージがnullの場合 □■  -->
				<s:elseif test="message == null">
					<h3>ご購入情報は以下になります。</h3>
					<table border="1">
						<tr>
							<th>商品名</th>
							<th>値段</th>
							<th>購入個数</th>
							<th>支払い方法</th>
							<th>購入日</th>
						</tr>
						<s:iterator value="myPageList">
						<!-- 【s:iteratorタグ】value属性に指定されたオブジェクトの個数だけ繰り返しを行う
							 このタグ内では繰り返し中のオブジェクトのプロパティを参照可能
							 コレクションの現在の要素を参照するために指定する属性はない
							 現在の要素は一時オブジェクトスコープにあり、値スタックの最上位に置かれているため
							 特定のオブジェクト id を指定しない s:property タグでアクセスすることができる	  -->
							<tr>
								<!-- 【s:propertyタグ】値の埋め込み：myPageListの現在の要素の各値をセット  -->
								<td><s:property value="itemName" /></td>
								<td><s:property value="totalPrice" /><span>円</span></td>
								<td><s:property value="totalCount" /><span>個</span></td>
								<td><s:property value="payment" /></td>
								<td><s:property value="insert_date" /></td>
							</tr>
						</s:iterator>
					</table>
					<!-- 削除ボタン -->
					<s:form action="MyPageAction">
						<!-- 値スタックにより変数名「deleteFlg」がMyPageActionと共有される（←たぶん）
							「削除」ボタンをクリック時、非表示データ「deleteFlg」に値1がセットされ
							MyPageActionに送られdeleteメソッドが実行される -->
						<input type="hidden" name="deleteFlg" value="1">
						<s:submit value="削除" method="delete" />
					</s:form>
				</s:elseif>
				<!-- ■□ 削除メッセージがnullではない場合 □■  -->
				<s:if test="message != null">
					<!-- 削除メッセージmessageを画面表示 -->
					<h3><s:property value="message" /></h3>
				</s:if>
				<div id="text-right">
	<%-- 				<p>Home/へ戻る場合は<a href='<s:url action="GoHomeAction" />'>こちら</a></p> --%>
					<p>ログアウトする場合は<a href='<s:url action="LogoutAction" />'>こちら</a></p>
				</div>
			</div>
		</div>
		<div id="footer">
			<div id="pr">
			</div>
		</div>
	</body>
</html>