package seastu.main.action;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class MainController extends HttpServlet {

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
		ActionForward forward = null;
		if (command.equals("/seastu.main")) {
			forward = new ActionForward();
			forward.setPath("./main/main.jsp");
			forward.setRedirect(false);
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
		System.out.println("doGet 메서드 실행");
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("doPost 메서드 실행");
		doProcess(request, response);
	}
}
	
