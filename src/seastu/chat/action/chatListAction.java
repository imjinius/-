package seastu.chat.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import seastu.chat.db.ChatDAO;

public class chatListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("M : getChatListAction_execute 호출");
		
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		
		ChatDAO dao = new ChatDAO();
		request.setAttribute("chatLists",dao.getChatList(id));
		
		ActionForward forward = new ActionForward();
		forward.setPath("./chat/chatList.jsp");
		forward.setRedirect(false);
				
		
		return forward;
	}

}
