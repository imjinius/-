package seastu.chat.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import seastu.chat.db.ChatDAO;
import seastu.chat.db.ChatDTO;

public class chatSendAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("M : SendChatAction_execute 호출");
		
		int t_num = Integer.parseInt(request.getParameter("t_num"));
		ChatDTO dto = new ChatDTO();
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		dto.setSender(id);
		dto.setContent(request.getParameter("content"));
		
		ChatDAO dao = new ChatDAO();
		dao.sendChat(t_num,dto);
		
		ActionForward forward = new ActionForward();
		forward.setPath("./chat/chatComplete.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

}
