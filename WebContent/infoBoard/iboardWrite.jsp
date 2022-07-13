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
			<script type="text/javascript">
			
			function writeCheck(){
			 	if(document.fr.title.value.length<1){
					alert("제목을 입력해주세요.");
					return false;
				} else if(document.fr.content.value.length<1){
					alert("내용을 입력해주세요.");
					return false;
				} else if (document.fr.category.selectedIndex == 0){
					alert("카테고리를 선택해주세요.");
			 		return false;
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
						<div>

								<!-- Content -->
										<section class="box-log">
										<header>
											<h3>글작성</h3>
										</header>
										<form action="./iboardWriteAction.bo" method="post" name="fr"
										 enctype="multipart/form-data" onsubmit="return writeCheck();">
										<table>
										<tr>
											<td>게시판</td>
											<td>
											<select name="category" >
												<option value="">카테고리를 선택하세요.</option>
												<option value="0">공모전</option>
												<option value="1">대외활동</option>
											</select>
											</td>
										</tr>
										<tr>
											<td>제목</td>
											<td><input type="text" size="25" name="title"></td>
										</tr>
										<tr>
											<td style="vertical-align: middle;">내용</td>
											<td><textarea cols="27" rows="15" name="content"></textarea></td>
										</tr>
										<tr>
											<td>이미지첨부</td>
											<td><input type="file" name="image" accept="image/*"></td>
										</tr>
										</table>
							
										<div id="table_search">
											<input type="submit" value="글쓰기" class="btn">
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