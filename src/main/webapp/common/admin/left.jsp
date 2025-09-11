<%@ page contentType="text/html;charset=UTF-8" %>
<aside class="bg-light border-end p-3" style="min-height:100vh;">
    <h6>Admin Menu</h6>
    <ul class="nav flex-column">
        <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/admin/dashboard">Dashboard</a></li>
        <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/admin/users">Users</a></li>
        <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/admin/reports">Reports</a></li>
    </ul>
</aside>
