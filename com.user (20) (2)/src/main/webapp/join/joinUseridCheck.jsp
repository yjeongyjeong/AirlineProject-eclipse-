<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
<%-- 
	아이디 : ${userid} 는 사용 가능합니다.
	<button type="submit" onclick="submitFormAndClose()" class="btnCancel">확인</button>
 --%>
 <div style="margin: auto; text-align: center;">
		<h4 align="center" style="color: gray"><br>ID 중복 확인</h4>
		</div>
	<br>
	
	<form name="checkIdForm" style="text-align: center; vertical-align: middle;">
<%-- 		<input type="text" name="id" value="${user_id}" id="userId" disabled>
 --%>		<c:set var="result" value="${result}"/>
		<c:choose>
		<c:when test="${result==1}">
			<h5 align="center" style="color: red;" >${userid}는 이미 사용 중인 아이디입니다.</h5> 
			<input type="hidden" name="chResult" value="N"/>
		</c:when>
		<c:otherwise>
			<p style="color: red">${userid}는 사용가능한 아이디입니다.</p>
			<input type="hidden" name="chResult" value="Y"/>
		</c:otherwise>
		</c:choose>
<br>
		<input type="submit" class="btn btn-primary" onclick="window.close()" value="취소"/>&nbsp;&nbsp;
		<input type="submit" class="btn btn-primary" onclick="sendCheckValue()" value="사용하기"/>
	</form>

	<script type="text/javascript">
/* 		function submitFormAndClose() {
			if (document.frm.command.value == "auth_email_send") {
				form.submit();
				window.close();
			}
			if (document.frm.command.value == "incorrect") {
				window.close();
			}
		} */
		function sendCheckValue() {
			var openJoinfrm = opener.document.frm;
			
			if (document.checkIdForm.chResult.value=="N") {
				alert("다른 아이디를 입력해주세요.");
				openJoinfrm.userid.focus();
				window.close();
			}else {
				// 중복체크 결과인 idCheck 값을 전달
				openJoinfrm.idDuplication.value="idCheck";
				openJoinfrm.btn_idCheck.disabled=true;
				openJoinfrm.btn_idCheck.style.cursor="default";
				window.close();
			}
			
		}
	</script>
</body>
</html>