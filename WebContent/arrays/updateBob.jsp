<%@page import="org.json.simple.JSONObject"%>
<%@page import="org.json.simple.JSONArray"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.DriverManager"%>
<%@page language="java" contentType="application/json; charset=UTF-8"
    pageEncoding="UTF-8"%>


<%

	Class.forName("com.mysql.cj.jdbc.Driver");
	Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/seastu","root","1234");
	String sql = "select * from seastu_board where bob=1 order by readcount desc";
	PreparedStatement pstmt = con.prepareStatement(sql);
	ResultSet rs = pstmt.executeQuery();
	
	
	JSONArray bobList = new JSONArray();
	while (rs.next()) {
		JSONObject obj = new JSONObject();
		
		obj.put("num", rs.getInt("num"));
		obj.put("title", rs.getString("title"));
		obj.put("id", rs.getString("id"));
		obj.put("thumb", rs.getInt("thumb"));
		obj.put("reg_date", rs.getTimestamp("reg_date")+"");
		obj.put("readcount", rs.getInt("readcount"));
		obj.put("replycount", rs.getInt("replycount"));
		obj.put("bob", rs.getInt("bob"));

		bobList.add(obj);
	}


%>
<%=bobList %>
