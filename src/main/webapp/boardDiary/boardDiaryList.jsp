<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ include file="../common/header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
<script	src="https://cdn.jsdelivr.net/npm/jquery@3.6.4/dist/jquery.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
<script type="text/javascript" src="script/boardDiary.js"></script>
<title>Insert title here</title>

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
	#th1 {
	background-color: #FFC107;
	border-top: 3px solid #727272;
	}
	
	td, th{
		text-align: center;
	}
	
	
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

	.hr1 {
	border: 0;
	height: 2px;
	background: #d3d3d3;
	}

</style>
</head>
<body style="background-color: white;">
	<div style="max-width: 1400px; margin: 0 auto; ">
	<h2>여행일기</h2>
	<hr class="hr1" noshade>
	<div class="mt-3 text-right">
		조회옵션
		<select onchange="if(this.value) location.href=this.value;">
			<option value="">선택하세요
			<option value="boardDiaryList.do?page=${paging.page}&keyword=${keyword}&searchType=${searchType}&viewLimit=10&sort=${sort}" <c:if test="${viewLimit==10}">selected="selected"</c:if>>10개씩
			<option value="boardDiaryList.do?page=${paging.page}&keyword=${keyword}&searchType=${searchType}&viewLimit=20&sort=${sort}" <c:if test="${viewLimit==20}">selected="selected"</c:if>>20개씩
			<option value="boardDiaryList.do?page=${paging.page}&keyword=${keyword}&searchType=${searchType}&viewLimit=40&sort=${sort}" <c:if test="${viewLimit==40}">selected="selected"</c:if>>40개씩
		</select>
		
		정렬
		<select onchange="if(this.value) location.href=this.value;">
			<option value="">선택하세요
			<option value="boardDiaryList.do?page=${paging.page}&keyword=${keyword}&searchType=${searchType}&viewLimit=${viewLimit}&sort=latest" <c:if test="${sort=='latest'}">selected="selected"</c:if>>최신순
			<option value="boardDiaryList.do?page=${paging.page}&keyword=${keyword}&searchType=${searchType}&viewLimit=${viewLimit}&sort=oldest" <c:if test="${sort=='oldest'}">selected="selected"</c:if>>과거순
			<option value="boardDiaryList.do?page=${paging.page}&keyword=${keyword}&searchType=${searchType}&viewLimit=${viewLimit}&sort=viewcount" <c:if test="${sort=='viewcount'}">selected="selected"</c:if>>조회순
			<option value="boardDiaryList.do?page=${paging.page}&keyword=${keyword}&searchType=${searchType}&viewLimit=${viewLimit}&sort=replycount" <c:if test="${sort=='replycount'}">selected="selected"</c:if>>댓글순
			<option value="boardDiaryList.do?page=${paging.page}&keyword=${keyword}&searchType=${searchType}&viewLimit=${viewLimit}&sort=likecount" <c:if test="${sort=='likecount'}">selected="selected"</c:if>>추천순
		</select>
	</div>
	<br>
	<div>
		<form action="boardDiaryList.do" method="get" id="searchForm" name="searchForm">
		<span style="text-align: left; font-size: 15px">▷ 총 ${boardCount}개의 게시물이 있습니다. </span>
		<span style="float: right;">
        <select name="searchType" class="type-box">
			<option value="boardTitle" <c:if test="${searchType =='boardTitle'}">selected="selected"</c:if> >제목</option>
			<option value="boardContent" <c:if test="${searchType =='boardContent'}">selected="selected"</c:if> >내용</option>
			<option value="boardWriter" <c:if test="${searchType =='boardWriter'}">selected="selected"</c:if> >작성자</option>
		</select>
          <input class="inputId" type="text" id="keyword" name="keyword" placeholder="검색어 입력" value="${keyword}">
          <input class="gradient" type="submit" value="검색하기" onclick="return search()">
          <input type="button" class="gradient" value="내글보기" onclick="location.href='boardDiaryList.do?searchType=boardWriter&keyword=${loginUser.userid}'">
          <input type="button" class="gradient" onclick="location.href='boardDiaryList.do'" value="전체">&nbsp; <br> <br>
        </span>
		</form>
	</div>
	<br>
	<input type="hidden" id="userId" name="userId" value="${loginUser.userid}" readonly="readonly">

		<table class="table table-hover" style="width: 120%;">
		 <c:if test="${boardCount>0}">

			<tr>
				<th id="th1">번호</th>
				<th id="th1">제목</th>
				<th id="th1">작성자</th>
				<th id="th1">날짜</th>
				<th id="th1">조회수</th>
				<th id="th1">추천수</th>
			</tr>

		
			<c:forEach var="board" items="${diaryList}">

				<tr>
					<td>${board.boardNum}</td>
					<td><a href="boardDiaryView.do?boardNum=${board.boardNum}">${board.boardTitle} [${board.replycount}]</a></td>
					<td>${board.boardWriter}</td>
					<c:choose>
						<c:when test="${board.regiDate>=board.modifyDate}">
							<td>${board.regiDate}</td>						
						</c:when>
						<c:when test="${board.regiDate<board.modifyDate}">
							<td>${board.modifyDate}</td>
						</c:when>
					</c:choose>
					<td>${board.readcount}</td>
					<td>${board.likecount}</td>
				</tr>
			</c:forEach>
		</c:if>	
			<c:if test="${empty diaryList}">
			<tr>
				<td colspan="6">등록된 글이 없습니다.</td>
			</tr>
			</c:if>
	
		 </table> 
		 <c:if test="${!empty loginUser.admin}">
			<div style="float: right;">
				<input type="button" class="gradient" onclick="location.href='boardDiaryWrite.do'" value="글쓰기">
			</div>
			</c:if>
			<c:if test="${empty loginUser.admin}">
				<p style="color: red; float:right;">*로그인 후 글 작성이 가능합니다.</p>
			</c:if>	 
		 <div class="container">
			<ul class="pagination pagination justify-content-center">
				<!-- <td colspan="5"> -->
					<c:choose>
						<c:when test="${paging.page<=1}"> 
							<li class="page-item"><a class="page-link">Previous</a><li>
						</c:when>
						<c:otherwise>
							<c:if test="${!empty keyword}">
								<li class="page-item"><a class="page-link" href="boardDiaryList.do?page=${paging.page-1}&keyword=${keyword}&searchType=${searchType}&viewLimit=${viewLimit}&sort=${sort}">Previous</a>&nbsp;<li>
							</c:if>
							<c:if test="${empty keyword}">
								<li class="page-item"><a class="page-link" href="boardDiaryList.do?page=${paging.page-1}&viewLimit=${viewLimit}&sort=${sort}">Previous</a>&nbsp;<li>
							</c:if>
						</c:otherwise>
					</c:choose> 
					<c:forEach var="a" begin="${paging.startPage}" end="${paging.endPage}" step="1">
						<c:choose>
							<c:when test="${a==paging.page}"> 
								<li class="page-item active"><a class="page-link" >${a}</a></li>
							</c:when>
							<c:otherwise>
								<c:if test="${!empty keyword}">
									<li class="page-item"><a class="page-link"  href="boardDiaryList.do?page=${a}&keyword=${keyword}&searchType=${searchType}&viewLimit=${viewLimit}&sort=${sort}">${a}</a>&nbsp;<li>
								</c:if>
								<c:if test="${empty keyword}">
									<li class="page-item"><a class="page-link"  href="boardDiaryList.do?page=${a}&viewLimit=${viewLimit}&sort=${sort}">${a}</a>&nbsp;</li>
								</c:if>
							</c:otherwise>
						</c:choose>
					</c:forEach> 
						
					<c:choose>
						<c:when test="${paging.page>=paging.maxPage}"> 
							<li class="page-item"><a class="page-link">Next</a><li>
						</c:when>
						<c:otherwise>
							<c:if test="${!empty keyword}">
								<li class="page-item"><a class="page-link" href="boardDiaryList.do?page=${paging.page+1}&keyword=${keyword}&searchType=${searchType}&viewLimit=${viewLimit}&sort=${sort}">Next</a></li>
							</c:if>					
							<c:if test="${empty keyword}">
								<li class="page-item"><a class="page-link" href="boardDiaryList.do?page=${paging.page+1}&viewLimit=${viewLimit}&sort=${sort}">Next</a></li>
							</c:if>
						</c:otherwise>
					</c:choose>
				<!-- </td> -->
			</ul>
			
		 </div>
			
				
			
	</div>
		<br>
	
	<%@ include file="../common/footer.jsp"%>
</body>
</html>