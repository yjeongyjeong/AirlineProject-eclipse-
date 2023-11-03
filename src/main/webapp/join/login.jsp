<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../common/header.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700">
<!-- Google web font "Open Sans" -->
<link rel="stylesheet" href="/css/font-awesome.min.css">
<!-- Font Awesome -->
<link rel="stylesheet" href="css/bootstrap.min.css">
<!-- Bootstrap style -->
<link rel="stylesheet" type="text/css" href="css/datepicker.css" />
<link rel="stylesheet" type="text/css" href="slick/slick.css" />
<link rel="stylesheet" type="text/css" href="slick/slick-theme.css" />
<link rel="stylesheet" href="css/templatemo-style.css">
<title>login</title>
</head>
<body>
	<!-- 쿠키값으로 id정보 받아오기 -->
	<%String cookie = "";
		Cookie[] cookies = request.getCookies(); //쿠키생성
		if(cookies !=null&& cookies.length > 0)
		for (int i = 0; i < cookies.length; i++){
			if (cookies[i].getName().equals("userid")) { // 내가 원하는 쿠키명 찾아서 값 저장
				cookie = cookies[i].getValue();}}%>
				
	<div class="tm-page-wrap mx-auto">
		<div class="tm-container-outer tm-banner-bg">
		<div class="container"><!-- 원복=> 컨테이너 제거 -->
			<div class="row tm-banner-row tm-banner-row-header">
				<div class="tm-banner-header">
					<h1 class="text-uppercase tm-banner-title">Login</h1>
					<p class="mb-4">아이디 및 패스워드를 입력 해 주세요.</p>
				</div>
			</div>
		<!-- 	<div class="row tm-banner-row tm-banner-row-header tm-about-text-wrap mx-auto text-center">	 -->
			<div class="row tm-banner-row mx-auto text-center">				
				<form action="UserServlet" method="post" class="tm-search-form tm-section-pad-4 name="frm" style="background-color:white; width: 670px; height:180px;">
					<!-- <div class="tm-banner-header">
					<h1 class="text-uppercase tm-banner-title">Login</h1>
					<p class="mb-4">아이디 및 패스워드를 입력 해 주세요.</p>
					</div>
					 -->
					<input type="hidden" name="command" value="login_check" />
					<div class="form-group tm-container">
						<label for="userid">ID</label> 
						<input type="text" id="userid" name="userid" class="form-control" placeholder="id" required value="<%=cookie%>"/>
					</div>
					<div class="form-group tm-container">
						<label for="pwd">PASSWORD</label> 
						<input type="password" id="pwd" name="pwd" class="form-control" placeholder="pwd" required />
					</div>
					<div class="form-group tm-container">
						<label for="remember">아이디 저장</label> 
						<input type="checkbox" name="rememberCheck" class="form-control" />
					</div>
					<input type="hidden" name = "mess" value="${message}"/>
					<c:choose>
						<c:when test="${messge}">
							<input type="text" value="${messge}"/>
						</c:when>
						<c:otherwise></c:otherwise>
					</c:choose>	
					<div class="form-group tm-form-group tm-form-group-pad tm-form-group-1">
						<button type="submit" class="btn btn-primary tm-btn-primary tm-btn-send text-uppercase" onclick="return check()">로그인</button>
					</div>
					<div class="form-group tm-form-group tm-form-group-pad tm-form-group-1">
						<button type="button" class="btn btn-primary tm-btn-primary tm-btn-send text-uppercase" onclick="location.href='UserServlet?command=join_terms'">회원가입</button>
					</div>
					<div class="form-group tm-form-group tm-form-group-pad tm-form-group-1">
						<button type="button" class="btn btn-primary tm-btn-primary tm-btn-send text-uppercase" onclick="location.href='UserServlet?command=find_id'">아이디 찾기</button>
					</div>
					<div class="form-group tm-form-group tm-form-group-pad tm-form-group-1">
						<button type="button" class="btn btn-primary tm-btn-primary tm-btn-send text-uppercase" onclick="location.href='UserServlet?command=find_pwd'">비밀번호 찾기</button>
					</div>
					<script type="text/javascript">alertMessage();</script>
			</form>
			
		</div>
			
			
					
		</div>

	</div>
			<div class="row tm-banner" id="tm-section-search" > 
									<form class="tm-search-form tm-section-pad-1 row mx-auto" style="background-color: white">
							<input type="hidden" name="command" value="flightList"/>
							<div class="form-row tm-search-form-row">
						
								<div class="form-group tm-form-group tm-form-group-pad tm-form-group-4" style="width:174px;">
									<label for="inputCheckIn"></label> 
									<input name="check-in" type="hidden" class="form-control" id="inputCheckIn" >
								</div>
								
							</div>
						</form>
	
					</div>
	</div>
	<%@ include file="../common/footer.jsp"%>
	<script type="text/javascript">
		function check() {
			var returnIdAndPwd = document.frm.msee;
			
			if(mess.value.length != 0){
				alert(returnIdAndPwd);
			}
			
			if (document.frm.userid.value.length == 0) {
				alert("아이디를 입력 해 주세요");
				document.frm.userid.focus;
				return false;
			} else if (document.frm.pwd.value.length == 0) {
				alert("아이디를 입력 해 주세요");
				document.frm.userid.focus;
				return false;
			}
			return true;
		}
		function alertMessage(){
			var message = ${ message }
			if(message && message.trim() !== "" && message !== "null"){
			   alert(message);
			}
		}
	</script>
</body>

</html>