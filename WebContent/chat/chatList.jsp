<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>쪽지 보내기</title>
<style type="text/css">
	table {
		width: 450px;
		margin: auto;
	}
	
	h1{
		text-align: center;
	}
	td{
		border: 1px dotted gray; 
	}
	
	.send {
		display: inline-block;
		color: #ffcc00;
		font-weight: bold;
		margin: 0;
		width: 66%;
	}
	
	.rece {
		display: inline-block;
		color: #009999;
		font-weight: bold;
		margin: 0;
		width: 66%;
	}
	
</style>
</head>
<body>
<h1>쪽지함</h1>
<table>
	<c:forEach var="list" items="${chatLists }">
		<tr>
		<td>
		<fmt:formatDate var="send_date" value="${list.send_date }" pattern="yyyy-MM-dd HH:mm:ss"/>
		<c:if test="${sessionScope.id.equals(list.sender) }"> <!-- session 영역에서 id를 받아서 sender인지 receiver 인지에 따라서 구별해줌 -->
		<a href="./chatContent.ch?talker=${list.receiver }&c_t_num=${list.c_t_num}"><span class="send">${list.content }</span></a><span>${send_date }</span>
		</c:if>
		<c:if test="${sessionScope.id.equals(list.receiver) }">
		<a href="./chatContent.ch?talker=${list.sender }&c_t_num=${list.c_t_num}"><span class="rece">${list.content }</span></a><span>${send_date }</span>
		</c:if>
		</td>
	</tr>
	</c:forEach>
</table>

</body>
</html>