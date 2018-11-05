<%@page import="vo.BoardVO"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!--
Author: W3layouts
Author URL: http://w3layouts.com
License: Creative Commons Attribution 3.0 Unported
License URL: http://creativecommons.org/licenses/by/3.0/
-->
<!DOCTYPE HTML>
<html>
	<head>
		<title>추리소설 랭킹 :: ${vo.name}</title>
		<link href="web/css/style.css" rel='stylesheet' type='text/css' />
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link rel="shortcut icon" type="image/x-icon" href="web/images/fav-icon.png" />
		<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
		<!----webfonts---->
		<link href='http://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700,800' rel='stylesheet' type='text/css'>
		<!----//webfonts---->
		<!---start-click-drop-down-menu----->
		<script src="web/js/jquery.min.js"></script>
        <!----start-dropdown--->
         <script type="text/javascript">
			var $ = jQuery.noConflict();
				$(function() {
					$('#activator').click(function(){
						$('#box').animate({'top':'0px'},500);
					});
					$('#boxclose').click(function(){
					$('#box').animate({'top':'-700px'},500);
					});
				});
				$(document).ready(function(){
				//Hide (Collapse) the toggle containers on load
				$(".toggle_container").hide(); 
				//Switch the "Open" and "Close" state per click then slide up/down (depending on open/close state)
				$(".trigger").click(function(){
					$(this).toggleClass("active").next().slideToggle("slow");
						return false; //Prevent the browser jump to the link anchor
				});
				
									
			});
		</script>
        <!----//End-dropdown--->
	</head>
	<body>
		<!---start-wrap---->
			<!---start-header---->
			<div class="header">
				<div class="wrap">
				<div class="logo">
					<a href="churiList.do"><img src="web/images/logo2.png" title="pinbal" /></a>
				</div>
				<div class="nav-icon">
					 <a href="#" class="right_bt" id="activator"><span> </span> </a>
				</div>
				 <div class="box" id="box">
					 <div class="box_content">        					                         
						<div class="box_content_center">
						 	<div class="form_content">
								<div class="menu_box_list">
									<ul>
										<li><a href="#"><span>home</span></a></li>
										<li><a href="#"><span>추리</span></a></li>
										<li><a href="#"><span>SF</span></a></li>
										<li><a href="#"><span>액션/스릴러</span></a></li>
										<li><a href="#"><span>판타지/무협</span></a></li>
										<li><a href="#"><span>라이트노벨</span></a></li>
										<li><a href="contact.html"><span>Contact</span></a></li>
										<div class="clear"> </div>
									</ul>
								</div>
								<a class="boxclose" id="boxclose"> <span> </span></a>
							</div>                                  
						</div> 	
					</div> 
				</div>       	  
				<div class="top-searchbar">
					<form>
						<input type="text" /><input type="submit" value="" />
					</form>
				</div>
				<div class="userinfo">
					<div class="user">
						<ul>
							<li><a href="#"><img src="web/images/user-pic.png" title="user-name" /><span>Ipsum</span></a></li>
						</ul>
					</div>
				</div>
				<div class="clear"> </div>
			</div>
		</div>
		<!---//End-header---->
		<!---start-content---->
		<div class="content">
			<div class="wrap">
			<div class="single-page">
							<div class="single-page-artical">
								<div class="artical-content">
								<p align="right">조회수 : ${vo.readcount}</p>
									<h4 class="style3"><a>${vo.rank}<b style="font-size: 20px">위</b></a></h4>
									<img src="web/images/book/${vo.name}.jpg" title="banner1">
									<p>&nbsp;</p>
									<h3><a href="#">${vo.name}</a></h3>
									<p>&nbsp;</p>
									<p>${vo.writer}</p> 
									<p>${vo.info}</p> 
									<p>${vo.price}원</p> 
									<p>&nbsp;</p>
									<p><a>${vo.content}</a></p> 
								    </div>
								    <div class="artical-links">
		  						 	<ul>
		  						 		<!-- <li><a href="#"><img src="web/images/blog-icon2.png" title="Admin"><span>admin</span></a></li>
		  						 		<li><a href="#"><img src="web/images/blog-icon3.png" title="Comments"><span>No comments</span></a></li>
		  						 		<li><a href="#"><img src="web/images/blog-icon4.png" title="Lables"><span>View posts</span></a></li> -->
		  						 	</ul>
		  						 </div>
		  						 <div class="share-artical">
		  						 	<ul>
		  						 		<!-- <li><a href="#"><img src="web/images/facebooks.png" title="facebook">Facebook</a></li>
		  						 		<li><a href="#"><img src="web/images/twiter.png" title="Twitter">Twiiter</a></li>
		  						 		<li><a href="#"><img src="web/images/google+.png" title="google+">Google+</a></li> -->
		  						 		<li><a href="https://book.naver.com/search/search.nhn?query=${vo.name}" target="_blank"><img src="web/images/naver2.png" title="rss">NAVER book</a></li>
		  						 	</ul>
		  						 </div>
		  						 <div class="clear"> </div>
							</div>
							<!---start-comments-section--->
							<div class="comment-section">
				<div class="grids_of_2">
					<h2>Comments</h2>
					<c:forEach var="cmt" items="${comments}">
					<c:if test="${cmt.re_seq==0}">
					<div class="grid1_of_2">
						<div class="grid_img">
							<img src="web/images/pic10.jpg" alt="">
						</div>
						<div class="grid_text">
							<h4 class="style2 list">${cmt.cmt_name}</h4>
							<h3 class="style2">${cmt.cmt_date}</h3>
							<p class="para top"> ${cmt.cmt_content}</p>
							<a class="btn1" name="reply_reply" reply_id="${cmt.cmt_number}" bno="${vo.bno}" style="cursor:pointer">Reply</a>
							<a style="cursor:pointer" onclick="openDelForm(${cmt.cmt_number},${vo.bno})" class="btn2">Delete</a>
						</div>
						<div class="clear"></div>
					</div>
					</c:if>
					<c:if test="${cmt.re_seq!=0}">
					<div class="grid1_of_2 left">
						<div class="grid_img">
							<img src="web/images/pic12.jpg" alt="">
						</div>
						<div class="grid_text">
							<h4 class="style2 list">${cmt.cmt_name}</h4>
							<h3 class="style2">${cmt.cmt_date}</h3>
							<p class="para top"> ${cmt.cmt_content}</p>
							<a style="cursor:pointer" onclick="openDelForm(${cmt.cmt_number},${vo.bno})" class="btn2">Delete</a>
						</div>
						<div class="clear"></div>
					</div>
					</c:if>
					</c:forEach>
					<!-- <div class="grid1_of_2 left">
						<div class="grid_img">
							<a href=""><img src="web/images/pic12.jpg" alt=""></a>
						</div>
						<div class="grid_text">
							<h4 class="style1 list"><a href="#">Designer First</a></h4>
							<h3 class="style">march 3, 2013 - 4.00 PM</h3>
							<p class="para top"> All the Lorem Ipsum generators on the Internet tend to repeat predefined chunks as necessary, making this the first true generator on the Internet. It uses a dictionary of over 200 Latin words, combined with a handful of model sentence structures, to generate Lorem Ipsum which looks reasonable.</p>
							<a href="" class="btn1">Click to Reply</a>
						</div>
						<div class="clear"></div>
					</div>
					<div class="grid1_of_2">
						<div class="grid_img">
							<a href=""><img src="web/images/pic12.jpg" alt=""></a>
						</div>
						<div class="grid_text">
							<h4 class="style1 list"><a href="#">Ro Kanth</a></h4>
							<h3 class="style">march 2, 2013 - 12.50 AM</h3>
							<p class="para top"> All the Lorem Ipsum generators on the Internet tend to repeat predefined chunks as necessary, making this the first true generator on the Internet. It uses a dictionary of over 200 Latin words, combined with a handful of model sentence structures, to generate Lorem Ipsum which looks reasonable.</p>
							<a href="" class="btn1">Click to Reply</a>
						</div>
						<div class="clear"></div>
					</div> -->								
						<div class="artical-commentbox">
						 	<h2>Leave a Comment</h2>
				  			<div class="table-form">
								<form action="cmtInsert.do" method="post" role="form">
									<div>
										<label>Name<span>*</span></label>
										<input type="text" name="cmt_name">
									</div>
									<div>
										<label>Password<span>*</span></label>
										<input type="password" name="cmt_password">
									</div>
									<div>
										<label>Your Comment<span>*</span></label>
										<textarea name="cmt_content"> </textarea>	
									</div>
									<input type=hidden name="article_number" value="${vo.bno}"> 
								<input type="submit" value="submit">
								</form>
								
									
							</div>
							<div class="clear"> </div>
				  		</div>			
					</div>
			</div>
							<!---//End-comments-section--->
						</div>
						 </div>
		</div>
		<!----start-footer--->
		<div class="footer">
		</div>
		<script>
		// 삭제창 open
		function openDelForm(cmt_number,article_number)
		{
			window.name = "parentForm";
			/* window.open("GuestbookDeleteFormAction.ge?num="+cmt_number,
					"delForm", "width=570, height=350, resizable = no, scrollbars = no"); */
			window.open("web/GuestbookDeleteForm.jsp?cmt_number="+cmt_number+"&article_number="+article_number,
					"delForm", "width=400, height=200, resizable = no, scrollbars = no");
		}
		

		</script>
		<!----//End-footer--->
		<!---//End-wrap---->
	<!-- <script src="http://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script>
	$.ajaxSetup({
		type:"POST",
		async:true,
		dataType:"json",
		error:function(xhr){
			console.log("error html = " +xhr.statusText);
		}
	});
	$(function() {
		$("#commentWrite").on("click", function() {
			$.ajax({
				url:"",
				
				data:{
					cmt_name:$("#cmt_name").val(),
					cmt_password:$("cmt_password").val(),
					cmt_content:$("cmt_content").val(),
					bno:"${vo.bno}"
				},
				beforeSend:function(){
					console.log("시작 전...");
				},
				complete:function(){
					console.log("완료 후...");
				},
				success:function(data){
					if(data.result == 1) {
						console.log("comment가 정상적으로 입력되었습니다.");
						$("#cmt_name").val("");
						$("#cmt_password").val("");
						$("#cmt_content").val("");
						
					}
				}
			})
		})
	})
	</script> -->
	<script type="text/javascript" src="http://code.jquery.com/jquery-1.11.3.js"></script>
	<script type="text/javascript">
	//대댓글 입력창
    $(document).on("click","a[name='reply_reply']",function(){ //동적 이벤트
    	
    	if(status==true){
    		alert("수정과 대댓글은 동시에 불가합니다.");
    		return false;
    	}
    	
    	status = true;
         
        $("#reply_add").remove();
         
        var reply_id = $(this).attr("reply_id");
        var bno = $(this).attr("bno");
        console.log(reply_id);
        console.log(bno);
        var last_check = false;//마지막 tr 체크
         
        //입력받는 창 등록
        var replyEditor = 
           '<tr id="reply_add" class="table-form2">'+
           '   <td>'+
           '       <textarea name="reply_reply_content" rows="3" cols="50"></textarea>'+
           '   </td>'+
           '   <td>'+
           '       <input type="text" name="reply_reply_writer" style="width:100%;" maxlength="10" placeholder="작성자"/>'+
           '   </td>'+
           '   <td>'+
           '       <input type="password" name="reply_reply_password" style="width:100%;" maxlength="10" placeholder="패스워드"/>'+
           '   </td>'+
           '   <td align="center">'+
           '       <button name="reply_reply_save" reply_id="'+reply_id+'" bno="'+bno+'">등록</button>'+
           '       <button name="reply_reply_cancel">취소</button>'+
           '   </td>'+
           '</tr>'
             
        var prevTr = $(this).parent().parent().next();
         
        //부모의 부모 다음이 sub이면 마지막 sub 뒤에 붙인다.
        //마지막 리플 처리
        if(prevTr.attr("reply_type") == undefined){
            prevTr = $(this).parent().parent();
        }else{
            while(prevTr.attr("reply_type")=="sub"){//댓글의 다음이 sub면 계속 넘어감
                prevTr = prevTr.next();
            }
             
            if(prevTr.attr("reply_type") == undefined){//next뒤에 tr이 없다면 마지막이라는 표시를 해주자
                last_check = true;
            }else{
                prevTr = prevTr.prev();
            }
             
        }
         
        if(last_check){//마지막이라면 제일 마지막 tr 뒤에 댓글 입력을 붙인다.
            $('#reply_area tr:last').after(replyEditor);    
        }else{
            prevTr.after(replyEditor);
        }
         
    });
	//대댓글 등록
         $(document).on("click","button[name='reply_reply_save']",function(){
                                  
             var reply_reply_writer = $("input[name='reply_reply_writer']");
             var reply_reply_password = $("input[name='reply_reply_password']");
             var reply_reply_content = $("textarea[name='reply_reply_content']");
             var reply_reply_content_val = reply_reply_content.val().replace("\n", "<br>"); //개행처리
             
             //널 검사
             if(reply_reply_writer.val().trim() == ""){
                 alert("이름을 입력하세요.");
                 reply_reply_writer.focus();
                 return false;
             }
              
             if(reply_reply_password.val().trim() == ""){
                 alert("패스워드를 입력하세요.");
                 reply_reply_password.focus();
                 return false;
             }
              
             if(reply_reply_content.val().trim() == ""){
                 alert("내용을 입력하세요.");
                 reply_reply_content.focus();
                 return false;
             }
             //location.href="reply_reply.do?bno="+bno+"&reply_id="+reply_id;
             //값 셋팅
             var objParams = {
                     bno	        : $(this).attr("bno"),
                     reply_id       : $(this).attr("reply_id"), 
                     reply_writer    : reply_reply_writer.val(),
                     reply_password  : reply_reply_password.val(),
                     reply_content   : reply_reply_content_val
             };
              
             var reply_id;
             var bno;
              
             //ajax 호출
             
             $.ajax({
                 url         :   "reply_reply.do",
                 dataType    :   "json",
                 contentType :   "application/x-www-form-urlencoded; charset=UTF-8",
                 type        :   "post",
                 async       :   false, //동기: false, 비동기: ture
                 data:{
                	 bno	        : $(this).attr("bno"),
                     reply_id       : $(this).attr("reply_id"), 
                     reply_writer    : reply_reply_writer.val(),
                     reply_password  : reply_reply_password.val(),
                     reply_content   : reply_reply_content_val
 						},
                 
             });
             location.href="churiHitUpdate.do?bno="+$(this).attr("bno");
             
             
             /* reply_id = reply_count++;//DB에 저장했다 하고 순번을 생성
              
             var reply = 
                 '<tr reply_type="sub">'+
                 '   <td width="820px"> → '+
                 reply_reply_content_val+
                 '   </td>'+
                 '   <td width="100px">'+
                 reply_reply_writer.val()+
                 '   </td>'+
                 '   <td width="100px">'+
                 '       <input type="password" id="reply_password_'+reply_id+'" style="width:100px;" maxlength="10" placeholder="패스워드"/>'+
                 '   </td>'+
                 '   <td align="center">'+
                 '       <button name="reply_modify" r_type = "sub" reply_id = "'+reply_id+'">수정</button>'+
                 '       <button name="reply_del" reply_id = "'+reply_id+'">삭제</button>'+
                 '   </td>'+
                 '</tr>';
                  
             var prevTr = $(this).parent().parent().prev();
              
             prevTr.after(reply);
                                  
             $("#reply_add").remove();
             
             status = false; */
              
         });
          
         //대댓글 입력창 취소
         $(document).on("click","button[name='reply_reply_cancel']",function(){
             $("#reply_add").remove();
             
             status = true;
         });
         </script>
	</body>
</html>

