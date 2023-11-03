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
	<div class="tm-main-content" id="top">
		<div class="tm-page-wrap mx-auto">
			<section class="tm-banner">
				<div class="tm-container-outer ">
					<div class="container">
						<div class="row tm-banner-row" id="tm-section-search"></div>
						<!-- row -->
					</div>
					<!-- .container -->
				</div>
				<!-- .tm-container-outer -->



			</section>
			<!-- 이미 가입된 회원입니다 창은 어케 띄울까? 이거 팝업창 안뜸ㅡㅡ..!!-->
			<script type="text/javascript">
			var message = ${ message }
			if(message && message.trim() !== "" && message !== "null"){
			   alert(message);
			}
			</script>



			<section class="tm-page-wrap-allwhite">
				<div class="container">
					<div class="row">
						<div class="col-xs-12 mx-auto tm-about-text-wrap text-center">
							<br> 
							<h3>회원가입이 완료되었습니다.</h3>
							<br> <br> <br>


							<button type="button" class="btn btn-primary"
								onclick="location.href='UserServlet?command=login'">로그인</button>
							<br>
							<br>


						</div>
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
		function loginCheck(){
		    if(document.frm.userid.value.length == 0){
		        alert("아이디를 입력해주세요.");
		        return false;
		    }
		    if(document.frm.pwd.value.length == 0){
		        alert("비밀번호를 입력해주세요.");
		        return false;
		    }
		}
		
		</script>

</body>
</html>