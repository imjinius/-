<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">
function chat_get(){
	window.open("./chatList.ch","","width=470, height=450, resizable=no, scrollbars=no, status=no");

}
</script>
			<section id="header-log">
			<c:if test="${sessionScope.id == null }">
			<a href="./login.mem?back=./seastu.main">로그인</a>
			</c:if>
			<c:if test="${sessionScope.id != null && sessionScope.id != 'admin'}">
			<span style="margin-right: 1em;"><b>${sessionScope.id }님 환영합니다!</b></span>
			<a href="" onclick="chat_get();">쪽지함</a> &nbsp;&nbsp;||
			<a href="./memberUpdateCheck.mem">회원정보수정</a> &nbsp;&nbsp;||
			<a href="./logout.mem">logout</a> 
			</c:if>
			<c:if test="${sessionScope.id == 'admin' }">
			<i>관리자 계정</i>&nbsp;&nbsp;&nbsp;&nbsp;
			<a href="./memberManage.ad">사이트관리</a>&nbsp;&nbsp; || &nbsp;&nbsp;
			<a href="./logout.mem">logout</a>
			</c:if>
			</section>