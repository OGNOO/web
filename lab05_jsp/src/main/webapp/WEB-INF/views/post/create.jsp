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
		<c:set var="pageTitle" value="New Post" scope="page" />
		<%@ include file="../fragments/header.jspf"%>
		<main>
			<div class="card mt-2">
				<div class="card-header text-center text-white bg-dark bg-gradient"
					style="margin-bottom: 0.5px;">
					<h2 style="margin-top: 0.25em; letter-spacing: 0.3em;">새 글 작성</h2>
				</div>
				<div class="card-body" style="padding: 0">
					<c:url var="newPostPage" value="/post/create" />
					<form method="post" action="${newPostPage}">
						<div class="m-2 ">
							<input class="form-control" type="text" name="title"
								placeholder="제목" required autofocus />
						</div>
						<div class="m-2">
							<textarea class="form-control" rows="5" name="content"
								placeholder="내용" required></textarea>
						</div>
						<div class="m-2">
							<input class="form-control" type="text" name="author"
								placeholder="작성자" required />
						</div>

						<div class="mt-3 m-2 d-flex gap-2  d-md-block">
							<input class="mb-1 form-control btn btn-success " type="submit"
								value="저장" /> <input id="cancle"
								class="mb-1 form-control btn btn-danger " value="취소" />
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
		const $title = document.querySelector('input[name="title"]');
		const $content = document.querySelector('textarea[name="content"]');
		const $author = document.querySelector('input[name="author"]');
		const $cancle = document.querySelector('#cancle');
	    		
		$cancle.addEventListener('click', () => {
			  $title.value='';
			  $content.value='';
			  $author.value='';
			});
	</script>
</body>
</html>
