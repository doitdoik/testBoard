<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 작성 페이지</title>
<style>
	#title{
		width: 500px;
		height: 50px;
	}
	#content{
		 resize: none;
		 width: 500px;
		 height: 500px;
	}
	td{
	border: 1px solid black;
	}
	
</style>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
</head>
<body>
	<form action="/testBoard/boardWrite" id="writeForm" method="post" enctype="multipart/form-data">
	<div>
		<input id="title" name="board_title" type="text" placeholder="제목을 입력하세요"/><br>
		<textarea id="content" name="board_content" placeholder="내용을 입력하세요"></textarea><br>
		<!-- <input type="file" name="multipartFile" id="board_file" />  -->
		<input multiple="multiple" type="file" name="file" id="board_file" />


	</div>
	<button class="btn btn-write">작성</button>
	<button type="button" onclick="location.href='boardList'" class="btn btn-detail">취소</button>
	</form>
</body>
</html>