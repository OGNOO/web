<%@page import="com.itwill.lab04.model.Contact"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>action tag</title>
</head>
<body>
	<%@ include file="header.jspf"%>
	<main>
		<h1>JSP Action Tag</h1>
		<%--
		JSP 액션 태그: 스크립트릿에서 사용되는 일부 자바 코드들을 HTML 또는 XML 과 비슷하게
		태그, 태그 속성(attribute), 태그 컨텐트를 작성해서 대체하는 문법.
		JSP action tag 는 대/소문자를 구분! (HTML 태그는 대/소문자를 구분하기 않음.)
		o. <jsp:forward></jsp:forward>
		o. <jsp:include></jsp:include>
		o. <jsp:useBean></jsp:useBean>: 생성자 호출
		o. <jsp:getProperty></jsp:getProperty>: getter 메서드 호출
		o. <jsp:setProperty></jsp:setProperty>: setter 메서드 호출
		--%>
		<h2>액션 태그를 사용하지 않은 자바 객체 생성</h2>
		<% Contact contact1 = new Contact();  // 기본 생성자 호출
		contact1.setId(1); // setter 메서드 호출
		contact1.setName("홍길동");
		contact1.setPhone("010-0000-0000");
		contact1.setEmail("hgd@itwill.com");
		%>
		<p>
		ID: <%= contact1.getId() %><br/>
		이름: <%= contact1.getName() %><br/>
		전화번호: <%= contact1.getPhone() %><br/>
		이메일: <%= contact1.getEmail() %><br/>
		</p>
		
		<h2>액션 태그 자바 빈을 사용한 객체 생성</h2>
	    <jsp:useBean id="contact2" class="com.itwill.lab04.model.Contact"/>
	    <%-- Contact contact2 = new Contact(); --%>
	    <jsp:setProperty property="id" name="contact2" value="2"/>
	    <%-- contact1.setId(2); --%>
	    <jsp:setProperty property="name" name="contact2" value="오쌤"/>
	    <jsp:setProperty property="phone" name="contact2" value="010-1234-5678"/>
	    <jsp:setProperty property="email" name="contact2" value="jake@itwill.com"/>
	    <p>
	    ID: <jsp:getProperty property="id" name="contact2"/><br/>
	    이름: <jsp:getProperty property="name" name="contact2"/><br/>
	    전화번호: <jsp:getProperty property="phone" name="contact2"/><br/>
	    이메일: <jsp:getProperty property="email" name="contact2"/><br/>
	    
	    </p>
	</main>
	<jsp:include page="footer.jsp" />
</body>
</html>