<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script src="./jq/jquery-3.6.0.js"></script>
<script type="text/javascript">
	var idCheck = 0;
	var idEqCheck = 0;
	var pwCheck = 0;
	var pwEqCheck = 0;
	var addrCheck = 0;

	$(function() {

		$('#input_id').change(function() {
			idCheck = 0;
			idEqCheck = 0;

		});

		$('.pw_input').change(function() {
			pwCheck = 0;
			pwEqCheck = 0;

		});

		$('#input_id')
				.on(
						"change keyup paste",
						function() {
							var id = $('#input_id').val();
							var idRegExp = /^[A-Za-z]{1}[A-Za-z0-9_-]{5,14}$/;

							if (!idRegExp.test(id)) {
								$('#result').css("display", "inline");
								$('#result')
										.css("color", "red")
										.text(
												'아이디는 영문으로 시작해야 하며 영문 대소문자 혹은 영문 대소문자+숫자 6~15자리로 입력해야합니다.');
								idCheck = 0;
							} else {
								$('#result').css("color", "green").text(
										'사용 가능한 아이디 입니다.');
								idCheck = 1;
							}

						});

		$('.input_name').on("change keyup paste", function() {
			var name = $('.input_name').val();
			var nameRegExp = /^[가-힣]+$/;

			if (!nameRegExp.test(name)) {
				$('#nm_result').css("display", "inline");
				$('#nm_result').css("color", "red").text('올바르게 입력해주세요.');
			} else {
				$('#nm_result').css("display", "none");
			}

		});

		$('.insertme_btn').click(
				function() {
					var id = $('#input_id').val();

					if (id != '') {

						// 아이디를 서버로 전송 > DB 유효성 검사 > 결과 반환받기
						$.ajax({
							type : 'GET',
							url : './memberIdCheck.mem',
							data : {
								id : id,
							},
							success : function(result) {
								if (result == "0") {
									$('#result').css("display", "inline");
									$('#result').css("color", "green").text(
											'사용 가능한 아이디입니다.');
									idEqCheck = 1;

								} else if (result == "1") {
									$('#result').css("color", "red").text(
											'이미 사용중인 아이디입니다.');
									idEqCheck = 0;
								}
							},
							error : function(a, b, c) {
								console.log(a, b, c);
							}

						});

					} else {
						alert('아이디를 입력하세요.');
						$('#input_id').focus();
					}

				});

		$('.pw_input').on("change keyup paste",function() {
			var pw = $('.pw_input').val();
			var pwRegExp = /(?=.*\d{1,20})(?=.*[~`!@#$%\^&*()-+=]{1,20})(?=.*[a-zA-Z]{1,20}).{8,20}$/;

			if (pwRegExp.test(pw)) {
				$('.pw_input_re_1').css('display', 'inline');
				$('.pw_input_re_2').css('display', 'none');
				pwCheck = 1;
			} else {
				$('.pw_input_re_1').css('display', 'none');
				$('.pw_input_re_2').css('display', 'inline');
				pwCheck = 0;
			}

		});

		$('.pwck_input').on("change keyup paste", function() {
			var pw = $('.pw_input').val();
			var pwck = $('.pwck_input').val();

			$('.final_pwck_ck').css('display', 'none');

			if (pw == pwck) {
				$('.pwck_input_re_1').css('display', 'inline');
				$('.pwck_input_re_2').css('display', 'none');
				pwEqCheck = 1;
			} else {
				$('.pwck_input_re_1').css('display', 'none');
				$('.pwck_input_re_2').css('display', 'inline');
				pwEqCheck = 0;
			}

		});

	}); //jquery

	function insertCheck() {

		if (idEqCheck == 0) {
			alert("아이디 중복체크를 해주시기 바랍니다.");
			$('#input_id').focus();
			return false;
		} else if (idCheck == 0) {
			alert("사용할 수 없는 아이디입니다.");
			return false;
		} else if (pwEqCheck == 0) {
			alert("비밀번호가 일치하는지 확인해 주세요.")
			$('.pwck_input').focus();
			return false;
		} else if (pwCheck == 0) {
			alert("사용할 수 없는 비밀번호입니다.");
			return false;
		} else if (document.fr.zip_code.value.length < 1) {
			alert("주소를 입력해 주세요.");
			return false;
		}

	}//insertCheck

	function findAddr() {
		new daum.Postcode({
			oncomplete : function(data) {
				console.log(data);

				var roadAddr = data.roadAddress; // 도로명 주소 변수
				var jibunAddr = data.jibunAddress; // 지번 주소 변수
				// 우편번호와 주소 정보를 해당 필드에 넣는다.
				document.getElementById('member_post').value = data.zonecode;
				if (roadAddr !== '') {
					document.getElementById("member_addr").value = roadAddr;
				} else if (jibunAddr !== '') {
					document.getElementById("member_addr").value = jibunAddr;
				}
			}
		}).open();
	}
</script>