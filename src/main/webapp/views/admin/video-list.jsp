<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<a href="<c:url value='/admin/video/add'></c:url>">Add Video</a>
<table border="1" width="100%">
	<tr>
		<th>STT</th>
		<th>Video ID</th>
		<th>Poster</th>
		<th>Video Title</th>
		<th>Description</th>
		<th>Views</th>
		<th>Category</th>
		<th>Status</th>
		<th>Action</th>
	</tr>

	<c:forEach items="${listVideo}" var="video" varStatus="loopStatus">
		<tr>
			<td>${loopStatus.index + 1}</td>
			<td>${video.videoId}</td>
			<td><c:if test="${video.poster != null && video.poster != ''}">
					<c:if test="${video.poster.substring(0, 5) != 'https'}">
						<c:url value="/image?fname=${video.poster}" var="posUrl"></c:url>
					</c:if>
					<c:if test="${video.poster.substring(0, 5) == 'https'}">
						<c:url value="${video.poster}" var="posUrl"></c:url>
					</c:if>
					<img height="150" width="200" src="${posUrl}" alt="${video.title}"
						onerror="this.onerror=null; this.src='<c:url value='/images/default.jpg' />'">
				</c:if> <c:if test="${video.poster == null || video.poster == ''}">
					<img height="150" width="200"
						src="<c:url value='/images/default.jpg'/>" alt="Default Image">
				</c:if></td>

			<td>${video.title}</td>
			<td>${video.description}</td>
			<td>${video.views}</td>
			<td><c:if test="${video.category != null}">
                    ${video.category.categoryname}
                </c:if></td>
			<td><c:choose>
					<c:when test="${video.active == 1}">
						<span>Hoạt động</span>
					</c:when>
					<c:otherwise>
						<span>Khóa</span>
					</c:otherwise>
				</c:choose></td>
			<td><a
				href="<c:url value='/admin/video/edit?id=${video.videoId}'/>">Edit</a>
				| <a href="<c:url value='/admin/video/delete?id=${video.videoId}'/>">Delete</a>
			</td>
		</tr>
	</c:forEach>
</table>

<!-- Tools -->
<p>Tools:</p>
<ul>
	<li><a href="<c:url value='/admin/video/print'/>">Print</a></li>
	<li><a href="<c:url value='/admin/video/exportToPdf'/>">Save
			as PDF</a></li>
	<li><a href="<c:url value='/admin/video/exportToExcel'/>">Export
			to Excel</a></li>
</ul>

