<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Quản lý Giảng Viên</title>
    <style>
        body {
            font-family: Arial, sans-serif;
        }
        
        .container {
            display: flex;
        }
        
        .form-container {
            width: 30%;
            padding: 20px;
            border-right: 1px solid #ddd;
        }
        
        .content {
            width: 70%;
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
    <h2>Quản lý Giảng Viên</h2>

    <div class="container">
        <div class="form-container">
            <h3>Thêm Giảng Viên Mới</h3>
            <form action="GiangVienFormServlet" method="POST">
                <label for="maGV">Mã GV:</label><br>
                <input type="text" id="maGV" name="maGV" required><br><br>
                
                <label for="tenGV">Tên GV:</label><br>
                <input type="text" id="tenGV" name="tenGV" required><br><br>
                
                <label for="linhVucNghienCuu">Lĩnh vực Nghiên cứu:</label><br>
                <input type="text" id="linhVuc" name="linhVuc" required><br><br>
                
                <label for="lienKet">Liên kết:</label><br>
                <input type="text" id="lienKet" name="lienKet"><br><br>
                
                <input type="submit" value="Thêm Giảng Viên">
            </form>
        </div>

        <div class="content">
            <h3>Danh Sách Giảng Viên</h3>
            <table>
                <thead>
                    <tr>
                        <th>Mã GV</th>
                        <th>Tên GV</th>
                        <th>Lĩnh vực</th>
                        <th>Liên Kết</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="gv" items="${giangVienList}">
                        <tr>
                            <td>${gv.maGV}</td>
                            <td>${gv.tenGV}</td>
                            <td>${fn:substring(gv.linhVuc, 0, 250)}...</td>
                            <td>${fn:substring(gv.lienKet, 0, 50)}...</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</body>
</html>
