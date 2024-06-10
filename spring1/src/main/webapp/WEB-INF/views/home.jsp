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
    <title>Spring 1</title>
  </head>
  <body>
    <header>
      <h1>Home</h1>
      <h2>${ now }</h2>
      <c:url var="img" value="/images/img.jpg"/>
      <img alt="" src="${ img }">
      <!-- <img alt="" src="/spring1/images/img.jpg"> -->
      <!-- <img alt="" src="./images/img.jpg"> -->
    </header>
    <main>
      <h1>Contents</h1>
      <nav>
        <ul>
          <li>
            <c:url var="exPage" value="/example"></c:url>
            <a href="${ exPage }">컨트롤러 예제</a>
          </li>
          <li>
            <c:url var="testPage" value="/test"></c:url>
            <a href="${ testPage }">테스트 페이지</a>
          </li>
          <li>
            <c:url var="forwardPage" value="/test2"></c:url>
            <a href="${ forwardPage }">포워드 페이지</a>
          </li>
          <li>
            <c:url var="redirectPage" value="/test3"></c:url>
            <a href="${ redirectPage }">리다이렉트 페이지</a>
          </li>
          <li>
            <c:url var="rest1" value="/rest1"></c:url>
            <a href="${ rest1 }">REST 1</a>
          </li>
           <li>
            <c:url var="rest2" value="/rest2"></c:url>
            <a href="${ rest2 }">REST 2</a>
          </li>
           <li>
            <c:url var="rest3" value="/rest3"></c:url>
            <a href="${ rest3 }">REST Controller 3</a>
          </li>
          <li>
            <c:url var="rest4" value="/rest4"></c:url>
            <a href="${ rest4 }">REST Controller 4</a>
          </li>
        </ul>
      </nav>
    </main>
    <script
      src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
      integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
      crossorigin="anonymous"
    ></script>
  </body>
</html>
