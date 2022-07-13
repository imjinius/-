package seastu.admin.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class AdminController extends HttpServlet {
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
		
		if(command.equals("/memberManage.ad")){
			System.out.println("C : /memberManage.ad 호출");
			
			action = new memberListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/membersDelete.ad")){
			System.out.println("C : /membersDelete.ad 호출");
			
			action = new membersDeleteAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/memberSearch.ad")){
			System.out.println("C : /memberSearch.ad 호출");
			
			action = new memberSearchAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/boardManage.ad")){
			System.out.println("C : /boardManage.ad 호출");
			
			action = new boardManageAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/boardsDelete.ad")){
			System.out.println("C : /boardsDelete.ad 호출");
			
			action = new boardsDeleteAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/boardBobUpdate.ad")){
			System.out.println("C : /boardBobUpdate.ad 호출");
			
			action = new boardBobUpdateAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/boardAlign.ad")){
			System.out.println("C : /boardAlign.ad 호출");
			
			action = new boardAlignAction();
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
		System.out.println("doGet 호출");
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("doPost 호출");
		doProcess(request, response);
	}
}
