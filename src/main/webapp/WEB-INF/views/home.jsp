<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglib.jsp" %>

<div class="container mt-4">
    <h1>Welcome to MyApp</h1>
    <p>Đây là trang Home dành cho người dùng.</p>

    <c:if test="${not empty message}">
        <div class="alert alert-success">${message}</div>
    </c:if>
</div>
