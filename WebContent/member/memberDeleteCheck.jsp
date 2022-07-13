<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>

<html>
	<head>
			<script type="text/javascript">
			function loginCheck(){
				if(document.fr.pw.value.length<1){
					alert("비밀번호를 입력하세요!");
					return false;
				} 
			}
			
			</script>
			<jsp:include page="../inc/head.jsp"/>
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
											<h3>회원탈퇴</h3>
										</header>
										<%-- <%String delete = "true"; %> --%>
										<c:set var="delete" value="true" />
										<form action="./memberPwCheck.mem" method="post" onsubmit="return loginCheck()" name="fr">
										<input type="hidden" name="delete" value="true">
										비밀번호 : <input type="password" name="pw" placeholder="비밀번호를 입력하세요."> <br>
										<input type="submit" value="확인" >
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