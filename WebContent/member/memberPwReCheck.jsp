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
			function loginCheck(){
				if(document.fr.pw.value<1){
					alert("비밀번호를 입력해주세요");
					return false;
				}
			}
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
											<h3>비밀번호 재확인</h3> <!-- num=0은 회원정보 업데이트 num=1은 비밀번호 변경 -->
											<p>본인확인을 위해 한번 더 비밀번호를 입력해 주세요.</p>
										</header>
										<c:if test="${param.num == 0 }">
										<form action="./memberPwCheck.mem" method="post" onsubmit="return loginCheck()" name="fr">
										<input type="hidden" name="update" value="true">
										비밀번호 : <input type="password" name="pw" placeholder="비밀번호를 입력하세요."> <br>
										<input type="submit" value="확인" >
										</form>
										</c:if>
										<c:if test="${param.num == 1 }">
										<form action="./memberPwCheck.mem" method="post" onsubmit="return loginCheck()" name="fr">
										<input type="hidden" name="pwupdate" value="true">
										비밀번호 : <input type="password" name="pw" placeholder="비밀번호를 입력하세요."> <br>
										<input type="submit" value="확인" >
										</form>
										</c:if>
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