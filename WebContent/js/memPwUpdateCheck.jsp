<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script src="./jq/jquery-3.6.0.js"></script>
			<script type="text/javascript">
			var pwCheck = 0;
			var pwEqCheck = 0;
			
			$('.pw_input').change(function(){
				   pwCheck = 0;
				   pwEqCheck = 0;
			      
			    });
		   
		   $('.pwck_input').change(function(){
				   pwCheck = 0;
				   pwEqCheck = 0;
			      
			    });
			
			$(function(){
				
				
				$('.pw_input').on("change keyup paste", function(){
					var pw = $('.pw_input').val();
					var pwRegExp = /(?=.*\d{1,50})(?=.*[~`!@#$%\^&*()-+=]{1,50})(?=.*[a-zA-Z]{2,50}).{8,20}$/;
					
					 if(pwRegExp.test(pw)){
					        $('.pw_input_re_1').css('display','inline');
					        $('.pw_input_re_2').css('display','none');
					        pwCheck = 1;
				    }else{
				        $('.pw_input_re_1').css('display','none');
				        $('.pw_input_re_2').css('display','inline');
				        pwCheck = 0;
				    }  
					        
				});
				
				
				$('.pwck_input').on("change keyup paste", function(){
					var pw = $('.pw_input').val();
					var pwck = $('.pwck_input').val();
					
					$('.final_pwck_ck').css('display','none');
					
					 if(pw == pwck){
					        $('.pwck_input_re_1').css('display','inline');
					        $('.pwck_input_re_2').css('display','none');
					        pwEqCheck = 1;
				    }else{
				        $('.pwck_input_re_1').css('display','none');
				        $('.pwck_input_re_2').css('display','inline');
				        pwEqCheck = 0;
				    }  
					        
				});
			    
			}); //jquery
			
			function updateCheck(){
				
		  		if(pwCheck == 0){
		  			alert("사용할 수 없는 비밀번호입니다.")
		  			$('.pw_input').focus();
		  			return false;
		  		} else if(pwEqCheck == 0){
		  			alert("비밀번호가 일치하지 않습니다.")
		  			$('.pwck_input').focus();
		  			return false;
		  		}
		  		
			}//insertCheck
			
			
			
			
			
			</script>