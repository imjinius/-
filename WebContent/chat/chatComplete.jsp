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
<table>
	<tr>
		<td><h3>쪽지를 보내는데 성공했습니다.</h3></td>
	</tr>
	
</table>
<div id="chatbox">
<button type="button" onclick="location.href='./chatList.ch';">쪽지함</button>
</div>

</body>
</html>