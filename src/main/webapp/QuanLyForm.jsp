<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Quản lý đề tài</title>
</head>
<body>
	<h2>Quản lý đề tài</h2>
	<table border="1">
		<tr>
			<th>Mã DT</th>
			<th>Tên DT</th>
			<th>Năm đăng ký</th>
			<th>Mô tả đề tài</th>
		</tr>
		<c:forEach var="dt" items="${deTaiList }">
			<tr>
				<td>${dt.maDT}</td>
				<td>${dt.tenDT}</td>
				<td>${dt.namDangKy}</td>
				<td>${dt.moTa}</td>
				<td>
					<form action="QuanLyFormServlet" method="post">
						<input type="hidden" name="maDT" value="${dt.maDT}"> <input
							type="submit" value="Xóa">
					</form>
				</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>