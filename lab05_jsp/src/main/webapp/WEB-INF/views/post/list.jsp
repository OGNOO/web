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
				<div class="card-header text-center text-white bg-dark bg-gradient">
					<h2>POSTS</h2>
				</div>
				<div class="card-body">
					<table class="table table-hover table-dark table-striped">
						<thead>
							<tr>
								<td>NO</td>
								<td>Title</td>
								<td>Author</td>
								<td>M.T</td>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${ posts }" var="p">
								<tr>
									<td>${ p.id }</td>
									<td>${ p.title }</td>
									<td>${ p.author }</td>
									<td>${ p.modifiedTime }</td>
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
