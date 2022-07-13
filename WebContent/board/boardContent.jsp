<%@page import="seastu.board.db.BoardDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE HTML>
<html>
<head>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<jsp:include page="../inc/head.jsp"/>
<jsp:include page="../js/getContentInfo.jsp"/>
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
						<div class="row">
							<div class="col-4 col-12-medium">

							</div>
							<div class="col-12">

								<!-- 글 내용 -->
										<div class="content">
											<h2>
											<span>${dto.title }
											<c:if test="${dto.file != null }">
											<img src="./css/images/save.png" width="15">
											</c:if>
											</span>
											</h2>
											<br>
											<fmt:formatDate var="reg_date" value="${dto.reg_date }" pattern="yyyy-MM-dd HH:mm:ss"/>
											<span>${dto.name }</span> &nbsp;&nbsp;| &nbsp;&nbsp; <span>${reg_date }</span>
										</div>
										
							<!-- content -->
							<div class="content">
							
							<!-- 이미지 파일 존재 시, 이미지 파일 본문에 나타내기 -->
							
							<c:if test="${result == 1 }">
									<img src="boardUpload/${dto.file }" border="300px" width="300px" height="300px"><br>
							</c:if>
									<pre>${dto.content }</pre> 
							</div>
							
							
							
							<!-- 추천 기능 -->
					<div>
					<c:if test="${not empty dto.file }">
					<b>첨부파일명 : <a href="./board/fileDown.jsp?fileName=${dto.file }">${dto.file }[다운로드]</a></b><br><hr>
					</c:if>
					</div>		
					
					<br>		
					<div>
						<button type="button" class="thumb" id="thu_update">
						<img src="./images/empty_heart.png" width="30px" height="30px" id="like_img">
						&nbsp;<span class="thu_count" style="color: black;"></span>
						</button>
					</div>
							
							
							<div class="right_align">
							<!-- 아이디가 같을 시 수정/삭제 버튼 보이게함 -->
							<c:if test="${dto.id == sessionScope.id }"> 
							<button type="submit" class="button search" onclick="location.href='./boardUpdate.bo?num=${dto.num}';">수정</button>
							
							<button type="submit" class="button search" onclick="deleteCheck();">삭제</button>
							</c:if>
							</div>
							
							
							
							
				<!-- 댓글 기능 -->
				<h5 style="margin: 1.5em 0;">댓글 
				<span class="re_count"></span>
				 <c:if test="${s_id == null }">(댓글은 로그인 후 작성 하실 수 있습니다)</c:if></h5>   
				
				<!-- 댓글 작성 창 -->
				<div class="reply_board">
				<form action="./reInsert.re?seq=0" method="post" onsubmit="return replyCheck();" name="fr"> <!-- 대댓글과 비교해주기 위해 seq 번호를 같이 보냄. 0:댓글 1:대댓글 -->
				<input type="hidden" value="${dto.num }" name="re_b_num">
				<c:if test="${s_id != null }">
				<table style="margin-bottom: 0">
				<tr>
					<td width="150" style="vertical-align: top;"><input type="text" placeholder="닉네임" name="re_name">
					<span id="counter">(0 / 최대 300자)</span>
					</td>
					<td width="700" style="padding-right: 0;"><textarea name="re_content" class="re_content" placeholder="댓글을 달아주세요.(300자 이내)"></textarea></td>
				</tr>
				</table>
				<div class="right_align">
				<input type="submit" style="margin-bottom: 1em; height: 2em; text-align: center; padding: 0.2em 0.7em 0.7em;" value="작성">
				</div>
				</c:if>
				</form>	
				
				
				<!-- 댓글 목록 -->
				<c:if test="${reList.size()>0}">
				<c:forEach var="i" begin="0" end="${reList.size()-1 }" step="1">
				<c:set var="list" value="${reList[i] }"/>
				<div class="reply_box">
				<fmt:formatDate value="${list.re_date }" var="re_date" pattern="yyyy-MM-dd hh:mm:ss"/>
				<textarea class="re_num">${list.re_num }</textarea> <!-- 해당 댓글번호 class index로 받아와서 수정 -->
				<div class="reply_list">
				<h4><span>${list.re_name }</span></h4>&nbsp;&nbsp; &nbsp;&nbsp;<span>${re_date}</span>
					
					<c:if test="${list.re_m_id == s_id }">
					<div class="update_btn" style="display: inline-block; padding-left: 1em;">
					<a href="javascript:doUpdateDisplay(${i });">수정</a>
					&nbsp; | &nbsp;
					<a href="javascript:reDelete(${list.re_num });">삭제</a>
					</div>
					</c:if>
				<br>
				
				<!-- 댓글수정 -->
				<div class="up_content" style="display: inline-block; word-break:break-all;">${list.re_content }</div>
						<div class="update_input" style="display: none;">
						<form>
						<table style="margin-bottom: 0;">
						<tr>
							<td style="padding-right: 0; width: 1200px; word-break:break-all;" class="update_view">
							<textarea class="re_upcontent">${list.re_content }</textarea>
							</td>
						</tr>
						</table>
						</form>
						<div class="right_align">
						<a href="javascript:doUpdate(${i });" class="button"
						style="margin-bottom: 1em; height: 2em; text-align: center; padding: 0.2em 0.7em 0.7em;"
						>수정</a>
						</div>
						</div>
						<br>
				<a href="javascript:doDisplay(${i });" class="normal_btn">답글</a>
				</div>
				
				
				<!-- 대댓글 작성 -->
				<div class="reReplyBox" style="display: none;">
				<form action="./reInsert.re?seq=1" method="post">
				<input type="hidden" value="${list.re_num }" name="re_num">
				<input type="hidden" value="${dto.num }" name="re_b_num">
				<table style="margin-bottom: 0">
				<tr>
					<td width="150" style="vertical-align: top;"><input type="text" placeholder="닉네임" name="re_name" required>
					</td>
					<td width="700" style="padding-right: 0;"><textarea required name="re_content" placeholder="댓글을 달아주세요.(300자 이내)"></textarea></td>
				</tr>
				</table>
				<div class="right_align">
				<input type="submit" style="margin-bottom: 1em; height: 2em; text-align: center; padding: 0.2em 0.7em 0.7em;" value="작성">
				</div>
				</form>
				</div>
				
				<!-- 대댓글 목록 -->
				<c:if test="${reReList.size()>0}">
						<c:forEach var="j" begin="0" end="${reReList.size()-1 }" step="1">
							<c:set var="reReplyList" value="${reReList[j] }" />
							<c:if test="${reReplyList.re_ref==list.re_num }">
								<div class="reReply_box">
								<span>┗</span>
								<fmt:formatDate value="${reReplyList.re_date }" var="reReplyList_date" pattern="yyyy-MM-dd hh:mm:ss"/>
									<h4><span>${reReplyList.re_name }</span></h4>&nbsp;&nbsp; &nbsp;&nbsp;<span>${reReplyList_date}</span>
									<c:if test="${reReplyList.re_m_id == s_id }">
									<div>
									<a href="javascript:reDelete(${reReplyList.re_num });"><img src="./images/delete.png" height="15" width="15"></a>
									</div>
									</c:if>	
								<br>
								<span style="padding-left: 1.3em;">${reReplyList.re_content }</span><br>
								</div>
							</c:if>
						</c:forEach>
				</c:if>
				
				</div>
				</c:forEach>
				</c:if>
				</div> <!-- 댓글 태그 끝 -->
				
			</div>
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