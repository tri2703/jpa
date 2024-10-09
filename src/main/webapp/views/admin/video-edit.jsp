<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<!-- Edit Video Form -->
<form action="<c:url value='/admin/video/update'></c:url>" method="post" enctype="multipart/form-data">
    <input type="hidden" name="videoId" value="${video.videoId}">

    <label for="videoTitle">Video Title:</label><br>
    <input type="text" id="videoTitle" name="title" value="${video.title}" required><br>

    <label for="viewCount">View Count:</label><br>
    <input type="number" id="viewCount" name="views" value="${video.views}" min="0"><br>

    <label for="category">Category:</label><br>
    <select id="category" name="categoryId" required>
        <c:forEach items="${listCategories}" var="category">
            <option value="${category.categoryId}" ${video.category.categoryId == category.categoryId ? 'selected' : ''}>
                ${category.categoryname}
            </option>
        </c:forEach>
    </select><br>

    <label for="description">Description:</label><br>
    <textarea id="description" name="description" rows="4" cols="50" required>${video.description}</textarea><br>

    <label>Status:</label><br>
    <input type="radio" id="active" name="active" value="1" ${video.active == 1 ? 'checked' : ''}> Hoạt động<br>
    <input type="radio" id="inactive" name="active" value="0" ${video.active == 0 ? 'checked' : ''}> Khóa<br>

    <label for="poster">Poster:</label><br>

    <!-- Conditional Display of Current Poster Image -->
    <c:if test="${video.poster != null && video.poster != ''}">
        <c:choose>
            <c:when test="${video.poster.substring(0, 5) == 'https'}">
                <c:url value="${video.poster}" var="posUrl"></c:url>
            </c:when>
            <c:otherwise>
                <c:url value="/image?fname=${video.poster}" var="posUrl"></c:url>
            </c:otherwise>
        </c:choose>
        <img id="posterPreview" src="${posUrl}" height="150" width="200" alt="${video.title}"
             onerror="this.onerror=null; this.src='<c:url value='/images/default.jpg' />'"/><br>
    </c:if>
    
    <c:if test="${video.poster == null || video.poster == ''}">
        <img id="posterPreview" src="<c:url value='/images/default.jpg'/>" height="150" width="200" alt="Default Image"/><br>
    </c:if>

    <input type="file" id="poster" name="poster" accept="image/*" onchange="previewImage(event)"><br>

    <input type="submit" value="Update">
</form>

<script>
    function previewImage(event) {
        const file = event.target.files[0];
        const reader = new FileReader();
        reader.onload = function(e) {
            const img = document.getElementById('posterPreview');
            img.src = e.target.result; // Update src to display new image
        };
        reader.readAsDataURL(file);
    }
</script>

