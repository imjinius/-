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
			<script type="text/javascript">
			function loginCheck(){
				if(document.fr.id.value.length<1){
					alert("아이디를 입력하세요!");
					return false;
				} else if(document.fr.pw.value.length<1){
					alert("비밀번호를 입력하세요!");
					return false;
				}
			}
			
			</script>
	</head>
	<body class="left-sidebar is-preload">
		<div id="page-wrapper">

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
											<h3>로그인</h3>
										</header>
										<form action="./memberCheck.mem" method="post" onsubmit="return loginCheck()" name="fr">
										<input type="hidden" name="back" value="${param.back }">
										아이디 : <input type="text" name="id"> <br>
										비밀번호 : <input type="password" name="pw"> <br><br>
										<input type="submit" value="로그인" >
										</form>
										<hr>
										<button type="submit" onclick="location.href='./memberInsert.mem';">회원가입하기</button>
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