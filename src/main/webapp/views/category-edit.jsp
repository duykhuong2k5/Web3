<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head><title>Sửa danh mục</title></head>
<body>
<h2>Sửa danh mục</h2>
<form action="${pageContext.request.contextPath}/category/edit" method="post">
    <input type="hidden" name="id" value="${cate.cateid}">
    Tên: <input type="text" name="name" value="${cate.catename}"><br>
    Ảnh: <input type="text" name="icon" value="${cate.icon}"><br>
    <button type="submit">Cập nhật</button>
</form>
</body>
</html>
