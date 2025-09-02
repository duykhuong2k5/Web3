<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Danh sách Category</title>
    <style>
        * {
            box-sizing: border-box;
            margin: 0;
            padding: 0;
        }
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background-color: #f5f5f5;
            padding: 20px;
            line-height: 1.6;
        }
        .container {
            max-width: 1200px;
            margin: 0 auto;
            background: white;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 2px 10px rgba(0,0,0,0.1);
        }
        h2 {
            color: #333;
            margin-bottom: 20px;
            text-align: center;
        }
        .header-actions {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 20px;
            padding: 15px;
            background: #f8f9fa;
            border-radius: 5px;
        }
        .btn {
            display: inline-block;
            padding: 10px 20px;
            background: #007bff;
            color: white;
            text-decoration: none;
            border-radius: 5px;
            transition: background 0.3s;
        }
        .btn:hover {
            background: #0056b3;
        }
        .btn-logout {
            background: #dc3545;
        }
        .btn-logout:hover {
            background: #c82333;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin: 20px 0;
            background: white;
        }
        th, td {
            padding: 15px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }
        th {
            background-color: #f8f9fa;
            font-weight: bold;
            color: #495057;
        }
        tr:hover {
            background-color: #f8f9fa;
        }
        .actions {
            white-space: nowrap;
        }
        .actions a {
            display: inline-block;
            padding: 5px 10px;
            margin: 0 2px;
            text-decoration: none;
            border-radius: 3px;
            font-size: 14px;
        }
        .edit-btn {
            background: #28a745;
            color: white;
        }
        .edit-btn:hover {
            background: #218838;
        }
        .delete-btn {
            background: #dc3545;
            color: white;
        }
        .delete-btn:hover {
            background: #c82333;
        }
        .category-icon {
            text-align: center;
        }
        .category-icon img {
            max-width: 60px;
            max-height: 60px;
            border-radius: 5px;
            border: 2px solid #dee2e6;
            padding: 2px;
            background: white;
            object-fit: contain;
        }
        .empty-state {
            text-align: center;
            padding: 40px;
            color: #6c757d;
        }
        .empty-state a {
            color: #007bff;
            text-decoration: none;
        }
        .empty-state a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>📋 Danh sách Category</h2>

        <div class="header-actions">
            <a href="${pageContext.request.contextPath}/category/add" class="btn">➕ Thêm Category</a>
            <a href="${pageContext.request.contextPath}/logout" class="btn btn-logout">🚪 Đăng xuất</a>
        </div>

        <c:if test="${not empty list}">
            <table>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Tên danh mục</th>
                        <th>Icon</th>
                        <th>Thao tác</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="c" items="${list}">
                        <tr>
                            <td>${c.cateid}</td>
                            <td>${c.catename}</td>
                            <td class="category-icon">
                                <img src="${pageContext.request.contextPath}/${c.icon}" 
                                     onerror="this.src='${pageContext.request.contextPath}/images/default.png'" 
                                     alt="Icon ${c.catename}"
                                     title="${c.icon}">
                            </td>
                            <td class="actions">
                                <a href="${pageContext.request.contextPath}/category/edit?id=${c.cateid}" 
                                   class="edit-btn">✏️ Sửa</a>
                                <a href="${pageContext.request.contextPath}/category/delete?id=${c.cateid}" 
                                   class="delete-btn"
                                   onclick="return confirm('Bạn có chắc chắn muốn xóa danh mục \"${c.catename}\" không?')">
                                   🗑️ Xóa
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>

        <c:if test="${empty list}">
            <div class="empty-state">
                <p>📝 Bạn chưa có danh mục nào.</p>
                <p><a href="${pageContext.request.contextPath}/category/add">Tạo danh mục đầu tiên của bạn</a></p>
            </div>
        </c:if>
    </div>

    <script>
    // Xử lý lỗi ảnh trên toàn trang
    document.addEventListener('DOMContentLoaded', function() {
        const images = document.querySelectorAll('img');
        images.forEach(img => {
            img.onerror = function() {
                this.src = '${pageContext.request.contextPath}/images/default.png';
            };
        });
    });
    </script>
</body>
</html>