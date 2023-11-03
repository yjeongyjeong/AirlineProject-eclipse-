<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700">
<!-- Google web font "Open Sans" -->
<link rel="stylesheet" href="/css/font-awesome.min.css">
<!-- Font Awesome -->
<link rel="stylesheet" href="css/bootstrap.min.css">
<!-- Bootstrap style -->
<link rel="stylesheet" type="text/css" href="css/datepicker.css" />
<link rel="stylesheet" type="text/css" href="slick/slick.css" />
<link rel="stylesheet" type="text/css" href="slick/slick-theme.css" />
<link rel="stylesheet" href="css/templatemo-style.css">
</head>


<body style="background: white;">
<%! String email = ""; %>
<% email = request.getParameter("email"); %>

<form id="myForm" action="UserServlet" method="post" name="frm"" style="text-align: center;">
	<c:set var="emailDupleCheckResult" value="${emailDupleCheckResult}" />
	<c:choose>
		<c:when test="${emailDupleCheckResult == 'useable' }">
			<h4 align="center" style="color: gray"><br>메일을 전송중입니다.<br>창을 닫지 마세요.</h4>
			<input type="hidden" name="command" value="auth_email_send">
			<input type="hidden" name="email" value="${email}">
			<script type="text/javascript">opener.close();</script>
		</c:when>
		<c:otherwise>
			<h4> email 정보가 올바르지 않습니다.</h4>
			<input type="hidden" name="command" value="incorrect">
		</c:otherwise>
	</c:choose>
	<br>
	<button type="submit" onclick="submitFormAndClose()" class="btn btn-primary" >확인</button>
</form>
<!-- 
이메일이 중복되지 않았다면 requestAuthEamil()를 실행해 새창으로 인증이메일을 전송
이메일이 중복되었다면 '확인'버튼과 결과를 알려주는 글귀만 출력

 -->

<script type="text/javascript">

	function submitFormAndClose() {
		if(document.frm.command.value == "auth_email_send"){
			form.submit();
			self.close();
		}
		if(document.frm.command.value == "incorrect"){
			self.close();
		}
	}

	
</script>
</body>
</html>