<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%> <%@ taglib prefix="c"
uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <link
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
      rel="stylesheet"
      integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
      crossorigin="anonymous"
    />
    <style>
      .commentBtn {
        text-align: center;
        padding-top: 12px;
        padding-left: 0;
        padding-right: 0;
      }
    </style>
    <title>Spring Legacy 2</title>
  </head>
  <body>
    <div class="container-fluid">
      <c:set var="pageTitle" value="Post Details" />
      <%-- scope 의 기본값은 page --%> <%@ include
      file="../fragments/header.jspf"%>

      <main>
        <div class="card mt-2 mb-3">
          <div
            class="card-header text-center text-white bg-dark bg-gradient"
            style="margin-bottom: 0.5px"
          >
            <h2 style="margin-top: 0.25em; letter-spacing: 0.3em">상세보기</h2>
          </div>
          <div class="card-body" style="padding: 0">
            <!--  <c:url var="newPostPage" value="/post/create" />-->
            <form>
              <div class="m-2">
                <label
                  for="id"
                  class="form-label badge bg-dark"
                  style="font-size: medium; line-height: 130%"
                  >번호</label
                >
                <input
                  id="id"
                  class="mb-3 form-control"
                  type="text"
                  value="${post.id }"
                  readonly
                />
              </div>
              <div class="m-2">
                <label
                  for="title"
                  class="form-label badge bg-dark"
                  style="font-size: medium; line-height: 130%"
                  >제목</label
                >
                <input
                  id="title"
                  class="mb-3 form-control"
                  type="text"
                  name="title"
                  value="${ post.title }"
                  readonly
                />
              </div>
              <div class="m-2">
                <label
                  for="content"
                  class="form-label badge bg-dark"
                  style="font-size: medium; line-height: 130%"
                  >내용</label
                >
                <textarea
                  id="content"
                  class="mb-3 form-control"
                  rows="5"
                  name="content"
                  placeholder="내용"
                  readonly
                >
${ post.content }</textarea
                >
              </div>
              <div class="m-2">
                <label
                  for="author"
                  class="form-label badge bg-dark"
                  style="font-size: medium; line-height: 130%"
                  >작성자</label
                >
                <input
                  id="author"
                  class="mb-3 form-control"
                  type="text"
                  name="author"
                  value="${ post.author }"
                  readonly
                />
              </div>
              <div class="m-2">
                <label
                  for="createdTime"
                  class="form-label badge bg-dark"
                  style="font-size: medium; line-height: 130%"
                  >작성시간</label
                >
                <input
                  id="createdTime"
                  class="mb-3 form-control"
                  type="text"
                  name="createdTime"
                  value="${ post.createdTime }"
                  readonly
                />
              </div>
              <div class="m-2">
                <label
                  for="modifiedTime"
                  class="form-label badge bg-dark"
                  style="font-size: medium; line-height: 130%"
                  >수정시간</label
                >
                <input
                  id="modifiedTime"
                  class="mb-3 form-control"
                  type="text"
                  name="modifiedTime"
                  value="${ post.modifiedTime }"
                  readonly
                />
              </div>
            </form>
          </div>
          <%--
          <c:if test="${ post.author == signedInUser }">
            --%>
            <div class="card-footer text-center">
              <c:url var="postModifyPage" value="/post/modify">
                <c:param name="id" value="${post.id}" />
              </c:url>
              <a class="btn btn-outline-primary" href="${postModifyPage}"
                >수정하기</a
              >
              <button
                id="btnToggleComment"
                class="btn btn-primary"
                type="button"
                style="margin-left: 1.5%"
                data-bs-toggle="collapse"
                data-bs-target="#collapseExample"
                aria-expanded="false"
                aria-controls="collapseExample"
              >
                댓글 보기
              </button>
            </div>
            <div class="collapse" id="collapseExample">
              <div
                class="card card-body"
                id="collapseComments"
                style="border-radius: 0 0 0.25rem 0.25rem"
              >
                <!-- 댓글등록 -->
                <div class="mt-2 card card-body">
                  <div
                    class="mt-2 row"
                    style="overflow: scroll; scrollbar-width: none"
                  >
                    <div class="col-10" style="padding-right: 0">
                      <!-- 댓글입력 -->
                      <textarea
                        class="form-control"
                        id="ctext"
                        rows="2"
                        placeholder="댓글 내용"
                      ></textarea>
                      <!-- 댓글 작성자 아이디: TODO: 로그인한 사용자의 아이디로 설정 -->
                      <input
                        id="userName"
                        placeholder="댓글 작성자"
                        type="text"
                      />
                    </div>
                    <div class="col-2 commentBtn">
                      <button
                        class="btn btn-outline-success"
                        id="btnRegisterComment"
                        style="min-width: 60px"
                      >
                        등록
                      </button>
                    </div>
                  </div>
                </div>

                <!-- 포스트에 달려 있는 댓글 목록을 보여줄 영역 -->
                <div class="mt-2" id="comments"></div>
              </div>
            </div>
            <%--
          </c:if>
          --%>
          <div id="commentModal" class="modal" tabindex="-1">
            <div
              class="modal-dialog modal-dialog-centered modal-dialog-scrollable"
            >
              <div class="modal-content">
                <div class="modal-header">
                  <h5 class="modal-title">댓글 업데이트</h5>
                  <button
                    type="button"
                    class="btn-close"
                    data-bs-dismiss="modal"
                    aria-label="Close"
                  ></button>
                </div>
                <div class="modal-body">
                  <input class="d-none" id="modalCommentId" />
                  <textarea
                    class="form-control"
                    id="modalCommentText"
                  ></textarea>
                </div>
                <div class="modal-footer">
                  <button
                    type="button"
                    class="btn btn-outline-secondary"
                    data-bs-dismiss="modal"
                  >
                    취소
                  </button>
                  <button
                    id="btnUpdateComment"
                    type="button"
                    class="btn btn-outline-primary"
                  >
                    저장
                  </button>
                </div>
              </div>
            </div>
          </div>
        </div>
      </main>
    </div>
    <script
      src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
      integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
      crossorigin="anonymous"
    ></script>
    <script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
    <script>
      const $createdTime = document.querySelector("#createdTime");
      const createdTimeTrimmedDateString = $createdTime.value
        .substring(0, 19)
        .replace("T", " ");
      $createdTime.value = createdTimeTrimmedDateString;

      const $modifiedTime = document.querySelector("#modifiedTime");
      const modifiedTimeTrimmedDateString = $modifiedTime.value
        .substring(0, 19)
        .replace("T", " ");
      $modifiedTime.value = modifiedTimeTrimmedDateString;
    </script>
    <c:url var="commentsJs" value="/js/comment.js"></c:url>
    <script src="${ commentsJs }"></script>
  </body>
</html>
