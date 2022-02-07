<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Board Detail</title>
<!-- Bootstrap CSS -->
<link
    href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css"
    rel="stylesheet"
    integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1"
    crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<style type="text/css">
	#textbold {
	font-weight:bold;
	}
	a {
    text-decoration: auto;
	}


</style>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
    href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
    integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
    crossorigin="anonymous">
 
<!-- Optional theme -->
<link rel="stylesheet"
    href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
    integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp"
    crossorigin="anonymous">
 
<!-- Latest compiled and minified JavaScript -->
<script
    src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
    integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
    crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
</head>
<body>
    <br />
    <h1 class="text-center">Board Detail</h1>
    <br />
    <br />
    <div class="container">
  
    
        <form  name="viewForm"  action="updateTest.do" id="viewForm" method="post"
            encType="multipart/form-data">
            <input type="hidden" id="orgName" name="orgName" class="form-control" />
            <input type="hidden" id="fileSize" name="fileSize" class="form-control" />
			<input type="hidden" id="fileNo" name="fileNo" value=""> 

				
            <table class="table table-bordered">
                <tbody>
                    <tr>
                        <th>글번호</th>
                        <td><input name="testId" type="text" value="${vo.testId}"
                            class="form-control" readonly /></td>
                    </tr>
                    <tr>
                        <th>작성자</th>
                        <td><input type="text" value="${vo.testName}"
                            class="form-control" readonly /></td>
                    </tr>
                    <tr>
                        <th>제목</th>
                        <c:choose>
                        <c:when  test="${vo.testName != sessionScope.userId }" >
                        <td><input type="text" value="${vo.testTitle}" maxlength="50"
                            name="testTitle" class="form-control" readonly /></td>
                        </c:when>
                        <c:when  test="${vo.testName == sessionScope.userId }" >
                        <td><input type="text" value="${vo.testTitle}" maxlength="50"
                            name="testTitle" class="form-control" /></td>
                        </c:when></c:choose>
                    </tr>
                    <tr>
                        <th>내용</th>
                        <c:choose>
                        <c:when  test="${vo.testName != sessionScope.userId }" >
                        <td><textarea name="testContent" class="form-control" maxlength="300"
                                style="height: 200px;" readonly>${vo.testContent} </textarea></td> 
                        </c:when>
                        <c:when  test="${vo.testName == sessionScope.userId }" >
                        <td><textarea name="testContent" class="form-control" maxlength="300"
                                style="height: 200px;" >${vo.testContent} </textarea></td> 
                        </c:when></c:choose>
                    </tr>
					<tr>
                        <th>첨부파일</th>
                        <c:choose>
                        <c:when  test="${vo.testName != sessionScope.userId }" >
                        <td><input type="hidden" name="file" id="file"  onchange="valExt()">
                        <a href="#" onclick="fn_fileDown('${file.fileNo}'); return false;">${file.orgName}</a><br>
                        </c:when>
                        <c:when  test="${vo.testName == sessionScope.userId }" >
                        <td><input type="file" name="file" id="file"  onchange="valExt()">
                        <a href="#" onclick="fn_fileDown('${file.fileNo}'); return false;">${file.orgName}</a><br>
                        <button id="filedelete" onclick="deleteFile('${file.fileNo}')" name="filedelete" type="button" class="btn btn-sm btn-primary" style="float: right">파일삭제</button>
                        </td>
                        </c:when></c:choose>
                    </tr>
                    <tr>
                    <c:if test="${vo.testName == sessionScope.userId }" >
                        <td colspan="2" style="text-align: right;">
                            <button id="btn_previous" type="button" class="btn btn-sm btn-primary">목록</button>
                            <button id="btn_register" type="button" class="btn btn-sm btn-primary">수정</button>
                            <button id="btn_delete" type="button" class="btn btn-sm btn-primary">삭제</button>
                        </td>
                        </c:if>
                        <c:if test="${ vo.testName != sessionScope.userId}">
                         <td colspan="2" style="text-align: right;">
                        <button id="btn_previous" type="button" class="btn btn-sm btn-primary">목록</button>
                        </c:if>
                    </tr>
                </tbody>
            </table>
        </form>

	
    <div>
    <form id="replyForm" name="replyForm" method="post" action="replyWrite.do">
    
	    <c:if test="${empty reply}">
		<p>※ 댓글이 없습니다.</p>
		</c:if>
	
	    <c:forEach items="${reply}" var="ReplyVo">
			<div>
			<div id="textbold"><p>ID : ${ReplyVo.writer} </p></div>
			
			<c:if test="${ReplyVo.writer == sessionScope.userId }">
			<button id="replydelete" type="button" class="btn btn-sm btn-primary" name="replydelete" style="float: right" onclick="replyDelete('${ReplyVo.replyNo}')" >댓글 삭제</button>
			</c:if>
			
			<p>${ReplyVo.content}</p>
			<p>  ${ReplyVo.regDate}</p>
			</div>
		</c:forEach>
	
    	<input type="hidden" id="replyNo" name="replyNo" value="${ReplyVo.replyNo}"> 
        <p>
            <label>댓글 작성자</label> <input type="text" value="${sessionScope.userId }" name="writer" id="writer" readonly> 
        </p>
        <p>
            <textarea rows="5" cols="50" name="content" id="content" maxlength="100" ></textarea>
        </p>
        <p>
        <input type="hidden" name="testId" value="${vo.testId}">
            <button id="btnsubmit" type="button" name="btnsubmit" class="btn btn-sm btn-primary">댓글 작성</button>
        </p>
        
    </form>
    
	</div>
    </div>
     <script
        src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW"
        crossorigin="anonymous"></script>
 
    <script
        src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"
        integrity="sha384-q2kxQ16AaE6UbzuKqyBE9/u/KzioAlnx2maXQHiDX9d4/zp8Ok3f+M7DPm+Ib6IU"
        crossorigin="anonymous"></script>
    <script
        src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.min.js"
        integrity="sha384-pQQkAEnwaBkjpqZ8RU1fF1AKtTcHJwFl3pblpTlHXybJjHpMYo79HY3hIi4NKxyj"
        crossorigin="anonymous"></script>
 </body>   
    <script type="text/javascript">
    
    /*확장자 및 파일 용량 체크*/
	function valExt(){
		
	       if($("#file").val()!=""){
	              file_nm = $("#file").val();
	              var fSize = document.getElementById("file").files[0].size;
	              var result = false;
	              file_ext = file_nm.substring(file_nm.lastIndexOf(".") , file_nm.length).toLowerCase();
	              var arrExt = new Array(".gif", ".jpg",".jpeg",".png",".bmp",".pdf",".hwp",".zip");
	              for(var i=0; i<arrExt.length; i++) {
	                     if(arrExt[i] == file_ext) {
	                            result = true;
	                     }
	              }
	              if(result==true){
	              $("#fileSize").val(fSize);
	              $("#orgName").val(file_nm);
	              }else{
	                     $("#file").replaceWith( $("#file").clone(true) );
	                     alert("등록가능한 확장자가 아닙니다. 확장자명을 확인해주십시오(gif,jpg,jpeg,png,bmp,pdf,hwp,zip)");
	                     $("#file").val("");
	              }
	       }else{
	              alert("파일을 선택해주십시오.");
	              $("#file").val("");
	       }

	}
    
    //첨부파일 삭제
    function deleteFile(fileNo) {
    	
    	var chk = confirm("파일을 삭제하시겠습니까 ?");
    		if(chk==true) {
				$("#fileNo").val(fileNo);
				$("#viewForm").attr("action", "deleteFile.do");
				$("#viewForm").submit();
    		}
    }
    
  	//댓글 삭제
    function replyDelete(replyNo) {
    	
    	var chk = confirm("댓글을 삭제하시겠습니까 ?");
    		if(chk==true) {
				$("#replyNo").val(replyNo);
				$("#replyForm").attr("action", "replyDelete.do");
				$("#replyForm").submit();
    		}
    }
    
  	//파일 다운로드
	function fn_fileDown(fileNo) {
    var formObj = $("form[name='viewForm']");
    $("#fileNo").val(fileNo);
    console.log($("#fileNo").val());
    formObj.attr("action","/fileDown.do");
    formObj.submit();
    }
    
  	
    $(document).ready(function() {
    	
    	var userId = '<%=(String)session.getAttribute("userId")%>';
    	if(userId=="null"){
    		alert("로그인이 필요한 항목입니다. 로그인을 해주세요.");
    		location.href="testList.do";
    	} 
    	
		//댓글등록클릭
    	$("#btnsubmit").on("click",function() {
    		if($("#writer").val()==""){
    			alert("작성자를 입력해주세요.");					
    			return false;
    		}
    		if($("#content").val()==""){
    			alert("내용을 입력해주세요.");
    			return false;
    		}
    	
    		$("#replyForm").submit();
    	})
    	
    	
		//글 수정 버튼
    	 $(document).on('click', '#btn_register', function(e) {
    	        if (confirm("정말 수정하시겠습니까 ?") == true) {
    	            $("#viewForm").attr("action", "updateTest.do");
    	            $("#viewForm").submit();
    	        } else {
    	            return;
    	        }
    	    });

		//글 삭제 버튼
    	 $(document).on('click', '#btn_delete', function(e) {
    	        
    	     var testId = ${vo.testId};    	      
    	       if (confirm("정말 삭제하시겠습니까 ?") == true) {
    	            $("#viewForm").attr("action", "deleteTest.do?testId="+testId);
    	            $("#viewForm").submit();
    	        } else {
    	            return;
    	        }
    	    });
		
		
	    //댓글 삭제 버튼
// 	     $(document).on('click', '#replydelete', function(e) {
// 	    	 	$("#replyNo").val(replyNo);
// 				$("#replyForm").attr("action", "replyDelete.do");
// 				$("#replyForm").submit();
// 	    	   });
    	    
	    
    	//이전 클릭 시 testList로 이동
    	 $("#btn_previous").click(function previous() {
    	 $(location).attr('href', 'testList.do');
    	 });
    	    
   	})
    
   
</script>


</html>
