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
<title>회원 마이페이지</title>
</head>
<body>
	<div class="tm-page-wrap mx-auto">
	<%-- 	<h2 class="text-uppercase mb-4 mx-auto text-center">
			<strong>회원(${userid}) 페이지 입니다</strong>
		</h2> --%>
		<div class="p-5 tm-container-outer " id="tm-section-3">
			<div class="container">
				<div class="tab-content">
						<h2 class="text-uppercase mb-4 mx-auto text-center">
							<strong>회원(${admin.userid}) 페이지 입니다</strong>
						</h2>
				</div>
				<div class="tab-content clearfix">
					<div class="tab-pane fade show active" id="4a">
						<!-- Current Active Tab WITH "show active" classes in DIV tag -->
						<div class="tm-recommended-place-wrap">
							<div class="tm-recommended-place">
								<img src="img/라이언2.png" alt="Image" class="img-fluid tm-recommended-img">
								<div class="tm-recommended-description-box">
									<h3 class="tm-recommended-title">회원정보 수정</h3>
									<p class="tm-text-gray">회원정보 조회 및 수정, 탈퇴</p>
								</div>
								<div id="preload-hover-img"></div>
								<a href="UserServlet?command=updateUserForm&userid=${admin.userid}" class="tm-recommended-price-box">바로가기</a>
								
							</div>
							<div class="tm-recommended-place">
								<img src="img/춘식이2.png" alt="Image"
									class="img-fluid tm-recommended-img">
								<div class="tm-recommended-description-box">
									<h3 class="tm-recommended-title">구매관리</h3>
									<p class="tm-text-gray">항공권 구매내역 조회</p>
								</div>
								<div id="preload-hover-img"></div>
								<a href="UserServlet?command=userticket&userid=${admin.userid}&curPage=1" class="tm-recommended-price-box">바로가기</a>
							</div>
							<div class="tm-recommended-place">
                        <img src="img/라이언.png" alt="Image"
                           class="img-fluid tm-recommended-img">
                        <div class="tm-recommended-description-box">
                           <h3 class="tm-recommended-title">게시판 조회</h3>
                           <p class="tm-text-gray">내가 쓴 게시글, 댓글 조회 및 관리</p>
                        </div>
                        <div id="preload-hover-img"></div>
                        <a href="boardDiaryList.do?searchType=boardWriter&keyword=${loginUser.userid}" class="tm-recommended-price-box">바로가기</a>
                     </div>
							
						</div>

	
					</div>
				</div>
			</div>
		</div>
	</div>

	<div class="tm-container-outer" id="tm-section-3">
		<div class="tab-content clearfix">
			<!-- Tab 5 -->
			<div class="tab-pane fade" id="5a">
				<div class="tm-recommended-place-wrap"></div>
			</div>
		</div>
		<div class="tm-container-outer tm-position-relative" id="tm-section-4"></div>
	</div>
	<%@ include file="../common/footer.jsp"%>
	
</body>

</html>