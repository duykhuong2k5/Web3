<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Thêm Danh Mục</title>
    <link rel="stylesheet" href="path/to/your/styles.css"> <!-- Link to your CSS file -->
</head>
<body>
    <h1>Thêm Danh Mục Mới</h1>
    <form role="form" action="${pageContext.request.contextPath}/categories/add" method="post" enctype="multipart/form-data">
        <div class="form-group">
            <label for="name">Tên danh mục:</label>
            <input type="text" class="form-control" placeholder="Please enter category Name" name="name" id="name" />
        </div>
        <div class="form-group">
            <label for="icon">Ảnh đại diện:</label>
            <input type="file" class="form-control" name="icon" id="icon" />
        </div>
        <button type="submit" class="btn btn-default">Thêm</button>
        <button type="reset" class="btn btn-primary">Hủy</button>
    </form>
</body>
</html>
