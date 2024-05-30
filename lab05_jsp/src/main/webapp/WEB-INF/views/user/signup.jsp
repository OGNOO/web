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
    <title>Lab 5</title>
  </head>
  <body>
    <div class="container-fluid">
      <c:set var="pageTitle" value="New Post" scope="page" />
      <%@ include file="../fragments/header.jspf"%>
      <main>
        <div class="card mt-2">
          <div
            class="card-header text-center text-white bg-dark bg-gradient"
            style="margin-bottom: 0.5px"
          >
            <h2 style="margin-top: 0.25em; letter-spacing: 0.3em">
              계정 만들기
            </h2>
          </div>
          <div class="card-body" style="padding: 0">
            <c:url var="signup" value="/user/signup" />
            <form method="post" action="${ signup }">
              <div class="ms-5 me-5 mt-5">
                <input
                  class="form-control"
                  name="userId"
                  type="text"
                  placeholder="아이디를 입력해 주세요"
                  required
                  autofocus
                />
              </div>
              <div class="ms-5 me-5 mt-3">
                <input
                  class="form-control"
                  name="email"
                  type="email"
                  placeholder="이메일 주소를 입력해 주세요"
                  required
                />
              </div>
              <div class="ms-5 me-5 mt-3">
                <input
                  id="pw"
                  class="form-control"
                  type="password"
                  placeholder="비밀번호를 입력해 주세요"
                  required
                />
              </div>
              <div class="ms-5 me-5 mt-3">
                <input
                  id="checkPw"
                  class="form-control"
                  name="password"
                  type="password"
                  placeholder="비밀번호를 재입력해 주세요"
                  required
                />
              </div>
              <div id="wrongPw" class="ms-5 me-5 mt-1" style="display: none">
                <p class="mb-0" style="color: red">
                  비밀번호가 일치하지 않습니다.
                </p>
              </div>
              <div class="ms-5 me-5 mt-3 mb-3 text-center">
                <input
                  id="btnSubmit"
                  class="mb-1 btn btn-success"
                  type="submit"
                  value="회원가입"
                />
              </div>
            </form>
          </div>
        </div>
      </main>
    </div>
    <script
      src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
      integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
      crossorigin="anonymous"
    ></script>
    <script>
      const $pw = document.querySelector("#pw");
      const $checkPw = document.querySelector("#checkPw");
      const $checkPwBox = document.querySelector("#checkPwBox");
      const $wrongPw = document.querySelector("#wrongPw");
      const $btnSubmit = document.querySelector("#btnSubmit");

      function checkPw() {
        if ($checkPw.value === $pw.value) {
          $wrongPw.style.display = "none";
          $btnSubmit.disabled = false;
        } else {
          $wrongPw.style.display = "";
          $btnSubmit.disabled = true;
        }
      }
      $pw.addEventListener("input", () => {
        checkPw();
      });
      $checkPw.addEventListener("input", () => {
        checkPw();
      });
    </script>
  </body>
</html>
