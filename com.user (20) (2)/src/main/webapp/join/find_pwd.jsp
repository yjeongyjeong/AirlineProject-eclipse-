<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../common/header.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
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
<title>login</title>
</head>

<body>
	<div class="tm-page-wrap mx-auto">
		<section class="tm-banner">
			<div class="tm-container-outer ">
				<div class="container">
					<!-- <div class="row tm-banner-row" id="tm-section-search"></div> -->
					<!-- row -->
				</div>
				<!-- .container -->
			</div>
			<!-- .tm-container-outer -->
		</section>

		<section class="tm-page-wrap-allwhite">
			<div class="container">
				<div class="row">
					<div class="col-xs-12 mx-auto tm-about-text-wrap text-center">
						<h2>비밀번호 찾기</h2>
						<br>
						<h6>
							회원가입 시 등록한 아이디 및 이메일 주소를 입력해주시기 바랍니다.<br> 등록된 회원정보와 입력된 내용이
							일치할 경우, 등록된 이메일 주소로 안내 메일을 발송해 드립니다.
						</h6>
						<br>
						<br>
						<hr>
						<br>

						<form name="signUpForm" action="UserServlet" method="post"
							accept-charset="UTF-8">
							<table style="margin-left: auto; margin-right: auto;">
								<tr>
									<th>아이디</th>
									<td style="padding-bottom: 6px"><input type="text" name="userid" id="inputUseridForm"
										maxlength="30" class="form-control"></td>
								</tr>
								<tr>
									<th
										style="padding: 8px; border-top: none; text-align: center; vertical-align: middle; width: 150px">이메일</th>
									<td style="border-top: none; padding: 0"><input
										type="text" name="email" id="inputEmailForm" maxlength="30"
										class="form-control" style="display: inline; width: 250px">

										<button onclick="emailAuthentication()" id="eamilAuthBtn"
											type="button" class="btn btn-primary">인증 메일 보내기</button></td>
								</tr>



							</table>
							<br>
							<br>
							<button
								onclick="location.href='UserServlet?command=update_pwd_form'"
								id="authCodeCheckBtn" type="button" disabled="disabled"
								class="btn btn-primary">확인</button>
							<br>
							<br>
						</form>


					</div>
				</div>
			</div>
		</section>



		<div class="tm-container-outer tm-position-relative" id="tm-section-4">


			<%@ include file="../common/footer.jsp"%>
		</div>
	</div>
	</div>

	<script>
		/* DOM is ready
		------------------------------------------------*/
		$(function() {

			// Change top navbar on scroll
			$(window).on("scroll", function() {
				if ($(window).scrollTop() > 100) {
					$(".tm-top-bar").addClass("active");
				} else {
					$(".tm-top-bar").removeClass("active");
				}
			});

			// Smooth scroll to search form
			$('.tm-down-arrow-link').click(function() {
				$.scrollTo('#tm-section-search', 300, {
					easing : 'linear'
				});
			});

			// Date Picker in Search form
			var pickerCheckIn = datepicker('#inputCheckIn');
			var pickerCheckOut = datepicker('#inputCheckOut');

			// Update nav links on scroll
			$('#tm-top-bar').singlePageNav({
				currentClass : 'active',
				offset : 60
			});

			// Close navbar after clicked
			$('.nav-link').click(function() {
				$('#mainNav').removeClass('show');
			});

			// Slick Carousel
			$('.tm-slideshow').slick({
				infinite : true,
				arrows : true,
				slidesToShow : 1,
				slidesToScroll : 1
			});


	</script>
	<script type="text/javascript">		

		function emailAuthentication(){
			if (!emailValCheck()){
		    	return false;
		    }
			 var url = "UserServlet?command=userid_email_check&userid=" + document.signUpForm.userid.value+"&email="+document.signUpForm.email.value;
			open(url, "confirm",
					"toolbar=no, location=no,menubar=no,scrollbars=no,resizable=no,width=600,height=200") ;			
			//emailValCheck() 호출 후 결과값 확인
			//결과값이 true라면 새 창에 email정보를 get방식으로 전송
			//결과값이 false라면 위의 기능을 실행하지 않음
			
		}

		const form = document.signUpForm;

		function emailValCheck(){
			var emailPattern= /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
			var email = form.email.value;
			if(!check(emailPattern, email, "유효하지 않은 이메일 주소입니다.")) {
				return false;
			}
		    return true;
		    //const form : email을 포함하는 form의 위치
		    //var emailPattern : 이메일 정규식 패턴
		    //check(pattern, taget, message)를 호출해 결과에 따라 새창을 띄웁니다.
		}
		
		function check(pattern, taget, message) {
			if(pattern.test(taget)) {
		    	return true;
		    }
		    alert(message);
		    taget.focus();
		    return false;
		    //정규식 검증이 필요한 다른 항목에서도 사용가능하도록 만들어 코드 재사용성을 높임
		    //taget을 pattern으로 검사해 유효하면 ture 반환
		    //taget이 pattern에 맞지 않는다면?
		    //alert로 message를 띄워 사용자에게 알림
		    //유요하지 않은 입력에 포커스를 줌
		    //false 반환
		}

		</script>

</body>
</html>