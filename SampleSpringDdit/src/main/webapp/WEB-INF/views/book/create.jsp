<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>책 생성하기</title>
</head>
<body>
<!-- 
뷰? 화면을 담당한다. 웹 어플리케이션에서 화면은 웹 브라우저가 렌더링하므로
브라우저가 읽어서 해석할 수 있는 HTML로 최종 변환될 수 있도록 작성한다.
* 데이터 변경(insert) => POST. HTTP 파라미터가 주소창에 노출되지 않음
-->
<h1>책 생성하기</h1>
<form method="POST">
<p>제목 : <input type="text" name="title" value="" /></p>
<p>카테고리 : <input type="text" name="category" /></p>
<p>가격 : <input type="text" name="price" /></p>
<p><input type="submit" value="저장" />
</form>
</body>
</html>

