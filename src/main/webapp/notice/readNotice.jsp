<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@include file="../common/header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700">
<link rel="stylesheet"
	href="https://unpkg.com/tailwindcss@^2/dist/tailwind.min.css">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/gh/alpinejs/alpine@v2.x.x/dist/alpine.min.js">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<!-- Google web font "Open Sans" -->
<link rel="stylesheet" href="/css/font-awesome.min.css">
<!-- Font Awesome -->
<link rel="stylesheet" href="css/bootstrap.min.css">
<!-- Bootstrap style -->
<link rel="stylesheet" type="text/css" href="css/datepicker.css" />
<link rel="stylesheet" type="text/css" href="slick/slick.css" />
<link rel="stylesheet" type="text/css" href="slick/slick-theme.css" />
<link rel="stylesheet" href="css/templatemo-style.css">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
<script
	src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.slim.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script type="text/javascript" src="script/boardQna.js"></script>
<link rel="stylesheet" type="text/css" href="css/searchbar.css">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
<script src="summernote/summernote-lite.js"></script>
<script src="summernote/summernote-ko-KR.js"></script>
<link rel="stylesheet" href="summernote/summernote-lite.css">
<title>Insert title here</title>
<script type="text/javascript" src="script/boardDiary.js"></script>
<link rel="stylesheet" type="text/css" href="css/searchbar.css">
<style>
body {
	font-family: Arial, sans-serif;
	/* background-color: #f0f0f0; */
	margin: 0 auto;
	padding: 0;
	width: 70%;
}

.container1 {
	background-color: #fff;
	margin: 0 auto;
	padding: 10px;
	border: 1px solid #ddd;
	border-radius: 5px;
	box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
}

.form-group1 {
	margin-bottom: 15px;
}

.button123 {
	margin-bottom: 20px;
	margin-top: 20px;
}

pre{
    
    padding:10px;
    overflow: auto;
    white-space: pre-wrap; /* pre tag내에 word wrap */
}  
</style>
</head>
<body>


	<div class="container-fluid mt-5">
		<div class="row">
			<div class="col-md-5">
				<div class="carousel slide" data-ride="carousel" id="carousel-1">
					<div class="carousel-inner" role="listbox">
						<div class="carousel-item active">
							<img class="img-thumbnail w-100 "
								src="<%= request.getContextPath() %>/noticeFileUpload/${file.origin}"
								onclick="window.open(this.src)"
								onerror="this.style.display='none';">
								<c:if test="${!empty file.fileurl}">
								<span style="font-size:11px;">사진을 클릭하시면 더 자세히 보입니다.</span>
								</c:if>
						</div>
					</div>
				</div>
			</div>

			<div class="col-md-7">
				<h2>${list.boardsubject}</h2>
				<hr>
				<div class="price">
					<span class="mr-2"><i class="fa fa-rupee text-success"></i>작성자
						: &nbsp;${list.boardwriter}</span>
				</div>
				<hr>
				<div class="d-flex align-items-center mt-4 offers mb-1">
					<pre > <span class="ml-1 font-weight-bold">${list.boardcontent}</span>
					</pre>
				</div>

				<br> <br> <br> <br> <br> <br> <br>

				<div class="mt-3" style="display: inline;">
					<c:if test="${loginUser.admin==1}">
						<button class="btn btn-warning mr-2" type="button"
							onclick="location.href='BoardServlet?command=notice_write_form'">글쓰기</button>
					</c:if>
					<c:if test="${loginUser.admin==1}">
						<button class="btn btn-primary mr-2" type="button"
							onclick="location.href='BoardServlet?command=notice_update_form&boardnum=${list.boardnum}'">수정</button>
					</c:if>

					<button class="btn btn-secondary mr-2" type="button"
						onclick="location.href='BoardServlet?command=notice_list'">목록</button>
					<br> <br>
					<c:if test="${loginUser.admin==1}">
						<form action="BoardServlet" method="post">
							<input type="hidden" name="command" value="notice_delete">
							<input type="hidden" name="boardnum" value="${list.boardnum}">
							<button class="btn btn-danger mr-2" type="submit"
								onclick="return del()">삭제</button>
						</form>
					</c:if>
				</div>
			</div>
		</div>
	</div>
	<br>
	<br>



	<%-- 	<div class="container">

		<h2><a href="BoardServlet?command=notice_list" style="text-decoration: none; color: gray;">공지사항 게시글</a></h2>
		<div class="form-group">제목 : ${list.boardsubject}</div>

		<div class="form-group">작성자 : ${list.boardwriter}</div>
		<input type="hidden" name="uuid" value="${file.uuid}">
		<input type="hidden" name="fileurl" value="${file.fileurl}">
		<input type="hidden" name="origin" value="${file.origin}">
		<input type="hidden" name="extension" value="${file.extension}">
			<img width="400px" height="auto" alt="" onerror="this.style.display='none'" src="<%= request.getContextPath() %>/noticeFileUpload/${file.fileurl}">

		<div class="form-group">
			내용 <br>
			<fieldset>
				${list.boardcontent}<br>
			</fieldset>
		</div>
		<div class="button123">
		<c:if test="${loginUser.admin==1}">
		<button type="button" class="btn btn-primary" style="float:left; margin-left:30px; "
			onclick="location.href='BoardServlet?command=notice_write_form'">글쓰기</button>
		&nbsp;
		</c:if>
		<c:if test="${loginUser.admin==1}">
		<button type="button" class="btn btn-info" style="float:left; margin-left:-140px; "
			onclick="location.href='BoardServlet?command=notice_update_form&boardnum=${list.boardnum}'">수정</button>
		&nbsp;
		</c:if>
		
		<button type="button" class="btn btn-success" style="float:left; margin-left:-140px; "
			onclick="location.href='BoardServlet?command=notice_list'">목록</button>
		&nbsp;

		<c:if test="${loginUser.admin==1}">
		<form action="BoardServlet" style="float:left; margin-left:-140px; ">
		<input type="hidden" name="command" value="notice_delete">
		<input type="hidden" name="boardnum" value="${list.boardnum}">
		<button type="submit" id="delbtn" class="btn btn-info" onclick="return del()">삭제</button>
		</form>
		&nbsp;
		</c:if>
		</div>
	</div>
 --%>
	<%@include file="../common/footer.jsp"%>
</body>
</html>