<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
		<script src="./jq/jquery-3.6.0.js"></script>
		<script type="text/javascript">
		
		$(function(){
			$( '.check-all' ).click( function() {
		          $( '.mems' ).prop( 'checked', this.checked );
		        } ); // membermanage 전체선택 
			
			$( '.bcheck-all' ).click( function() {
		          $( '.board' ).prop( 'checked', this.checked );
		        } ); // boardmanage 전체선택
		        
		        
		        $.getJSON("./arrays/updateBob.jsp", function(data){
		        	$.each(data,function(index,item){
						var tag = "<tr>";
						tag += "<td>"+item.num+"</td>";
						tag += "<td>"+item.title+"</td>";
						tag += "<td>"+item.id+"</td>";
						tag += "<td>"+item.thumb+"</td>";
						tag += "<td>"+item.readcount+"</td>";
						tag += "<td>"+item.replycount+"</td>";
						tag += "<td><select class='bbob' name='bbob'>"
						tag += "<option value='0'>일반글</option>"
						tag += "<option value='1' selected>인기글</option>"
						tag += "</select><button class='button manage' onclick='manageBob("+index+","+item.num+");'>적용</button></td>"
						tag += "</tr>";
					
					$('#bobcol').append(tag);
				});
			});    
		        
			}); //jquery
		function deleteData(){
			if(confirm("정말로 탈퇴 시키겠습니까?")){
				var check = prompt("관리자 비밀번호를 입력하세요.");
				$.ajax({
					url: "/collegestudent/adminPwCheck.mem",
					success: function(data){
						if(Number(check) == Number(data)){
							 var mems = '';
								$('input:checkbox[name="mems"]:checked').each(function(i){
									mems += $(this).val();
									mems += ",";
								});
								location.href='./membersDelete.ad?mems='+mems;
							
						} else{
							alert("비밀번호가 일치하지 않습니다.");
						}
					}
				});
			} 
		}
			
		function deleteBData(){
			if(confirm("정말로 삭제 하시겠습니까?")){
				var check = prompt("관리자 비밀번호를 입력하세요.");
				$.ajax({
					url: "/collegestudent/adminPwCheck.mem",
					success: function(data){
						if(Number(check) == Number(data)){
							 var boards = '';
								$('input:checkbox[name="board"]:checked').each(function(i){
									boards += $(this).val();
									boards += ",";
								});
								location.href='./boardsDelete.ad?boards='+boards;
							
						} else{
							alert("비밀번호가 일치하지 않습니다.");
						}
					}
				});
			} 
		}
		
		
		
		
		function searchCheck(){
			
			if(document.fr.s_keyword.selectedIndex == 0){
				alert("검색할 항목을 선택해주세요.");
				return false;
			} 
			if(document.fr.s_content.value.length <1){
				alert("검색어를 입력해 주세요.");
				return false;
			} 
		}
		
		function updateCheck(id){
			var check = prompt("관리자 비밀번호를 입력하세요.");
			$.ajax({
				url: "/collegestudent/adminPwCheck.mem",
				success: function(data){
					if(Number(check) == Number(data)){
						location.href="./memberUpdate.mem?id="+id;
					} else{
						alert("비밀번호가 일치하지 않습니다.");
					}
				}
			});
		};
		
		
		function bupdateCheck(num){
			var check = prompt("관리자 비밀번호를 입력하세요.");
			$.ajax({
				url: "/collegestudent/adminPwCheck.mem",
				success: function(data){
					if(Number(check) == Number(data)){
						location.href="./boardUpdate.bo?num="+num;
					} else{
						alert("비밀번호가 일치하지 않습니다.");
					}
				}
			});
		};
		
		
		function updateBob(i,num){
			
			var bob_val = document.getElementsByClassName("bob");
			
			var bob = bob_val[i].value;
			var check = prompt("관리자 비밀번호를 입력하세요.");
			$.ajax({
				url: "/collegestudent/adminPwCheck.mem",
				success: function(data){
					if(Number(check) == Number(data)){
						location.href="./boardBobUpdate.ad?num="+num+"&bob="+bob;
					} else{
						alert("비밀번호가 일치하지 않습니다.");
					}
				}
			});
		};
		
		function manageBob(i,num){
			var bbob_val = document.getElementsByClassName("bbob");
			
			var bob = bbob_val[i].value;
			var check = prompt("관리자 비밀번호를 입력하세요.");
			$.ajax({
				url: "/collegestudent/adminPwCheck.mem",
				success: function(data){
					if(Number(check) == Number(data)){
						location.href="./boardBobUpdate.ad?num="+num+"&bob="+bob;
					} else{
						alert("비밀번호가 일치하지 않습니다.");
					}
				}
			});
		};
		
		</script>