<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<link href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/5.3.3/css/bootstrap.min.css" rel="stylesheet" />
<link href="https://cdn.datatables.net/2.3.3/css/dataTables.bootstrap5.css" rel="stylesheet" />

<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/5.3.3/js/bootstrap.bundle.min.js"></script>
<script src="https://cdn.datatables.net/2.3.3/js/dataTables.js"></script>
<script src="https://cdn.datatables.net/2.3.3/js/dataTables.bootstrap5.js"></script>

<div class="container my-3">
    <form action="${pageContext.request.contextPath}/logout" method="get">
        <button type="submit" class="btn btn-danger">Đăng xuất</button>
    </form>

    <%-- Chỉ hiển thị nút "Thêm mới" cho Admin và User --%>
    <c:if test="${sessionScope.roleId == 0 || sessionScope.roleId == 2}">
        <a href="<c:url value='/views/add-category.jsp'/>" class="btn btn-success">Thêm mới danh mục</a>
    </c:if>
</div>

<table id="example" class="table table-striped">
    <thead>
        <tr>
            <th>STT</th>
            <th>Icon</th>
            <th>Tên danh mục</th>
            <%-- Chỉ hiển thị cột "Hành động" cho Admin và User --%>
            <c:if test="${sessionScope.roleId == 0 || sessionScope.roleId == 2}">
                <th>Hành động</th>
            </c:if>
        </tr>
    </thead>
    <tbody>
        <c:forEach items="${cateList}" var="cate" varStatus="STT">
            <tr class="odd gradeX">
                <td>${STT.index + 1}</td>
                <c:url value="/image?fname=${cate.icon}" var="imgUrl"></c:url>
                <td><img height="150" width="200" src="${imgUrl}" /></td>
                <td>${cate.categoryName}</td>

                <%-- Chỉ hiển thị các nút "Sửa" và "Xóa" cho Admin và User --%>
                <c:if test="${sessionScope.roleId == 0 || sessionScope.roleId == 2}">
                    <td>
                        <a href="<c:url value='/categories/update?id=${cate.id}'/>" class="center">Sửa</a> |
                        <a href="<c:url value='/categories/delete?id=${cate.id}'/>" class="center">Xóa</a>
                    </td>
                </c:if>
            </tr>
        </c:forEach>
    </tbody>
    <tfoot>
        <tr>
            <th>STT</th>
            <th>Icon</th>
            <th>Tên danh mục</th>
            <%-- Chỉ hiển thị cột "Hành động" ở footer cho Admin và User --%>
            <c:if test="${sessionScope.roleId == 0 || sessionScope.roleId == 2}">
                <th>Hành động</th>
            </c:if>
        </tr>
    </tfoot>
</table>

<script>
    $(document).ready(function() {
        $('#example').DataTable({
            "paging": true,
            "ordering": true,
            "info": true,
        });
    });
</script>