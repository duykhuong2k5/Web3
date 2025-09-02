<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Đăng nhập</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background: #f7f7f7;
        }
        .login-container {
            width: 400px;
            margin: 100px auto;
            background: #fff;
            padding: 25px;
            border-radius: 8px;
            box-shadow: 0 0 8px rgba(0,0,0,0.2);
        }
        h2 {
            text-align: center;
            margin-bottom: 20px;
            color: #333;
        }
        input[type=text], input[type=password] {
            width: 100%;
            padding: 10px;
            margin: 6px 0 15px 0;
            border: 1px solid #ccc;
            border-radius: 4px;
        }
        .options {
            display: flex;
            justify-content: space-between;
            align-items: center;
            font-size: 14px;
        }
        .options a {
            text-decoration: none;
            color: #007BFF;
        }
        button {
            width: 100%;
            padding: 10px;
            background: #007BFF;
            border: none;
            color: white;
            font-size: 16px;
            border-radius: 4px;
            cursor: pointer;
        }
        button:hover {
            background: #0056b3;
        }
        .register-link {
            margin-top: 15px;
            text-align: center;
            font-size: 14px;
        }
        .register-link a {
            text-decoration: none;
            color: #007BFF;
        }
    </style>
</head>
<body>
<div class="login-container">
    <h2>Đăng Nhập Vào Hệ Thống</h2>

    <c:if test="${not empty alert}">
        <p style="color:red; text-align:center">${alert}</p>
    </c:if>

    <form action="${pageContext.request.contextPath}/login" method="post">
        <input type="text" name="username" placeholder="Tên đăng nhập" required>
        <input type="password" name="password" placeholder="Mật khẩu" required>

        <div class="options">
            <label>
                <input type="checkbox" name="remember"> Nhớ tôi
            </label>
            <a href="${pageContext.request.contextPath}/forget-password">Quên mật khẩu?</a>
        </div>

        <button type="submit">Đăng nhập</button>
    </form>

    <div class="register-link">
        Nếu bạn chưa có tài khoản, hãy 
        <a href="${pageContext.request.contextPath}/register">Đăng ký</a>
    </div>
</div>
</body>
</html>
