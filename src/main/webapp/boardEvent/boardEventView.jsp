<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
<link rel="stylesheet" type="text/css" href="css/shopping.css">
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
	
	<input type="hidden" id="boardNum" name="boardNum" value="${board.boardNum}" readonly="readonly">
	<input type="hidden" id="userId" name="userId" value="${loginUser.userid}" readonly="readonly">
	<div class="container">
		<h1>이벤트 상세보기</h1>
			<form action="boardEventDelete.do" method="get" name="frm">
			<input type="hidden" id="boardNum" name="boardNum" value="${board.boardNum}" readonly="readonly">
			<div class="form-group">
				<label for="boardTitle">제목</label> 
				<input type="text" class="form-control" id="boardTitle" name="boardTitle" value="${board.boardTitle}" readonly="readonly">
			</div>

			<div class="form-group">
				<label for="startDate">시작일</label> 
				<input type="text" class="form-control" id="startDate" name="startDate" value="${board.startDate}" readonly="readonly">
			</div>

			<div class="form-group">
				<label for="endDate">종료일</label> 
				<input type="text" class="form-control" id="endDate" name="endDate" value="${board.endDate}" readonly="readonly">
			</div>
			
			<div class="form-group">
				<label for="readCount">조회수</label> 
				<input type="text" class="form-control" id="readCount" name="readCount" value="${board.readCount}" readonly="readonly">
			</div>

			<div class="form-group">
				<label for="regiDate">등록일/수정일</label> 
				<c:choose>
					<c:when test="${board.regiDate>=board.modifyDate}">
						 : ${board.regiDate}					
					</c:when>
					<c:when test="${board.regiDate<board.modifyDate}">
						 : ${board.modifyDate}
					</c:when>
				</c:choose>
			</div>
	
			<c:if test="${imgCount>0}">
			<%-- 	<ul>
					<li>이미지</li><br>
					<li>이미지 개수 : ${imgCount}</li>	<br>	
				</ul> --%>
	
				<c:forEach var="file" items="${fileList}">
					<c:if test="${file.repImgYn=='Y'}">
						<input type="hidden" id="boardNum" name="boardNum" value="${board.boardNum}" readonly="readonly">	
						<!-- 파일이름 : ${file.oriFileName} &nbsp; &nbsp;	<br> -->
						<!-- 대표이미지입니다.<br> -->
						<img src="./upload/${file.oriFileName}" style="max-width: 100%; height: auto;">	<br>
					</c:if>
					<c:if test="${file.repImgYn=='N'}">
						<input type="hidden" id="boardNum" name="boardNum" value="${board.boardNum}" readonly="readonly">	
						<!-- 파일이름 : ${file.oriFileName} &nbsp; &nbsp;	<br> -->
						<img src="./upload/${file.oriFileName}" style="max-width: 100%; height: auto;">	<br>
					</c:if>
				</c:forEach>
			</c:if>
			
			<div class="form-group">
				<label for="boardContent"><!-- 내용 --></label><br>
				<textarea id="summernote" class="summernote" name="boardContent" readonly="readonly">${board.boardContent}</textarea>
			</div>
			
			<div id="bottom"></div>
			<div class="mt-3 text-right">
			<c:choose>
				<c:when test="${loginUser.admin==1}">
					<button type="button" class="gradient" onclick="location.href='boardEventList.do'" style="width: 100px">리스트목록</button>
					<button type="button" class="gradient" onclick="location.href='boardEventListGrid.do'" style="width: 100px">그리드목록</button>
					<button type="button" class="gradient" onclick="del()">삭제</button>&nbsp;
					<button type="button" class="gradient" onclick="location.href='boardEventUpdate.do?boardNum=${board.boardNum}'">수정</button>	
					<button type="button" class="gradient" onclick="location.href='boardEventWrite.do'" id="write">글쓰기</button>
				</c:when>
				<c:otherwise>
					<button type="button" class="gradient" onclick="location.href='boardEventList.do'" style="width: 100px">리스트목록</button>
					<button type="button" class="gradient" onclick="location.href='boardEventListGrid.do'" style="width: 100px">그리드목록</button>
				</c:otherwise>
			</c:choose>
			</div>
			<br><br>
			<div style="position: fixed; bottom: 5px; right: 5px;">
				<a href="#">
				<img src="./img/upArrow.png" width="100px" height="100px" title="위로">
				</a><br>
				<a href="#bottom">
				<img src="./img/downArrow.png" width="100px" height="100px" title="아래로">
				</a>
			</div>
			
		</form>
	</div>

	<script>
		$(document).ready(
				function() {
					$('#summernote').summernote({
						disableDragAndDrop: true,
						/* height: 800, */
						lang : "ko-KR"
					});
					$('#summernote').summernote('disable')({
						/* height : 300,
						width : 500, */
						
						
					});
					
				});
	</script>
	<%@ include file="../common/footer.jsp"%>
</body>
</html>