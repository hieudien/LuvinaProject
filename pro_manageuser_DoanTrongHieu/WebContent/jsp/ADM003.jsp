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
	<form
		action="addUserValidate.login?sessionId=<c:out value="${sessionId}"></c:out>"
		method="post" name="inputform" accept-charset="UTF-8">
		<table class="tbl_input" border="0" width="75%" cellpadding="0"
			cellspacing="0">
			<tr>
				<th align="left">
					<div style="padding-left: 100px;">会員情報編集</div>
				</th>
			</tr>
			<tr>
				<td class="errMsg">
					<div style="padding-left: 120px">
						<c:forEach items="${listError}" var="error">
							<p>${error}</p>
						</c:forEach>
					</div>
				</td>
			</tr>
			<tr>
				<td align="left">
					<div style="padding-left: 100px;">
						<table border="0" width="100%" class="tbl_input" cellpadding="4"
							cellspacing="0">
							<tr>
								<td class="lbl_left"><font color="red">*</font> アカウント名:</td>
								<td align="left"><input class="txBox" type="text"
									name="userName"
									value="<c:out value="${userInfo.userName}"></c:out>" size="15"
									onfocus="this.style.borderColor='#0066ff';"
									onblur="this.style.borderColor='#aaaaaa';" /></td>

							</tr>
							<tr>
								<td class="lbl_left"><font color="red">*</font> グループ:</td>
								<td align="left"><select name="groupId">
										<c:forEach items="${listGroup}" var="group">
											<c:choose>
												<c:when test="${group.groupId == userInfo.groupId}">
													<option value="${group.groupId}" selected="selected">${group.groupName}</option>
												</c:when>
												<c:otherwise>
													<option value="${group.groupId}">${group.groupName}</option>
												</c:otherwise>
											</c:choose>
										</c:forEach>
								</select> <span>&nbsp;&nbsp;&nbsp;</span></td>
							</tr>
							<tr>
								<td class="lbl_left"><font color="red">*</font> 氏名:</td>
								<td align="left"><input class="txBox" type="text"
									name="fullName"
									value="<c:out value="${userInfo.fullName}"></c:out>" size="30"
									onfocus="this.style.borderColor='#0066ff';"
									onblur="this.style.borderColor='#aaaaaa';" /></td>
							</tr>
							<tr>
								<td class="lbl_left">カタカナ氏名:</td>
								<td align="left"><input class="txBox" type="text"
									name="katakanaName"
									value="<c:out value="${userInfo.katakanaName}"></c:out>"
									size="30" onfocus="this.style.borderColor='#0066ff';"
									onblur="this.style.borderColor='#aaaaaa';" /></td>
							</tr>
							<tr>
								<td class="lbl_left"><font color="red">*</font> 生年月日:</td>
								<td align="left"><select name="birthYear">
										<c:forEach items="${listYear}" var="year">
											<c:choose>
												<c:when test="${year == birthDayArray[0]}">
													<option value="${year}" selected="selected">${year}</option>
												</c:when>
												<c:otherwise>
													<option value="${year}">${year}</option>
												</c:otherwise>
											</c:choose>
										</c:forEach>
								</select>年 <select name="birthMonth">
										<c:forEach items="${listMonth}" var="month">
											<c:choose>
												<c:when test="${month == birthDayArray[1]}">
													<option value="${month}" selected="selected">${month}</option>
												</c:when>
												<c:otherwise>
													<option value="${month}">${month}</option>
												</c:otherwise>
											</c:choose>
										</c:forEach>
								</select>月 <select name="birthDay">
										<c:forEach items="${listDay}" var="day">
											<c:choose>
												<c:when test="${day == birthDayArray[2]}">
													<option value="${day}" selected="selected">${day}</option>
												</c:when>
												<c:otherwise>
													<option value="${day}">${day}</option>
												</c:otherwise>
											</c:choose>
										</c:forEach>
								</select>日</td>
							</tr>
							<tr>
								<td class="lbl_left"><font color="red">*</font> メールアドレス:</td>
								<td align="left"><input class="txBox" type="text"
									name="email" value="<c:out value="${userInfo.email}"></c:out>"
									size="30" onfocus="this.style.borderColor='#0066ff';"
									onblur="this.style.borderColor='#aaaaaa';" /></td>
							</tr>
							<tr>
								<td class="lbl_left"><font color="red">*</font>電話番号:</td>
								<td align="left"><input class="txBox" type="text"
									name="tel" value="<c:out value="${userInfo.tel}"></c:out>"
									size="30" onfocus="this.style.borderColor='#0066ff';"
									onblur="this.style.borderColor='#aaaaaa';" /></td>
							</tr>
							<tr>
								<td class="lbl_left"><font color="red">*</font> パスワード:</td>
								<td align="left"><input class="txBox" type="password"
									name="password" value="" size="30"
									onfocus="this.style.borderColor='#0066ff';"
									onblur="this.style.borderColor='#aaaaaa';" /></td>
							</tr>
							<tr>
								<td class="lbl_left">パスワード（確認）:</td>
								<td align="left"><input class="txBox" type="password"
									name="rePassword" value="" size="30"
									onfocus="this.style.borderColor='#0066ff';"
									onblur="this.style.borderColor='#aaaaaa';" /></td>
							</tr>
							<tr>
								<th align="left" colspan="2"><a href="#"
									onclick="anThongTin()">日本語能力</a></th>
							</tr>
						</table>
						<div id="khungNgay" style="display: none">
							<table class="">
								<tr>
									<td class="lbl_left">資格:</td>
									<td align="left"><select name="codeLevel">
											<c:forEach items="${listJapan}" var="japan">
												<c:choose>
													<c:when test="${japan.codeLevel == userInfo.codeLevel}">
														<option value="${japan.codeLevel}" selected="selected">${japan.nameLevel}</option>
													</c:when>
													<c:otherwise>
														<option value="${japan.codeLevel}">${japan.nameLevel}</option>
													</c:otherwise>
												</c:choose>
											</c:forEach>
									</select></td>
								</tr>
								<tr>

									<%-- •	// Start fix bug ID 56 – HieuDT 2017/02/22 --%>
									<td class="lbl_left"><font color="red">*</font> 資格交付日:</td>
									<td align="left"><select name="startYear">
											<c:forEach items="${listYear}" var="year">
												<c:choose>
													<c:when test="${year == startDayArray[0]}">
														<option value="${year}" selected="selected">${year}</option>
													</c:when>
													<c:otherwise>
														<option value="${year}">${year}</option>
													</c:otherwise>
												</c:choose>
											</c:forEach>
									</select>年 <select name="startMonth">
											<c:forEach items="${listMonth}" var="month">
												<c:choose>
													<c:when test="${month == startDayArray[1]}">
														<option value="${month}" selected="selected">${month}</option>
													</c:when>
													<c:otherwise>
														<option value="${month}">${month}</option>
													</c:otherwise>
												</c:choose>
											</c:forEach>
									</select>月 <select name="startDay">
											<c:forEach items="${listDay}" var="day">
												<c:choose>
													<c:when test="${day == startDayArray[2]}">
														<option value="${day}" selected="selected">${day}</option>
													</c:when>
													<c:otherwise>
														<option value="${day}">${day}</option>
													</c:otherwise>
												</c:choose>
											</c:forEach>
									</select>日</td>
								</tr>
								<tr>
									<td class="lbl_left"><font color="red">*</font> 失効日:</td>
									<%-- •	// End fix bug ID 56 – HieuDT 2017/02/22 --%>
									<td align="left"><select name="endYear">
											<c:forEach items="${endYear}" var="year">
												<c:choose>
													<c:when test="${year == endDayArray[0]}">
														<option value="${year}" selected="selected">${year}</option>
													</c:when>
													<c:otherwise>
														<option value="${year}">${year}</option>
													</c:otherwise>
												</c:choose>
											</c:forEach>
									</select>年 <select name="endMonth">
											<c:forEach items="${listMonth}" var="month">
												<c:choose>
													<c:when test="${month == endDayArray[1]}">
														<option value="${month}" selected="selected">${month}</option>
													</c:when>
													<c:otherwise>
														<option value="${month}">${month}</option>
													</c:otherwise>
												</c:choose>
											</c:forEach>
									</select>月 <select name="endDay">
											<c:forEach items="${listDay}" var="day">
												<c:choose>
													<c:when test="${day == endDayArray[2]}">
														<option value="${day}" selected="selected">${day}</option>
													</c:when>
													<c:otherwise>
														<option value="${day}">${day}</option>
													</c:otherwise>
												</c:choose>
											</c:forEach>
									</select>日</td>
								</tr>
								<tr>
									<td class="lbl_left">点数:</td>
									<td align="left"><input class="txBox" type="text"
										name="total" value="<c:out value="${userInfo.total}"></c:out>"
										size="5" onfocus="this.style.borderColor='#0066ff';"
										onblur="this.style.borderColor='#aaaaaa';" /></td>
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
					<td><input class="btn" type="submit" value="確認" /></td>
					<td><input class="btn" type="button"
						onclick="goToPage('listUser.login?type=page&value=<c:out value="${currentPage}"></c:out>')"
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