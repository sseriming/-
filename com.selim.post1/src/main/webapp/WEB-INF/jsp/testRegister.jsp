<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Board Write</title>
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
<!-- <script type="text/javascript" src="js/jquery-3.1.1.min.js"></script> -->
</head>
<body>
<br/>
    <h1 class="text-center">Board Write</h1>
<br/>
<br/>
<div class="container">
        <form id="form_test" action="insertTest.do" method="post"
            encType="multipart/form-data">
            
            <input type="hidden" id="orgName" name="orgName" class="form-control" />
            <input type="hidden" id="fileSize" name="fileSize" class="form-control" />
            
            
            <table class="table table-bordered">
                <tbody>
                    <tr>
                        <th>제목</th>
                        <td><input type="text" placeholder="제목을 입력하세요." maxlength="50"
                            id="testTitle" name="testTitle" class="form-control" /></td>
                    </tr>
                    <tr>
                        <th>작성자</th>
                        <td>
                        <input  type="text" value="${userId}" id="testName" name="testName"
                        readonly class="form-control"  />
                        </td>
                    </tr>
                    <tr>
                        <th>내용</th>
                        <td><textarea placeholder="내용을 입력하세요 ." maxlength="300"  
                        name="testContent" id="testContent" class="form-control" style="height: 200px;"></textarea></td>
                    </tr>
                    <tr>
                        <th>첨부파일</th>
                        <td><input type="file" name="file" id="file"  onchange="valExt()" ></td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <button id="btn_register" type="button" class="btn_register">등록</button>
                            <button id="btn_previous" type="button" class="btn_previous">이전</button>
                    </tr>
 
                </tbody>
            </table>
<!--             <div id= "fileDiv"> -->
<!--             <p> -->
<!--             <input type="file" name="file" id="file"  onchange="valExt()"  > -->
<!--             <button id="btn_register" type="button" class="btn_register" style="float: right;" style="margin-left:10px;">등록</button> -->
<!--             <button id="btn_previous" type="button" class="btn_previous" style="float: right;"   >이전</button> -->
<!--             </p> -->
<!--             </div> -->
        </form>
    </div>
</body>
<script type="text/javascript">
		$(document).ready(function(){
			//이전 클릭
			$("#btn_previous").click(function previous() {
	        	$(location).attr('href', 'testList.do');
	   		 });
			
			//등록 클릭
			$("#btn_register").on("click",function() {
				if($("#testTitle").val()==""){
					alert("제목을 입력해주세요.");ㅣ					
					return false;
				}
				if($("#testContent").val()==""){
					alert("내용을 입력해주세요.");
					return false;
				}
				$("#form_test").submit();
			})
		});

		/*확장자 및 파일 용량 체크*/
		function valExt(){
// 			alert("ddd");
			
		       if($("#file").val()!=""){
		              file_nm = $("#file").val();
		              var fSize = document.getElementById("file").files[0].size;
// 		              var fName = document.getElementById("orgName"); 
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
		
		
		
    
</script>
</html>
