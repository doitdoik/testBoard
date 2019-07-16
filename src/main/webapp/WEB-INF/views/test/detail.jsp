<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${detail.board_num}번 글 상세</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

<style>
	#board_detail{
		width : 500px;
		height: 500px;
		margin: 0 auto;
	}
	#title{
		height:50px;
	}
	td{
	border: 1px solid black;
	}
</style>

<script>


$(function(){
$("#deleteBtn").on("click",function(){
	
    var board_num = $("#deleteBtn").data("board_num");
    var params ={
          board_num:board_num,
          _method:'delete'
    }
    $.ajax({
       url:'/testBoard/boardDelete',
       method:'post',
       data:params,
       success:function(){
          self.location.replace('/testBoard/boardList')
       }
    });
 });
});
/*  function checkAll(){
      if( $("#checkAll").is(':checked') ){
        $("input[name=file_num]").prop("checked", true);
      }else{
        $("input[name=file_num]").prop("checked", false);
      }
} */

 $(function(){
		
	   $('#download').on("click", function(e){
		   var file_name = "${fileList.file_name }";
		   var org_file_name = "${fileList.file_org_name }";
	
	   })
	}); 
 
/* $(function(){
	
	   $('#download').on("click", function(e){
		   var file_org_name = document.getElementsByName("file_org_name");
		   var file_name = document.getElementsByName("file_name");
		   console.log(document.getElementsByName("file_org_name"));
		   for(var i =0; i < file_org_name.length; i++){
			   if(file_org_name[i].onclick){
				   file_org_name:file_org_name[i].value 
				   
			   }
		   }
	   })
	});  */
	
/* $(function(){
	  $("#download").click(function() {
		
			var chk_arr =  document.getElementsByName("file_org_name");
		
			for (var i = 0; i < chk_arr.length; i++) {
				if(chk_arr[i].checked){
				  console.log(i+" value = "+chk_arr[i].value);
				  var params ={
				          file_org_name:chk_arr[i].value   
				    }
				    $.ajax({
				       url:'boardDownloadData',
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
});		 */
</script>
</head>
<body>
	<table id="board_detail">
	<tr id="title">
		<td>제목</td>
		<td>
		<c:out value="${detail.board_title}" />
		</td>
	</tr>
	<tr id="file">
		<td>첨부파일</td>
		<td>
		<form action="boardDownloadData" id="downform">
		
		<input type="checkbox" name="checkAll" id="checkAll" onclick="checkAll();"/>
		
		<c:choose>
		<c:when test="${detail.board_file ne null }">
			<p><c:forEach items="${detail.fileCommand }" var="fileList">
			<span><input type="checkbox" name="file_org_name" class="file_org_name" value="${list.file_num }"></span>
			<span>${fileList.file_org_name}&nbsp;&nbsp;&nbsp;&nbsp;
			<span>${fileList.file_size} KB</span>
			<button id="download" class="btn btn-detail">다운로드</button> <br>
			<input type="hidden" name="file_org_name" class="file_org_name" value="${fileList.file_org_name }"/>
			<input type="hidden" name="file_name" value="${fileList.file_name }"/>
			</span>
			</c:forEach></p>
			
		</c:when>	
		<c:otherwise>
			<p>첨부파일 없음</p>
		</c:otherwise>
		</c:choose>
			<br />
		<c:if test="${detail.board_file ne null}">
			<input type="hidden" id="org_file_name" name="org_file_name" value="${detail.board_file }"/>
		</c:if>
		
		</form>
		</td>
	</tr>
	<tr>
		<td>내용</td>
		<td>	
		<c:out value="${detail.board_content}" />
		
		</td>
	</tr>
	<tr><td>test</td>
	<td><input type="hidden" name="board_file" value="${detail.board_file}"/>
		<c:out value="${detail.board_file}" />
	</td>
	</tr>
	</table>
	
	<a href="/testBoard/boardUpdate?board_num=${detail.board_num}"><button class="btn btn-detail">수정</button></a>
	<button id="deleteBtn" type="button" class="btn btn-detail" data-board_num="${detail.board_num}">삭제</button>
	<button type="button" onclick="location.href='boardList'" class="btn btn-detail">목록</button>
</body>
</html>