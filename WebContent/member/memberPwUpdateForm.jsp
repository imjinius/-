<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<!--
	Dopetrope by HTML5 UP
	html5up.net | @ajlkn
	Free for personal and commercial use under the CCA 3.0 license (html5up.net/license)
-->
<html>
	<head>
			<jsp:include page="../inc/head.jsp"/>
			<jsp:include page="../js/memPwUpdateCheck.jsp"/>
	</head>
	<body class="left-sidebar is-preload">
		<div id="page-wrapper">
		<jsp:include page="../inc/log.jsp"></jsp:include>

			<!-- Header -->
				<section id="header">
					<!-- Logo -->
						<h1><a href="./seastu.main">대학생의바다</a></h1>
				</section>

			<!-- Main -->
				<section id="main">
					<div class="container">
						<div>

								<!-- Content -->
										<section class="box-log">
										<header>
											<h3>비밀번호변경</h3>
										</header>
										<form action="./memberUpdatePro.mem?num=1" method="post" onsubmit="return updateCheck();">
										비밀번호 : <input type="password" name="pw" class="pw_input" 
										style="display: inline-block; width: 50%">
										<br>
										<span class="pw_input_re_1">사용가능한 비밀번호입니다.</span>
										<span class="pw_input_re_2">비밀번호는 영문 대소문자,숫자,특수기호의 조합으로 8~20자리를 입력해야합니다.</span>
										 <br><br>
										비밀번호확인 : <input type="password" name="updatePw" class="pwck_input"
										style="display: inline-block; width: 50%">
										<br>
										 <span class="final_pwck_ck">비밀번호 확인을 입력해주세요.</span>
										 <span class="pwck_input_re_1">비밀번호가 일치합니다.</span>
										 <span class="pwck_input_re_2">비밀번호가 일치하지 않습니다.</span>
										  <br><br>
										<input type="submit" value="비밀번호변경">
										</form>
										<hr>
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
			<jsp:include page="../inc/script.jsp"></jsp:include>

	</body>
</html>