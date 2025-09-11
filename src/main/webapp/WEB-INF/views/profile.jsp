<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglib.jsp" %>

<div class="container mt-4">
    <h2>Update Profile</h2>

    <form:form method="post" modelAttribute="userProfile" 
               action="${pageContext.request.contextPath}/profile/update" 
               enctype="multipart/form-data" class="mt-3">

        <div class="mb-3">
            <label for="fullname" class="form-label">Full Name</label>
            <form:input path="fullname" cssClass="form-control" id="fullname"/>
        </div>

        <div class="mb-3">
            <label for="phone" class="form-label">Phone</label>
            <form:input path="phone" cssClass="form-control" id="phone"/>
        </div>

        <div class="mb-3">
            <label for="image" class="form-label">Profile Image</label>
            <input type="file" name="image" id="image" class="form-control"/>
        </div>

        <button type="submit" class="btn btn-primary">Update</button>
    </form:form>

    <c:if test="${not empty userProfile.imagePath}">
        <div class="mt-3">
            <p>Ảnh hiện tại:</p>
            <img src="${pageContext.request.contextPath}/uploads/${userProfile.imagePath}" 
                 alt="Profile Image" class="img-thumbnail" style="max-width: 200px;"/>
        </div>
    </c:if>
</div>
