<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>更新</title>
<link href="css/commons.css" rel="stylesheet">
</head>
<body>
  <div class="header">
    <h1 class="site_logo"><a href="menu.jsp">商品管理システム</a></h1>
    <div class="user">
      <c:if test="${not empty user}">
      	<p class="user_name">${user.name}さん、こんにちは</p>
      </c:if>
      <form class="logout_form" action="logout.jsp" method="get">
        <button class="logout_btn" type="submit">
          <img src="images/ドアアイコン.png">ログアウト</button>
      </form>
    </div>
  </div>

  <hr>

  <div class="insert">
    <div class="form_body">
      <c:if test="${not empty InsertMsg}">
      	<p class="error">${UpdateMsg}</p>
  	　</c:if>

      <form action="RenewalServlet" method="get">
        <fieldset class="label-130">
          <div>
            <label>商品ID</label>
            <input type="text" name="proId" value="${product.proId}" class="base-text">
            <input type="hidden" name="hydeId" value="${product.proId}" class="base-text">
            <c:if test="${not empty IdMsg}">
		      <span class="error">${IdMsg}</span>
		    </c:if>
          </div>
          <div>
            <label>商品名</label>
            <input type="text" name="proName" value="${product.proName}" class="base-text">
            <c:if test="${not empty NameMsg}">
		      <span class="error">${NameMsg}</span>
		    </c:if>
          </div>
          <div>
            <label>単価</label>
            <input type="text" name="price" value="${product.price}" class="base-text">
            <c:if test="${not empty PriceMsg}">
		      <span class="error">${PriceMsg}</span>
		    </c:if>
          </div>
          <div>
            <label>カテゴリ</label> <select name="cateId" class="base-text">
              <option value="1" selected>筆記具</option>
              <option value="2">紙製品</option>
              <option value="3">事務消耗品</option>
              <option value="4">オフィス機器</option>
              <option value="5">雑貨</option>
            </select>
          </div>
          <div>
            <label>商品説明</label>
            <textarea name="description" class="base-text">
${product.description}
            </textarea>
          </div>
          <div>
            <label>画像</label>
            <input type="file" name="file">
            <span class="error">エラーメッセージ</span>
          </div>
        </fieldset>
          <div class="btns">
            <button type="button" onclick="openModal()" class="basic_btn">更新</button>
            <input type="button" onclick="location.href='./menu.jsp'" value="メニューに戻る" class="cancel_btn">
          </div>
          <div id="modal">
            <p class="modal_message">更新しますか？</p>
            <div class="btns">
              <button type="submit" class="basic_btn">更新</button>
              <button type="button" onclick="closeModal()" class="cancel_btn">キャンセル</button>
            </div>
          </div>
      </form>
    </div>
  </div>
  <div id="fadeLayer"></div>
</body>
</html>
<script src="./js/commons.js"></script>