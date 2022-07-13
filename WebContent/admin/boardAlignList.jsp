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

								<!-- Content -->
								
										<header>
											<h2 class="right_align">게시판 관리</h2>
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
													<h1>베스트 게시글 관리</h1>
														<table>
															<thead>
																<tr>
																	<th>글번호</th>
																	<th>제목</th>
																	<th>아이디</th>
																	<th>좋아요</th>
																	<th>조회수</th>
																	<th>댓글수</th>
																	<th>베스트게시글</th>
																</tr>
															</thead>
															<thead id="bobcol">
															</thead>
														</table>
													</div>
													<br><br>
													
													
													<div class="right_align">
													<nav id="nav">
														<ul>
															<li><a href="./boardManage.ad">최신순▼</a></li>
															<li><a href="./boardAlign.ad?op=readcount" id="readcount">조회수 많은 순▼</a></li>
															<li><a href="./boardAlign.ad?op=replycount">댓글 많은 순▼</a></li>
															<li><a href="./boardAlign.ad?op=thumb">좋아요 많은 순▼</a></li>
														</ul>
													</nav>
													</div>
													
													
													<div class="table-wrapper">
														<table>
															<thead>
																<tr>
																	<th>삭제<input type="checkbox" class="bcheck-all" ></th>
																	<th>글번호</th>
																	<th>제목</th>
																	<th>아이디</th>
																	<th>좋아요</th>
																	<th>작성일자</th>
																	<th>조회수</th>
																	<th>댓글수</th>
																	<th>베스트게시글</th>
																	<th>수정</th>
																</tr>
															</thead>
															<thead>
															<c:forEach var="i" begin="0" end="${boardList.size()-1 }" step="1">
															<c:set var="dto" value="${boardList[i] }"/>
																<tr>
																	<td><input type="checkbox" class="board" name="board" value="${dto.num }"></td>
																	<td>${dto.num }</td>
																	<td style="padding: 0.5em 0em 0.5em 0em; width: 250px;"><a href="./boardContent.bo?num=${dto.num }">${dto.title }</a></td>
																	<td>${dto.id }</td>
																	<td>${dto.thumb }</td>
																	<fmt:formatDate var="reg_date" value="${dto.reg_date }" pattern="yyyy-MM-dd hh:mm:ss"/>
																	<td>${reg_date }</td>
																	<td>${dto.readcount}회</td>
																	<td>${dto.replycount }개</td>
																	<td>
																		<select class="bob" name="bob">
																			<option value="0"
																			<c:if test="${dto.bob == 0 }">
																			selected
																			</c:if>
																			>일반글</option>
																			<option value="1"
																			<c:if test="${dto.bob == 1 }">
																			selected
																			</c:if>
																			>인기글</option>
																		</select>
																		<button class="button manage" onclick="updateBob(${i},${dto.num });">적용</button>
																	</td>
																	<td><button class="button manage" onclick="bupdateCheck('${dto.num}');">수정</button></td>
																</tr>
																</c:forEach>
															</thead>
														</table>
													</div>
							
							<div class="right_align">
							<span><button type="button" id="del" class="button manage" onclick="deleteBData();">삭제</button></span>
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