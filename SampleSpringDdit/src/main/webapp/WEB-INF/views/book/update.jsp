<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>책 수정하기</title>
</head>
<body>
<!-- 
뷰? 화면을 담당한다. 웹 어플리케이션에서 화면은 웹 브라우저가 렌더링하므로
브라우저가 읽어서 해석할 수 있는 HTML로 최종 변환될 수 있도록 작성한다.
* 데이터 변경(insert) => POST. HTTP 파라미터가 주소창에 노출되지 않음
-->
<h1>책 수정하기</h1>
<form method="POST">
<p>
제목 : <input type="text" name="title" value="${data.title}" />
<c:if test="${errors.title}">책 제목을 입력하세요</c:if>
</p>
<p>
카테고리 : <input type="text" name="category" value="${data.category}" />
<c:if test="${errors.category}">책 카테고리를 입력하세요</c:if>
</p>
<p>
가격 : <input type="text" name="price" value="${data.price}" />
<c:if test="${errors.price}">책 가격을 입력하세요</c:if>
</p>
<p><input type="submit" value="저장" />
</form>
</body>
</html>







