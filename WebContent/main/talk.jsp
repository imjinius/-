<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
		
		function idCheck(){
			var id= '<%=(String) session.getAttribute("id") %>';
			
			if(id == "null"){
				alert("로그인 후 작성 가능합니다.")
				location.href="./login.mem?back=./boardWrite.bo";
			} else {
				location.href = "./boardWrite.bo";
			}
			
		}
		
		function searchCheck(){
			
			if(document.fr.search.value.length<1){
				alert("검색어를 입력해주세요.")
				return false;
			} else {
				return true;
			}
		}
		</script>
	</head>
	<body class="no-sidebar is-preload">
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
					<article class="box post">

								<!-- Content -->
								
										<a href="#" class="image featured"><img src="images/pic01.jpg" alt="" /></a>
										<header>
											<h2>자유게시판</h2>
											<p>그냥 떠드는 공간</p>
										</header>
											<!-- Table -->
													<div class="table-wrapper">
														<table>
															<thead>
																<tr>
																	<th>No.</th>
																	<th>글제목</th>
																	<th>글쓴이</th>
																	<th>작성일자</th>
																	<th>조회수</th>
																</tr>
															</thead>
															<thead>
															<c:forEach var="dto" items="${boardList }">
																<tr>
																	<td>${dto.num }</td>
																	<td width="350">
																	<a href="./boardContent.bo?num=${dto.num }">${dto.title } [${dto.replycount }]
																	<c:if test="${dto.file != null }">
																	<img src="./css/images/save.png" width="15">
																	</c:if>
																	</a>
																	</td>
																	<td>${dto.name }</td>
																	<fmt:formatDate var="reg_date" value="${dto.reg_date }" pattern="yyyy-MM-dd HH:mm:ss"/>
																	<td>${reg_date }</td>
																	<td>${dto.readcount }</td>
																</tr>
																</c:forEach>
															</thead>
														</table>
													</div>
													<div class="listnum">
													<c:if test="${result != 0 }">
														<c:if test="${startPage>pageBlock }">
														<a href="boardList.bo?pageNum=${startPage-pageBlock }">◀</a>
														</c:if>
														
														<c:forEach begin="${startPage }" end="${endPage }" step="1" var="i">
														<a href="boardList.bo?pageNum=${i }">${i }</a>
														</c:forEach>
														<c:if test="${endPage<pageCount }">
														<a href="boardList.bo?pageNum=${startPage+pageBlock }">▶</a>
														</c:if>
													</c:if>	
													</div>
											<br>
											<form action="./boardSearch.bo" class="right_align" 
											onsubmit="return searchCheck();" name="fr" method="post">
											<input type="hidden" value="${result }" name="result">
											<input type="text" name="search" placeholder="글제목으로 검색" id="text-box">
											<input type="submit" value="검색" class="button search"> 
											</form>
											<div class="right_align">	
											<button type="submit" class="button search" onclick="idCheck()">글쓰기</button>
											</div>	
											</article>
											<!-- Footer -->
											</div>
											</section>
											<section id="footer">
												<jsp:include page="../inc/bottom.jsp"></jsp:include>
											</section>
			</div>

		<!-- Scripts -->
			<jsp:include page="../inc/script.jsp"/>

	</body>
</html>