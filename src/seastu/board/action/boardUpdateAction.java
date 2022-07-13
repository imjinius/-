package seastu.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import seastu.board.db.BoardDAO;
import seastu.board.db.BoardDTO;

public class boardUpdateAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("M : boardUpdateAction_execute() 호출");
		
		int num = Integer.parseInt(request.getParameter("num"));
		
		BoardDAO dao = new BoardDAO();
		
		request.setAttribute("boardContent", dao.getBoardContent(num));
		
		ActionForward forward = new ActionForward();
		forward.setPath("./board/boardUpdateWrite.jsp");
		forward.setRedirect(false);
		
		
		return forward;
		
	}

}
