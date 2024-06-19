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
<title>Spring Legacy 2</title>
</head>
<body>
	<div class="container-fluid">
		<c:set var="pageTitle" value="New Post" scope="page" />
		<%@ include file="../fragments/header.jspf"%>
		<main>
			<div class="card mt-2">
				<div class="card-header text-center text-white bg-dark bg-gradient"
					style="margin-bottom: 0.5px">
					<h2 style="margin-top: 0.25em; letter-spacing: 0.3em">로그인</h2>
				</div>
				<div class="card-body" style="padding: 0">
					<c:url var="signin" value="/user/signin" />
					<form method="post" action="${ signin }">
						<div class="ms-5 me-5 mt-5">
							<input class="form-control" name="userId" type="text"
								placeholder="아이디를 입력해 주세요" required autofocus />
						</div>
						<div class="ms-5 me-5 mt-3">
							<input id="pw" class="form-control" name="password"
								type="password" placeholder="비밀번호를 입력해 주세요" required />
						</div>
						<div class="ms-5 me-5 mt-3 mb-3 text-center">
							<input id="btnSubmit" class="mb-1 btn btn-success" type="submit"
								value="로그인" />
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
	<script>
		function getCookieValue(cookieName) {
			const name = cookieName + "=";
			const decodedCookie = decodeURIComponent(document.cookie);
			const cookieArray = decodedCookie.split(';');

			for (let i = 0; i < cookieArray.length; i++) {
				let cookie = cookieArray[i];

				while (cookie.charAt(0) == ' ') {
					cookie = cookie.substring(1);
				}

				if (cookie.indexOf(name) == 0) {
					return cookie.substring(name.length, cookie.length);
				}
			}
			return "";
		}

		const resultCookieValue = getCookieValue('result');
		console.log(resultCookieValue);

		function deleteCookie(name) {
			document.cookie = name + '=; Max-Age=-99999999;';
		}

		if (resultCookieValue === 'f') {
			deleteCookie('result');
			alert("비밀번호가 일치하지 않습니다.");
		}
	</script>
</body>
</html>
