<%@ page contentType="text/html;charset=UTF-8" %>
<%
    // Nếu có session role thì redirect theo role
    String role = (String) session.getAttribute("role");
    if ("ADMIN".equals(role)) {
        response.sendRedirect(request.getContextPath() + "/admin/dashboard");
    } else if ("USER".equals(role)) {
        response.sendRedirect(request.getContextPath() + "/home");
    } else {
        response.sendRedirect(request.getContextPath() + "/login");
    }
%>
