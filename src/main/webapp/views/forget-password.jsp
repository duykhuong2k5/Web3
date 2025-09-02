<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Forget Password</title>
</head>
<body>
<h2>Quên mật khẩu</h2>
<form action="${pageContext.request.contextPath}/forget-password" method="post">
    <label>Email:</label>
    <input type="email" name="email" required>
    <br>
    <label>Mật khẩu mới:</label>
    <input type="password" name="newPassword" required>
    <br>
    <button type="submit">Đặt lại mật khẩu</button>
</form>

<c:if test="${not empty message}">
    <p style="color:red">${message}</p>
</c:if>
</body>
</html>
