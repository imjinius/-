<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
	<head>
			<jsp:include page="../inc/head.jsp"/>
			<script type="text/javascript">
			function writeCheck(){
				if(document.fr.price.value.length<1){
					alert("가격을 입력해주세요.");
					return false;
				} else if(document.fr.title.value.length<1){
					alert("제목을 입력해주세요.");
					return false;
				} else if(document.fr.content.value.length<1){
					alert("내용을 입력해주세요.");
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
								<li><a href="./iboardList.bo">정보의 바다</a></li>
								<li class="current"><a href="./tradeList.tr">중고바다</a></li>
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
										<form action="./tradeUpdateProAction.tr" method="post" name="fr" onsubmit="return writeCheck();">
										 <input type="hidden" name="t_num" value="${dto.t_num }">
										<table>
										<tr>
											<td>판매상태</td>
											<td>
											<input type="radio" name="status" value="0"
											<c:if test="${dto.status==0 }">
											checked
											</c:if>
											>판매중
											<input type="radio" name="status" value="1"
											<c:if test="${dto.status==1 }">
											checked
											</c:if>
											>판매완료
											</td>
										</tr>
										<tr>
											<td>제목</td>
											<td><input type="text" size="25" name="uTitle" value="${dto.title }"></td>
										</tr>
										<tr>
											<td style="vertical-align: middle;">내용</td>
											<td>
											<textarea cols="27" rows="15" name="uContent" style="resize: none;" placeholder="책상태, 필기감 등을 상세히 설명해주세요.">${dto.content }</textarea></td>
										</tr>
										<tr>
											<td>가격</td>
											<td><input type="text" size="25" name="uPrice" value="${dto.price }"></td>
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