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
    <title>Spring Legacy 2</title>
    <style>
      .form-control:focus {
        color: #212529;
        background-color: #fff;
        border-color: #000000;
        outline: 0;
        box-shadow: 0 0 1.5px 0.2rem rgb(255 255 255/ 44%);
      }

      td {
        white-space: nowrap;
        overflow: hidden;
      }

      .mt-td {
        text-align: end;
      }
    </style>
  </head>
  <body>
    <div class="container-fluid">
      <c:url var="pageTitle" value="Post 목록" scope="page" />
      <%@ include file="../fragments/header.jspf"%>

      <main>
        <div class="card mt-2">
          <div
            class="card-header text-center text-white bg-dark bg-gradient"
            style="margin-bottom: 0.5px"
          >
            <!-- <c:url var="postSearchPage" value="/post/search" />
            <form action="${ postSearchPage }" method="get"> -->
            <form style="margin-top: 0.25em; letter-spacing: 0.3em">
              <div class="row">
                <div class="col-3">
                  <select
                    id="searchSelect"
                    class="form-control"
                    name="category"
                  >
                    <option value="t">제목</option>
                    <option value="c">내용</option>
                    <option value="tc">제목+내용</option>
                    <option value="a">작성자</option>
                  </select>
                </div>
                <div class="col-7">
                  <input
                    type="text"
                    class="form-control"
                    name="keyword"
                    placeholder="검색어 입력"
                    required
                    autofocus
                  />
                </div>
                <div class="col-2">
                  <input
                    id="formSubmit"
                    type="submit"
                    class="form-control btn btn-secondary"
                    value="검색"
                  />
                </div>
              </div>
            </form>
          </div>
          <div class="card-body" style="padding: 0; overflow-x: auto">
            <table class="table table-hover table-dark table-striped">
              <thead style="text-align: center">
                <tr>
                  <td style="width: 10%">
                    <button
                      id="no"
                      class="btn btn-outline-light btn-sm"
                      style="width: 100%"
                    >
                      NO
                    </button>
                  </td>
                  <td style="width: 33%">
                    <button
                      id="title"
                      class="btn btn-outline-light btn-sm"
                      style="width: 70%"
                    >
                      Title
                    </button>
                  </td>
                  <td style="width: 33%">
                    <button
                      id="author"
                      class="btn btn-outline-light btn-sm"
                      style="width: 80%"
                    >
                      Author
                    </button>
                  </td>
                  <td>
                    <button
                      id="modifiedTime"
                      class="btn btn-outline-light btn-sm"
                      style="width: 70%"
                    >
                      M.T
                    </button>
                  </td>
                </tr>
              </thead>
              <tbody>
                <c:forEach items="${ posts }" var="p">
                  <tr>
                    <td style="white-space: nowrap; overflow: hidden">
                      ${ p.id }
                    </td>
                    <td style="white-space: nowrap; overflow: hidden">
                      <c:url var="postDetailsPage" value="/post/details">
                        <c:param name="id" value="${ p.id }"></c:param>
                      </c:url>
                      <a
                        style="text-decoration: none; color: #fff"
                        href="${postDetailsPage}"
                        >${ p.title }</a
                      >
                    </td>
                    <td style="white-space: nowrap; overflow: hidden">
                      ${ p.author }
                    </td>
                    <td
                      class="modifiedTime"
                      style="
                        text-align: end;
                        white-space: nowrap;
                        overflow: hidden;
                      "
                    >
                      ${ p.modifiedTime }
                    </td>
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
      crossorigin="anonymous"
    ></script>
    <script
      src="https://code.jquery.com/jquery-3.6.3.min.js"
      integrity="sha256-pvPw+upLPUjgMXY0G+8O0xUf+/Im1MZjXxxgOcBQBXU="
      crossorigin="anonymous"
    ></script>
    <script>
      const $$modifiedTime = document.querySelectorAll(".modifiedTime");
      const $formSubmit = document.querySelector("#formSubmit");
      $$modifiedTime.forEach((e) => {
        const trimmedDateString = e.innerText
          .substring(0, 19)
          .replace("T", " ");
        e.innerText = trimmedDateString;
      });

      function sortList(e, itemVal) {
        console.log(e.target.innerText);
        console.log(itemVal);

        $.ajax({
          url: "../post/sort",
          type: "get",
          data: {
            sortCategory: e.target.innerText,
            sort: itemVal,
          },
          // 요청이 성공했을 때 실행되는 부분
          success: function (res) {
            console.log("성공", res);
          },
          // 요청이 실패했을 때 실행되는 부분
          error: function () {
            console.log("실패");
          },
        });
      }

      const $no = document.querySelector("#no");
      let noVal = 1;

      $no.addEventListener("click", (e) => {
        if (noVal === 1) {
          noVal = 0;
        } else if (noVal === 0) {
          noVal = 1;
        }
        sortList(e, noVal);
      });

      const $title = document.querySelector("#title");
      let titleVal = 1;

      $title.addEventListener("click", () => {
        console.log(titleVal);
        if (titleVal === 1) {
          titleVal = 0;
        } else if (titleVal === 0) {
          titleVal = 1;
        }
      });

      const $author = document.querySelector("#author");
      let authorVal = 1;

      $author.addEventListener("click", () => {
        console.log(authorVal);
        if (authorVal === 1) {
          authorVal = 0;
        } else if (authorVal === 0) {
          authorVal = 1;
        }
      });

      const $modifiedTime = document.querySelector("#modifiedTime");
      let modifiedTimeVal = 1;

      $modifiedTime.addEventListener("click", () => {
        console.log(modifiedTimeVal);
        if (modifiedTimeVal === 1) {
          modifiedTimeVal = 0;
        } else if (modifiedTimeVal === 0) {
          modifiedTimeVal = 1;
        }
      });

      $formSubmit.addEventListener("click", (e) => {
        e.preventDefault();

        const $searchSelect = document.querySelector("#searchSelect");
        const $keyword = document.querySelector('[name="keyword"]');

        $.ajax({
          url: "../post/search",
          type: "get",
          data: {
            category: $searchSelect.value,
            keyword: $keyword.value,
          },
          // 요청이 성공했을 때 실행되는 부분
          success: function (res) {
            const $tbody = document.querySelector("tbody");
            const $newTbody = document.createElement("tbody");
            $tbody.parentNode.replaceChild($newTbody, $tbody);

            res.forEach((row) => {
              const $tr = document.createElement("tr");

              let aForNo = 0;
              Object.values(row).forEach((cell, index) => {
                const $td = document.createElement("td");
                if (index === 0) {
                  aForNo = cell;
                }
                if (index === 1) {
                  const $a = document.createElement("a");
                  $a.textContent = cell;
                  $a.href = "/spring2/post/details?id=" + aForNo;
                  $a.style.textDecoration = "none";
                  $a.style.color = "#fff";
                  $td.append($a);
                } else {
                  $td.textContent = cell;
                }
                if (index === 3) {
                  $td.classList.add("mt-td");
                }
                $tr.append($td);
              });
              $newTbody.append($tr);
            });

            console.log("성공", res);
          },
          // 요청이 실패했을 때 실행되는 부분
          error: function () {
            const $tbody = document.querySelector("tbody");
            const $newTbody = document.createElement("tbody");
            $tbody.parentNode.replaceChild($newTbody, $tbody);
          },
        });
      });
    </script>
  </body>
</html>
