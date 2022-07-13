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
var i_num = "${dto.i_num}";
var file = "${dto.image}";

function deleteCheck(){
	if(confirm("정말로 삭제하시겠습니까?")){
		location.href="./iboardDelete.bo?i_num="+i_num+"&file="+file;
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
								<li class="current"><a href="./iboardList.bo">정보의 바다</a></li>
								<li><a href="./tradeList.tr">중고바다</a></li>
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
											<span>${dto.i_title }</span>
											</h2>
											<br>
											<fmt:formatDate var="reg_date" value="${dto.reg_date }" pattern="yyyy-MM-dd HH:mm:ss"/>
											<span>관리자</span> &nbsp;&nbsp;| &nbsp;&nbsp; <span>${reg_date }</span> &nbsp;&nbsp;| &nbsp;&nbsp; 
											<span>조회수 &nbsp;&nbsp; ${dto.readcount }</span>
										</div>
										
							<!-- content -->
							<div class="content">
									<img src="iBoardUpload/${dto.image }" border="300px" width="800px" height="900px"><br>
									<pre>${dto.i_content }</pre>
							</div>
							
							
							
							<!-- 추천 기능 -->
					<div>
					<b>첨부파일명 : <a href="./infoBoard/fileDown.jsp?fileName=${dto.image }">${dto.image }[다운로드]</a></b><br><hr>
					</div>		
					
					<br>		
							<div class="right_align">
							<button type="button" class="button search" onclick="history.back();">목록</button>
							<c:if test="${sessionScope.id == 'admin' }"> 
							<button type="submit" class="button search" onclick="location.href='./iboardUpdate.bo?i_num=${dto.i_num}';">수정</button>
							
							<button type="submit" class="button search" onclick="deleteCheck();">삭제</button>
							</c:if>
							</div>
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