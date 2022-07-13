
package seastu.board.action;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class BoardController extends HttpServlet {

	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println(" C : 1. 가상 주소 계산 시작");
		String requestURI = request.getRequestURI();
		String ctxPath = request.getContextPath();
		String command = requestURI.substring(ctxPath.length());
		System.out.println(" C : command - " + command);
		
		System.out.println(" C : 1. 가상 주소 계산 끝 \n");
		///////////////////////////////////////////////////////////////////////////////////////
		System.out.println(" C : 2. 가상 주소 매핑 시작 ");
		Action action = null;
		ActionForward forward = null;
		if (command.equals("/boardWrite.bo")) {
			System.out.println("C : /boardWrtie.bo 호출");
			forward = new ActionForward();
			forward.setPath("./board/boardWrite.jsp");
			forward.setRedirect(false);
		} else if (command.equals("/boardWriteAction.bo")) {
			System.out.println("C : /boardWriteAction.bo 호출");
			action = new boardWriteAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/boardList.bo")) {
			System.out.println("C : /boardList.bo 호출");
			action = new boardListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/boardContent.bo")) {
			System.out.println("C : /boardContent.bo 호출");
			action = new boardContentAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/boardUpdate.bo")){
			System.out.println("C : /boardUpdate.bo 호출");
			
			action = new boardUpdateAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} else if(command.equals("/boardUpdateProAction.bo")){
			System.out.println("C : /boardUpdateProAction.bo 호출");
			
			action = new boardUpdateProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} else if(command.equals("/boardSearch.bo")){
			System.out.println("C : /boardSearch.bo 호출");
			
			action = new boardSearchAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} else if(command.equals("/boardDelete.bo")){
			System.out.println("C : /boardDelete.bo 호출");
			
			action = new boardDeleteAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/thumbUpdate.bo")){
			System.out.println("C : thumbUpdate.bo 호출");
			
			action = new thumbUpdateAction();
			try {
				action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} else if(command.equals("/thumbCount.bo")){
			System.out.println("C : thumbCount.bo 호출");
			
			action = new thumbCountAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/thumbCheck.bo")){
			System.out.println("C : /thumbCheck.bo 호출");
			
			action = new thumbCheckAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/reCount.bo")){
			System.out.println("C : /reCount.bo 호출");
			
			action = new reCountAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/iboardWrite.bo")){
			System.out.println("C : /iboardWrite.bo 호출");
			
			forward = new ActionForward();
			forward.setPath("./infoBoard/iboardWrite.jsp");
			forward.setRedirect(false);
		} else if(command.equals("/iboardWriteAction.bo")){
			System.out.println("C : /iboardWriteAction.bo 호출");
			
			action = new iboardWriteAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/iboardList.bo")){
			System.out.println("C : /iboardList.bo 호출");
			
			action = new iboardListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/iboardContent.bo")){
			System.out.println("C : /iboardContent.bo 호출");
			
			action = new iboardContentAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/iboardUpdate.bo")){
			System.out.println("C : /iboardUpdate.bo 호출");
			
			action = new iboardUpdateAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/iboarUpdateProAction.bo")){
			System.out.println("C : /iboarUpdateProAction.bo 호출");
			
			action = new iboarUpdateProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/iboardDelete.bo")){
			System.out.println("C: /iboardDelete.bo 호출");
			
			action = new iboardDeleteAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
			
			
		
		
		
		
		System.out.println(" C : 2. 가상 주소 매핑 끝 \n");
		/////////////////////////////////////////////////////////////////////////////
		System.out.println(" C : 3. 페이지 이동 시작");
		if (forward != null)
			if (forward.isRedirect()) {
				response.sendRedirect(forward.getPath());
				System.out.println(" C : redirect 방식, " + forward.getPath() + "로 이동");
			} else {
				RequestDispatcher dis = request.getRequestDispatcher(forward.getPath());
				dis.forward(request, response);
				System.out.println(" C : forward 방식, " + forward.getPath() + "로 이동");
			}
		System.out.println(" C : 3. 페이지 이동 끝 \n");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}
}
