<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
	
		#mailbtn {
		-webkit-appearance: none;
		display: inline-block;
		text-decoration: none;
		cursor: pointer;
		border: 0;
		border-radius: 5px;
		background: #d52349;
		color: #fff !important;
		font-weight: 700;
		outline: 0;
		font-size: 1.1em;
		padding: 0.65em 1.5em 0.65em 1.5em;
		text-align: center;
		margin-top: 1em;
		margin-left: 13.5em;
	}
	
		#mailbtn:HOVER {
		background: #e53359;
	}
</style>
</head>
<body>
<form action="./chatSendAction.ch" method="post" name="fr">
<input type="hidden" name="t_num" value="${param.t_num }">
<h1>쪽지 보내기</h1>
<table>
	<tr>
		<td><input type="text" name="content" style="width: 87%; height: 30px;" placeholder="보내실 쪽지 내용을 여기에 작성하세요!">
		<button type="submit" style="height: 35px;">전송</button>
		</td>
	</tr>
	<tr>
		<td>아직 주고 받은 쪽지가 없습니다.</td>
	</tr>
</table>


</form>

</body>
</html>