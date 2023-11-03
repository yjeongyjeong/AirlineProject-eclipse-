<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@include file="../common/header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
<script	src="https://cdn.jsdelivr.net/npm/jquery@3.6.4/dist/jquery.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
<script type="text/javascript" src="script/boardDiary.js"></script>
<style>
	ul {
    display:grid;
    list-style-type:none;
    margin:0;padding:0;
    grid-template-columns: repeat(4, auto);
    grid-template-rows: repeat(2, auto);
	}
	th {
	background-color: #FFC107;
	border-top: 3px solid #727272;
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
<body style="background-color: white;">
	<div style="max-width: 1300px; margin: 0 auto; ">	
	<h2>이벤트 게시판</h2>
	<hr class="hr1" noshade>
	<div class="mt-3 text-right">
		<input type="button" class="gradient" onclick="location.href='boardEventList.do'" style="width: 120px" value="리스트형으로 보기">
	</div>
	<div>
	<form action="boardEventList.do" method="get" id="searchForm" name="searchForm">
		<input type="hidden" name="Grid" value="Grid">
		<span style="text-align: left;">▷ 총 ${boardCount}개의 게시물이 있습니다. </span>
		<span style="float: right;">
        <select name="searchType" class="type-box">
			<option value="boardTitle" <c:if test="${searchType =='boardTitle'}">selected="selected"</c:if> >제목</option>
			<option value="boardContent" <c:if test="${searchType =='boardContent'}">selected="selected"</c:if> >내용</option>
		</select>
          <input class="inputId" type="text" id="keyword" name="keyword" placeholder="검색어 입력" value="${keyword}">
          <input class="gradient" type="submit" value="검색" onclick="return search()">
          <input type="button" class="gradient" onclick="location.href='boardEventListGrid.do'" value="전체">&nbsp; 
         </span>
		</form>
	</div>
	<br>
		<ul class="thumbTypeGrid">
			<c:forEach var="board" items="${EventList}">			
				<div class="img">
					<a href="boardEventView.do?boardNum=${board.boardNum}">
						<input type="hidden" id="boardNum" name="boardNum" value="${board.boardNum}" readonly="readonly">	
						<img src="upload/${board.repImg}" width="200" height="300">	<br>
					</a>
					<p style="width: 200px"><a href="boardEventView.do?boardNum=${board.boardNum}">
					${board.boardTitle}</a><br>
					${board.startDate} ~ <br>&nbsp;&nbsp;&nbsp;${board.endDate}</p>
				</div>
			</c:forEach>
		</ul>
	</div>
	<div class="container">		
		<table class="table table-hover">
			<c:if test="${empty EventList}">
			<tr>
				<td colspan="5">등록된 글이 없습니다.</td>
			</tr>
			</c:if>
	
			<tr>
				<!-- <td colspan="2"><a href="./boardDiaryWrite.do">[글쓰기]</a></td> -->
				<%-- <td colspan="5" align="right">
					현재 페이지: ${paging.page}
					게시글의 개수: ${EventList.size()}/${boardCount}</td> --%>
				<c:if test="${empty EventList}">
				<tr>
					<td colspan="5">등록된 글이 없습니다.</td>
				</tr>
		</c:if>
			</tr>
		</table>
		 <c:if test="${boardCount>0}">
		
			<ul class="pagination pagination justify-content-center">
					<c:choose>
						<c:when test="${paging.page<=1}"> 
							<li class="page-item"><a class="page-link">Previous</a><li>
						</c:when>
						<c:otherwise>
							<c:if test="${!empty keyword}">
								<li class="page-item"><a class="page-link" href="boardEventListGrid.do?page=${paging.page-1}&keyword=${keyword}&searchType=${searchType}">Previous</a></li>
							</c:if>
							<c:if test="${empty keyword}">
								<li class="page-item"><a class="page-link" href="boardEventListGrid.do?page=${paging.page-1}">Previous</a></li>
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
									<li class="page-item active"><a class="page-link"  href="boardEventListGrid.do?page=${a}&keyword=${keyword}&searchType=${searchType}">${a}</a></li>
								</c:if>
								<c:if test="${empty keyword}">
									<li class="page-item active"><a class="page-link"  href="boardEventListGrid.do?page=${a}">${a}</a></li>
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
								<li class="page-item"><a class="page-link" href="boardEventListGrid.do?page=${paging.page+1}&keyword=${keyword}&searchType=${searchType}">Next</a></li>
							</c:if>					
							<c:if test="${empty keyword}">
								<li class="page-item"><a class="page-link" href="boardEventListGrid.do?page=${paging.page+1}">Next</a></li>
							</c:if>
						</c:otherwise>
					</c:choose>
			</ul>
			
		</c:if>
		

	
	</div>
	<div class="container">		   
		
	<div class="mt-3 text-right">
		<c:if test="${loginUser.admin==1}">
			<input type="button" class="gradient" onclick="location.href='boardEventWrite.do'" value="글쓰기">
		</c:if> 
	</div>
	<br><br>
	</div>
	
	<%@ include file="../common/footer.jsp"%>
</body>
</html>