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
			<jsp:include page="../js/memUpdateCheck.jsp"/>
			
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
											<h3>회원정보수정</h3>
										</header>
										<form action="./memberUpdatePro.mem?num=0" method="post">
										id : <input type="text" name="id" readonly value="${dto.id }"
										style="display: inline-block; width: 40%;"> 
										<br><br>
										이름 : <input type="text" name="name" value="${dto.name }" class="input_name" required
										style="display: inline-block; width: 50%">
										<span class="pw_input_re_2" id="nm_result"></span>
										  <br><br>
										생년월일 : <br> <input type="date" name="birth" value="${dto.birth }" required> <br><br>
										전화번호 : <br> <input type="text" name="tel" class="in-bl" style="width: 30%;" required value="${dto.tel }"> <button class="insertme_btn">인증번호받기</button> <br><br>
										주소 : <input id="member_post" type="text" name="zip_code" value="${dto.zip_code }" readonly required> <button type="button" class="insertme_btn" onclick="findAddr();">우편번호</button>
										<input id="member_addr" type="text" name="addr" value="${dto.addr }" readonly required>
										<br><br>
										<input type="submit" value="회원정보수정">
										</form>
										<hr>
										<a href="./memberDelete.mem" id="a-delete">회원탈퇴</a>
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