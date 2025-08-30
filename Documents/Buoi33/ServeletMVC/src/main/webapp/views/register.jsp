<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Đăng ký</title>
</head>
<body>
    <h2>Đăng ký tài khoản</h2>

    <c:if test="${alert != null}">
        <p style="color:red">${alert}</p>
    </c:if>

    <form action="${pageContext.request.contextPath}/register" method="post">
        <label>Họ và tên:</label><br/>
        <input type="text" name="fullname"/><br/><br/>

        <label>Tài khoản:</label><br/>
        <input type="text" name="username"/><br/><br/>

        <label>Mật khẩu:</label><br/>
        <input type="password" name="password"/><br/><br/>

        <button type="submit">Đăng ký</button>
    </form>

    <p>Đã có tài khoản? <a href="${pageContext.request.contextPath}/login">Đăng nhập</a></p>
</body>
</html>
