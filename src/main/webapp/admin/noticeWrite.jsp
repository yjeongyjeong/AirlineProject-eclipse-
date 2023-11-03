<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="../common/header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
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
<body>
	<div class="tm-page-wrap mx-auto">
		<div class="tm-container-outer">
			<div class="container">
			<!-- row -->
				<div class="row tm-banner-row">
					<h2>공지사항 글쓰기</h2>
					<p>notice page for Kakao airline</p>
				</div>
					<!-- row -->
				<form action="UserServlet?command=noticeWrite_form" method="post" enctype="multipart/form-data"" id ="formData" name="frm">	
					<!-- <input type = "hidden" name="command" value="noticeWrite_form"/> -->
					<div class="row tm-banner-row">	
					<table class="table table-hover">
						<thead>
							<tr>
								<td>제목</td>
								<td name="boardsubject"><input type="text" name="boardsubject" style="border:none;"/></td>
							</tr>
						</thead>
	
						<tbody>
							<tr>
								<td>작성자</td>
								<td><input type="hidden" name="userid" value="${userid}"/>${userid}</td>
							</tr>
							<tr>
								<td>파일첨부</td>
								<td><input type="file" name="fileurl"/></td>
							</tr>
							<tr>	
								<td>내용</td>
								<td><textarea  cols="80px" width="200%" name="boardcontent" placeholder="공지사항 내용을 작성 해 주세요" style="border:none;"></textarea></td>
							</tr>
							<div class="form-group tm-form-group tm-form-group-pad tm-form-group-1">
								<button type="submit" class="btn btn-primary tm-btn-primary tm-btn-send text-uppercase" id ="upload" onclick="return noticeCheck()">글쓰기</button>
							</div>
						</tbody>
					</table>
					</div>
				</form>
			</div>
		</div>
	<%@ include file="../common/footer.jsp"%>
	</div>
	
	<script type="text/javascript">
		function noticeCheck() {
			if(document.frm.boardsubject.value.length==0){
				alert("제목을 작성 해 주십시오");
				document.frm.boardsubject.focus;
				return false;
			}else if(document.frm.boardcontent.value.length==0){
				alert("내용 작성 해 주십시오");
				document.frm.boardcontent.focus;
				return false;
			}
			alert("공지사항을 작성하시겠습니까?");
			return true;
		} 
		
		$("#upload").on("change",function(){
			upload();
		})
		
		function upload(){
			let form = new FormData($("#formDate")[0]); 
			form.append("name","test");
			
			let apiUrl = "UserServlet?command=noticeWrite_form";
			$.ajax({
				url : apiUrl,
				type: "POST",
				data : form,
				dataType : "json",
				processData: false,
				contentType: false,
				enctype : 'multipart/form-data',
				success: function(result) {
					Alert("성공");
				}
					
			})
		}
	</script>
</body>
</html>