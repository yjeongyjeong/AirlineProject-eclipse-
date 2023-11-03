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
<title>find userid</title>
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
					<div class="row">
						<div class="col-xs-12 mx-auto tm-about-text-wrap text-center">
							<h2 class="hidden">아이디 찾기</h2>
							<h6 class="text_type2 gray">입력하신 정보와 일치하는 아이디는 아래와 같습니다.</h6>
							<form action="UserServlet" name="frm" method="post"
								class="tm-contact-form" style="position: inherit; width: 570px; align-content: center;">
								<hr>
								<br>
							<table style="text-align: center;">
								<tbody>
									<tr>
										<th width="200px">아이디</th>
										<td width="200px">${result}</td>
									</tr>
								</tbody>

							</table>
							<br>
							<hr>
							<br>
							<!-- 비밀번호 찾기의 경우 아이디를 입력받는 jsp화면을 만들고(이메일입력과 매우 유사) 메일을보내서 임시 비밀번호로(update해서) 지정할 예정.. -->
							<button onclick="location.href='UserServlet?command=find_pwd'"
								type="button" class="btn btn-primary">비밀번호 찾기</button>
							<button onclick="location.href='UserServlet?command=login'"
								type="button" class="btn btn-primary">로그인</button>
							
							</form>


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
	<!-- .main-content -->

	<!-- load JS files -->
	<script src="js/jquery-1.11.3.min.js"></script>
	<!-- jQuery (https://jquery.com/download/) -->
	<script src="js/popper.min.js"></script>
	<!-- https://popper.js.org/ -->
	<script src="js/bootstrap.min.js"></script>
	<!-- https://getbootstrap.com/ -->
	<script src="js/datepicker.min.js"></script>
	<!-- https://github.com/qodesmith/datepicker -->
	<script src="js/jquery.singlePageNav.min.js"></script>
	<!-- Single Page Nav (https://github.com/ChrisWojcik/single-page-nav) -->
	<script src="slick/slick.min.js"></script>
	<!-- http://kenwheeler.github.io/slick/ -->
	<script src="js/jquery.scrollTo.min.js"></script>
	<!-- https://github.com/flesler/jquery.scrollTo -->
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


</body>
</html>