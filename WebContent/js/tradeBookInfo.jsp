<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
			<script src="./jq/jquery-3.6.0.js"></script>
			<script type="text/javascript">
			var bookCheck = 0;
			
			function book_search(){
				var query = $("#book_title").val();
				var tag = "";
				$.ajax({
					type: "get",
					url: "https://dapi.kakao.com/v3/search/book?target=title",
					headers: {"Authorization":"KakaoAK e7b2755a38190905cefb51d6956be707"},
					data: {"query":query, "size":30},
					success: function(data){
						$(data.documents).each(function(){
							
							tag += "<option value='"+this.price+","+this.publisher+"'>"+this.price+","+this.publisher+"</option>";
							
						});
							$("#book_info").append(tag);
							bookCheck = 1;
					}
				});
				
			}
			
			 $('#book_title').change(function(){
			      bookCheck = 0;
			    }); 
		   
			
			
			function writeCheck(){
				if(document.fr.price.value.length<1){
					alert("가격을 입력해주세요.");
					return false;
				} else if(document.fr.title.value.length<1){
					alert("제목을 입력해주세요.");
					return false;
				} else if(document.fr.content.value.length<1){
					alert("내용을 입력해주세요.");
					return false;
				} else if(bookCheck == 0){
					alert("도서 검색을 해주세요.");
					return false;
				}
			}
			</script>