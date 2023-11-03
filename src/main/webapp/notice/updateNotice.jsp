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
<script type="text/javascript" src="script/boardQna.js"></script>
<style type="text/css">
</style>
</head>
<body>

	<div class="py-4 px-2 w-3/5 m-auto">
		<form action="FileServlet" method="post" name="frm"
			enctype="multipart/form-data">
			<input type="hidden" name="command" value="notice_update"> <input
				type="hidden" name="boardnum" value="${list.boardnum}">


			<div
				class="max-w-full bg-white rounded-lg overflow-hidden md:max-w-full">
				<div class="md:flex">
					<div class="w-full px-1 py-1">

						<div class="mb-1">
							<span class="text-sm">제목</span> <input type="text"
								name="boardsubject" value="${list.boardsubject}" readonly
								class="h-12 px-3 w-full border-yellow-400 border-2 rounded focus:outline-none focus:border-blue-600">
						</div>
						<div class="mb-1">

							<span>파일 업로드</span>
							
							<div
								class="relative border-dotted h-12 rounded-lg border-dashed border-2 border-yellow-400 bg-gray-100 flex justify-center items-center">
								<div class="absolute">
									<div class="flex flex-col items-center">
									
										<i class="fa fa-folder-open fa-1x text-blue-700">글 수정 시 사진을 다시 업로드 해주십시오.</i> 
										<span
											class="block text-gray-400 font-normal"> </span>
									</div>
								</div>
								<input type="file" class="h-full w-full opacity-0"
									name="fileurl" value="${file.fileurl}">
							</div>

							<div class="mb-1">
								<span class="text-sm">내용</span>
								<textarea type="text" name="boardcontent"
									class="h-56 py-1 px-6 w-full border-2 border-yellow-400 rounded focus:outline-none focus:border-blue-600 resize-none">${list.boardcontent}</textarea>
							</div>


						</div>

						<div class="mt-3 text-right">

							<input
								class="ml-2 h-10 w-24 bg-yellow-400 rounded text-white hover:bg-green-700"
								type="reset" value="다시 작성">
							<button
								class="ml-2 h-10 w-24 bg-yellow-400 rounded text-white hover:bg-blue-700"
								onclick="location.href='BoardServlet?command=notice_list'">목록</button>
							<button
								class="ml-2 h-10 w-24 bg-yellow-400 rounded text-white hover:bg-blue-700"
								onclick="return boardnoticeCheck()">등록</button>

						</div>


					</div>
				</div>
			</div>
		</form>
	</div>

	<script type="text/javascript">
		function boardnoticeCheck() {
			if (document.frm.boardsubject.value.trim() == "") {
				alert("제목을 입력해주세요.");
				return false;
			}
			return true;
		}
	</script>
	<%@include file="../common/footer.jsp"%>
</body>
</html>



<%-- <%@ page language="java" contentType="text/html; charset=UTF-8"
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
<script type="text/javascript" src="script/boardQna.js"></script>
<style type="text/css">
</style>
</head>
<body>

	<div class="py-4 px-2 w-3/5 m-auto">
		<form action="FileServlet" method="post" name="frm"
			enctype="multipart/form-data">
			<input type="hidden" name="command" value="notice_update"> <input
				type="hidden" name="boardnum" value="${list.boardnum}">
				
			<div
				class="max-w-full bg-white rounded-lg overflow-hidden md:max-w-full">
				<div class="md:flex">
					<div class="w-full px-1 py-1">

						<div class="mb-1">
							<span class="text-sm">제목</span> <input type="text"
								name="boardsubject" value="${list.boardsubject}" readonly
								class="h-12 px-3 w-full border-blue-400 border-2 rounded focus:outline-none focus:border-blue-600">
						</div>
						
						<input type="hidden" name="uuid" value="${file.uuid}">
		<input type="hidden" name="fileurl" value="${file.fileurl}">
		<input type="hidden" name="origin" value="${file.origin}">
		<input type="hidden" name="extension" value="${file.extension}">
			
						<div class="mb-1">

							<span>파일 업로드</span>

							<div
								class="relative border-dotted h-12 rounded-lg border-dashed border-2 border-blue-700 bg-gray-100 flex justify-center items-center">
								<div class="absolute">
									<div class="flex flex-col items-center">
										<i class="fa fa-folder-open fa-3x text-blue-700"></i> <span
											class="block text-gray-400 font-normal"> </span>
									</div>
								</div>
								<input type="file" class="h-full w-full opacity-0"
									name="fileurl" value="${file.fileurl}">
							</div>

							<div class="mb-1">
								<span class="text-sm">내용</span>
								<textarea type="text" name="boardcontent"
									class="h-56 py-1 px-6 w-full border-2 border-blue-400 rounded focus:outline-none focus:border-blue-600 resize-none" 
									>${list.boardcontent}</textarea>
							</div>



						</div>

						<div class="mt-3 text-right">

							<input
								class="ml-2 h-10 w-24 bg-blue-600 rounded text-white hover:bg-green-700"
								type="reset" value="다시 작성">
							<button
								class="ml-2 h-10 w-24 bg-blue-600 rounded text-white hover:bg-blue-700"
								onclick="location.href='BoardServlet?command=notice_list'">목록</button>
							<button
								class="ml-2 h-10 w-24 bg-blue-600 rounded text-white hover:bg-blue-700"
								onclick="return boardnoticeCheck()">수정</button>

						</div>


					</div>
				</div>
			</div>
		</form>
	</div>

	<script type="text/javascript">
		function boardnoticeCheck() {
			if (document.frm.boardsubject.value.trim() == "") {
				alert("제목을 입력해주세요.");
				return false;
			}
			return true;
		}
	</script>
	<%@include file="../common/footer.jsp"%>
</body>
</html>



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
<script type="text/javascript" src="script/boardQna.js"></script>
</head>
<body>

	<div class="container">
		<h2><a href="BoardServlet?command=notice_list" style="text-decoration: none; color: gray;">공지사항 수정</a></h2>
		<form action="FileServlet" method="post" name="frm" enctype="multipart/form-data">
			<input type="hidden" name="command" value="notice_update" >
			<input type="hidden" name="boardnum" value="${list.boardnum}">
			<div class="form-group">
				<label for="boardsubject">제목</label> <input type="text"
					class="form-control" id="boardsubject" name="boardsubject"
					value="${list.boardsubject}" readonly>
			</div>
			
			<br>
			<input type="hidden" name="uuid" value="${file.uuid}">
		<input type="hidden" name="fileurl" value="${file.fileurl}">
		<input type="hidden" name="origin" value="${file.origin}">
		<input type="hidden" name="extension" value="${file.extension}">
			<input type="file" id="fileurl" name="fileurl" class="btn btn-warning" style="float:left;" value="${file.fileurl}">
			<br><br>

			<div class="form-group">
				<label for="boardContent">내용</label><br>
				<textarea id="summernote" class="summernote" name="boardcontent"
					placeholder="${list.boardcontent}"></textarea>
			</div>

			<button type="submit" class="btn btn-primary" style="float:left; margin-left:30px; ">수정</button>
			&nbsp;
			<button type="reset" class="btn btn-info" style="float:left; margin-left:-140px; ">다시작성</button>
			&nbsp;
			<button type="button" class="btn btn-success" style="float:left; margin-left:-140px; "
				onclick="location.href='BoardServlet?command=notice_list'">목록</button>
			&nbsp;
		</form>
		
		<br><br>
	</div>
	<script>
		$(document).ready(function() {
			$('#summernote').summernote({
				height : 300,
				width : 1000,
				lang : "ko-KR",
			});

		});
	</script>
<%@include file="../common/footer.jsp"%>
</body>
</html> --%>