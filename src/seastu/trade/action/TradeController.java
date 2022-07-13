
package seastu.trade.action;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;


public class TradeController extends HttpServlet {

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
		
		
		if(command.equals("/tradeWrite.tr")){
			System.out.println("C : /tradeWrite.tr 호출");
			
			forward = new ActionForward();
			forward.setPath("./trade/tradeWrite.jsp");
			forward.setRedirect(false);
		} else if(command.equals("/tradeWriteAction.tr")){
			System.out.println("C : /tradeWriteAction.tr 호출");
			
			action = new tradeWriteAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/tradeList.tr")){
			System.out.println("C : /tradeList.tr 호출");
			
			action = new tradeListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/tradeContent.tr")){
			System.out.println("C : /tradeContent.tr 호출");
			
			action = new tradeContentAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/tradeUpdate.tr")){
			System.out.println("C : /tradeUpdate.tr 호출");
			
			action = new tradeUpdateAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/tradeUpdateProAction.tr")){
			System.out.println("C : /tradeUpdateProAction.tr 호출");
			
			action = new tradeUpdateProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/tradeDelete.tr")){
			System.out.println("C : /tradeDelete.tr 호출");
			
			action = new tradeDeleteAction();
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
