<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${detail.board_num }번 글 수정</title>
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
</head>
<body>
	<form action="/testBoard/boardUpdate" id="updateForm" method="post">
	<input type="hidden" name="board_num" value="${detail.board_num }"/>
	<div>
		<input id="title" name="board_title" type="text" value="${detail.board_title }"/><br>
		<input id="content" name="board_content" type="text" value="${detail.board_content}"/><br>
	</div>
	<a href="/testBoard/boardUpdate?board_num=${detail.board_num}"><button>수정완료</button></a>
	</form>
</body>
</html>