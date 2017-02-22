<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/script.js"></script>
<title>ユーザ管理</title>
</head>
<body>
	<!-- Begin vung header -->
	<jsp:include page="header.jsp" />
	<!-- End vung header -->

	<!-- Begin vung input-->
	<form action="addUserOk.login?sessionId=<c:out value="${sessionId}"></c:out>"
		method="post" name="inputform">
		<table class="tbl_input" border="0" width="75%" cellpadding="0"
			cellspacing="0">
			<tr>
				<th align="left">
					<div style="padding-left: 100px;">
						情報確認<br> 入力された情報をＯＫボタンクリックでＤＢへ保存してください
					</div>
					<div style="padding-left: 100px;">&nbsp;</div>
				</th>
			</tr>
			<tr>
				<td align="left">
					<div style="padding-left: 100px;">
						<table border="1" width="70%" class="tbl_input" cellpadding="4"
							cellspacing="0">
							<tr>
								<td class="lbl_left">アカウント名:</td>
								<td align="left"><c:out value="${userInfo.userName }"></c:out></td>
							</tr>
							<tr>
								<td class="lbl_left">グループ:</td>
								<td align="left"><c:forEach items="${listGroup}"
										var="group">
										<c:choose>
											<c:when test="${group.groupId == userInfo.groupId}">
												<c:out value="${group.groupName}"></c:out>
											</c:when>
										</c:choose>
									</c:forEach></td>
							</tr>
							<tr>
								<td class="lbl_left">氏名:</td>
								<td align="left"><c:out value="${userInfo.fullName }"></c:out></td>
							</tr>
							<tr>
								<td class="lbl_left">カタカナ氏名:</td>
								<td align="left">名<c:out value="${userInfo.katakanaName }"></c:out></td>
							</tr>
							<tr>
								<td class="lbl_left">生年月日:</td>
								<td align="left"><c:out value="${userInfo.birthday }"></c:out></td>
							</tr>
							<tr>
								<td class="lbl_left">メールアドレス:</td>
								<td align="left"><c:out value="${userInfo.email }"></c:out></td>
							</tr>
							<tr>
								<td class="lbl_left">電話番号:</td>
								<td align="left"><c:out value="${userInfo.tel }"></c:out></td>
							</tr>
							<tr>
								<th colspan="2"><a href="#" onclick="anThongTin()">日本語能力</a></th>
							</tr>
						</table>
						<div id="khungNgay" style="display: none">
							<table border="1" width="70%" class="tbl_input" cellpadding="4"
								cellspacing="0">
								<tr>
									<td class="lbl_left">資格:</td>
									<td align="left"><c:forEach items="${listJapan}"
											var="japan">
											<c:choose>
												<c:when test="${japan.codeLevel == userInfo.codeLevel}">
													<c:out value="${japan.nameLevel}"></c:out>
												</c:when>
											</c:choose>
										</c:forEach></td>
								</tr>
								<tr>
									<td class="lbl_left">資格交付日:</td>
									<td align="left"><c:out value="${userInfo.startDate }"></c:out></td>
								</tr>
								<tr>
									<td class="lbl_left">失効日:</td>
									<td align="left"><c:out value="${userInfo.endDate }"></c:out></td>
								</tr>
								<tr>
									<td class="lbl_left">点数:</td>
									<td align="left"><c:out value="${userInfo.total }"></c:out></td>
								</tr>
							</table>
						</div>
					</div>
				</td>
			</tr>
		</table>
		<div style="padding-left: 100px;">&nbsp;</div>
		<!-- Begin vung button -->
		<div style="padding-left: 45px;">
			<table border="0" cellpadding="4" cellspacing="0" width="300px">
				<tr>
					<th width="200px" align="center">&nbsp;</th>
					<td><input class="btn" type="submit" value="OK" /></td>
					<td><input class="btn" type="button"
						onclick="goToPage('addUserValidate.login?type=back&sessionId=<c:out value="${sessionId}"></c:out>')"
						value="戻る" /></td>
				</tr>
			</table>
		</div>
		<!-- End vung button -->
	</form>
	<!-- End vung input -->

	<!-- Begin vung footer -->
	<jsp:include page="footer.jsp" />
	<!-- End vung footer -->
</body>
</html>