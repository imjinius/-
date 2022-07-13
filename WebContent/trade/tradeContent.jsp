<%@page import="org.apache.commons.io.FilenameUtils"%>
<%@page import="seastu.board.db.iBoardDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE HTML>
<html>
<head>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<jsp:include page="../inc/head.jsp"/>
<script type="text/javascript">
var t_num = "${dto.t_num}";
var file = "${dto.file}";
var id = "${sessionScope.id}";

function chat_send(){
	if(id != ""){
		window.open("./chatSend.ch?t_num="+t_num,"","width=470, height=450, resizable=no, scrollbars=no, status=no");
	} else {
		alert("로그인이 필요한 서비스입니다.");
		location.href="./login.mem?back=./tradeContent.tr?t_num="+t_num;
	}

}

function deleteCheck(){
	if(confirm("정말로 삭제하시겠습니까?")){
		location.href="./tradeDelete.tr?t_num="+t_num+"&file="+file;
	}
}
</script>
	</head>
	<body class="no-sidebar is-preload">
		<div id="page-wrapper">

			<!-- Header -->
			<jsp:include page="../inc/log.jsp"></jsp:include>
				<section id="header">

					<!-- Logo -->
						<h1><a href="./seastu.main">대학생의바다</a></h1>

					<!-- Nav -->
						<nav id="nav">
							<ul>
								<li><a href="./seastu.main">Home</a></li>
								<li><a href="./iboardList.bo">정보의 바다</a></li>
								<li class="current"><a href="./tradeList.tr">중고바다</a></li>
								<li><a href="./boardList.bo">자유의 바다</a></li>
							</ul>
						</nav>

				</section>

			<!-- Main -->
				<section id="main">
					<div class="container">
						<div class="row">
							<div class="col-4 col-12-medium">

							</div>
							<div class="col-12">

								<!-- 글 내용 -->
										<div class="content">
											<h2>
											<span>${dto.title }</span>
											</h2>
											<br>
											<fmt:formatDate var="reg_date" value="${dto.reg_date }" pattern="yyyy-MM-dd hh:mm:ss"/>
											<span>${reg_date }</span>
										</div>
										
							<!-- content -->
							<div class="content">
									<img src="tradeUpload/${dto.file }" border="300px" width="200px" height="300px"><br>
									<pre>${dto.content }</pre>
							</div>
							<c:if test="${dto.status == 0 }">
							<div>
							<c:if test="${!dto.seller.equals(sessionScope.id) && result == 0 }"> <!-- result=0은 쪽지보낸적 없는것 -->
							<button type="button" onclick="chat_send();">판매자에게 쪽지 보내기</button>
							</c:if>
							<c:if test="${!dto.seller.equals(sessionScope.id) && result == 1 }">
							<h4>판매자에게 문의 완료</h4>
							</c:if>
							</div>
							</c:if>
							<c:if test="${dto.status == 1 }">
							<h4>이 책은 판매가 완료되었습니다.</h4>
							</c:if>
							<!-- 추천 기능 -->
							<br>		
							<c:if test="${dto.seller.equals(sessionScope.id) }">
							<div class="right_align">
							<button type="submit" class="button search" onclick="location.href='./tradeUpdate.tr?t_num=${dto.t_num}';">수정</button>
							<button type="submit" class="button search" onclick="deleteCheck();">삭제</button>
							</div>
							</c:if>
				</div>
				</div>
				
			</div>
				</section>
							<div class="col-6 col-12-medium">
							</div>
						</div>
			<!-- Footer -->
				<section id="footer">
					<jsp:include page="../inc/bottom.jsp"></jsp:include>
				</section>

		<!-- Scripts -->
			<jsp:include page="../inc/script.jsp"/>
	</body>
</html>