# 👨‍👨‍👦‍👦 sea-of-student
---

## 💬 프로젝트 주제

대학생들을 위한 커뮤니티.

어플 '에브리타임'을 모티브로 제작하였고 JSP 모델2 방식을 사용하여 개발하였습니다.

각종 대외활동, 구직정보 등을 얻을 수 있으며 대학생끼리 정보를 공유할 수 있는 커뮤니티입니다.
<br><br><br>

## 🕒 개발 기간

2022.05.02 ~ 2022.05.31
<br><br><br>

## 🔨 개발 환경
- 운영체제 : Windows10
- 사용 언어, 기술 : Java JDK1.8, HTML, CSS, JQuery, Ajax, JavaScript, JSP&Servlet
- 서버 : Tomcat 8.5, MySQL Workbench
- IDE : Eclipse
<br><br><br>

## 💬 ER 다이어그램
<img src="https://user-images.githubusercontent.com/103329327/178713950-ecbed6ad-c7f0-470c-a7ee-5da17b82d24e.png" width="80%" height="700px">

## 🎞 시연 영상

1. 메인페이지
- 인기글
- ajax 활용. 실시간 전국 날씨 출력<br>


![메인](https://user-images.githubusercontent.com/103329327/178718185-d6dc207f-020f-488b-a150-e9f2a4accac8.gif)


- 구글 메일 API를 이용하여 관리자에게 문의 기능 구현

![이메일문의](https://user-images.githubusercontent.com/103329327/178718335-20bf41d9-fd3a-47a6-9c66-73e55cf52aa5.gif)

---


2. 회원

- 회원가입
- 아이디 및 비밀번호 중복, 유효성 체크
- 다음 주소 API를 이용해 회원 주소 입력
<br>

![회원가입](https://user-images.githubusercontent.com/103329327/178718323-0ed22a25-91a8-408b-99f6-be18757e9fec.gif)
<br><br>

- 회원정보 수정(최신 정보로 변경) 및 비밀 번호 수정 시 비밀번호 체크 후 동작 실행
- 회원 탈퇴 시 비밀번호 체크 후 탈퇴 수행
<br>

회원정보수정

![회원정보수정](https://user-images.githubusercontent.com/103329327/178718294-4f7d8453-62b1-45a9-ad2b-4a5359046be5.gif)

비밀번호 변경

![비밀번호변경](https://user-images.githubusercontent.com/103329327/178718217-ebcd5ce1-e082-4bc7-811c-66e53647a5ed.gif)

회원탈퇴

![탈퇴](https://user-images.githubusercontent.com/103329327/178718361-799e730d-27c2-429b-bdd8-2c25d1fea652.gif)

---

3. 게시판
3-1. 자유게시판
- 글쓰기 유효성 체크 후 글쓰기 동작 수행
- 글쓴이만 수정/삭제 버튼 보이게 함

![게시판 CRUD](https://user-images.githubusercontent.com/103329327/178726328-6a801b6a-9b5b-413c-b94a-b9ac61878c81.gif)

<br><br>

- 키워드 입력시 글제목으로 검색하여 목록 출력
- 댓글 class의 index를 


- 중고 거래 게시판에서 판매자에게 쪽지 기능으로 문의
- seastu_chat의 sender와 receiver 컬럼으로 수신쪽지, 발신쪽지 색상 다르게 함
- 메인페이지의 쪽지함을 통해서 확인 가능

![쪽지](https://user-images.githubusercontent.com/103329327/178718374-13c3a759-3434-4fea-a8a1-bba7a19966df.gif)







