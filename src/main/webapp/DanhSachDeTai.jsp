	<%@ page contentType="text/html; charset=UTF-8" language="java"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
	<%@ taglib prefix="c" uri="jakarta.tags.core"%>
	
	<!DOCTYPE html>
	<html>
	<head>
	<meta charset="UTF-8">
	<title>Quản lý Đề Tài</title>
	<style>
	body {
		font-family: Arial, sans-serif;
	}
	
	.container {
		display: flex;
	}
	
	.menu {
		width: 20%;
		border-right: 1px solid #ddd;
		padding-right: 20px;
	}
	
	.menu ul {
		list-style-type: none;
		padding: 0;
	}
	
	.menu li {
		margin-bottom: 10px;
	}
	
	.menu li a {
		text-decoration: none;
		color: blue;
	}
	
	.menu li a:hover {
		text-decoration: underline;
	}
	
	.content {
		width: 80%;
		padding-left: 20px;
	}
	
	table {
		width: 100%;
		border-collapse: collapse;
	}
	
	table, th, td {
		border: 1px solid black;
	}
	
	th, td {
		padding: 10px;
		text-align: left;
	}
	</style>
	</head>
	<body>
		<form action="/Tuan04_Bai3/DanhSachTinTucServlet" method='GET'>
			<h2>Quản lý Đề tài</h2>
	
			<div class="container">
	
				<div class="menu">
					<h3>Giảng Viên</h3>
					<ul>
						<c:forEach var="giangVien" items="${giangVienList}">
							<li><a href="DanhSachDeTaiServlet?madm=${GiangVien.maGV}">
									${giangVien.tenGV} </a></li>
						</c:forEach>
					</ul>
				</div>
	
	
				<div class="content">
					<h3>Danh Sách Đề Tài</h3>
					<table>
						<thead>
							<tr>
								<th>Mã Đề tài</th>
								<th>Tên Đề tài</th>
								<th>Năm đăng ký</th>
								<th>Mô tả đề tài</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="dt" items="${deTaiList}">
								<tr>
									<td>${dt.maDT}</td>
									<td>${dt.tenDT}</td>
									<td>${fn:substring(dt.namDangKy, 0, 50)}...</td>
									<td>${fn:substring(tin.moTa, 0, 250)}...</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</form>
	</body>
	</html>