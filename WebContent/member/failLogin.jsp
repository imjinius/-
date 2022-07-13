<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script type="text/javascript">
var result = "${param.result}";

if(result == "0"){
	alert("비밀번호가 틀렸습니다.");
	history.back();
} else if (result == "-1"){
	alert("아이디 혹은 비밀번호가 틀렸습니다.");
	history.back();
}
</script>
