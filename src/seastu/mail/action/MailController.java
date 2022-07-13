
package seastu.mail.action;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;


public class MailController extends HttpServlet {

	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println(" C : 1. 가상 주소 계산 시작");
		String requestURI = request.getRequestURI();
		String ctxPath = request.getContextPath();
		String command = requestURI.substring(ctxPath.length());
		System.out.println((new StringBuilder("C : command :")).append(command).toString());
		System.out.println(" C : 1. 가상 주소 계산 끝 \n");
		///////////////////////////////////////////////////////////////////////////////////////
		System.out.println(" C : 2. 가상 주소 매핑 시작 ");
		Action action = null;
		ActionForward forward = null;
		
		
		if(command.equals("/SendMail.ma")){
			System.out.println("C : /SendMail.ma 호출");
			
			forward = new ActionForward();
			forward.setPath("./mail/mailForm.jsp");
			forward.setRedirect(false);
		}else if(command.equals("/SendMailAction.ma")){
			System.out.println("C : /SendMailAction.ma 호출");
			
			action = new SMTP();
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
