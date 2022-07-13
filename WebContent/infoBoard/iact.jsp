<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<!--
	Dopetrope by HTML5 UP
	html5up.net | @ajlkn
	Free for personal and commercial use under the CCA 3.0 license (html5up.net/license)
-->
<html>
	<head>
			<jsp:include page="../inc/head.jsp"/>
			<script src="./jq/jquery-3.6.0.js"></script>
			<script type="text/javascript">
			
			function idCheck(){
				var id= '<%=(String) session.getAttribute("id") %>';
				
				if(id == "admin"){
					location.href="./iboardWrite.bo";
				} else {
					alert("실행할 수 없는 동작입니다.");
					location.href = "./seastu.main";
				}
				
			}
			
			</script>
	</head>
	<body class="left-sidebar is-preload">
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

								<!-- Sidebar -->
										<nav class="menubar">
										<ul id="menu">
											<li><a href="./iboardList.bo">공모전</a></li>
											<li><a href="./iboardList.bo?cate=1">대외활동</a></li>
										</ul>
										</nav>

							<section id="main">
					<div class="container">
					<article class="box post">

								<!-- Content -->
								
									<div class="info-wrapper" id="gong">
										<header>
											<h2>대외활동</h2>
											<p>지금 바로 참여해보세요!</p>
										</header>
										<c:forEach var="act" items="${act }">
													<ul class="info-list">
														 <li>
															<a href="./iboardContent.bo?i_num=${act.i_num }" class="i_list">
																<span><img src="iBoardUpload/t-${act.image }"></span>
															</a>
														</li> 
													</ul>
										</c:forEach>
									
														<div style="width: 100%; text-align: center;">
														<c:if test="${pageNum != 1 }">
														<a href="iboardList.bo?pageNum=${pageNum-1 }&cate=1" class="pre">◀</a>
														</c:if>
														<c:if test="${pageNum != pageCount }">
														<a href="iboardList.bo?pageNum=${pageNum+1 }&cate=1">▶</a>
														</c:if>
														</div>
													<br>
											</div>
											
												
											<c:set var="id" value="${sessionScope.id }"/>
											<c:if test="${id == 'admin' }">
											<div class="right_align">	
											<button type="submit" class="button search" onclick="idCheck()">글쓰기</button>
											</div>	
											</c:if> 
											
											</article>
											<!-- Footer -->
											</div>
											</section>
											<div class="col-6 col-12-medium">
							</div>
						</div>
					</div>
				</section>

			<!-- Footer -->
				<section id="footer">
					<jsp:include page="../inc/bottom.jsp"></jsp:include>
				</section>

		</div>

		<!-- Scripts -->
				<jsp:include page="../inc/script.jsp"/>

	</body>
</html>