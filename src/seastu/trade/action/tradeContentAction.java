package seastu.trade.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import seastu.chat.db.ChatDAO;
import seastu.trade.db.TradeDAO;
import seastu.trade.db.TradeDTO;


public class tradeContentAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("M : tradeContentAction_execute() 호출");
		
		int t_num = Integer.parseInt(request.getParameter("t_num"));
        
        TradeDAO dao = new TradeDAO();
        TradeDTO dto = dao.getTradeContent(t_num);
        String seller = dto.getSeller();
        HttpSession session = request.getSession();
        String id = (String) session.getAttribute("id");
        int result = 0;
        if(!seller.equals(id)){ // 세션의 id와 seller가 다르면 쪽지 보내기 버튼이 보이게 하기 위해서
        	ChatDAO cDAO = new ChatDAO();
        	result = cDAO.chatCheck(id,t_num); // 만약 해당 id가 해당 글 seller에게 쪽지 보낸적이 있으면 쪽지 보내기 기능이 비활성화 되게 함.
        }
        
        request.setAttribute("dto", dao.getTradeContent(t_num));
        request.setAttribute("result", result);
        
        ActionForward forward = new ActionForward();
        forward.setPath("./trade/tradeContent.jsp");
        forward.setRedirect(false);
		
		return forward;
	}

}
