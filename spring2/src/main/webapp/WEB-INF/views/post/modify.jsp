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
<title>Spring Legacy 5</title>
</head>
<body>
  <div class="container-fluid">
    <c:set var="pageTitle" value="Post Modify" />
    <%-- scope 의 기본값은 page --%>
    <%@ include file="../fragments/header.jspf"%>
    <main>
      <div class="card mt-2">
        <div
          class="card-header text-center text-white bg-dark bg-gradient"
          style="margin-bottom: 0.5px">
          <h2 style="margin-top: 0.25em; letter-spacing: 0.3em">수정
            페이지</h2>
        </div>
        <div class="card-body" style="padding: 0">
          <form id="modifyForm">
            <div class="m-2">
              <label for="id" class="form-label badge bg-dark"
                style="font-size: medium; line-height: 130%">번호</label>
              <input id="id" class="mb-3 form-control" type="text"
                name="id" readonly />
            </div>
            <div class="m-2">
              <label for="title" class="form-label badge bg-dark"
                style="font-size: medium; line-height: 130%">제목</label>
              <input id="title" class="mb-3 form-control" type="text"
                name="title" value="${ post.title }" required />
            </div>
            <div class="m-2">
              <label for="content" class="form-label badge bg-dark"
                style="font-size: medium; line-height: 130%">내용</label>
              <textarea id="content" class="mb-3 form-control" rows="5"
                name="content" placeholder="내용">${ post.content }</textarea>
            </div>
            <%-- <c:if test="${ post.author == signedInUser }"> --%>
            <div class="m-2">
              <label for="author" class="form-label badge bg-dark"
                style="font-size: medium; line-height: 130%">작성자</label>
              <input value="${ post.author }" id="author"
                class="mb-3 form-control" name="author" type="text"
                readonly />
            </div>
            <%-- </c:if> --%>
          </form>
        </div>
        <div class="card-footer text-center ">
          <button id="btnDelete" class="me-1 btn btn-outline-danger">삭제</button>
          <button id="btnUpdate" class="ms-1 btn btn-outline-success">업데이트</button>
        </div>
      </div>
    </main>
  </div>
  <script
    src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
    integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
    crossorigin="anonymous"></script>
  <c:url var="post_modify_js" value="../js/post_modify.js" />
  <script src="${ post_modify_js }"></script>
  <script>
			const $id = document.querySelector("#id");
			$id.value = `${ post.id }`;
		</script>
</body>
</html>
