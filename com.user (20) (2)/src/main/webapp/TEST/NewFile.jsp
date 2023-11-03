<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!--  중복체크 버튼 AJAX <-> 서블릿으로 보내서 결과 가져오기-->
<script type="text/javascript">
	function registerCheckFunction() {
		// userID 변수에 userID의 입력된 값을 가져오게 함
		var userID = $('#userid').val();
		$.ajax({
			type : 'POST', // GET or POST 전송방법 
			url : './UserRegisterCheckServlet', // 이쪽으로 보낸다(호출URL)
			data : {
				userid : userid
			}, // userid 이름에 userid 데이터 값을 넣어서 보낸다
			success : function(result) { // 만약 성공적으로 수행되었다면 result로 값반환
				if (result == 1) { // id가 checkMessage인 것에 아래 텍스트 출력
					$('#checkMessage').html('사용할 수 있는 아이디입니다.');
				} else {
					$('#checkMessage').html('사용할 수 없는 아이디입니다.');
				}
				// id 가 checkModal인 모달함수 실행시켜서 모달 실행시키기 위해
				$('#checkModal').modal("show");
			}
		})
	}
</script>
</head>

<!-- // index.jsp (폼 부분 + 모달 부분) -->
<body>
	<!-- 아이디 입력 폼 부분 -->
	<tbody>
		<tr>
			<td style="width: 120px;"><h5>아이디</h5></td>
			<td><input class="form-control" type="text" id="userID"
				maxlength="20"></td>
			<td style="width: 120px;"><button class="btn btn-primary"
					onclick="registerCheckFunction();" type="button">중복체크</button></td>
		</tr>
	</tbody>

	<!-- 중복체크를 위한 알림메시지 모달 -->
	<div class="modal fade" id="checkModal" tabindex="-1" role="dialog"
		aria-hidden="true">
		<div class="vertical-alignment-helper">
			<div class="modal-dialog vertical-align-center">
				<!--  패널 출력 성공 메시지냐 오류 메시지에 따라 -->
				<div class="modal-content panel-info">
					<div class="modal-header panel-heading">
						<button type="button" class="close" data-dismiss="modal">
							<span aria-hidden="true">&times;</span> <span class="sr-only">Close</span>
						</button>
						<h4 class="modal-title">확인 메시지</h4>
						<div class="modal-body" id="checkMessage"></div>
						<div class="modal-footer">
							<button type="button" class="btn btn-primary"
								data-dismiss="modal">확인</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>


<form action="UserServlet" method="post">
<tr>
<td>
id
</td>
<td>
<input type="text" name = userid class="input_id">
<font id="checkId" size="2"></font>
 </td>
</tr>
</form>

<script type="js/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
$('.input_id').focusout(function(){
	
let userid = $('.input_id').val(); // input_id에 입력되는 값

$.ajax({
	url : "UserServlet",
	type : "post",
	data : {userid : userid},
	success : function(result){
		if(result == 0){
			$("#checkId").html('사용할 수 없는 아이디입니다.');
			$("checkId").attr('color', 'red');
		} else{
			$("#checkId").html('사용할 수 있는 아이디입니다.');
			$("checkId").attr('color', 'green');
		}
	},
	error : function(){
		alert("server error")
	}
})
})

</script>

</form>















</body>
</html>
