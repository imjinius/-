<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
	<head>
			<jsp:include page="../inc/head.jsp"/>
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
								<li><a href="./iboardList.bo">정보의 바다</a></li>
								<li><a href="./tradeList.tr">중고바다</a></li>
								<li class="current"><a href="./boardList.bo">자유의 바다</a></li>
							</ul>
						</nav>
				</section>

			<!-- Main -->
				<section id="main">
					<div class="container">
						<div>

								<!-- Content -->
										<section class="box-log">
										<header>
											<h3>글 수정</h3>
										</header>
										<form action="./boardUpdateProAction.bo" method="post" name="fr"
										 enctype="multipart/form-data">
										 <input type="hidden" value="${boardContent.num }" name="num">
										 <input type="hidden" value="${boardContent.file }" name="oFile">
										<table>
										<tr>
											<td>닉네임</td>
											<td><input type="text" size="25" name="uName" value="${boardContent.name }"></td>
										</tr>
										<tr>
											<td>제목</td>
											<td><input type="text" size="25" name="uTitle" value="${boardContent.title }"></td>
										</tr>
										<tr>
											<td style="vertical-align: middle;">내용</td>
											<td><textarea cols="27" rows="15" name="uContent">${boardContent.content }</textarea></td>
										</tr>
										<tr>
											<td>파일 변경하기</td>
											<td><input type="file" name="uFile"></td>
										</tr>
										</table>
							
										<div id="table_search">
											<input type="submit" value="글수정하기" class="btn">
										</div>
							
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