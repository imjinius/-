package seastu.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import seastu.board.db.BoardDAO;

public class iboardUpdateAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("M : iboardUpdateAction_execute 호출");
		
		int i_num = Integer.parseInt(request.getParameter("i_num"));
		
		BoardDAO dao = new BoardDAO();
		request.setAttribute("dto", dao.getUpdateiboard(i_num));
		
		ActionForward forward = new ActionForward();
		forward.setPath("./infoBoard/iboardUpdate.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

}
