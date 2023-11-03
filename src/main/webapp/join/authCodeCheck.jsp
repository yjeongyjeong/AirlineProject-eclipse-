<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>

<%
String result = "false";
String authCode = (String) request.getSession().getAttribute("AuthenticationKey");
String inputedCode = request.getParameter("inputedCode");
System.out.println("마지막까지 넘어온 값(authCode) : " + authCode);
System.out.println("마지막까지 넘어온 값(inputedCode) : " + inputedCode);

if (authCode.equals(inputedCode)) {
	result = "true";
	System.out.println("값의 비교(result) : " + result);
}
%>


<script type="text/javascript">
	var result = "<%= result %>";
	var inputedCode = "<%=inputedCode%>";
	
	
	function authCodeCheck(){
		if (inputedCode == ""){
			alert("인증번호가 올바르지 않습니다.");
			window.close();
		}
		if(<%=result%> == true){
			alert("인증이 완료되었습니다.");
			opener.document.signUpForm.inputEmailForm.readOnly = true;
			opener.document.signUpForm.eamilAuthBtn.readOnly = true;
			opener.document.signUpForm.authCodeCheckBtn.disabled = false;
			window.close();
		} else {
			alert("인증번호가 올바르지 않습니다.");
			window.close();
		}
	}
</script>
<body>
<script type="text/javascript">authCodeCheck();</script>
<% 
session.removeAttribute("AuthenticationKey") ; 
//세션만료
%>
</body>
</html>