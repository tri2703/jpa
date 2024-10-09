<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<!-- Add Video Form -->
<form action="<c:url value='/admin/video/insert'></c:url>" method="post" enctype="multipart/form-data">
    <label for="videoId">Video ID:</label><br>
    <input type="text" id="videoId" name="videoId" required><br>

    <label for="videoTitle">Video Title:</label><br>
    <input type="text" id="videoTitle" name="title" required><br>

    <label for="viewCount">View Count:</label><br>
    <input type="number" id="viewCount" name="views" value="0" min="0"><br>

    <label for="category">Category:</label><br>
    <select id="category" name="categoryId" required>
        <c:forEach items="${listCategories}" var="category">
            <option value="${category.categoryId}">${category.categoryname}</option>
        </c:forEach>
    </select><br>

    <label for="description">Description:</label><br>
    <textarea id="description" name="description" rows="4" cols="50"></textarea><br>

    <label>Status:</label><br>
    <input type="radio" id="active" name="active" value="1" checked> Hoạt động<br>
    <input type="radio" id="inactive" name="active" value="0"> Khóa<br>

    <label for="poster">Poster:</label><br>
    <input type="file" id="poster" name="poster" accept="image/*" required onchange="previewImage(event)"><br>
    <img id="posterPreview" height="150" width="200" style="display:none;"/><br>

    <input type="submit" value="Create">
    <input type="button" value="Reset" onclick="window.location.href='<c:url value='/admin/video/add'></c:url>'">
</form>

<script>
    function previewImage(event) {
        const file = event.target.files[0];
        const reader = new FileReader();
        reader.onload = function(e) {
            const img = document.getElementById('posterPreview');
            img.src = e.target.result;
            img.style.display = 'block'; // Show the image preview
        };
        reader.readAsDataURL(file);
    }
</script>

