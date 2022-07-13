package seastu.chat.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import seastu.chat.db.ChatDAO;
import seastu.chat.db.ChatDTO;

public class chatReplyAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("M : chatReplyAction_execute 호출");
		
		int c_t_num = Integer.parseInt(request.getParameter("c_t_num"));
		String receiver = request.getParameter("receiver");
		ChatDTO dto = new ChatDTO();
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		dto.setSender(id);
		dto.setContent(request.getParameter("content"));
		dto.setReceiver(receiver);
		
		ChatDAO dao = new ChatDAO();
		dao.sendReply(c_t_num,dto);
		
		ActionForward forward = new ActionForward();
		forward.setPath("./chatList.ch");
		forward.setRedirect(true);
		
		return forward;
	}

}
