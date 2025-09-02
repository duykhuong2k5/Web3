<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Form Category</title>
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
            max-width: 600px;
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
        .form-group {
            margin-bottom: 20px;
        }
        label {
            display: block;
            margin-bottom: 5px;
            font-weight: bold;
            color: #555;
        }
        input[type="text"] {
            width: 100%;
            padding: 12px;
            border: 2px solid #ddd;
            border-radius: 5px;
            font-size: 16px;
            transition: border-color 0.3s;
        }
        input[type="text"]:focus {
            outline: none;
            border-color: #007bff;
        }
        .preview {
            margin-top: 15px;
            text-align: center;
        }
        .preview img {
            max-width: 100px;
            max-height: 100px;
            border: 2px solid #ddd;
            border-radius: 5px;
            padding: 5px;
            background: #f8f9fa;
        }
        .btn {
            display: inline-block;
            padding: 12px 30px;
            background: #007bff;
            color: white;
            text-decoration: none;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-size: 16px;
            transition: background 0.3s;
        }
        .btn:hover {
            background: #0056b3;
        }
        .back-link {
            display: inline-block;
            margin-top: 20px;
            color: #007bff;
            text-decoration: none;
        }
        .back-link:hover {
            text-decoration: underline;
        }
        .help-text {
            font-size: 12px;
            color: #666;
            margin-top: 5px;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>
            <c:choose>
                <c:when test="${category != null}">✏️ Cập nhật Category</c:when>
                <c:otherwise>➕ Thêm mới Category</c:otherwise>
            </c:choose>
        </h2>

        <form action="${pageContext.request.contextPath}/category" method="post">
            <c:if test="${category != null}">
                <input type="hidden" name="id" value="${category.cateid}" />
            </c:if>

            <div class="form-group">
                <label for="name">Tên danh mục:</label>
                <input type="text" id="name" name="name" 
                       value="${category != null ? category.catename : ''}" 
                       required
                       placeholder="Nhập tên danh mục"/>
            </div>

            <div class="form-group">
                <label for="icon">Icon (tên file ảnh):</label>
                <input type="text" id="icon" name="icon" 
                       value="${category != null ? category.icon.replace('images/', '') : ''}" 
                       oninput="updatePreview()"
                       placeholder="ví dụ: shirt.png"
                       required/>
                <div class="help-text">Chỉ nhập tên file, ví dụ: shirt.png, pants.jpg</div>
            </div>

            <div class="form-group preview">
                <label>Xem trước:</label>
                <img id="iconPreview" 
                     src="${pageContext.request.contextPath}/images/${category != null ? category.icon.replace('images/', '') : 'default.png'}" 
                     onerror="this.src='${pageContext.request.contextPath}/images/default.png'" 
                     alt="Preview icon"/>
            </div>

            <button type="submit" class="btn">💾 Lưu</button>
        </form>

        <br/>
        <a href="${pageContext.request.contextPath}/category" class="back-link">← Quay lại danh sách</a>
    </div>

    <script>
    function updatePreview() {
        const iconInput = document.getElementById('icon');
        const preview = document.getElementById('iconPreview');
        let filename = iconInput.value.trim();
        
        // Loại bỏ "images/" nếu có
        filename = filename.replace('images/', '');
        
        if (filename) {
            // Thêm extension nếu thiếu
            if (!filename.includes('.')) {
                filename += '.png';
            }
            preview.src = '${pageContext.request.contextPath}/images/' + filename;
        } else {
            preview.src = '${pageContext.request.contextPath}/images/default.png';
        }
        
        // Xử lý khi ảnh không tồn tại
        preview.onerror = function() {
            this.src = '${pageContext.request.contextPath}/images/default.png';
        };
    }
    
    // Khởi tạo preview khi trang load
    document.addEventListener('DOMContentLoaded', function() {
        updatePreview();
    });
    </script>
</body>
</html>