<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<!-- 합쳐지고 최소화된 최신 CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<!-- 부가적인 테마 -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">

<script
	src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<title>LOGIN PAGE</title>
</head>
<script type="text/javascript">
	$(document).ready(function() {
		
		//회원가입 버튼
		 $("#btnregister").click(function() {
			 location.href="/register.do"

       });
		
		 
		//로그인 버튼
		$("#btnLogin").on("click",function() {
			if($("#userId").val()==""){
				alert("아이디를 입력해주세요.");
				$("#userId").focus();
				return false;
			}
			if($("#userPw").val()==""){
				alert("비밀번호를 입력해주세요.");
				$("#userPw").focus();
				return false;
			}
			
		});
		
		
		
		$(document).on('click','btnLogin',function(e) {
			document.form1.action = "${form1}/loginCheck.do"
			//제출
			document.form1.submit();
			location.href="/testList.do"
		});
			
			
	})
	
	

	
</script>

<body>
	<h2>로그인</h2>
	<form name="form1" method="post">
		<table border="1" width="400px">
			<tr>
				<td>아이디</td>
				<td><input name="userId" id="userId"></td>
			</tr>
			<tr>
				<td>비밀번호</td>
				<td><input type="password" name="userPw" id="userPw"></td>
			</tr>

			<tr>
				<td colspan="2" align="center">
					<button type="button" id="btnLogin" class="btnLogin">로그인</button> <c:if
						test="${msg == 'failure'}">
						<div style="color: red">아이디 또는 비밀번호가 일치하지 않습니다.</div>
					</c:if> <c:if test="${msg == 'logout'}">
						<div style="color: red">로그아웃 되었습니다.</div>
					</c:if>
					<button type="button" id="btnregister" class="btnRegister">회원가입</button>
				</td>
		</table>
	</form>
</body>





</html>