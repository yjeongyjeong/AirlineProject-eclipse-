<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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

<body style="background : white;">
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
							<form action="UserServlet" method="post" name="frm"
								class="tm-contact-form">
								<br><br><br>
								<table>
									<tr>
										<th width="150px"><a>인증번호 입력
										</a></th>
										<td><input type="text" name=inputedCode
											id="inputAuthCode" maxlength="10" class="form-control" style="display: inline;"> 
											<input type="hidden" name="inputedCode" value="${email}">
											<input
											type="hidden" name="command" value="authCode_Check">
									</tr>
								</table>
<br><br>
											<button id="authCodeCheckBtn" type="submit"
												class="btn btn-primary" style="display: inline;">인증</button></td>
							</form>
						</div>
					</div>
					</div>
					</section>
					</div>
					</div>
</body>
</html>