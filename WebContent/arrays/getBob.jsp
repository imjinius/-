<?xml version="1.0" encoding="UTF-8"?>
<%@ page language="java" contentType="text/xml; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*, javax.sql.*, java.io.*, java.net.*"%>

<% 
//db 연결
	Class.forName("com.mysql.jdbc.Driver");
	Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/seastu","root","1234");
	String sql = "select num,title,replycount from seastu_board where bob=1";
	PreparedStatement pstmt = con.prepareStatement(sql);
	
	ResultSet rs = pstmt.executeQuery();

	// xml 형식으로 출력하기
		out.println("<board>");
		while(rs.next()){
			out.println("<bob>");
			
			out.println("<num>"+rs.getInt("num")+"</num>");
			out.println("<title>"+rs.getString("title")+"</title>");
			out.println("<replycount>"+rs.getInt("replycount")+"</replycount>");
			
			out.println("</bob>");
		}
		out.println("</board>");
		
	    // db 연결 닫아주기
		rs.close();
		pstmt.close();
		con.close();

%>    

    
    
    
    