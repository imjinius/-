package seastu.board.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import seastu.board.db.BoardDAO;
import seastu.board.db.BoardDTO;

public class boardSearchAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("M : boardSearchAction_execute() 호출");
		
		String search = request.getParameter("search");
		BoardDAO dao = new BoardDAO();
		int result = dao.getBoardCount(search);
		
		
		/////////////페이징 처리1////////////////////////////
		int pageSize = 5;
		String pageNum = request.getParameter("pageNum");
		if (pageNum == null)
			pageNum = "1";
		int currentPage = Integer.parseInt(pageNum);
		int startRow = (currentPage - 1) * pageSize + 1;
		int endRow = currentPage * pageSize;
		/////////////페이징 처리1////////////////////////////
		
		List<BoardDTO> boardSearchList = null;
		
		if(result > 0){
			boardSearchList = dao.getBoardList(startRow, pageSize, search);
		}
		
		////////////페이징 처리2///////////////////////////////////////////
		int pageCount = result / pageSize + (result % pageSize != 0 ? 1 : 0);
		int pageBlock = 5;
		int startPage = ((currentPage - 1) / pageBlock) * pageBlock + 1;
		int endPage = (startPage + pageBlock) - 1;
		if (endPage > pageCount)
			endPage = pageCount;
		////////////페이징 처리2///////////////////////////////////////////
		request.setAttribute("boardList", boardSearchList);
		
		request.setAttribute("result", result);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		
		ActionForward forward = new ActionForward();
		forward.setPath("./main/talk.jsp");
		forward.setRedirect(false);
		
		
		
		return forward;
	}

}
