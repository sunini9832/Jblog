<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%> 


<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/assets/css/jblog.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/assets/js/jquery/jquery-1.12.4.js"></script>
<script type="text/javascript">
	function getList(cateno){
	 	$.ajax({
			url : "${pageContext.request.contextPath }/post/list/"+cateno,		
			type : "get",
			dataType : "json",
			contentType: "application/json; charset=utf-8",
			success : function(data) {
				var str = '';
				
				$.each(data, function(i){
					str += '<li>'
					str += '<a href="#" class="getPost" onclick="return getPost('+ data[i].postNo +')">' + data[i].postTitle + '</a>'
					str += '<span>' + data[i].postDate +'</span>'
					str += '</li>'
				});
								
				$(".blog-list").html(str);
				
				var str2 = '';
				str2 += '<h4>' + data[0].postTitle + '</h4>'
				str2 += '<p>' + data[0].postContent +'</p>'
								
				$(".blog-content").html(str2);
				
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});
	 	
	 	return false;
	}

	function getPost(postno){
		$.ajax({
			url : "${pageContext.request.contextPath }/post/select/"+postno,		
			type : "get",
			dataType : "json",
			contentType: "application/json; charset=utf-8",
			success : function(data) {
				
				var str = '';
				str += '<h4>' + data.postTitle + '</h4>'
				str += '<p>' + data.postContent +'</p>'
				
				$(".blog-content").html(str);
				
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});
	 	
	 	return false;
	}

</script>
</head>
<body>

	<div id="container">

		<!-- 블로그 해더 -->
		<div id="header">
			<h1><a href="/jblog/${id}"> ${blogTitle}</a></h1>
			<ul>
			
				<c:choose>
					<c:when test="${authUser == null }">
						<!-- 로그인 전 -->
						<li><a href="/jblog/user/login">로그인</a></li>
					</c:when>
					<c:when test="${authUser != null && authUser.id == id}">
						<li><a href="/jblog/user/logout">로그아웃</a></li>
						<li><a href="/jblog/${authUser.id}/admin/basic">내블로그 관리 </a></li>
					</c:when>
					<c:otherwise>
						<li><a href="/jblog/user/logout">로그아웃</a></li>
					</c:otherwise>
				</c:choose>
					
			</ul>
		</div>
		
		<div id="wrapper">
			<div id="content">
				<div class="blog-content">
					<h4>${post.postTitle}</h4>
					<p>${post.postContent}</p>
					<br/><br/>
					</div>		
					<table id="cmt-area">
						<tr align="center">
						
					</table>
					
				<ul class="blog-list">					
					<c:forEach items="${postList}" var="postlist" varStatus="status">
						<li>
							<a href="#" class="getPost" onclick="return getPost(${postlist.postNo})">${postlist.postTitle}</a> 
							<span>${postlist.postDate}</span>
						</li>
					</c:forEach>
				</ul>
			</div>
		</div>

		<div id="extra">
			<div class="blog-logo">
				<c:choose>
				<c:when test="${logoFile == 'default'|| logoFile == null}">
					<img src="${pageContext.request.contextPath}/resources/assets/images/spring-logo.jpg">
				</c:when>				
				<c:otherwise>
					<img src="${pageContext.request.contextPath}/resources/assets/images/${logoFile}">
				</c:otherwise>
				</c:choose>				
			</div>
		</div>
		
		<div id="navigation">
			<h2>카테고리</h2>
			<ul>
				<c:forEach items="${cateList}" var="catelist" varStatus="status">
					<li>
						<a href="#" class="getList" onclick="return getList(${catelist.cateNo})">${catelist.cateName}</a> 
					</li> 
				</c:forEach>
			</ul>
		</div>
		
		<!-- 푸터-->
		<div id="footer">
			<p>
				<strong>Spring 이야기</strong> is powered by JBlog (c)2018
			</p>
		</div>
		
	</div>
</body>
</html>