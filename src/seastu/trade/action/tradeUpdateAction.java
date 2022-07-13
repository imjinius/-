package seastu.trade.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import seastu.trade.db.TradeDAO;

public class tradeUpdateAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("M : tradeUpdateAction_execute 호출");
		
		int t_num = Integer.parseInt(request.getParameter("t_num"));
		
		TradeDAO dao = new TradeDAO();
		request.setAttribute("dto", dao.getTradeContent(t_num));
		
		ActionForward forward = new ActionForward();
		forward.setPath("./trade/tradeUpdate.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

}
