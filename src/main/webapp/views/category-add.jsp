<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head><title>Thêm danh mục</title></head>
<body>
<h2>Thêm danh mục</h2>
<form action="${pageContext.request.contextPath}/category/add" method="post">
    Tên: <input type="text" name="name"><br>
    Ảnh: <input type="text" name="icon" placeholder="shirt.png"><br>
    <button type="submit">Lưu</button>
</form>
</body>
</html>
