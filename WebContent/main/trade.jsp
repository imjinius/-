<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE HTML>
<html>
	<head>
		<jsp:include page="../inc/head.jsp"/>
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
								<li class="current"><a href="./tradeList.tr">중고바다</a></li>
								<li><a href="./boardList.bo">자유의 바다</a></li>
							</ul>
						</nav>

				</section>

			<!-- Main -->
				<section id="main">
					<div class="container">
					<article class="box post">

								<!-- Content -->
								
									<div class="info-wrapper" id="gong">
									<!-- <iframe src="https://service.dongledongle.com/deal" frameborder="0" width="100%" height="500"></iframe> -->
										<header>
											<h2>판매 중인 책</h2>
											<p>전공서적 등, 서적 중고거래</p>
										</header>
										<c:forEach var="dto" items="${tradeList }">
													<ul class="info-list">
														 <li>
															<a href="./tradeContent.tr?t_num=${dto.t_num }" class="i_list">
																<c:if test="${dto.status==0 }">
																<span><img src="tradeUpload/t-${dto.file }"></span>
																</c:if>
																<c:if test="${dto.status==1 }"> <!-- 판매완료시 이미지 흐리게 적용 -->
																<span><img src="tradeUpload/t-${dto.file }" style="opacity: 0.2;"></span>
																</c:if>
																<c:if test="${dto.status==0 }">
																<br><span>${dto.title }</span>
																<br><span class="price">${dto.price }원</span><span class="book_price">${dto.book_price }원</span>
																</c:if>
																<c:if test="${dto.status==1 }">
																<br><span>판매완료</span>
																<br><span class="price">${dto.price }원</span><span class="book_price">${dto.book_price }원</span>
																</c:if>
															</a>
														</li> 
													</ul>
										</c:forEach>
									
														<div style="width: 100%; text-align: center;">
														<c:if test="${pageNum != 1 }">
														<a href="./tradeList.tr?pageNum=${pageNum-1 }" class="pre">◀</a>
														</c:if>
														<c:if test="${pageNum != pageCount }">
														<a href="./tradeList.tr?pageNum=${pageNum+1 }">▶</a>
														</c:if>
														</div>
													<br>
											</div>
											
											

											
												
											<c:if test="${not empty sessionScope.id }">
											<div class="right_align">	
											<button type="button" class="button search" onclick="location.href='./tradeWrite.tr';">글쓰기</button>
											</div>	
											</c:if> 
											
											</article>
											<!-- Footer -->
											</div>
											</section>
			</div>

		<!-- Scripts -->
			<jsp:include page="../inc/script.jsp"/>

	</body>
</html>