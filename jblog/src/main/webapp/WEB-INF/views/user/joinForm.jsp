<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/assets/css/jblog.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/assets/js/jquery/jquery-1.12.4.js"></script>
<script src="${pageContext.servletContext.contextPath }/resources/assets/js/jquery/jquery-1.9.0.js"></script>
<script>
	var count = 0;
	$(function(){
		$(".join_button").click(function(){
			if($("#userName").val()==""){
				alert("이름을 입력해주세요");
				$("#userName").focus();
				return false;
			}
			else if($("#id").val()==""){
				alert("아이디를 입력해주세요");
				$("#id").focus();
				return false;
			}
			else if(count == 0){
				alert("아이디 중복체크를 해주세요");
				return false;
			}
			
			else if($("#passWord").val()==""){
				alert("패스워드를 입력해주세요");
				$("#passWord").focus();
				return false;
			}
			else if($("#agree-prov").prop("checked") == false){
				alert("약관에 동의해 주세요");
				return false;
			}else{
				$(".join-form").submit();
			}
		
			
		})
		
		$('#btn-checkid').click(function(){
			count++;
			var id = $('#id').val();
			if(id == ''){
				alert("아이디를 입력해주세요");
				return false;
			}
			// ajax 통신
			$.ajax({
				url : "/jblog/user/idcheck?id="+id, //문자열로 인식이 되는게 아니라 서버에서 el값으로 먼저 치환후 js통신을 한다.
				type : "post",
				dataType : "json",
				data :id, //post방식일때 값을 여기에 넣어줌
				success : function(isExist) {
			           console.log(isExist);
			           if(isExist == false){
			             $("#checkid-msg").text("사용할 수 있는 아이디 입니다.")
			             $("#checkid-msg").css("color", "green")
			           }else {
			             $("#checkid-msg").text("다른 아이디로 가입해 주세요.")
			             $("#checkid-msg").css("color", "red")
			             $('#id').val('');
			             count = 0;
			           }
			         },
			         error : function(XHR, status, error) {
			           console.error(status + " : " + error);
			         }
			});
		})

	});
</script>

</head>
<body>
	<div class="center-content">
		
		<!-- 메인해더 -->
	 	<a href="/jblog">
			<img class="logo" src="${pageContext.request.contextPath}/resources/assets/images/logo.jpg">
		</a>
		<ul class="menu">
				<!-- 로그인 전 메뉴 -->
				<li><a href="/jblog/user/login">로그인</a></li>
				<li><a href="/jblog/user/join">회원가입</a></li>

				<!-- 로그인 후 메뉴 -->
				<!-- 
				<li><a href="">로그아웃</a></li>
				<li><a href="">내블로그</a></li> 
				-->
 		</ul>
		
		<form class="join-form" id="join-form" method="post" action="/jblog/user/join">
			<label class="block-label" for="name">이름</label>
			<input type="text" name="userName" id="userName" value="" />
			
			<label class="block-label" for="id">아이디</label>
			<input type="text" name="id" id="id" value="" />
			
			<input id="btn-checkid" type="button" value="id 중복체크">
			<p id="checkid-msg" class="form-error">
			&nbsp;
			</p>
			
			<label class="block-label" for="password">패스워드</label>
			<input type="password" name="passWord" id="passWord" value="" />

			<fieldset>
				<legend>약관동의</legend>
				<input id="agree-prov" type="checkbox" name="agreeProv" value="y">
				<label class="l-float">서비스 약관에 동의합니다.</label>
			</fieldset>
			<input type="button" class="join_button" value="가입하기">
			
		</form>
	</div>

</body>


</html>