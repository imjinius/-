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
			
			function writeCheck(){
			 	if(document.fr.uTitle.value.length<1){
					alert("제목을 입력해주세요.");
					return false;
				} else if(document.fr.uContent.value.length<1){
					alert("내용을 입력해주세요.");
					return false;
				} else if (document.fr.uCategory.selectedIndex == 0){
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
											<h3>글수정</h3>
										</header>
										<form action="./iboarUpdateProAction.bo" method="post" name="fr"
										 enctype="multipart/form-data" onsubmit="return writeCheck();">
										 <input type="hidden" name="oFile" value="${dto.image }">
										 <input type="hidden" name="i_num" value="${dto.i_num }">
										<table>
										<tr>
											<td>게시판</td>
											<td>
											<select name="uCategory" >
												<option value="">카테고리를 선택하세요.</option>
												<option value="0"
												<c:if test="${dto.category == 0 }">
												selected
												</c:if>
												>공모전</option>
												<option value="1"
												<c:if test="${dto.category == 1 }">
												selected
												</c:if>
												>대외활동</option>
											</select>
											</td>
										</tr>
										<tr>
											<td>제목</td>
											<td><input type="text" size="25" name="uTitle" value="${dto.i_title }"></td>
										</tr>
										<tr>
											<td style="vertical-align: middle;">내용</td>
											<td><textarea cols="27" rows="15" name="uContent">${dto.i_content }</textarea></td>
										</tr>
										<tr>
											<td>이미지첨부</td>
											<td><input type="file" name="uImage" accept="image/*"></td>
										</tr>
										</table>
							
										<div id="table_search">
											<input type="submit" value="수정" class="btn">
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