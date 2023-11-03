<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!--<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>-->
<%@include file="../common/header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
<script	src="https://cdn.jsdelivr.net/npm/jquery@3.6.4/dist/jquery.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
<script src="summernote/summernote-lite.js"></script>
<script src="summernote/summernote-ko-KR.js"></script>
<link rel="stylesheet" href="summernote/summernote-lite.css">
<title>Insert title here</title>
<script type="text/javascript" src="script/boardDiary.js"></script>

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
<style>
.gradient {
	width: 80px;
	height: 30px;
	font-weight: 900;
	color: white;
	text-align: center;
	background: #FFC107;
	border: solid 2px white;
	border-radius: 5px;
}
</style>

</head>
<body style="background-color: white;">
	
	 <div class="container">
		<h2>여행일기 등록</h2>
		<form action="boardDiaryWrite.do" method="post" name="frm">
			<input type="hidden" id="boardNum" name="boardNum" value="${boardNum}"> 
			<div class="form-group">
				<label for="boardTitle">제목</label> 
				<input type="text" class="form-control" id="boardTitle" name="boardTitle">
			</div>
			
			<div class="form-group">
				<label for="boardWriter">작성자</label> 
				<input type="text" class="form-control" id="boardWriter" name="boardWriter" value="${loginUser.userid}" readonly="readonly">
			</div>
			
			<div class="form-group">
				<label for="boardContent">내용</label><br> 
				<textarea id="summernote" class="summernote" name="boardContent"></textarea>
			</div>
			
			<div class="mt-3 text-right">
				<button type="button" class="gradient" onclick="location.href='boardDiaryList.do'">목록</button> &nbsp;
				<button type="reset" class="gradient">다시작성</button> &nbsp;
				<button type="submit" class="gradient" onclick="return boardCheck()">등록</button> &nbsp;
			<br>

			</div>
		</form>
			<br><br>
	</div> 

 	<script>
		$(document).ready(
				function() {
					$('#summernote').summernote({
						height : 700,
						lang : "ko-KR",
					});

				});
	</script> 
	<%@ include file="../common/footer.jsp"%>
</body>
</html>