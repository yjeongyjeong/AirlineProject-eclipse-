<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="../common/header.jsp"%>
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
		<div class="tm-container-outer">
			<div class="container">
			<!-- row -->
				<div class="row tm-banner-row">
					<h2>공지사항</h2>
					<p>notice page for Kakao airline</p>
				<c:if test="${loginUser.admin == 1}">	
					<div class="form-group tm-form-group tm-form-group-pad tm-form-group-4">
						<label for="btnSubmit">&nbsp;</label>
							<button type="button" class="btn btn-primary tm-btn tm-btn-search text-uppercase" id="btnSubmit" onclick="location.href='UserServlet?command=noticeWrite&userid=${loginUser.userid}'">글쓰기</button>
					</div>
				</c:if>
				</div>
					<!-- row -->
				<div class="row tm-banner-row">	
				<table class="table table-hover">
					<thead>
						<tr>
							<th>번호</th>
							<th>제목</th>
							<th>작성일</th>
							<th>작성자</th>
							<th>조회수</th>
							<c:if test="${not empty loginUser}">
								<th>삭제</th>
							</c:if>
						</tr>
					</thead>
					<c:forEach items="${pagelist}" var="list">
						<tbody>
							<tr>
								<td><a href="UserServlet?command=noticeDetail&num=${list.boardnum}">${list.boardnum}</a></td>
								<td><a href="UserServlet?command=noticeDetail&num=${list.boardnum}">${list.boardsubject}</a></td>
								<td><a href="UserServlet?command=noticeDetail&num=${list.boardnum}">${list.regidate}</a></td>
								<td><a href="UserServlet?command=noticeDetail&num=${list.boardnum}">${list.userid}</a></td>
								<td><a href="UserServlet?command=noticeDetail&num=${list.boardnum}">${list.views}</a></td>
								<c:if test="${not empty loginUser}">
									<td><button type="button" class="btn btn-primary" onclick="location.href='UserServlet?command=userDelete&num=${list.boardnum}'">삭제</button></td>
								</c:if>
							</tr>
						</tbody>
					</c:forEach>
				</table>
				</div>
				<div class="container">
				  <ul class="pagination pagination justify-content-center">
				  	<c:choose>
					  	<c:when test="{page!=1}">
					    	<li class="page-item"><a class="page-link" onclick="location.href='UserServlet?command=user_list&curPage=${prevPage}'">Previous</a></li>
					    </c:when>
   					  	<c:otherwise>
					    	<li class="page-item"><a class="page-link" onclick="return previous()">Previous</a></li>
					    </c:otherwise>
				    </c:choose>
				    <c:forEach items="${page}" var="page">
				    	<li class="page-item"><a class="page-link" href="UserServlet?command=user_list&curPage=${page}" name="curPage">${page}</a></li>
				    </c:forEach>
   				  	<c:choose>
					  	<c:when test="{page!=lastPage}">
					    	<li class="page-item"><a class="page-link" onclick="location.href='UserServlet?command=user_list&curPage=${nextPage}'">Next</a></li>
					    </c:when>
   					  	<c:otherwise>
					    	<li class="page-item"><a class="page-link" onclick="return next()">Next</a></li>
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
			alert("첫 페이지 입니다");
		}
		
		function next() {
			alert("마지막 페이지 입니다");
		}
		
		function delete(){
			var confirmValue = confirm("정말 삭제하시겠습니까?");
			if(confirmValue == true){
				location.href='UserServlet?command=userDelete&num=${list.boardnum}';
				return true;
			}else{
				return false;
			}
			
		}
	</script>
</body>
</html>