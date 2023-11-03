<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../common/header.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
<title>Insert title here</title>
</head>
<body>
	<div class="tm-page-wrap mx-auto">
		<div class="tm-container">
			<div class="container mx-auto text-center">
				<h2>관리자 항공운항 조회</h2>
				<div class="row tm-banner" id="tm-section-search"> 
					<form action="UserServlet" method="post" class="tm-search-form tm-section-pad-1 row mx-auto">
						<input type="hidden" name="command" value="adminFlight_list" />
						<div class="form-row tm-search-form-row">
							<div class="form-group tm-form-group tm-form-group-pad tm-form-group-3">
								<label for="inputCity">Choose Your Department</label> 
								<input name="department" type="text" class="form-control" id="inputCity" placeholder="Type your destination...">
							</div>
							<div
								class="form-group tm-form-group tm-form-group-pad tm-form-group-3">
								<label for="inputCity">Choose Your Arrive</label> 
								<input name="arrive" type="text" class="form-control" id="inputCity" placeholder="Type your destination...">
							</div>
							<div class="form-group tm-form-group tm-form-group-pad tm-form-group-4" style="width:174px;">
								<label for="inputCheckIn">Check In Date</label> 
								<input name="check-in" type="text" class="form-control" id="inputCheckIn" placeholder="Check In">
							</div>
							<div class="form-group tm-form-group tm-form-group-pad tm-form-group-4">
								<label for="btnSubmit">&nbsp;</label>
								<button type="submit" class="btn btn-primary tm-btn tm-btn-search text-uppercase" id="btnSubmit">Check Availability</button>
							</div>
						</div>
					</form>
				</div>
					
				<div class="form-row tm-search-form-row"><label for="inputCity"></label> </div>
				<c:if test="${not empty checkin}">
					<h3>${checkin}일${department}에서 ${arrive}까지 의 운항 조회 내역입니다.</h3>
				</c:if>
				<table class="table table-hover">
					<thead>
						<tr>
							<th>출/도착 구분</th>
							<th>날짜</th>
							<th>항공사</th>
							<th>편명</th>
							<th>출발공항코드</th>
							<th>출발공항명</th>
							<th>도착공항코드</th>
							<th>도착공항명</th>
							<th>계획시간</th>
							<th>예상시간</th>
							<th>도착시간</th>
						</tr>
					</thead>
					<c:forEach items="${list}" var="list">
						<tbody>
							<tr>
								<td>${list.departureState}</td>
								<td>${list.regidate}</td>
								<td>${list.airline}</td>
								<td>${list.flightnum}</td>
								<td>${list.depairportcode}</td>
								<td>${list.depairport}</td>
								<td>${list.arrairportcode}</td>
								<td>${list.arrairport}</td>
								<td>${list.plantimes}</td>
								<td>${list.estimate}</td>
								<td>${list.arrtime}</td>
								<!-- <td><button type="button" onclick="location.href='#'">삭제</button></td> -->
							</tr>
						</tbody>
					</c:forEach>
				</table>
				<div class="container">
					<ul class="pagination pagination justify-content-center">
						<c:choose>
							<c:when test="{prevBlock eq -9}">
								<li class="page-item"><a class="page-link"
									onclick="return previous()">Previous</a></li>
							</c:when>
							<c:otherwise>
								<li class="page-item"><a class="page-link"
									onclick="location.href='UserServlet?command=adminFlight_list&curPage=${PrevPage}'">Previous</a></li>
							</c:otherwise>
						</c:choose>
						<c:forEach items="${pageCount}" var="page">
							<li class="page-item"><a class="page-link"
								href="UserServlet?command=adminFlight_list&curPage=${page}"
								name="curPage">${page}</a></li>
						</c:forEach>
						<c:choose>
							<c:when test="{nextBlock eq 41}">
								<li class="page-item"><a class="page-link"
									onclick="return next()">Next</a></li>
							</c:when>
							<c:otherwise>
								<li class="page-item"><a class="page-link"
									onclick="location.href='UserServlet?command=adminFlight_list&curPage=${nextPage}'">Next</a></li>

							</c:otherwise>
						</c:choose>
					</ul>
				</div>
			</div>
		</div>

		<%@ include file="../common/footer.jsp"%>
	</div>	
	
	<script type="text/javascript">
		function previous() {
			alert("첫번째 페이지 입니다.");
		}

		function next() {
			alert("마지막 페이지 입니다.");
		}
	</script>
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

</body>

</html>