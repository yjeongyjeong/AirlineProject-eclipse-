<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../common/header.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
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
<title>Insert title here</title>
</head>
<body>
	<div class="tm-page-wrap mx-auto">
		<div class="tm-container">
			<div class="container mx-auto text-center">
				 <h2>항공운항 조회</h2>
						<!-- row -->
					 <div class="row tm-banner" id="tm-section-search"> 
					
						<form action="UserServlet" method="post" class="tm-search-form tm-section-pad-1 row mx-auto" name="frm">
							<input type="hidden" name="command" value="flightList"/>
							<div class="form-row tm-search-form-row">
								<div class="form-group tm-form-group tm-form-group-pad tm-form-group-3">
									<label for="inputCity">Choose Your Department</label> 
									<input name="department" type="text" class="form-control" id="inputCity" placeholder="Type your destination...">
								</div>
								<div class="form-group tm-form-group tm-form-group-pad tm-form-group-3">
									<label for="inputCity">Choose Your Arrive</label> 
									<input name="arrive" type="text" class="form-control" id="inputCity" placeholder="Type your destination...">
								</div>
								<div class="form-group tm-form-group tm-form-group-pad tm-form-group-4" style="width:174px;">
									<label for="inputCheckIn">Check In Date</label> 
									<input name="check-in" type="text" class="form-control" id="inputCheckIn" placeholder="Check In">
								</div>
								<div class="form-group tm-form-group tm-form-group-pad tm-form-group-4">
									<label for="btnSubmit">&nbsp;</label>
									<button type="submit" class="btn btn-primary tm-btn tm-btn-search text-uppercase" id="btnSubmit" onclick="return checksubmit()">Check Availability</button>
								</div>	
							</div>
						</form>
					</div>
					<div class="form-row tm-search-form-row"><label for="inputCity"></label> </div>
					<!-- </div> -->
				<c:if test="${not empty checkin}">
					<h3>${checkin}일 ${department}에서 ${arrive}까지 의 운항 조회 내역입니다.</h3>
				</c:if>
 				<!-- <form action="UserServlet" method="post" name="frm"> -->
					<input type="hidden" name="command" value="buyticket"/>
					<input type="hidden" name="userid" value="${loginUser.userid}"/> 
					<table class="table table-hover">
						<thead>
							<tr>
								<th>날짜</th>
								<th>항공사</th>
								<th>편명</th>
								<th>출발공항명</th>
								<th>도착공항명</th>
								<th>예상시간</th>
								<th>도착시간</th>
								<c:if test="${not empty loginUser}">
									<th>예매</th>
								</c:if>	
							</tr>
						</thead>
						<c:forEach items="${list}" var="list" varStatus="status">
							<tbody>
								<tr>
									<td hidden="hidden"><input type="hidden" name="currList${status.index}" value="${list.num}"/></td>
									<td><input type="hidden" name="regidate" value="${list.regidate}"/>${list.regidate}</td>
									<td><input type="hidden" name="airline" value="${list.airline}"/>${list.airline}</td>
									<td><input type="hidden" name="flightnum" value="${list.flightnum}"/>${list.flightnum}</td>
									<td><input type="hidden" name="depairport" value="${list.depairport}"/>${list.depairport}</td>
									<td><input type="hidden" name="arrairport" value="${list.arrairport}"/>${list.arrairport}</td>
									<td><input type="hidden" name="estimate" value="${list.estimate}"/>${list.estimate}</td>
									<td><input type="hidden" name="arrtime" value="${list.arrtime}"/>${list.arrtime}</td>
									<c:if test="${not empty loginUser}">
										<%-- <td><button type="submit" class="btn btn-primary" onclick="location.href='UserServlet?command=buyticket&userid=${loginUser.userid}'">예매</button></td> --%>
										<c:choose>
											<c:when test="${not empty checkin}">
												<td><button type="submit" class="btn btn-primary" onclick="location.href='UserServlet?command=buyticket&userid=${loginUser.userid}&num=${list.num}&department=${list.depairport}&arrive=${list.arrairport}&checkin=${list.regidate}'">예매</button></td>
											</c:when>
											<c:otherwise>
												<td><button type="submit" class="btn btn-primary" onclick="location.href='UserServlet?command=buyticket&userid=${loginUser.userid}&num=${list.num}'">예매</button></td>
											</c:otherwise>	
										</c:choose>
									</c:if>	
								</tr>
							</tbody>
						</c:forEach>
					</table>
				<!-- </form> -->
				<div class="container">
				  <ul class="pagination pagination justify-content-center">
					<c:choose>
   					  	<c:when test="${PrevPage eq -9}">
					    	<li class="page-item"><a class="page-link" onclick="return previous()">Previous</a></li>
					    </c:when>
   					  	<c:otherwise>
					    	<li class="page-item"><a class="page-link" onclick="location.href='UserServlet?command=flightList&curPage=${PrevPage}'">Previous</a></li>
					    </c:otherwise>
				    </c:choose>
				    <c:forEach items="${pageCount}" var="page">
				    	<li class="page-item"><a class="page-link" href="UserServlet?command=flightList&curPage=${page}" name="curPage">${page}</a></li>
				    </c:forEach>
 					<c:choose>
					  	<c:when test="${nextPage gt lastPage}">
					  		<li class="page-item"><a class="page-link" onclick="return next()">Next</a></li>
					    </c:when>
   					  	<c:otherwise>
   					  		<li class="page-item"><a class="page-link" onclick="location.href='UserServlet?command=flightList&curPage=${nextPage}'">Next</a></li>
					    	
					    </c:otherwise>
				   </c:choose> 
				  </ul>
				</div>
			</div>
		</div>
	
		<%@ include file="../common/footer.jsp"%>
	</div>
	<script type="text/javascript">
		function previous(){
			alert("첫번째 블록 입니다.");
		}
		
		function next() {
			alert("마지막 블록 입니다.");
		}
		
		function buy(num){
			var form = document.frm;
			if(!window.confirm("티켓을 구매하시겠습니까?")){
				return false;
			}else{
				alert("구매를 진행합니다.");
				location.href="UserServlet?command=buyticket&num="+num;
				
			}
		} 
		function checksubmit() {
	     
			if (document.frm.department.value.length == 0 ) {
	            alert("출발지를 입력해주세요.");
	            document.frm.department.focus;
	            return false;
	         } 
	         else if(document.frm.arrive.value.length == 0){
	            alert("도착지를 입력해주세요.")
	            document.frm.arrive.focus;
	            return false;
	         } 
			
			const dayform = ["Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"];
			var date = $("#inputCheckIn").val();
			var arrdate = date.split(" ");
			
			if(dayform.indexOf(arrdate[0])<0){
				alert("날짜를 올바로 입력해주세요.");
				document.frm.inputCheckIn.focus;
				return false;
			}
			
			const monform = ["Oct", "Nov", "Dec", "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep"];
			if(monform.indexOf(arrdate[1])<0){
				alert("날짜를 올바로 입력해주세요.");
				document.frm.inputCheckIn.focus;
				return false;
			}
			
			if(isNan(arrdate[2])){
				alert("날짜를 올바로 입력해주세요.");
				document.frm.inputCheckIn.focus;
				return false;
			}
			
			if(isNan(arrdate[3])){
				alert("날짜를 올바로 입력해주세요.");
				document.frm.inputCheckIn.focus;
				return false;
			}
			
	 
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