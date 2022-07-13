package seastu.admin.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import seastu.board.db.BoardDAO;

public class boardAlignAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("M : boardAlignAction_execute 호출");
		
		String align_op = request.getParameter("op");
		System.out.println("M : align_op : "+align_op);
		
		BoardDAO dao = new BoardDAO();
		int result = dao.getBoardCount();
		
		request.setAttribute("boardList", dao.getAlignBoardList(0, result, align_op));
		request.setAttribute("result", result);
		
		
		ActionForward forward = new ActionForward();
		forward.setPath("./admin/boardAlignList.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

}
