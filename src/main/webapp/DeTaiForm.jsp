<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Thêm Đề Tài Mới</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
            padding: 20px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }
        h2 {
            color: #333;
        }
        label {
            font-weight: bold;
        }
        .error-message {
            color: red;
            font-weight: bold;
        }
    </style>
</head>
<body>
    <h2>Thêm Đề Tài Mới</h2>

    <c:if test="${not empty errorMessage}">
        <div class="error-message">
            <p>${errorMessage}</p>
        </div>
    </c:if>

    <form action="/Tuan04_Bai6/DeTaiFormServlet" method="post">
        <label for="maDT">Mã Đề Tài:</label><br>
        <input type="text" id="maDT" name="maDT" value="${param.maDT}" required><br><br>

        <label for="tenDT">Tên Đề Tài:</label><br>
        <input type="text" id="tenDT" name="tenDT" value="${param.tenDT}" required><br><br>

        <label for="namDangKy">Năm Đăng Ký:</label><br>
        <select id="namDangKy" name="namDangKy" required>
            <c:forEach var="year" begin="2000" end="2030">
                <option value="${year}" ${param.namDangKy == year ? 'selected' : ''}>${year}</option>
            </c:forEach>
        </select><br><br>

        <label for="moTa">Mô Tả:</label><br>
        <textarea id="moTa" name="moTa" rows="4" cols="50">${param.moTa}</textarea><br><br>

        <label for="giangVien">Giảng Viên:</label><br>
        <select id="giangVien" name="giangVien" required>
            <option value="">Chọn Giảng Viên</option>
            <c:forEach var="giangVien" items="${giangVienList}">
                <option value="${giangVien.maGV}" ${param.giangVien == giangVien.maGV ? 'selected' : ''}>
                    ${giangVien.tenGV}
                </option>
            </c:forEach>
        </select><br><br>

        <input type="submit" value="Thêm">
    </form>
</body>
</html>
