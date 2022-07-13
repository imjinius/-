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
		<jsp:include page="../js/adminManageCheck.jsp"/>
		
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
								<li><a href="./boardList.bo">자유의 바다</a></li>
							</ul>
						</nav>

				</section>

			<!-- Main -->
				<section id="main">
					<div class="container">
					<article class="box post" style="font-size: 16px; margin: 0;">

								<!-- Sidebar -->


								<!-- Content -->
								
										<header>
											<h2 class="right_align">회원 관리</h2>
										</header>
									<section class="box" style="">
										<nav class="menubar">
										<ul id="menu">
											<li><a href="./memberManage.ad">회원관리</a></li>
											<li><a href="./boardManage.ad">게시판관리</a></li>
										</ul>
										</nav>
									</section>
											<!-- Table -->
													<div class="table-wrapper">
														<table>
															<thead>
																<tr>
																	<th><input type="checkbox" class="check-all" ></th>
																	<th>아이디</th>
																	<th>이름</th>
																	<th>생년월일</th>
																	<th>전화번호</th>
																	<th>주소</th>
																	<th>작성글</th>
																	<th>댓글</th>
																	<th>가입일자</th>
																	<th>수정</th>
																</tr>
															</thead>
															<thead>
															<c:forEach var="dto" items="${list }">
																<tr>
																	<td><input type="checkbox" class="mems" name="mems" value="${dto.id }"></td>
																	<td>${dto.id }</td>
																	<td>${dto.name }</td>
																	<td>${dto.birth }</td>
																	<td>${dto.tel }</td>
																	<td>${dto.addr }</td>
																	<td>${dto.w_count}개</td>
																	<td>${dto.re_count }개</td>
																	<fmt:formatDate var="reg_date" value="${dto.reg_date }" pattern="yyyy-MM-dd hh:mm:ss"/>
																	<td>${reg_date }</td>
																	<td><button class="button manage" onclick="updateCheck('${dto.id}');">수정</button></td>
																</tr>
																</c:forEach>
															</thead>
														</table>
													</div>
							
							<form action="./memberSearch.ad" method="get" class="search_box" name="fr" onsubmit="return searchCheck();">
							<select name="s_keyword">
								<option value="">검색하고 싶은 항목을 선택하세요.</option>
								<option value="1">아이디</option>
								<option value="2">이름</option>
							</select>
							<input type="text" name="s_content" >
							<input type="submit" class="button manage" value="조회">
							</form>
							<div class="right_align">
							<span><button type="button" id="del" class="button manage" onclick="deleteData();">삭제</button></span>
							</div>
													
													
													<div class="listnum">
													<c:if test="${result != 0 }">
														<c:if test="${startPage>pageBlock }">
														<a href="./memberManage.ad?pageNum=${startPage-pageBlock }">◀</a>
														</c:if>
														
														<c:forEach begin="${startPage }" end="${endPage }" step="1" var="i">
														<a href="./memberManage.ad?pageNum=${i }">${i }</a>
														</c:forEach>
														<c:if test="${endPage<pageCount }">
														<a href="./memberManage.ad?pageNum=${startPage+pageBlock }">▶</a>
														</c:if>
													</c:if>	
													</div>
											<br>
											</article>
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