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
		<form action="BoardServlet" method="post" name="frm"
			enctype="multipart/form-data">
			<input type="hidden" name="command" value="write_notice">


			<div
				class="max-w-full bg-white rounded-lg overflow-hidden md:max-w-full">
				<div class="md:flex">
					<div class="w-full px-1 py-1">

						<div class="mb-1">
							<span class="text-sm">제목</span> <input type="text"
								name="boardsubject"
								class="h-12 px-3 w-full border-yellow-400 border-2 rounded focus:outline-none focus:border-blue-600">
						</div>
						<div class="mb-1">

							<span>파일 업로드</span>

							<div
								class="relative border-dotted h-12 rounded-lg border-dashed border-2 border-yellow-400 bg-gray-100 flex justify-center items-center">
								<div class="absolute">
									<div class="flex flex-col items-center">
										<i class="fa fa-folder-open fa-1x text-blue-600">파일을
											드래그하시거나, 눌러서 파일 첨부를 해주세요.</i> <span
											class="block text-gray-400 font-normal"> </span>
									</div>
								</div>
								<input type="file" class="h-full w-full opacity-0"
									name="fileurl">
							</div>

							<div class="mb-1">
								<span class="text-sm">내용</span>
								<textarea name="boardcontent"
									class="h-56 py-1 px-6 w-full border-2 border-yellow-400 rounded focus:outline-none focus:border-blue-600 resize-none"></textarea>
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