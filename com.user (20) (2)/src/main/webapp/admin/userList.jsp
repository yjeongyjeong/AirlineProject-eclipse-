<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="../common/header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
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
<title>Insert title here</title>
</head>
<body>
	<div class="tm-page-wrap mx-auto">
		<div class="tm-container">
			<div class="container mx-auto text-center">
				<h2>회원목록</h2>
				<p>일반회원 및 관리자 모두 포함</p>
				<div class="row tm-banner" id="tm-section-search">

					<form action="UserServlet" method="post"
						class="tm-search-form tm-section-pad-1 row mx-auto text-left">
						<input type="hidden" name="command" value="user_list" />


						<div
							class="form-group tm-form-group tm-form-group-pad tm-form-group-3">
							<label for="type">검색조건</label> <select name="type"
								class="form-control tm-select" id="type">
								<option value="1" selected>등급</option>
								<option value="2">아이디</option>
								<option value="3">이름</option>
							</select>
						</div>
						<div
							class="form-group tm-form-group tm-form-group-pad tm-form-group-1">
							<label for="keyword">검색할 내용을 작성해 주세요(단어)</label>
							<c:set var="keyword" value="${keyword}"/>
								<c:if test="${keyword != null}">
									<input name="keyword" type="text" class="form-control"
										id="keyword" value="${keyword}">
								</c:if>
								<c:if test="${keyword == null}">
									<input name="keyword" type="text" class="form-control"
										id="keyword" placeholder="현재 아이디만 가능">
								</c:if>
						</div>

						<div
							class="form-group tm-form-group tm-form-group-pad tm-form-group-3">
							<label for="btnSubmit">&nbsp;</label>
							<button type="submit"
								class="btn btn-primary tm-btn tm-btn-search text-uppercase"
								id="btnSubmit">검색</button>
						</div>
					</form>
				</div>

				<div class="form-row tm-search-form-row">
					<label for="inputCity"></label>
				</div>

				<table class="table table-hover">
					<thead>
						<tr>
							<th>아이디</th>
							<th>등급</th>
							<th>이름(한국)</th>
							<th>이름(영어)</th>
							<th>주민번호</th>
							<th>성별</th>
							<th>메일주소</th>
							<th>전화번호</th>
							<th>우편번호</th>
							<th>주소</th>
							<th>관리자</th>
							<th>삭제</th>
						</tr>
					</thead>
					<c:forEach items="${pagelist}" var="list">
						<tbody>
							<tr>
								<td hidden="hidden"><input type="hidden"
									name="currList${status.index}" value="${list.num}" /></td>
								<td><input type="hidden" name="userid"
									value="{list.userid}" />${list.userid}</td>
								<td>${list.usergrade}</td>
								<td>${list.korName}</td>
								<td>${list.engName}</td>
								<td>${list.usernum}</td>
								<c:choose>
									<c:when test="${list.gender==1}">
										<td>남자</td>
									</c:when>
									<c:otherwise>
										<td>여자</td>
									</c:otherwise>
								</c:choose>
								<td>${list.mail}</td>
								<td>${list.phone}</td>
								<td>${list.postcode}</td>
								<td>${list.address}</td>
								<c:choose>
									<c:when test="${list.admin==0}">
										<td>회원</td>
									</c:when>
									<c:otherwise>
										<td>관리자</td>
									</c:otherwise>
								</c:choose>
								<td><button type="button" class="btn btn-primary"
										onclick="location.href='UserServlet?command=userDelete&userid=${list.userid}'">삭제</button></td>
							</tr>
						</tbody>
					</c:forEach>
				</table>

				<div class="container">
					<ul class="pagination pagination justify-content-center">
						<c:choose>
							<c:when test="${prevPage eq -9}">
								<li class="page-item"><a class="page-link"
									onclick="return previous()">Previous</a></li>
							</c:when>
							<c:otherwise>
								<li class="page-item"><a class="page-link"
									onclick="location.href='UserServlet?command=user_list&curPage=${prevPage}'">Previous</a></li>
							</c:otherwise>
						</c:choose>
						<c:forEach items="${page}" var="page">
							<li class="page-item"><a class="page-link"
								href="UserServlet?command=user_list&curPage=${page}"
								name="curPage">${page}</a></li>
						</c:forEach>
						<c:choose>
							<c:when test="${nextPage gt lastPage}">
								<li class="page-item"><a class="page-link"
									onclick="return next()">Next</a></li>
							</c:when>
							<c:otherwise>
								<li class="page-item"><a class="page-link"
									onclick="location.href='UserServlet?command=user_list&curPage=${nextPage}'">Next</a></li>
							</c:otherwise>
						</c:choose>
					</ul>
				</div>
			</div>
		</div>
		<%@ include file="../common/footer.jsp"%>
	</div>

	<script type="text/javascript">
		function previous() {
			alert("첫번째 블록 페이지 입니다");
		}

		function next() {
			alert("마지막 블록 페이지 입니다");
		}
	</script>
</body>
</html>