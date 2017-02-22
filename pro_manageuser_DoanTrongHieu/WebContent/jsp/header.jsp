<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div>
		<div>
			<table>
				<tr>
					<td width="80%"><img src="images/logo-manager-user.gif"
						alt="Luvina" />
					<td>
					<td align="left"><a href="logout">ログアウト</a> &nbsp; <a
						href="listUser.login?type=page&value=<c:out value="${currentPage}"></c:out>">トップ</a>
						
						
					<td>
				</tr>
				<tr></tr>
			</table>
		</div>
	</div>
</body>
</html>