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
    <header><h1>Test Page</h1></header>
    <nav>
        <ul>
            <li><c:url var="homePage" value="/" /> <a href="${ homePage }">홈페이지</a>
            </li>
        </ul>
    </nav>
    <main>
        <section>
        </section>
    </main>
    <script
      src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
      integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
      crossorigin="anonymous"
    ></script>
  </body>
</html>
