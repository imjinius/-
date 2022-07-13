<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<script src="./jq/jquery-3.6.0.js"></script>
	<script type="text/javascript">
	$(function(){
		
		 $.ajax({
			url: "./arrays/getWeatherData.jsp",
			success: function (data) {
				$(data).find('info').each(function(i,o){
					$('.wt').append('<li class="weather_li'+i+'"></li>');
					var region = $(this).find('region').text();
					var temp = $(this).find('TMPValue').text();
					var rain = $(this).find('PTYValue').text();
					var sky = $(this).find('SKYValue').text();
					
					$('.weather_li'+i).html(temp+"℃");
					$('.weather_li'+i).prepend(region+"&nbsp;&nbsp&nbsp;&nbsp;");
					
					
					if(rain!=0){
						switch(rain){
						case '1':
							$('.weather_li'+i).append(" / 비");
							$('.weather_li'+i).prepend('<i class="fa-solid fa-cloud-rain"></i>&nbsp;&nbsp;');
							break;
						case '2':
							$('.weather_li'+i).append(" / 비/눈");
							$('.weather_li'+i).prepend('<i class="fa-solid fa-cloud-meatball"></i>&nbsp;&nbsp;');
							break;
						case '3':
							$('.weather_li'+i).append(" / 눈");
							$('.weather_li'+i).prepend('<i class="fa-solid fa-snowflake"></i>&nbsp;&nbsp;');
							break;
						case '4':
							$('.weather_li'+i).append(" / 소나기");
							$('.weather_li'+i).prepend('<i class="fa-solid fa-cloud-showers-heavy"></i>&nbsp;&nbsp;');
							break;
						}
						
					} else {
						switch(sky){
						case '1':
							$('.weather_li'+i).append(" / 맑음");
							$('.weather_li'+i).prepend('<i class="fa-solid fa-sun"></i>&nbsp;');
							break;
						case '3':
							$('.weather_li'+i).append(" / 구름많음");
							$('.weather_li'+i).prepend('<i class="fa-solid fa-cloud-sun"></i>&nbsp;');
							break;
						case '4':
							$('.weather_li'+i).append(" / 흐림");
							$('.weather_li'+i).prepend('<i class="fa-solid fa-cloud"></i>&nbsp;');
							break;
						}
					}
					
				});
			},//success
			error: function(){
				alert("날씨 정보 가져오기 실패");
			}
		}); //ajax
		
		
		
		$.ajax({
			url: "./arrays/getBob.jsp",
            success: function (data) {
            	$(data).find('bob').each(function(){
            		var num = $(this).find('num').text();
            		var title = $(this).find('title').text();
            		var replycount = $(this).find('replycount').text();
            		
            		$('.bob').append(function() {
        				return "<tr> <td><a href='./boardContent.bo?num="+num+"'>" + title +"</a></td><td>["+ replycount + "]</td></tr>";
        			});
            		
            	});
            	
            }//success
			
		});
		
		
		
	});//jquery
	
	
	</script>