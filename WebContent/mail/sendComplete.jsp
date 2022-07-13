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
		height: 400px;
	}
	
	h3{
		text-align: center;
	}
	td{
		border: 1px dotted gray; 
	}
	
</style>
<script type="text/javascript">
function mailCheck(){
	if(document.fr.sender.value.includes("@")){
		alert("'@'를 제외한 아이디만 입력해주세요.");
		return false;
	}
	
}
</script>
</head>
<body>
<table>
	<tr>
		<td><h3>문의주셔서 감사합니다.</h3><h3>빠른 시일내에 답변 드리겠습니다.</h3></td>
	</tr>
</table>
</body>
</html>