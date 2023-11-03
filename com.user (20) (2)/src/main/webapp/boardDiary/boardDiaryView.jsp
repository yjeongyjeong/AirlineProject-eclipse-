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
		<h1>게시글 상세보기</h1>
			<form action="boardDiaryDelete.do" method="get" name="frm">
			<input type="hidden" id="userId" name="userId" value="${loginUser.userid}" readonly="readonly">
			<input type="hidden" id="boardNum" name="boardNum" value="${board.boardNum}" readonly="readonly">
			<div class="form-group">
				<label for="boardTitle">제목</label> 
				<input type="text" class="form-control" id="boardTitle" name="boardTitle" value="${board.boardTitle}" readonly="readonly">
			</div>
	
			<div class="form-group">
				<label for="boardWriter">작성자</label> 
				<input type="text" class="form-control" id="boardWriter" name="boardWriter" value="${board.boardWriter}" readonly="readonly">
			</div>
	
			<div class="form-group">
				<label for="boardContent">내용</label><br>
				<textarea id="summernote" class="summernote" name="boardContent" readonly="readonly">${board.boardContent}</textarea>
			</div>
			
			<div class="mt-3 text-right">	
			<c:choose>
				<c:when test="${loginUser.admin==1}">
					<button type="button" class="gradient" onclick="location.href='boardDiaryList.do'">목록</button>&nbsp;	
					<button type="button" class="gradient" onclick="location.href='boardDiaryUpdate.do?boardNum=${board.boardNum}'">수정</button>&nbsp;	
					<button type="button" class="gradient" onclick="location.href='boardDiaryWrite.do'">글쓰기</button>&nbsp;
					<button type="button" class="gradient" onclick="del()">삭제</button>&nbsp;
				</c:when>
				<c:when test="${loginUser.userid==board.boardWriter}">
					<button type="button" class="gradient" onclick="location.href='boardDiaryList.do'">목록</button>&nbsp; 
					<button type="button" class="gradient" onclick="location.href='boardDiaryUpdate.do?boardNum=${board.boardNum}'">수정</button>&nbsp;	
					<button type="button" class="gradient" onclick="location.href='boardDiaryWrite.do'">글쓰기</button>&nbsp;
					<button type="reset" class="gradient" onclick="del()">삭제</button>&nbsp;
				</c:when>
				<c:when test="${!empty loginUser.admin}">
					<button type="button" class="gradient" onclick="location.href='boardDiaryList.do'">목록</button>&nbsp;
					<button type="button" class="gradient" onclick="location.href='boardDiaryWrite.do'">글쓰기</button>&nbsp;
				</c:when>
				<c:otherwise>
					<button type="button" class="gradient" onclick="location.href='boardDiaryList.do'">목록</button>&nbsp; <br> <br>
				</c:otherwise>
			</c:choose>
			</div>
			<br>
			<div style="position: fixed; bottom: 5px; right: 5px;">
				<a href="#">
				<img src="./img/upArrow.png" width="100px" height="100px" title="위로">
				</a><br>
				<a href="#allReply">
				<img src="./img/downArrow.png" width="100px" height="100px" title="아래로">
				</a>
			</div>
			<br><br><br>
		</form>
	</div>

	<script>
		$(document).ready(
				function() {
					$('#summernote').summernote({
						disableDragAndDrop: true
					});
					$('#summernote').summernote('disable')({
						height : 300,
						width : 500,
						lang : "ko-KR"
					});
					
				});
	</script>
	<div class="container">
	<form action="boardDiaryLikeInsert.do" method="post"  style="margin: auto; width: 220px;">
		<input type="hidden" id="boardNum" name="boardNum" value="${board.boardNum}">
		<input type="hidden" id="userId" name="userId" value="${loginUser.userid}">
		<c:choose> 
			<c:when test="${empty loginUser.userid}">
				<p>추천 ${likecount}개</p>
				<!-- <input type="image" src="../img/thumbsUp.png" style="width: 80px; height: 80px;"><br>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;5개 -->
			</c:when>
			<c:when test="${checkLike==-1}"> 
			 	<input type="image" src="./img/thumbsUp.png" style="width: 80px; height: 80px;">
			</c:when>  
			<c:when test="${checkLike==0}"> 
			 	<%-- <input type="submit" value="추천 ${likecount}개"><input> --%>
			 	<input type="image" src="./img/thumbsUp.png" style="width: 80px; height: 80px;"><br>
			 	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${likecount}개<br>
			 	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;추천하기
			</c:when>  
			<c:when test="${checkLike==1}">
			 	<%-- <input type="submit" value="추천취소 ${likecount}개"> --%>
			 	<input type="image" src="./img/thumbsUp.png" style="width: 80px; height: 80px;"><br>
			 	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${likecount}개<br>
			 	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;추천취소
			</c:when> 
		 </c:choose> 
	</form>
	<br><br>
	</div>
	<div id="bottom"></div>
	<div class="container" id="allReply">
		 <c:if test="${replyCount>0}">
			<ul style="list-style:none; padding-left:0px;">
				<h2>댓글</h2>
				<li>댓글 개수 : ${replyCount}</li>			
			</ul>

			<c:forEach var="reply" items="${replyList}">
					<input type="hidden" id="boardNum" name="boardNum" value="${board.boardNum}" readonly="readonly">	
					번호 : ${reply.replyNum} &nbsp; &nbsp;
					작성자 : ${reply.replyWriter} &nbsp; &nbsp;
					<c:choose>
						<c:when test="${reply.regiDate>=reply.modifyDate}">
							날짜 : ${reply.regiDate}					
						</c:when>
						<c:when test="${reply.regiDate<reply.modifyDate}">
							날짜 : ${reply.modifyDate}
						</c:when>
					</c:choose>
					<form action="boardDiaryReplyUpdate.do" method="post">
						
						<c:choose> 
						<c:when test="${loginUser.userid==reply.replyWriter}">	
							<input type="hidden" id="boardNum" name="boardNum" value="${board.boardNum}">	
							<input type="hidden" id="replyNum" name="replyNum" value="${reply.replyNum}">
  							내용 : <textarea name="replyContent" class="chat-text" cols="80" rows="1" oninput="calcTextareaHeight(this)" >${reply.replyContent}</textarea> 	
							<button type="submit" class="gradient">수정</button>&nbsp;	
							<button type="button" class="gradient" onclick="del(); location.href='boardDiaryReplyDelete.do?boardNum=${board.boardNum}&replyNum=${reply.replyNum}'">삭제</button>&nbsp;
						</c:when>
						<c:otherwise>
							<input type="hidden" id="boardNum" name="boardNum" value="${board.boardNum}">	
							<input type="hidden" id="replyNum" name="replyNum" value="${reply.replyNum}">
  							내용 : ${reply.replyContent}								
						</c:otherwise>
						</c:choose>
					</form>
					<hr>
			</c:forEach>
			
			 <ul class="pagination pagination justify-content-center">
				<li>
					<c:choose>
						<c:when test="${paging.page<=1}"> 
							<li class="page-item"><a class="page-link">Previous</a><li>	
						</c:when>
						<c:otherwise>
							<li class="page-item"><a class="page-link" href="boardDiaryView.do?boardNum=${board.boardNum}&page=${paging.page-1}">Previous</a></li>
						</c:otherwise>
					</c:choose> 
					<c:forEach var="a" begin="${paging.startPage}" end="${paging.endPage}" step="1">
						<c:choose>
							<c:when test="${a==paging.page}"> 
								<li class="page-item active"><a class="page-link" >${a}</a></li>
							</c:when>
							<c:otherwise>
								<li class="page-item"><a class="page-link" href="boardDiaryView.do?boardNum=${board.boardNum}&page=${a}">${a}</a></li>
							</c:otherwise>
						</c:choose>
					</c:forEach> 
						
					<c:choose>
						<c:when test="${paging.page>=paging.maxPage}"> 
							<li class="page-item"><a class="page-link">Next</a><li>
						</c:when>
						<c:otherwise>
							<li class="page-item"><a class="page-link" href="boardDiaryView.do?boardNum=${board.boardNum}&page=${paging.page+1}">[다음]</a></li>
						</c:otherwise>
					</c:choose>
				</li>
			</ul>
 			
		</c:if>
		<c:if test="${empty replyList}">
			<ul>
				<li>등록된 댓글이 없습니다.</li>
			</ul>
		</c:if>
	</div>

	<br><br>
	<div class="container">
<!-- 	<button type="button" class="btn btn-success float-right" onclick="location.href='boardDiaryList.do'">목록</button>&nbsp; <br> <br>	 -->
	<c:if test="${!empty loginUser}">
		<div class="wrap">
	  		<h2>댓글쓰기</h2>
	  		<form action="boardDiaryReplyWriter.do" method="post">
	  			<input type="hidden" id="boardNum" name="boardNum" value="${board.boardNum}">
	  			<input type="hidden" id="boardWriter" name="boardWriter" value="${board.boardWriter}">
	    		<div>
	      			<div>
	      				<%-- 작성자 : <input type="text" class="form-control" id="replyWriter" name="replyWriter" value="${loginUser.userId}" readonly="readonly"> --%>
	      				작성자 : <input type="text" class="form-control" id="replyWriter" name="replyWriter" value="${loginUser.userid}">
	     			</div>
	    		</div>
	     		<div>
	      			<textarea rows="10" cols="155" id="replyContent" name="replyContent"></textarea>
	     		</div>
	     		<div class="mt-3 text-right">	
		    		<button type="submit" class="gradient">등록</button>
		    	</div>
	  		</form>
		</div>
	</c:if>
	<c:if test="${empty loginUser}">
		<h2>댓글쓰기</h2>
		<h4>로그인 후 댓글을 작성할 수 있습니다.</h4>
	</c:if> 
	</div>
	<br><br>
	<%@ include file="../common/footer.jsp"%>
</body>
</html>