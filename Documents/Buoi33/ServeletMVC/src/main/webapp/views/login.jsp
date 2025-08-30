<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Đăng nhập</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 30px; }
        form {
            width: 320px; margin: auto; border: 1px solid #ccc;
            padding: 20px; border-radius: 6px; box-shadow: 2px 2px 10px #ccc;
        }
        h2 { text-align: center; }
        .alert { color: red; text-align: center; margin-bottom: 10px; }
        input[type=text], input[type=password] {
            width: 95%; padding: 8px; margin: 5px 0 12px 0;
            border: 1px solid #ccc; border-radius: 4px;
        }
        button {
            width: 100%; padding: 8px; background: #007BFF;
            color: white; border: none; border-radius: 4px; cursor: pointer;
        }
        button:hover { background: #0056b3; }
    </style>
</head>
<body>

<form action="${pageContext.request.contextPath}/login" method="post">
    <h2>Đăng nhập</h2>

    <c:if test="${not empty alert}">
        <div class="alert">${alert}</div>
    </c:if>

    <label for="username">Tài khoản</label><br/>
    <input type="text" id="username" name="username" placeholder="Nhập tài khoản" />

    <label for="password">Mật khẩu</label><br/>
    <input type="password" id="password" name="password" placeholder="Nhập mật khẩu" />

    <label>
        <input type="checkbox" name="remember"/> Ghi nhớ đăng nhập
    </label>
    <br/><br/>

    <button type="submit">Đăng nhập</button>
    <p style="text-align:center; margin-top:10px;">
        Chưa có tài khoản? <a href="${pageContext.request.contextPath}/register">Đăng ký</a>
    </p>
</form>

</body>
</html>
    