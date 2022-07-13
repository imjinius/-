package seastu.trade.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import seastu.trade.db.TradeDAO;
import seastu.trade.db.TradeDTO;

public class tradeUpdateProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("M : tradeUpdateProAction_execute 호출");
		
		TradeDTO dto = new TradeDTO();
		dto.setContent(request.getParameter("uContent"));
		dto.setPrice(Integer.parseInt(request.getParameter("uPrice")));
		dto.setStatus(Integer.parseInt(request.getParameter("status")));
		dto.setT_num(Integer.parseInt(request.getParameter("t_num")));
		dto.setTitle(request.getParameter("uTitle"));
		
		TradeDAO dao = new TradeDAO();
		dao.updateTrade(dto);
		
		ActionForward forward = new ActionForward();
		forward.setPath("./tradeList.tr");
		forward.setRedirect(true);
		
		return forward;
	}

}
