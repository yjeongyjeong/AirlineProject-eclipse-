<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!--<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>-->
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
<script type="text/javascript" src="script/boardEvent.js"></script>

<script type="text/javascript" src="https://cdn.jsdelivr.net/jquery/latest/jquery.min.js"></script>
<script type="text/javascript" src="https://cdn.jsdelivr.net/momentjs/latest/moment.min.js"></script> 
<script type="text/javascript" src="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.min.js"></script> 
<link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.css" />
<script>
	var $jLatest = jQuery.noConflict();

	function setDetailImage(event){
		for(var image of event.target.files){
			var reader = new FileReader();
			
			reader.onload = function(event){
				var img = document.createElement("img");
				img.setAttribute("src", event.target.result);
				img.setAttribute("class", "col-lg-6");
				document.querySelector("div#images_container").appendChild(img);
			};
			
			console.log(image);
			reader.readAsDataURL(image);
		}
	}
</script>

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
	.images_container{
		width: 300px;
		height: 200px;
	}
	.img{
		width: 300px;
		height: 200px;
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

</style>

</head>
<body style="background-color: white;">
	
	 <div class="container">
		<h2>이벤트 등록</h2>
		<form action="boardEventWrite.do" method="post" name="frm" enctype="multipart/form-data">
			<input type="hidden" id="boardNum" name="boardNum" value="${boardNum}"> 
			<div class="form-group">
				<label for="boardTitle">제목</label> 
				<input type="text" class="form-control" id="boardTitle" name="boardTitle">
			</div>

			<!-- <input type="text" id=dates name="dates" value="" /> -->
			<div class="form-group">
				<label for="boardTitle">시작일</label> 
				<input type="text" class="form-control" id="startDate" name="startDate">
			</div>
			<div class="form-group">
				<label for="boardTitle">종료일</label> 
				<input type="text" class="form-control" id="endDate" name="endDate">
			</div>
			
			주의)파일은 5개까지 업로드 가능하며, 이미지 파일 형식만 사용할 수 있습니다(jpg, jpeg, png, bmp).<br>
				5MB 이하의 파일만 업로드 하실 수 있습니다.<br>
				첫번째 파일은 썸네일에 표시됩니다.
			<div class="form-group">
				1. 파일 지정하기 : <input type="file" multiple="multiple"
					name="uploadFile01" id="uploadFile01" onchange="setDetailImage(event);" accept=".png, .jpeg, .jpg, .bmp"> <br>
				2. 파일 지정하기 : <input type="file" multiple="multiple"
					name="uploadFile02" id="uploadFile02" onchange="setDetailImage(event);" accept=".png, .jpeg, .jpg, .bmp"> <br>
				3. 파일 지정하기 : <input type="file" multiple="multiple"
					name="uploadFile03" onchange="setDetailImage(event);" accept=".png, .jpeg, .jpg, .bmp"> <br>
				4. 파일 지정하기 : <input type="file" multiple="multiple"
					name="uploadFile04" id="uploadFile05" onchange="setDetailImage(event);" accept=".png, .jpeg, .jpg, .bmp"> <br>
				5. 파일 지정하기 : <input type="file" multiple="multiple"
					name="uploadFile04" id="uploadFile05" onchange="setDetailImage(event);" accept=".png, .jpeg, .jpg, .bmp"> <br>
			</div>
			이미지 미리보기
			<div id="images_container" style="heigt:200px;"></div>
			
			<div class="form-group">
				<label for="boardContent">내용</label><br> 
				<textarea id="summernote" class="summernote" name="boardContent"></textarea>
			</div>
			
			<br>
			<div class="mt-3 text-right">
				<button type="button" class="gradient" onclick="location.href='boardEventList.do'" style="width: 100px">리스트목록</button>
				<button type="button" class="gradient" onclick="location.href='boardEventListGrid.do'" style="width: 100px">그리드목록</button>
				<button type="reset" class="gradient">다시작성</button> &nbsp;
				<button type="submit" class="gradient" onclick="return boardCheck()" id="write">등록</button> &nbsp;
			</div>
			<br>
			
			<div style="position: fixed; bottom: 5px; right: 5px;">
				<a href="#">
				<img src="img/upArrow.png" width="100px" height="100px" title="위로">
				</a><br>
				<a href="#write">
				<img src="img/downArrow.png" width="100px" height="100px" title="아래로">
				</a>
			</div>
			
		</form>
	
	</div> 

 	<script>
 		$('#summernote').summernote({
 			disableDragAndDrop: true,
 			height : 500,
			/* width : 1200, */
			lang : "ko-KR",
 			toolbar: [
 			    // [groupName, [list of button]]
 			    ['style', ['bold', 'italic', 'underline', 'clear']],
 			    ['fontname', ['fontname']],
 			    ['fontsize', ['fontsize']],
 			    ['color', ['color']],
 			    ['para', ['ul', 'ol', 'paragraph']],
 			    ['height', ['height']],
 			    ['table', ['table']],
 	       		['view', ['fullscreen', 'codeview', 'help']]
 			  ]
 			});
	</script> 
	
	<script>
		$jLatest('input[id="dates"]').daterangepicker();
		$jLatest('input[id="startDate"]').daterangepicker({
			singleDatePicker: true,
		    timePicker: true,
		    timePicker24Hour: true,
			 "locale": {
			       "format": 'YYYY-MM-DD HH:mm:SS',
			       "separator": " ~ ",
			       "applyLabel": "확인",
			        "cancelLabel": "취소",
			        "fromLabel": "From",
			        "toLabel": "To",
			        "customRangeLabel": "Custom",
			        "weekLabel": "주",
			        "daysOfWeek": [
			             "일",
			             "월",
			             "화",
			             "수",
			             "목",
			             "금",
			             "토"
			       ],
			      "monthNames": [
			             "1월",
			             "2월",
			             "3월",
			             "4월",
			             "5월",
			             "6월",
			             "7월",
			             "8월",
			             "9월",
			             "10월",
			             "11월",
			             "12월"
			        ],
			        "firstDay": 1
			    },
		});
		$jLatest('input[id="endDate"]').daterangepicker({
			singleDatePicker: true,
		    timePicker: true,
		    timePicker24Hour: true,
			 "locale": {
			       "format": 'YYYY-MM-DD HH:mm:SS',
			       "separator": " ~ ",
			       "applyLabel": "확인",
			        "cancelLabel": "취소",
			        "fromLabel": "From",
			        "toLabel": "To",
			        "customRangeLabel": "Custom",
			        "weekLabel": "주",
			        "daysOfWeek": [
			             "일",
			             "월",
			             "화",
			             "수",
			             "목",
			             "금",
			             "토"
			       ],
			      "monthNames": [
			             "1월",
			             "2월",
			             "3월",
			             "4월",
			             "5월",
			             "6월",
			             "7월",
			             "8월",
			             "9월",
			             "10월",
			             "11월",
			             "12월"
			        ],
			        "firstDay": 1
			    },
		});
	</script>
	<%@ include file="../common/footer.jsp"%>
</body>
</html>