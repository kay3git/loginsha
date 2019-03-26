<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv="Content-Style-Type" content="text/css" />
	<meta http-equiv="Content-Script-Type" content="text/javascript" />
	<meta http-equiv="imagetoolbar" content="no" />
	<meta name="description" content="" />
	<meta name="keywords" content="" />
	<meta charset="utf-8">
	<title>BuyItem画面</title>
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
	</style>
</head>
<body>
	<div id="header">
	 	<div id="pr">
		</div>
	</div>
	<div id="main">
		<div id="top">
			<p>BuyItem</p>
		</div>
		<div>
		<s:form action="BuyItemAction">
			<table border="1">
				<tr>
					<th>商品名</th>
					<th>値段</th>
					<th>在庫</th>
					<th>支払い方法</th>
				</tr>
				<s:iterator value="buyItemList">
				<!-- 【s:iteratorタグ】value属性に指定されたオブジェクトの個数だけ繰り返しを行う
					 このタグ内では繰り返し中のオブジェクトのプロパティを参照可能
					 コレクションの現在の要素を参照するために指定する属性はない
					 現在の要素は一時オブジェクトスコープにあり、値スタックの最上位に置かれているため
					 特定のオブジェクト id を指定しない s:property タグでアクセスすることができる	  -->
					<tr>
						<!-- 【s:propertyタグ】値の埋め込み：buyItemListの現在の要素の各値をセット  -->
						<td><s:property value="itemName" /></td>
						<td><s:property value="itemPrice" /><span>円</span></td>
						<td><s:property value="totalCount" /><span>個</span></td>
						<td>
							<input type="radio" name="pay" value="1" checked="checked">現金払い
							<input type="radio" name="pay" value="2">クレジットカード
						</td>
					</tr>
				</s:iterator>
					<tr>
						<td>
							<s:submit value="購入"/>
						</td>
					</tr>
			</table>

<%-- 			<table>
				<tr>
					<td>
						<span>商品名</span>
					</td>
					<td>
<!-- 					以下の一行書き直し
						<s:property value="loginUserInfoMap.buyItem_name" />
	 					以上の一行書き直し -->
						<s:property value="#session.buyItem_name" />
					</td>
				</tr>
				<tr>
					<td>
						<span>値段</span>
					</td>
					<td>
<!-- 					以下の一行書き直し
						<s:property value="loginUserInfoMap.buyItem_price" /><span>円</span>
						以上の一行書き直し -->
						<s:property value="#session.buyItem_price" /><span>円</span>
					</td>
				</tr>
				<tr>
					<td>
						<span>在庫</span>
					</td>
					<td>
						<select name="stock">
							<option value="1" selected="selected">1</option>
							<option value="2">2</option>
							<option value="3">3</option>
							<option value="4">4</option>
							<option value="5">5</option>
						</select>
					</td>
				</tr>
				<tr>
					<td>
						<span>支払い方法</span>
					</td>
					<td>
						<input type="radio" name="pay" value="1" checked="checked">現金払い
						<input type="radio" name="pay" value="2">クレジットカード
					</td>
				</tr>
				<tr>
					<td>
						<s:submit value="購入"/>
					</td>
				</tr>
			</table> --%>
		</s:form>
			<div>
				<p>前画面に戻る場合は<a href='<s:url action="HomeAction" />'>こちら</a></p>
				<p>マイページは<a href='<s:url action="MyPageAction" />'>こちら</a></p>
			</div>
		</div>
	</div>
	<div id="footer">
	 	<div id="pr">
		</div>
	</div>
</body>
</html>