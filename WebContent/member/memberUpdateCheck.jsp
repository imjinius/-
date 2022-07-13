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
			<script type="text/javascript">
			
			</script>
			<jsp:include page="../inc/head.jsp"/>
	</head>
	<body class="left-sidebar is-preload">
		<div id="page-wrapper">
	<jsp:include page="../inc/log.jsp"></jsp:include>

			<!-- Header -->
			<section id="header">
		<div class="clear"></div>
					<!-- Logo -->
						<h1><a href="./seastu.main">대학생의바다</a></h1>

				</section>

			<!-- Main -->
				<section id="main">
					<div class="container">
						<div class="row">
							<div class="col-4box col-12-medium">


							</div>

								<!-- Content -->
										<section class="box-log">
										<header>
											<h3>회원정보수정</h3>
										</header>
										<a href="./memberPwReCheck.mem?num=0" class="makebox">
											<span><strong>최신정보로 변경</strong></span>
										</a>
										<a href="./memberPwReCheck.mem?num=1" class="makebox">
											<span><strong>비밀번호 변경</strong></span>
										</a>
										
										
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