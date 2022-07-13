<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script src="./jq/jquery-3.6.0.js"></script>
		<script type="text/javascript">
		var s_id = "${s_id}";
		var num = "${dto.num}";
		var file = "${dto.file}";
		
		
		$(function(){
		if(s_id){
			$("#thu_update").click(function(){
				$.ajax({
					url: "/collegestudent/thumbUpdate.bo",
	                type: "POST",
	                data: {
	                    b_num: num,
	                    id: s_id,
	                },
	                success: function (result) {
				     	thuCount();
				     	 if(result==0){
				     		  $('img#like_img').attr('src', './images/heart.png');
	                	} else {
	                		$('img#like_img').attr('src', './images/empty_heart.png');
	                	}//if
	                }//success
				})
			}) // #thu_update
		} //if 
		
		
		$('.re_content').keyup(function (e){
			 var content = $(this).val(); 
			 $('#counter').css("color","black");
			  $('#counter').html("("+content.length+" / 최대 300자)");   //글자수 실시간 카운팅
			 if (content.length > 300){
				  alert("최대 300자까지 입력 가능합니다."); 
				  $(this).val(content.substring(0, 300));
				 $('#counter').css("color","red");
				 $('#counter').html("(300 / 최대 300자)"); 
				 
				 }
			 });
		
		  	
		});//jquery
		
		
		function reCount(){
			$.ajax({
    			url: "/collegestudent/reCount.bo",
    			type: "post",
    			data: {
    				num: num,
    			},
    			success: function(count){
    				$(".re_count").html(count+"개");
    			},
    		})
		}
		reCount();
		
		function thuCount(){
    		$.ajax({
    			url: "/collegestudent/thumbCount.bo",
    			type: "post",
    			data: {
    				num: num,
    			},
    			success: function(count){
    				$(".thu_count").html(count);
    			},
    		})
    	};	
	    thuCount();
	    
	    
	    function thuCheck(){
	    	$.ajax({
				url: "/collegestudent/thumbCheck.bo",
                type: "POST",
                data: {
                    b_num: num,
                    id: s_id,
                },
                success: function (result) {
			     	 if(result==1){
			     		  $('img#like_img').attr('src', './images/heart.png');
                	} else {
                		$('img#like_img').attr('src', './images/empty_heart.png');
                	}//if
			     	thuCount();
                }//success
			})
	    }
	    	thuCheck();
	    
	    
	    function deleteCheck(){
			var check = confirm("이 글을 삭제하시겠습니까?");
			
			if(check)
				location.href="./boardDelete.bo?num="+num+"&file="+file;
			
		}
	    
	    
	    if(s_id){
	    function doDisplay(i){
	    	//var con = document.getElementById("reReplyBox");
	    	var reReplyBox = document.getElementsByClassName("reReplyBox");
	    	
	    	if(reReplyBox[i].style.display=='none'){
	    		reReplyBox[i].style.display = 'block';
	    	}else{
	    		reReplyBox[i].style.display='none';
	    		}
	    	
	    	}
	    }
	    
	    
	    function doUpdateDisplay(i){
	    	var update_input = document.getElementsByClassName("update_input");
	    	var up_content = document.getElementsByClassName("up_content");
	    	
	    	if(update_input[i].style.display=='none'){
	    		update_input[i].style.display = 'block';
	    		up_content[i].style.display = 'none';
	    	} else{
	    		update_input[i].style.display = 'none';
	    		up_content[i].style.display = 'inline-block';
	    	}
	    	
	    }
	    
	    
	    function doUpdate(i){
	    	var re_upcontent = document.getElementsByClassName("re_upcontent");
	    	var num = document.getElementsByClassName("re_num");
	    	var upcontent = re_upcontent[i].value;
	    	var re_num = num[i].value;
	    	
	    	 $.ajax({
	    		url: "/collegestudent/reUpdate.re",
	    		type: "post",
	    		data: {
	    			re_upcontent: upcontent,
	    			re_num: re_num
	    		},
	    		success: function(data){
	    			alert("글 수정이 완료되었습니다.");
	    			location.reload();
	    		}
	    	}); 
	    }
	    
	    
		function reDelete(re_num){
			
	    	var answer = confirm("정말로 삭제하시겠습니까?");
			 if(answer){
				$.ajax({
					url: "/collegestudent/reDelete.re",
					type: "post",
					data: {
						re_num: re_num
					},
					success: function(data) {
						alert("삭제가 완료되었습니다.");
						location.reload();
					}
				}) 
			} 
	    	
	    }
	  
	    function replyCheck(){
	    	if(document.fr.re_name.value.length<1){
	    		alert("닉네임을 입력해주세요");
	    		return false;
	    	}else if(document.fr.re_content.value.length<1){
	    		alert("내용을 입력해주세요");
	    		return false;
	    	}
	    	
	    }
	    
	    
	    function re_replyCheck(){
	    	if(document.rfr.re_rename.value.length<1){
	    		alert("닉네임을 입력해주세요");
	    		return false;
	    	}else if(document.rfr.re_recontent.value.length<1){
	    		alert("내용을 입력해주세요");
	    		return false;
	    	}
	    	
	    }
	    
		</script>