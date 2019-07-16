<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page session="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<script	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<title>리스트 페이지</title>
<style>
#board {
	margin: 0 auto;
	text-align: center;
	width: 800px;
}

td {
	border: 1px solid black;
}

ul {
	list-style-type: none;
	margin: 0;
	padding: 0;
}

li {
	display: inline;
}

#searchDiv {
	margin: 0 auto;
	text-align: center;
}

.pagination {
	margin: 0 auto;
	text-align: center;
}

#attachment{
	width: 12px;
	height: 12px;
}
</style>
<c:url var="boardListURL" value="/testBoard/boardList"></c:url>

<script>  
$(function(){
	  $("#deleteBtn").click(function() {
		
			var chk_arr =  document.getElementsByName("board_num");
		
			for (var i = 0; i < chk_arr.length; i++) {
				if(chk_arr[i].checked){
				  console.log(i+" value = "+chk_arr[i].value);
				  var params ={
				          board_num:chk_arr[i].value,
				          _method:'delete'
				    }
				    $.ajax({
				       url:'boardDelete',
				       method:'post',
				       data:params,
				       success:function(){
				          self.location.replace('boardList')
				       },
				    error : function() {
						alert("실패");
					}
				    });
			  }			
			
			}
		});
	  
	  $("#serchButton").click(function() {
			$.ajax({
				url : "boardList",
				method : "post",
				data : $("#serchFrm").serialize(),
				success : function(data) {
					$("#searchDiv").html(data);
				},
				error : function() {
					alert("실패");
				}
			});
		});
	  
});	

function search() {
 		
 	function list(pageNo) {
 			$("#pageNo").val(pageNo);
 			$("#serchBtn").click();
 		}
	}

 	  function checkAll(){
 	        if( $("#checkAll").is(':checked') ){
 	          $("input[name=board_num]").prop("checked", true);
 	        }else{
 	          $("input[name=board_num]").prop("checked", false);
 	        }
 	  }
 $(function(){
 		
 		   $('#download').on("click", function(e){
 			   var file_num = "${detail.fileCommand.file_name }";
 			   var org_file_num = "${detail.fileCommand.file_org_name }";
 		
 		   })
 });
</script>
</head>
<body>
	
	<table id="board">
		<colgroup>
			<col width="10%">
			<col width="10%">
			<col width="40%">
			<col width="10%">
			<col width="30%">
		</colgroup>
		<tr><th><input type="checkbox" name="checkAll" id="checkAll" onclick="checkAll();"/></th>
			<th>글번호</th>
			<th>제목</th>
			<th>조회수</th>
			<th>작성시간</th>
		</tr>
		<c:forEach items="${list}" var="list">
			<tr>
				<td><input type="checkbox" name="board_num" class="chkNum" value="${list.board_num }"></td>
				<td>${list.board_num}</td>
				<td><a href="/testBoard/boardDetail?board_num=${list.board_num}">${list.board_title}</a>
				&nbsp;&nbsp;<span><c:if test="${list.board_file ne null }"><img id="attachment" src="https://previews.123rf.com/images/tmricons/tmricons1510/tmricons151000236/45807641-%EC%A2%85%EC%9D%B4-%ED%81%B4%EB%A6%BD-%EC%95%84%EC%9D%B4%EC%BD%98-%EC%B2%A8%EB%B6%80-%EB%B2%84%ED%8A%BC.jpg"  /></c:if></span>
				</td>
				<td>${list.board_read_cnt }</td>
				<td>${list.board_date}</td>
			</tr>

		</c:forEach>
	</table>
	<br>
	<div>
		<button type="button" onclick="location.href='boardWrite'">글작성</button>
		
		<button id="deleteBtn" type="button">삭제</button>
		<button id="download" type="button">다운로드</button><br />
	</div>

	<!-- 페이징 -->

	<div class="pagination">
		<ul class="pagination">
			<c:if test="${map.pagination.pageNo ne 1 and map.pagination.prevPageNo ne 0}">
				<li class="start">
				<a href="/testBoard/boardList?searchType=${map.searchType}&keyword=${map.keyword}&searchBtn=&pageNo=1">처음으로</a>
				</li>
			</c:if>
			<c:if test="${map.pagination.prevPageNo ne 0}">
				<li class="previous">
				<a href="/testBoard/boardList?searchType=${map.searchType}&keyword=${map.keyword}&searchBtn=&pageNo=${map.pagination.prevPageNo}"> 이전 </a></li>
			</c:if>
			<c:forEach begin="${map.pagination.startPageNo}"
				end="${map.pagination.endPageNo}" var="i">
				<c:choose>
					<c:when test="${map.pagination.pageNo eq i }">
						<li class="active">
						<a href="/testBoard/boardList?searchType=${map.searchType}&keyword=${map.keyword}&searchBtn=&pageNo=${i}">${i}</a></li>
					</c:when>
					<c:otherwise>
						<li class="waves-effect">
						<a href="/testBoard/boardList?searchType=${map.searchType}&keyword=${map.keyword}&searchBtn=&pageNo=${i}">${i}</a></li>
					</c:otherwise>
				</c:choose>
			</c:forEach>
			<c:if test="${map.pagination.nextPageNo ne 0}">
				<li class="next">
				<a href="/testBoard/boardList?searchType=${map.searchType}&keyword=${map.keyword}&searchBtn=&pageNo=${map.pagination.nextPageNo}">다음</a></li>
			</c:if>
			<c:if test="${map.pagination.pageNo ne map.pagination.numOfPage and map.pagination.nextPageNo ne 0}">
					<li class="end">
					<a href="/testBoard/boardList?searchType=${map.searchType}&keyword=${map.keyword}&searchBtn=&pageNo=${map.pagination.numOfPage}">끝으로</a></li>	
			</c:if>
		</ul>
	</div>
	<br />
	<!-- 검색기능 -->
	<div>
		<div id="searchDiv">
		<form id="serchFrm" name="serchFrm">
		<c:out value="${cnt}"></c:out>개의 글이 있습니다. <br />
			<select name="searchType" id="searchType">
				<option value="board_title">제목</option>
				<option value="board_content">내용</option>
			</select> <input type="text" name="keyword" id="keyword" /> 
			<button name="searchBtn" id="searchBtn" >검색</button>
			<input type="button" name="reset" id="reset" value="검색초기화"
						onclick="location.href='boardList'">
			<input type="hidden" name="pageNo" id="pageNo" value="1">
			</form>
		</div>
	</div>
	${map}
</body>
</html>