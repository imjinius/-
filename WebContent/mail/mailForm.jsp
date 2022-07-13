<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>메일 보내기</title>
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
<script type="text/javascript">
function mailCheck(){
	if(document.fr.sender.value.includes("@")){
		alert("'@'를 제외한 아이디만 입력해주세요.");
		return false;
	} else if(document.fr.sender.value.length<1){
		alert("아이디를 입력해 주세요.");
		return false;
	}
	
}
</script>
</head>
<body>
<form action="./SendMailAction.ma" method="post" onsubmit="return mailCheck();" name="fr">
<h1>무엇이 궁금하세요?</h1>
<table>
	<tr>
		<td>보내는 사람 : </td><td><input type="text" name="sender" placeholder="아이디만 입력해주세요.">@gamil.com</td>
	</tr>
	<tr>
		<td>제목 : </td><td><input type="text" name="subject" ></td>
	</tr>
	<tr>
		<td>내용 : </td><td><textarea rows="20" cols="40" name="content"></textarea> </td>
	</tr>
</table>
		<input type="submit" value="보내기" id="mailbtn">


</form>

</body>
</html>