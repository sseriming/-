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


<title>LOGIN PAGE</title>
<script
	src="https://code.jquery.com/jquery-3.4.1.js"
	integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
	crossorigin="anonymous"></script>

</head>
<script type="text/javascript">
	$(document).ready(function() {
		
		//회원가입 버튼
		 $("#btnregister").click(function() {
			 location.href="/register.do"
       });
		
		
		 
		//로그인 버튼
		$("#btnLogin").on("click",function() {
			var userId = $('#userId').val();
			var userPw= $('#userPw').val();
			console.log("userId :"+userId+","+"userPw: "+userPw);
			var data = {userId:userId, userPw:userPw}
			if(userId==""){
				alert("아이디를 입력해주세요.");
				$("#userId").focus();
				return false;
			}
			if(userPw==""){
				alert("비밀번호를 입력해주세요.");
				$("#userPw").focus();
				return false;
			}
			else{
				console.log("ajax");
// 				$("login_form").submit();
				$.ajax({
					type:"POST",
					url:"/loginCheck.do",
					data: data,
					success:function(login) {
						console.log(login);
						if(login != 'false') {
							location.href="testList.do"
						}
													
						else {

							alert("잘못된 아이디이거나, 비밀번호가 틀렸습니다.");
							
						}
					}
				})		
			}							
		});
		
		

	})
	
	
	
</script>

<body>
	<h1 class="text-center" style= "margin-top: 50px" >LOGIN</h1>
	<form name="login_form" method="post" >
		<table border="1" width="600px" height="300px" style="margin-left:auto; margin-right: auto; margin-top:100px">
			<tr>
				<td class="text-center">아이디</td>
				<td><input class="id_input" name="userId" id="userId"></td>
			</tr>
			<tr>
				<td class="text-center">비밀번호</td>
				<td><input class="pw_input" type="password" name="userPw" id="userPw"></td>
			</tr>

			<tr>
				<td colspan="2" align="center" >
					<button type="button" id="btnLogin" class="btnLogin" >로그인</button> 
<%-- 					<c:if --%>
<%-- 						test="${msg == 'failure'}"> --%>
<!-- 						<div style="color: red">아이디 또는 비밀번호가 일치하지 않습니다.</div> -->
<%-- 					</c:if> <c:if test="${msg == 'logout'}"> --%>
<!-- 						<div style="color: red">로그아웃 되었습니다.</div> -->
<%-- 					</c:if> --%>
					<button type="button" id="btnregister" class="btnRegister" >회원가입</button>
				</td>
			</tr>
			
		</table>
	</form>
</body>





</html>