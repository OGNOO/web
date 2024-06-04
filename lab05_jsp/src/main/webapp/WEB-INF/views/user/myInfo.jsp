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
		<c:set var="pageTitle" value="Post Details" />
		<%-- scope 의 기본값은 page --%>
		<%@ include file="../fragments/header.jspf"%>

		<main>
			<div class="card mt-2">
				<div class="card-header text-center text-white bg-dark bg-gradient"
					style="margin-bottom: 0.5px">
					<h2 style="margin-top: 0.25em; letter-spacing: 0.3em">${ signedInUser }
						님의 정보</h2>
				</div>
				<div class="card-body" style="padding: 0">
					<c:url var="myinfoUpdatePage" value="/user/myinfoUpdate" />
					<form method="post" action="${myinfoUpdatePage}">
						<div class="m-2">
							<label for="id" class="form-label badge bg-dark"
								style="font-size: medium; line-height: 130%">번호</label><input
								id="id" class="mb-3 form-control" type="text" name="id"
								value="${ user.id }" readonly />
						</div>
						<div class="m-2">
							<label for="userId" class="form-label badge bg-dark"
								style="font-size: medium; line-height: 130%">아이디</label> <input
								id="title" class="mb-3 form-control" type="text" name="title"
								value="${ user.userId }" readonly />
						</div>
						<div class="m-2">
							<label for="password" class="form-label badge bg-dark"
								style="font-size: medium; line-height: 130%">비밀번호</label> <input
								id="password" class="mb-3 form-control" type="password"
								name="password" value="${ user.password }" readonly />
						</div>
						<div class="m-2">
							<label for="email" class="form-label badge bg-dark"
								style="font-size: medium; line-height: 130%">이메일</label> <input
								id="email" class="mb-3 form-control" type="text" name="email"
								value="${ user.email }" />
						</div>
						<div class="m-2">
							<label for="points" class="form-label badge bg-dark"
								style="font-size: medium; line-height: 130%">포인트</label> <input
								id="points" class="mb-3 form-control" type="text" name="points"
								value="${ user.points }" readonly />
						</div>
						<div class="mt-3 m-2 d-flex gap-2  d-md-block">
							<input class="mb-1 form-control btn btn-success " type="submit"
								value="정보 수정" />
						</div>
					</form>
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
