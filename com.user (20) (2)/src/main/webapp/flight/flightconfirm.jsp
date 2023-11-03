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
		<div class="tm-container-outer tm-banner-bg">
			<div class="container">
				<div class="row tm-banner-row tm-banner-row-header">
					<div class="tm-banner-header">
						<h1 class="text-uppercase tm-banner-title">항공권 구매가 완료되었습니다.</h1>
						<p class="mb-4">구매내역</p>
					</div>
				</div>

				 <div class="row tm-banner-row "> 
					<form action="UserServlet" method="post"  class="tm-search-form tm-section-pad-2" style="background-color:white; height:180px;"
						name="frm">
						<div class="row row mx-auto">
							<table class="table table-hover" style="width: 800px;">
								<input type="hidden" name="command" value="cancelTicket" />
								<input type="hidden" name="resno" value="${vo.resno}" />

								<tr>
									<td>구매자</td>
									<td><input type="text" id="userid" name="userid"
										class="form-control" value="${vo.userid}" readonly="readonly" /></td>
									<td>구매일</td>
									<td><input type="text" id="userid" name="userid"
										class="form-control" value="${vo.purchasedate}"
										readonly="readonly" /></td>
								</tr>
								<tr>
									<td>출발일</td>
									<td><input type="text" id="userid" name="userid"
										class="form-control" value="${vo.regidate}"
										readonly="readonly" /></td>
									<td>출발지</td>
									<td><input type="text" id="userid" name="userid"
										class="form-control" value="${vo.depairport}"
										readonly="readonly" /></td>
								</tr>
								<tr>
									<td>도착지</td>
									<td><input type="text" id="userid" name="userid"
										class="form-control" value="${vo.arrairport}"
										readonly="readonly" /></td>
									<td>항공사</td>
									<td><input type="text" id="userid" name="userid"
										class="form-control" value="${vo.airline}" readonly="readonly" /></td>
								</tr>

								<tr>
									<td>항공기 번호</td>
									<td><input type="text" id="userid" name="userid"
										class="form-control" value="${vo.flightnum}"
										readonly="readonly" /></td>
									<td>구매금액</td>
									<td><input type="text" id="userid" name="userid"
										class="form-control" value="${vo.price}" readonly="readonly" /></td>
								</tr>

								<tr>
									<td colspan="4" class="mx-auto text-center" style="align: center;">
									
									<button type="button"
									class="btn btn-primary tm-btn-primary tm-btn-send text-uppercase col-3"
									onclick="return cancelCheck()">예매취소</button>
									</td>
								<tr>
							</table>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	<%@ include file="../common/footer.jsp"%>
	</div>

	<script src="js/jquery-1.11.3.min.js"></script>
	jQuery (https://jquery.com/download/)
	<script src="js/popper.min.js"></script>
	https://popper.js.org/
	<script src="js/bootstrap.min.js"></script>
	https://getbootstrap.com/
	<script src="js/datepicker.min.js"></script>
	https://github.com/qodesmith/datepicker
	<script src="js/jquery.singlePageNav.min.js"></script>
	Single Page Nav (https://github.com/ChrisWojcik/single-page-nav)
	<script src="slick/slick.min.js"></script>
	http://kenwheeler.github.io/slick/
	<script src="js/jquery.scrollTo.min.js"></script>
	<script type="text/javascript">
		function cancelCheck() {
			var cancelForm = document.frm;
			if (!window.confirm("정말 취소하시겠습니까?")) {
				console.log("삭제취소");
				return false;
			} else {
				alert("티켓 취소가 진행됩니다.")
				frm.submit();
			}
		}
	</script>

</body>

</html>