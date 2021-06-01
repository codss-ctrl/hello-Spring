<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>책 목록</title>
<script type="text/javascript">
function fn_chk(){
	var varSelOpt = document.getElementById("selOpt").value;
	var varKeyword = document.getElementById("keyword").value;
	var flag;
	if(varSelOpt=="price"){
		flag = numCheck(varKeyword);
		
		if(flag == true){
			//submit
			return true;
		}else{
			document.getElementById("keyword").value = "";
			alert("숫자를 입력해주세요");
			document.getElementById("spanKeyword").innerHTML = "숫자를 입력해주세요";
			//submit을 막음
			return false;
		}
	}else{
		return true;
	}
	
}

//숫자 체크
function numCheck(num){
	//true : 숫자, false : 문자
	var flag = true;
	
	for(var i=0;i<num.length;i++){
		c = num.charAt(i);
		if(!(c>='0'&&c<='9')){
			flag = false;
			break;
		}//end if
	}//end for
	
	return flag;
}

</script>
</head>
<body>
<h1>책 목록</h1>
<p>
<form method="get" onsubmit="return fn_chk()">
	<select name="selOpt" id="selOpt">
		<option value="">전체</option>
		<option value="">--------</option>
		<option value="title" 
			<c:if test="${selOpt=='title'}">selected</c:if>>제목</option>
		<option value="category" 
			<c:if test="${selOpt=='category'}">selected</c:if>>카테고리</option>
		<option value="price" 
			<c:if test="${selOpt=='price'}">selected</c:if>>가격</option>
	</select>&nbsp;&nbsp;
	<input type="text" placeholder="검색" id="keyword" name="keyword" value="${keyword}"
		onclick="document.getElementById('spanKeyword').innerHTML='';" />
	<input type="submit" value="검색" />&nbsp;&nbsp;
	<span id="spanKeyword"></span>
</form>
</p>
<table border="1" style="width:100%;">
<thead>
	<tr>
		<th>제목</th>
		<th>카테고리</th>
		<th>가격</th>
	</tr>
</thead>
<tbody>
<c:forEach var="row" items="${data}">
	<tr>
		<td>${row.TITLE}</td>
		<td>${row.CATEGORY}</td>
		<td>${row.PRICE}</td>
	</tr>
</c:forEach>
</tbody>
</table>
<a href="/create">생성</a>
</body>
</html>



