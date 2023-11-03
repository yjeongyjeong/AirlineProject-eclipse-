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
<title>update user password</title>
</head>

<body>
	<div class="tm-main-content" id="top">
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
					<div class="col-xs-12 mx-auto tm-about-text-wrap text-center">
							<h2>새로운 비밀번호 입력</h2>
							<br>
							<h6>새로운 비밀번호를 입력해주시기 바랍니다.</h6>
							<br>
							<br>
							<br>
						</div>
						<form name="signUpForm" action="UserServlet" method="post"
							accept-charset="UTF-8" style="text-align: center;">
							<input type="hidden" name="userid" value="${userid}"> <input
								type="hidden" name="command" value="update_pwd">
								<hr>
								
							<table style="margin-left: auto; margin-right: auto; ">
								<tr>
									<th width="120px" style="text-align: left;">새 비밀번호</th>
									<td width="250px"><input type="password" name="pwd" id="pwd" maxlength="30"
										placeholder="영문+숫자+특수문자 8~20자리" class="form-control">
								</tr>
								<tr>
									<th style="text-align: left;">새 비밀번호 확인</th>
									<td><input type="password" name="pwd_check" id="pwd_check"
										maxlength="30" class="form-control" style="display: inline;">
										
										</td>
								</tr>



							</table>

							<br>
							<button onclick="valication()" id="pwdCheckBtn" type="button"
											class="btn btn-primary" style="align-self: center; display: inline;">비밀번호 확인</button>
							<button onclick="location.href='UserServlet?command=update_pwd'"
								id="pwdUpdateBtn" type="submit" disabled="disabled"
								class="btn btn-primary">비밀번호 변경</button>
<hr>
						</form>
<br><br>


					</div>
				</div>
			</section>
			



			<div class="tm-container-outer tm-position-relative"
				id="tm-section-4">


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
	function pwdCheck(){
        //비밀번호 확인
        if(document.signUpForm.pwd.value !== document.signUpForm.pwd_check.value){
            alert("비밀번호를 다시 확인해 주세요.")
            pwd_check.focus();
            return false;
        }
            document.signUpForm.pwdUpdateBtn.disabled = false;
	}
	function valication(){
		var regIdPw = /^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{6,10}$/;

        //비밀번호 확인
        if(document.signUpForm.pwd.value.length == 0){
            alert("비밀번호를 입력하세요.")
            pwd.focus();
            return false;
        }
        //비밀번호 영어 대소문자 확인
        else if(!regIdPw.test(document.signUpForm.pwd.value)){
            alert("6~10자 영문 대소문자, 숫자, 특수문자를 입력해주세요.")
            pwd.focus();
            return false;
        }
        //비밀번호와 아이디 비교
        else if(document.signUpForm.pwd.value == document.signUpForm.userid.value){
            alert("아이디와 동일한 비밀번호를 사용할 수 없습니다.")
            pwd.focus();
            return false;
        }
        else if(document.signUpForm.pwd.value !== document.signUpForm.pwd_check.value){
            alert("비밀번호를 다시 확인해 주세요.")
            pwd_check.focus();
            return false;
        }
            document.signUpForm.pwdUpdateBtn.disabled = false;
	}
		</script>

</body>
</html>