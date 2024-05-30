<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous" />
<title>Lab 5</title>
</head>
<body>
	<div class="container-fluid">
		<c:url var="pageTitle" value="Post 목록" scope="page" />
		<%@ include file="../fragments/header.jspf"%>

		<main>
			<div class="card mt-2">
				<div class="card-header text-center text-white bg-dark bg-gradient"
					style="margin-bottom: 0.5px;">
					<h2 style="margin-top: 0.25em; letter-spacing: 0.3em;">POSTS</h2>
				</div>
				<div class="card-body" style="padding: 0; overflow-x: auto;">
					<table class="table table-hover table-dark table-striped">
						<thead style="text-align: center;">
							<tr>
								<td style="width: 10%;">NO</td>
								<td style="width: 33%;">Title</td>
								<td style="width: 33%;">Author</td>
								<td>M.T</td>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${ posts }" var="p">
								<tr>
									<td style="white-space: nowrap; overflow: hidden;">${ p.id }</td>
									<td style="white-space: nowrap; overflow: hidden;"><c:url
											var="postDetailsPage" value="/post/details">
											<c:param name="id" value="${ p.id }"></c:param>
										</c:url> <a style="text-decoration: none; color: #fff"
										href="${postDetailsPage}">${ p.title }</a></td>
									<td style="white-space: nowrap; overflow: hidden;">${ p.author }</td>
									<td
										style="text-align: end; white-space: nowrap; overflow: hidden;">${ p.modifiedTime }</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</main>
	</div>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
		crossorigin="anonymous"></script>
</body>
</html>
