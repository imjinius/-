package seastu.chat.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import seastu.chat.db.ChatDAO;

public class chatContentAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("M : getChatAction_execute 호출");
		
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		String talker = request.getParameter("talker");
		int c_t_num = Integer.parseInt(request.getParameter("c_t_num"));
		
		ChatDAO dao = new ChatDAO();
		// 쪽지 기능은 익명이기 때문에 글번호로 구별하여 거래쪽지들을 구별해준다.
		request.setAttribute("chatContents", dao.getChatContent(id,talker,c_t_num));
		request.setAttribute("c_t_num", c_t_num);
		
		
		ActionForward forward = new ActionForward();
		forward.setPath("./chat/chatContent.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

}
