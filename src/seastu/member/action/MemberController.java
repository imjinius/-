package seastu.member.action;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class MemberController extends HttpServlet {

	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//////////////////////////// 1. 가상 주소
		//////////////////////////// 계산////////////////////////////////////////////////
		System.out.println(" C : 1. 가상 주소 계산 시작");
		// 가상주소 가져오기
		String requestURI = request.getRequestURI();
		System.out.println(" C : requestURI - " + requestURI);
		// request.getRequestURL();
		// 프로젝트명 가져오기
		String ctxPath = request.getContextPath();
		System.out.println(" C : ctxPath - " + ctxPath);
		// 가상주소 계산(가상주소 - 프로젝트명)
		String command = requestURI.substring(ctxPath.length());
		System.out.println(" C : command - " + command);

		System.out.println(" C : 1. 가상 주소 계산 끝 \n");
		//////////////////////////// 1. 가상 주소
		//////////////////////////// 계산////////////////////////////////////////////////

		//////////////////////////// 2. 가상 주소
		//////////////////////////// 매핑////////////////////////////////////////////////
		System.out.println(" C : 2. 가상 주소 매핑 시작 ");
		Action action = null;
		ActionForward forward = null;
		if (command.equals("/login.mem")) {
			System.out.println("C : /login.mem 호출");
			forward = new ActionForward();
			forward.setPath("./member/login.jsp");
			forward.setRedirect(false);
		} else if (command.equals("/memberInsert.mem")) {
			System.out.println("C : /memberInsert.mem 호출");
			forward = new ActionForward();
			forward.setPath("./member/memberInsert.jsp");
			forward.setRedirect(false);
		} else if (command.equals("/memberInsertAction.mem")) {
			System.out.println("C : /memberInsertAction.mem 호출");
			action = new memberInsertAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/memberCheck.mem")) {
			System.out.println("C : /memberCheck.mem 호출");
			action = new memberCheckAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/logout.mem")) {
			System.out.println("C : /logout.mem 호출");
			action = new logoutAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/memberUpdateCheck.mem")) {
			System.out.println("C : /memberUpdateCheck.mem 호출");
			forward = new ActionForward();
			forward.setPath("./member/memberUpdateCheck.jsp");
			forward.setRedirect(false);
		} else if (command.equals("/memberPwCheck.mem")) {
			System.out.println("C : /memberPwCheck.mem 호출");
			action = new memberPwCheck();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/memberUpdate.mem")) {
			System.out.println("C : /memberUpdate.mem 호출");
			action = new memberUpdateAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/memberUpdatePro.mem")) {
			System.out.println("C : /memberUpdatePro.mem 호출");
			action = new memberUpdateProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/memberDelete.mem")) {
			System.out.println("C : /memberDelete.mem 호출");
			forward = new ActionForward();
			forward.setPath("./member/memberDeleteCheck.jsp");
			forward.setRedirect(false);
		} else if (command.equals("/memberDeletePro.mem")) {
			System.out.println("C : /memberDeletePro.mem 호출");
			action = new memberDeleteAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/memberIdCheck.mem")){
			System.out.println("C : /memberIdCheck.mem 호출");
			
			action = new memberIdCheckAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/memberPwUpdate.mem")){
			System.out.println("C : /memberPwUpdate.mem 호출");
			
			forward = new ActionForward();
			forward.setPath("./member/memberPwUpdateForm.jsp");
			forward.setRedirect(false);
		} else if(command.equals("/memberPwReCheck.mem")){
			System.out.println("C : /memberPwReCheck.mem 호출");
			
			forward = new ActionForward();
			forward.setPath("./member/memberPwReCheck.jsp");
			forward.setRedirect(false);
		} else if(command.equals("/adminPwCheck.mem")){
			System.out.println("C : adminPwCheck.mem 호출");
			
			action = new adminPwCheckAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
			
		
		
		
		
		System.out.println(" C : 2. 가상 주소 매핑 끝 \n");
		//////////////////////////// 2. 가상 주소
		//////////////////////////// 매핑////////////////////////////////////////////////
		//////////////////////////// 3. 페이지
		//////////////////////////// 이동////////////////////////////////////////////////
		System.out.println(" C : 3. 페이지 이동 시작");
		if (forward != null) { // 페이지 이동정보가 있을때

			if (forward.isRedirect()) { // true
				System.out.println(" C : redirect 방식, " + forward.getPath() + "로 이동");
				response.sendRedirect(forward.getPath());

			} else { // false
				RequestDispatcher dis = request.getRequestDispatcher(forward.getPath());

				System.out.println(" C : forward 방식, " + forward.getPath() + "로 이동");
				dis.forward(request, response);

			}

		}

		System.out.println(" C : 3. 페이지 이동 끝 \n");
	}
	//////////////////////////// 3. 페이지
	//////////////////////////// 이동////////////////////////////////////////////////

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("doGet \uD638\uCD9C");
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("doPost \uD638\uCD9C");
		doProcess(request, response);
	}
}
