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
		width: 100%;
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
	
	input {
		width: 57%;
		height: 50px;
		margin-left: 9.3em;
	}
	
	#chatbox {
		margin-left: 28.5em;
		margin-top: 5px;
	
	}
	
</style>
</head>
<body>
<form action="./chatReplyAction.ch" method="post" name="fr">
<input type="hidden" name="c_t_num" value="${c_t_num }">
	<c:if test="${sessionScope.id.equals(chatContents[0].sender) }">
	<input type="hidden" name="receiver" value="${chatContents[0].receiver }">
	</c:if>
	
	<c:if test="${sessionScope.id.equals(chatContents[0].receiver) }">
	<input type="hidden" name="receiver" value="${chatContents[0].sender }">
	</c:if>
<h1>쪽지 보내기</h1>
<input type="text" name="content" placeholder="보내실 쪽지 내용을 여기에 작성하세요!">
<button type="submit" style="height: 58px;">전송</button>
</form>
<table>
	<c:forEach var="chat" items="${chatContents }">
	<tr>
		<td>
		<fmt:formatDate var="send_date" value="${chat.send_date }" pattern="yyyy-MM-dd HH:mm:ss"/>
		<c:if test="${sessionScope.id.equals(chat.sender) }">
		<span class="send">보낸쪽지</span><span>${send_date }</span>
		</c:if>
		<c:if test="${sessionScope.id.equals(chat.receiver) }">
		<span class="rece">받은쪽지</span><span>${send_date }</span>
		</c:if>
		${chat.content }</td>
	</tr>
	</c:forEach>
</table>
<div id="chatbox">
<button type="button" onclick="location.href='./chatList.ch';">쪽지함</button>
</div>


</body>
</html>