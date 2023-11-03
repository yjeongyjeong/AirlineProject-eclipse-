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
					<h2>공지사항 상세페이지</h2>
					<p>notice page for Kakao airline</p>
				</div>	
				<form action="UserServlet?command=noticeupdate" method="post" enctype="multipart/form-data" id ="formData" name="frm">		
				<c:if test="${loginUser.admin == 1}">	
					<div class="form-group tm-form-group tm-form-group-pad tm-form-group-4">
						<label for="btnSubmit">&nbsp;</label>
							<button type="button" class="btn btn-primary tm-btn tm-btn-search text-uppercase" id="btnSubmit" onclick="location.href='UserServlet?command=noticeupdate&num=${boardnum}'">수정</button>
					</div>
				</c:if>
				
					<!-- row -->
				<div class="row tm-banner-row">	
				<table class="table table-hover">
					<thead>
						<tr>
							<td>제목</td>
							<td name="boardsubject" value="${vo.boardsubject}">${vo.boardsubject}</td>
						</tr>
					</thead>

					<tbody>
						<tr>
							<td>작성자</td>
							<td name="userid" value="${vo.userid}">${vo.userid}</td>
						</tr>
						<tr>
							<td>작성일</td>
							<td  name="modifydate" value="${vo.modifydate}">${vo.modifydate}</td>
						</tr>
						<tr>
							<td>이미지 파일</td>
							<c:choose>
								<c:when test="${empty img.origin}">
									<img src="upload/chunsik.png" width="300px" height="300px"/>
									<td>이미지 출력안됨...</td>
								</c:when>
								<c:otherwise>
									<img src="${img.fileUrl}\${img.origin}" width="300px" height="300px"/>
									<td>이미지 출력</td>
								</c:otherwise>
							</c:choose>
						</tr>
						<tr>
							<td>내용</td>
							<td name="boardcontent" value="${vo.boardcontent}">${vo.boardcontent}</td>
						</tr>
					</tbody>

				</table>
				</div>
</form>
			</div>
		</div>
	<%@ include file="../common/footer.jsp"%>
	</div>
	
	<script type="text/javascript">

	</script>
</body>
</html>