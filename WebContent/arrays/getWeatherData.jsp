<?xml version="1.0" encoding="UTF-8"?>
<%@ page language="java" contentType="text/xml; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*, javax.sql.*, java.io.*, java.net.*,java.text.SimpleDateFormat,java.time.*,java.util.*,org.json.simple.JSONArray,org.json.simple.JSONObject,org.json.simple.parser.JSONParser,org.json.simple.parser.ParseException"%>
<%

class Loca {
	String region;
	String nx;
	String ny;
}

		// 날짜관련 변수
		Calendar todayCal = Calendar.getInstance();
		Calendar yesCal = Calendar.getInstance();
		String format = "yyyyMMdd"; //20220308 형식으로 받아오기
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		yesCal.add(yesCal.DATE, -1); //날짜를 하루 뺀다.
		String yesDate = sdf.format(yesCal.getTime()); // 어제 날짜
		String toDate = sdf.format(todayCal.getTime()); // 오늘 날짜
		String baseDate = toDate;
		
		String type = "json";	//조회하고 싶은 type(json, xml 중 고름)
		
		// 시간계산
		int[] hours_li = {2,5,8,11,14,17,20,23};
 	
		// 시간관련 변수
		int hour = 0;	//계산결과변수
		String baseTime = ""; //조회하고 싶은 시간
		LocalTime tnow = LocalTime.now();
 		int hourValue = tnow.getHour();
 		
		 	for(int i=0;i<hours_li.length;i++){
		 		int h = hours_li[i]-hourValue;
		 		if(h==-2 || h==-1 || h==0){
		 			hour = hours_li[i];
		 		} else if(hourValue==0 || hourValue==1){// 00시나 01시일 경우 전날 23시로 시간 조회
		 			hour = hours_li[7];
		 			baseDate = yesDate;
		 		}
		 	}
	 	
	 	if(hour<10){
				baseTime = "0"+hour+"00";
			} else {
				baseTime = hour+"00";
			}
 	
			// 위치 정보 가져오기
			List location = new ArrayList();
			
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/seastu","root","1234");
			Statement stmt = con.createStatement();
			
			ResultSet rset = stmt.executeQuery("select * from seastu_loca");
			
			while(rset.next()){
				Loca loca = new Loca();
				loca.region = rset.getString("region");
				loca.nx = rset.getString("nx");
				loca.ny = rset.getString("ny");
				
				location.add(loca);
			}
			
			rset.close();
			stmt.close();
			con.close();
     
     out.println("<weather>");
//   지역별로 날짜 정보 받아오기
     for(int l=0;l<location.size();l++){
     	Loca locainfo = new Loca(); 
     	locainfo = (Loca) location.get(l); // 지역정보 객체에 담아서 전국 날씨 정보 가져오기
     	
     	String Region = locainfo.region;
     	String nx = locainfo.nx;
     	String ny = locainfo.ny;
     
//		참고문서에 있는 url주소
     String apiUrl = "http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getVilageFcst";
//      홈페이지에서 받은 키
     String serviceKey = "o5qKZe2j9oe7vmdAxcZZN0KcEuO3a1rxqX%2Bcb4UO4BNvR7DiZsBK47AMFktXFNWu9%2BaaPKhVoKGLksmQ2iVN3Q%3D%3D";

     StringBuilder urlBuilder = new StringBuilder(apiUrl);
     
     urlBuilder.append("?" + URLEncoder.encode("ServiceKey","UTF-8") + "="+serviceKey);
     urlBuilder.append("&" + URLEncoder.encode("base_date","UTF-8") + "=" + URLEncoder.encode(baseDate, "UTF-8")); /* 조회하고싶은 날짜*/
     urlBuilder.append("&" + URLEncoder.encode("base_time","UTF-8") + "=" + URLEncoder.encode(baseTime, "UTF-8")); /* 조회하고싶은 시간 AM 02시부터 3시간 단위 */
     urlBuilder.append("&" + URLEncoder.encode("dataType","UTF-8") + "=" + URLEncoder.encode(type, "UTF-8"));	/* 타입 */
     urlBuilder.append("&" + URLEncoder.encode("nx","UTF-8") + "=" + URLEncoder.encode(nx, "UTF-8")); //경도
     urlBuilder.append("&" + URLEncoder.encode("ny","UTF-8") + "=" + URLEncoder.encode(ny, "UTF-8")); //위도
     //System.out.println("baseDate : "+baseDate);
     //System.out.println("baseTime : "+baseTime);
     //System.out.println("nx : "+nx);
     //System.out.println("ny : "+ny);
     
     
     for(int j=0;j<1;j++){
    	 
     
     /*
      * GET방식으로 전송해서 파라미터 받아오기
      */
     URL url = new URL(urlBuilder.toString());
     //System.out.println("url: "+url);

     HttpURLConnection conn = (HttpURLConnection) url.openConnection();
     conn.setRequestMethod("GET");
     conn.setRequestProperty("Content-type", "application/json");
     //System.out.println(conn.getResponseCode());
     
     BufferedReader rd;
     if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
         rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
     } else {
         rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
     }
     
     StringBuilder sb = new StringBuilder();
     String line;
     while ((line = rd.readLine()) != null) {
         sb.append(line);
     }
     rd.close();
     conn.disconnect();
     String result= sb.toString();
     //System.out.println(result);
     

	//=======이 밑에 부터는 json에서 데이터 파싱해 오는 부분이다=====//
  // Json parser를 만들어 만들어진 문자열 데이터를 객체화 
  		JSONParser parser = new JSONParser(); 
  		JSONObject obj = (JSONObject) parser.parse(result); 
  		// response 키를 가지고 데이터를 파싱 
  		JSONObject parse_response = (JSONObject) obj.get("response"); 
  		// response 로 부터 body 찾기
  		JSONObject parse_body = (JSONObject) parse_response.get("body"); 
  		// body 로 부터 items 찾기 
  		JSONObject parse_items = (JSONObject) parse_body.get("items");

  		// items로 부터 itemlist 를 받기 
  		JSONArray parse_item = (JSONArray) parse_items.get("item");
  		String category;
  		JSONObject weather; // parse_item은 배열형태이기 때문에 하나씩 데이터를 하나씩 가져올때 사용
  		// 카테고리와 값만 받아오기
  		out.println("<info>");
  		out.println("<region>"+Region+"</region>");
  		for(int i = 0 ; i < parse_item.size(); i++) {
  			weather = (JSONObject) parse_item.get(i);
  			Object fcstValue = weather.get("fcstValue");
  			//double형으로 받고싶으면 아래내용 주석 해제
  			//double fcstValue = Double.parseDouble(weather.get("fcstValue").toString());
  			category = (String)weather.get("category"); 
  			// 출력
  			if(category.equals("TMP")){
  				out.println("<TMPValue>"+fcstValue+"</TMPValue>");
  			} else if(category.equals("PTY")){
  				out.println("<PTYValue>"+fcstValue+"</PTYValue>");
  			} else if(category.equals("SKY")){
  				out.println("<SKYValue>"+fcstValue+"</SKYValue>");
  			}
  			
  			}
  				
  				out.println("</info>");
  				urlBuilder.delete(0, urlBuilder.length());
     	}
     }//loca for문 끝
     
     out.println("</weather>");

  		/*
  		 * 항목값	항목명	단위
  		 * POP	강수확률	 %
  		 * PTY	강수형태	코드값
  		 * R06	6시간 강수량	범주 (1 mm)
  		 * REH	습도	 %
  		 * S06	6시간 신적설	범주(1 cm)
  		 * SKY	하늘상태	코드값
  		 * TMP	1시간 기온	℃
  		 * T3H	3시간 기온	 ℃
  		 * TMN	아침 최저기온	 ℃
  		 * TMX	낮 최고기온	 ℃
  		 * UUU	풍속(동서성분)	 m/s
  		 * VVV	풍속(남북성분)	 m/s
  		 * WAV	파고	 M
  		 * VEC	풍향	 m/s
  		 * WSD	풍속	1

  		 */




%>