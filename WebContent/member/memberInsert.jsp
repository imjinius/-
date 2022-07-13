<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
	<head>
			<jsp:include page="../inc/head.jsp"/>
			<jsp:include page="../js/memInsertCheck.jsp"/>
	</head>
	<body class="left-sidebar is-preload">
		<div id="page-wrapper">

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
											<h3>회원가입</h3>
										</header>
										<form action="./memberInsertAction.mem" method="post" onsubmit="return insertCheck();" name="fr">
										id : <input type="text" name="id" style="display: inline-block; width: 40%;" placeholder="아이디 중복 검사를 해주세요."
										 id="input_id">
										<button type="button" class="insertme_btn">아이디 중복 검사</button><br>
										<span id="result"></span>
										<br><br>
										비밀번호 : <input type="password" name="pw" class="pw_input" required
										style="display: inline-block; width: 50%">
										<br>
										<span class="pw_input_re_1">사용가능한 비밀번호입니다.</span>
										<span class="pw_input_re_2">비밀번호는 영문 대소문자,숫자,특수기호의 조합으로 8~22자리를 입력해야합니다.</span>
										 <br><br>
										비밀번호확인 : <input type="password" name="pw2" class="pwck_input" required
										style="display: inline-block; width: 50%">
										 <span class="final_pwck_ck">비밀번호 확인을 입력해주세요.</span>
										 <span class="pwck_input_re_1">비밀번호가 일치합니다.</span>
										 <span class="pwck_input_re_2">비밀번호가 일치하지 않습니다.</span>
										  <br><br>
										이름 : <input type="text" name="name" class="input_name" required
										style="display: inline-block; width: 50%">
										 <span class="pw_input_re_2" id="nm_result"></span>
										  <br><br>
										생년월일 : <br> <input type="date" name="birth" required> <br><br>
										전화번호 : <br> <input type="text" name="tel" class="in-bl" style="width: 30%;" required><button class="insertme_btn">인증번호받기</button> <br><br> 
										
										주소 : <input id="member_post" type="text" name="zip_code" onclick="findAddr();" readonly> <button type="button" class="insertme_btn" onclick="findAddr();">우편번호</button>
										<input id="member_addr" type="text" name="addr" onclick="findAddr();" readonly>
										 <br><br>
										
										<input type="submit" value="회원가입하기" class="button search">
										</form>
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