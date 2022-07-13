package seastu.trade.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import seastu.trade.db.TradeDAO;
import seastu.trade.db.TradeDTO;

public class tradeListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("M : tradeListAction_execute() 호출");
		
		TradeDAO dao = new TradeDAO();
		int result = dao.getTradeCount();
		
		/////////////페이징 처리1////////////////////////////
		int pageSize = 4;
		String pageNum = request.getParameter("pageNum");
		if (pageNum == null)
			pageNum = "1";
		int currentPage = Integer.parseInt(pageNum);
		int startRow = (currentPage - 1) * pageSize + 1;
		int endRow = currentPage * pageSize;
		/////////////페이징 처리1////////////////////////////
		
		
		List<TradeDTO> tradeList = dao.getTradeList(startRow,pageSize);
		
		////////////페이징 처리2///////////////////////////////////////////
		int pageCount = result / pageSize + (result % pageSize != 0 ? 1 : 0);
		int pageBlock = 5;
		int startPage = ((currentPage - 1) / pageBlock) * pageBlock + 1;
		int endPage = (startPage + pageBlock) - 1;
		if (endPage > pageCount)
			endPage = pageCount;
		////////////페이징 처리2///////////////////////////////////////////
		
		
		request.setAttribute("tradeList", tradeList);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("result", result);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		
		
		ActionForward forward = new ActionForward();
		forward.setPath("./main/trade.jsp");
		forward.setRedirect(false);
		
		return forward;
		
		
	}

}
