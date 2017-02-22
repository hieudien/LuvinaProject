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

	<!-- Begin vung dieu kien tim kiem -->
	<form action="listUser.login?type=search" method="post" name="mainform">
		<table class="tbl_input" border="0" width="90%" cellpadding="0"
			cellspacing="0">
			<tr>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td>会員名称で会員を検索します。検索条件無しの場合は全て表示されます。</td>
			</tr>
			<tr>
				<td width="100%">
					<table class="tbl_input" cellpadding="4" cellspacing="0">
						<tr>
							<td class="lbl_left">氏名:</td>
							<td align="left"><input class="txBox" type="text"
								name="name"
								value="<c:out value="${searchName}" escapeXml="true"/>"
								size="20" onfocus="this.style.borderColor='#0066ff';"
								onblur="this.style.borderColor='#aaaaaa';" /></td>
							<td></td>
						</tr>
						<tr>
							<td class="lbl_left">グループ:</td>
							<td align="left" width="80px">
							<select name="group_id">
									<c:forEach items="${listGroup}" var="group">
										<c:choose>
											<c:when test="${group.groupId == searchGroupId}">
												<option value="${group.groupId}" selected="selected">${group.groupName}</option>
											</c:when>
											<c:otherwise>
												<option value="${group.groupId}">${group.groupName}</option>
											</c:otherwise>
										</c:choose>

									</c:forEach>
							</select></td>
							<td align="left"><input class="btn" type="submit" value="検索" />
								<input class="btn" type="button" value="新規追加"
								onclick="goToPage('addUserInput.login')" /></td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		<!-- End vung dieu kien tim kiem -->
	</form>
	<!-- Begin vung hien thi danh sach user -->
	<table class="tbl_list" border="1" cellpadding="4" cellspacing="0"
		width="80%">

		<tr class="tr2">
			<th align="center" width="20px">ID</th>
			<c:choose>
				<c:when test="${listSortType[0] == 'ASC'}">
					<th align="left">氏名 <a
						href="listUser.login?type=sort&value=full_name" name="sort">▲▽</a>
					</th>
				</c:when>
				<c:otherwise>
					<th align="left">氏名 <a
						href="listUser.login?type=sort&value=full_name" name="sort">△▼</a>
					</th>
				</c:otherwise>
			</c:choose>

			<th align="left">生年月日</th>
			<th align="left">グループ</th>
			<th align="left">メールアドレス</th>
			<th align="left" width="70px">電話番号</th>

			<c:choose>
				<c:when test="${listSortType[1] == 'ASC'}">
					<th align="left">日本語能力 <a
						href="listUser.login?type=sort&value=code_level" name="sort">▲▽</a>
					</th>
				</c:when>
				<c:otherwise>
					<th align="left">日本語能力 <a
						href="listUser.login?type=sort&value=code_level" name="sort">△▼</a>
					</th>
				</c:otherwise>
			</c:choose>

			<c:choose>
				<c:when test="${listSortType[2] == 'ASC'}">
					<th align="left">失効日 <a
						href="listUser.login?type=sort&value=end_date" name="sort">▲▽</a>
					</th>
				</c:when>
				<c:otherwise>
					<th align="left">失効日 <a
						href="listUser.login?type=sort&value=end_date" name="sort">△▼</a>
					</th>
				</c:otherwise>
			</c:choose>

			<th align="left">点数</th>
		</tr>
		<c:if test="${fn:length(listUser) == 0}">
			<tr>
				<td colspan=9 style="color: red; font-size: 20px" align="center">検索条件に該当するユーザが見つかりません。</td>
			</tr>
		</c:if>
		<c:forEach items="${listUser}" var="user">
			<tr>
				<td align="right"><a href="detailUser.login?id=${user.id}">${user.id}</a></td>
				<td>${user.fullName}</td>
				<td align="center">${user.birthday}</td>
				<td>${user.group}</td>
				<td>${user.email}</td>
				<td>${user.tel}</td>
				<td>${user.nameLevel}</td>
				<td align="center">${user.endDate}</td>
				<td align="right">${user.total}</td>
			</tr>
		</c:forEach>
	</table>
	<!-- End vung hien thi danh sach user -->

	<!-- Begin vung paging -->
	<table>
		<c:if test="${fn:length(listPaging) > 1}">
			<tr class="lbl_paging">

				<c:if test="${listPaging[0]>3}">
					<td><a
						href="listUser.login?type=page&value=${listPaging[0]-3}">&lt;&lt;</a>&nbsp;</td>
				</c:if>
				<c:forEach items="${listPaging}" var="page">
					<c:if test="${page == currentPage}">
						<td>${page}&nbsp;</td>
					</c:if>
					<c:if test="${page != currentPage}">
						<td><a href="listUser.login?type=page&value=${page}">${page}</a>&nbsp;</td>
					</c:if>
				</c:forEach>
				<c:if test="${listPaging[2] < totalPage}">
					<td><a
						href="listUser.login?type=page&value=${listPaging[2]+1}">&gt;&gt;</a>&nbsp;</td>
				</c:if>
			</tr>
		</c:if>
	</table>
	<!-- End vung paging -->

	<!-- Begin vung footer -->
	<jsp:include page="footer.jsp" />
	<!-- End vung footer -->
</body>
</html>