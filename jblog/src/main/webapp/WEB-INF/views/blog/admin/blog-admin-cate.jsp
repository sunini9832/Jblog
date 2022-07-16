<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/assets/css/jblog.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/assets/js/jquery/jquery-1.12.4.js"></script>
<script type="text/javascript">
	function addCate(){
		var catename = $('#addCateName').val();
		var description = $('#addDescription').val();
		var authorId = $('#authorId').val();
				
		var params = { "cateName" : catename , "description" : description };
		
	 	$.ajax({
			url : "${pageContext.request.contextPath}/"+authorId+"/addCate",		
			type : "post",
			dataType : "json",
			data : JSON.stringify(params),
			contentType: "application/json; charset=utf-8",
			success : function(data) {
				var str = '';
				$.each(data, function(i){
					str += '<tr>'
					str += '<td>'+ data[i].cateNo +'</td>'
					str += '<td>'+ data[i].cateName +'</td>'
					str += '<td>'+ data[i].count +'</td>'
					str += '<td>'+ data[i].description +'</td>'
					str += '<td><a href="#" class="deleteCate" onclick="return deleteCate(' + data[i].cateNo +','+ data[i].count + ')">'
					str += '<img src="${pageContext.request.contextPath}/resources/assets/images/delete.jpg"></a></td>'
					str += '</tr>'
				});
							
				$("#cateList").html(str);
				
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});
	}
	
	function deleteCate(cateno, count){
		if(count > 0){
			alert('삭제할 수 없습니다.');
		}
		else{
		$.ajax({
			url : "${pageContext.request.contextPath }/removeCate/"+cateno,		
			type : "get",
			dataType : "json",
			contentType: "application/json; charset=utf-8",
			success : function(data) {
				
				var str = '';
				$.each(data, function(i){
					str += '<tr>'
					str += '<td>'+ data[i].cateNo +'</td>'
					str += '<td>'+ data[i].cateName +'</td>'
					str += '<td>'+ data[i].count +'</td>'
					str += '<td>'+ data[i].description +'</td>'
					str += '<td><a href="#" class="deleteCate" onclick="return deleteCate(' + data[i].cateNo +','+ data[i].count + ')">'
					str += '<img src="${pageContext.request.contextPath}/resources/assets/images/delete.jpg"></a></td>'
					str += '</tr>'
				});
				
				$('#cateList').html(str);
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});
	 	
	 	return false;
		}
	}

</script>
</head>
<body>

	<div id="container">
		
		<!-- 블로그 해더 -->
		<div id="header">
			<h1><a href="/jblog/${id}">${blogTitle}</a></h1>
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
			<div id="content" class="full-screen">
				<ul class="admin-menu">
					<li><a href="/jblog/${authUser.id}/admin/basic">기본설정</a></li>
					<li class="selected"><a href="/jblog/${authUser.id}/admin/category">카테고리</a></li>
					<li><a href="/jblog/${authUser.id}/admin/write">글작성</a></li>
				</ul>
				
		      	<table class="admin-cat">
		      		<thead>
			      		<tr>
			      			<th>번호</th>
			      			<th>카테고리명</th>
			      			<th>포스트 수</th>
			      			<th>설명</th>
			      			<th>삭제</th>      			
			      		</tr>
		      		</thead>
		      		<tbody id=cateList>
				
						<c:forEach items="${cateList}" var="catelist" varStatus="status">
		      				<tr>
								<td>${catelist.cateNo}</td>
								<td>${catelist.cateName}</td>
								<td>${catelist.count}</td>
								<td>${catelist.description}</td>
								<td>
									<a href="#" class="deleteCate" onclick="return deleteCate(${catelist.cateNo} , ${catelist.count})">
									<img src='${pageContext.request.contextPath}/resources/assets/images/delete.jpg'>
									</a>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
      	
      			<h4 class="n-c">새로운 카테고리 추가</h4>
      			<form action="/jblog/${authUser.id}/addCate" method="post">
		      		<table id="admin-cat-add" >
		      			<tr>
		      				<td class="t">카테고리명</td>
		      				<td><input type="text" id="addCateName" name="cateName" value=""></td>
		      			</tr>
		      			<tr>
		      				<td class="t">설명</td>
		      				<td><input type="text" id="addDescription" name="description"></td>
		      				<td><input type="hidden" name="authorId" id="authorId" value="${authUser.id}"></td>
		      			</tr>
		      			<tr>
		      				<td class="s">&nbsp;</td>
		      				<td><input id="btnAddCate" type="button" value="카테고리 추가" onclick="return addCate()"></td>
		      			</tr>      		      		
		      		</table> 
		      	</form>
			</div>
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