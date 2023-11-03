<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../common/header.jsp"%>
<!DOCTYPE html>
<html lang="en">
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
							<div align="center">
								<h2 class="text-uppercase mb-4">기존 회원 여부 확인</h2>
								<h6>기존 회원 여부를 확인해주시기 바랍니다.</h6>
								<br> <br>
							</div>
							<form action="UserServlet" name="frm" method="post"
								class="tm-contact-form" style="position: inherit; width: 570px;">
								<!-- class="tm-contact-form" -->
								<input type="hidden" name="command" value="join_check_user">

								<table class="table_form" style="margin: auto">

									<tbody>
										<tr>
											<hr>
											<br>
											<th scope="row" width="120px" style="text-align: left;"><label
												for="engName">영문명</label></th>
											<td colspan="3"><input type="text" id="engName"
												placeholder="영문 명 입력 (예 : HONGGILDONG)" maxlength="10"
												style="text-transform: uppercase;"
												oninput="handleOnInputEng(this)" class="form-control"
												name="engName"></td>
										</tr>
										<tr>
											<th scope="row" style="text-align: left;"><label
												for="en_name">한글명</label></th>
											<td colspan="3"><input type="text" id="korName"
												placeholder="한글 명 입력 (예 : 홍길동)" maxlength="5"
												oninput="handleOnInputKor(this)" name="korName"
												class="form-control"></td>

										</tr>
										<tr>
											<th scope="row" style="text-align: left;">성별</th>

											<td>
												<div class="form-control">
													<input type="radio" name="gender" id="gender_man" value=1
														checked="checked"> <label for="gender_man">남</label>
												</div>
											</td>
											<td>
												<div class="form-control">
													<input type="radio" name="gender" id="gender_woman" value=2>
													<label for="gender_woman">여</label>
												</div>
											</td>
										</tr>
										<tr>
											<th scope="row" style="text-align: left;"><label
												for="birth_date">주민등록번호</label></th>
											<td>
												<div>
													<input class="form-control" type="text"
														name="usernum_first"
														oninput="handleOnInput(this, 6); this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');">
												</div>
											</td>
											<td>
												<div>
													<input class="form-control" type="password"
														name="usernum_last"
														oninput="handleOnInput(this, 7); this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');">
												</div>
											</td>
										</tr>
									</tbody>
								</table>

								<br>
								<hr>
								<br>
								<button type="submit" class="btn btn-primary"
									onclick="return joinInformCheck();" >확인</button>
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
		function joinInformCheck(){ //값이 비었는지 확인
			var regfirst = /^(?=.*[0-9]).{6,7}$/;
			var reglast = /^(?=.*[0-9]).{7,8}$/;
			
			if(document.frm.engName.value.length == 0){
		        alert("영문 명을 입력해주세요.");
		        return false;
		    }
		    if(document.frm.korName.value.length == 0){
		        alert("한글 명을 입력해주세요.");
		        return false;
		    }
		    if(document.frm.usernum_first.value.length == 0){
		        alert("주민등록번호를 입력해주세요.");
		        return false;
		    }
		    if(document.frm.usernum_last.value.length == 0){
		        alert("주민등록번호를 입력해주세요.");
		        return false;
		    }
		    
		    if(!regfirst.test(document.frm.usernum_first.value)){
	            alert("주민등록번호를 다시 확인해주시기 바랍니다.")
	            return false;
	        } 
		    if(!reglast.test(document.frm.usernum_last.value)){
	            alert("주민등록번호를 다시 확인해주시기 바랍니다.")
	            return false;
	        }
		}
		
		function handleOnInput(el, maxlength){
			if(el.value.length > maxlength){
				el.value = el.value.substr(0, maxlength);
			}
		}
		function handleOnInputEng(e)  {
			  e.value = e.value.replace(/[^A-Za-z]/ig, '')
			}
		function handleOnInputKor(e)  {
			  e.value = e.value.replace(/[^ㄱ-힣]/ig, '')
			}
		
		/* function pwdLengthCheck(){
			var regfirst = /^(?=.*[0-9]).{6,7}$/;
			var reglast = /^(?=.*[0-9]).{7,8}$/;
				        if(!regfirst.test(document.frm.usernum_first.value)){
				            alert("주민등록번호를 다시 확인해주시기 바랍니다.")
				            usernum_first.focus();
				            return false;
				        } else if(!reglast.test(document.frm.usernum_last.value)){
				            alert("주민등록번호를 다시 확인해주시기 바랍니다.")
				            usernum_last.focus();
				            return false;
				        }
			} */
		</script>
</body>
</html>