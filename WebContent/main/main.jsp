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
	<jsp:include page="../js/getMainInfo.jsp"/>
	</head>
	<body class="homepage is-preload">
		<div id="page-wrapper">

			<!-- Header -->
			<jsp:include page="../inc/log.jsp"></jsp:include>
				<section id="header">
					<!-- Logo -->
						<h1><a href="./seastu.main">대학생의바다</a></h1>

					<!-- Nav -->
						<nav id="nav">
							<ul>
								<li class="current"><a href="./seastu.main">Home</a></li>
								<li><a href="./iboardList.bo">정보의 바다</a></li>
								<li><a href="./tradeList.tr">중고바다</a></li>
								<li><a href="./boardList.bo">자유의 바다</a></li>
							</ul>
						</nav>

					<!-- Banner -->
						<section id="banner">
							<header>
								<h2>대학생을 위한 정보의 바다</h2>
								<p>Dictionary for College Student</p>
							</header>
						</section>
					</section>	

					<!-- Intro -->
					<section class="seastu_main">
					<div class="m_bob">
									<section>
										<header class="main_bob">
										<h3 class="m_title">Best 게시글 ${dto.num }</h3>
										<table class="bob">
										</table>
										</header>
									</section>
								</div>
					</section>
					<section class="seastu_main2">
					<div class="row">
												<section class="main_box">
												<h3 class="m_title">전국 날씨</h3>
													<ul class="wt">
													</ul>
												</section>
												<section class="main_box">
												<h3 class="m_title">나가기 전 체크!</h3>
												<header>
													<img src="./images/clothInfo.jpg" width="400" height="600" >
												</header>
												</section>
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