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
<link rel="stylesheet" type="text/css" href="css/searchbar.css">
<script type="text/javascript" src="script/boardDiary.js"></script>
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
			<div class="col-md-12">
				<h2>${list.boardsubject}</h2>
				<hr>
				<div class="price">
					<span class="mr-2"><i class="fa fa-rupee text-success"></i>작성자
						: &nbsp;${list.boardwriter}</span>
				</div>
				<hr>
				<div class="d-flex align-items-center mt-4 offers mb-1">
					<pre> <span
						class="ml-1 font-weight-bold">${list.boardcontent}</span></pre>
				</div>
				
				<br> <br> <br> <br> <br> <br> <br>

				<div class="mt-3" style="display: inline;">
					<c:if test="${!empty loginUser.admin}">
						<button class="btn btn-secondary mr-2" type="button"
							onclick="location.href='BoardServlet?command=qna_write_form'">글쓰기</button>
					</c:if>
					<c:if
						test="${loginUser.korName==list.boardwriter || loginUser.admin==1}">
						<button class="btn btn-dark mr-2" type="button"
							onclick="location.href='BoardServlet?command=qna_update_form&boardnum=${list.boardnum}'">수정</button>
					</c:if>
					<c:if test="${loginUser.admin==1}">
						<button class="btn btn-warning mr-2" type="button"
							onclick="location.href='BoardServlet?command=qna_reply_form&boardnum=${list.boardnum}'">답변</button>
					</c:if>

					<button class="btn btn-info mr-2" type="button"
						onclick="location.href='BoardServlet?command=qna_list'">목록</button>
					<br> <br>
					<c:if
						test="${loginUser.korName==list.boardwriter || loginUser.admin==1}">
						<form action="BoardServlet" method="post">
							<input type="hidden" name="command" value="qna_delete"> <input
								type="hidden" name="boardnum" value="${list.boardnum}">
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

	<%@include file="../common/footer.jsp"%>
</body>
</html>
