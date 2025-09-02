<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="vn.oistart.model.User" %>
<%
    User user = (User) session.getAttribute("account");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Trang Chủ</title>
</head>
<body>
    <h2>Xin chào, <%= user.getFullName() %> 👋</h2>
    <p>Email: <%= user.getEmail() %></p>
    <p>Username: <%= user.getUserName() %></p>

    <% if(user.getAvatar() != null) { %>
        <img src="<%= user.getAvatar() %>" alt="Avatar" width="120" />
    <% } %>

    <br><br>
    <a href="<%= request.getContextPath() %>/logout">Đăng xuất</a>
</body>
</html>
