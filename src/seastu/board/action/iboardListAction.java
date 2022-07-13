package seastu.board.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import seastu.board.db.BoardDAO;

public class iboardListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("M : iboardListAction_execute() 호출");
		
		BoardDAO dao = new BoardDAO();
		int gong_result = dao.getiBoardCount(0);
		int act_result = dao.getiBoardCount(1);
		int cate = 0;
		if(request.getParameter("cate") != null){
			cate = Integer.parseInt(request.getParameter("cate"));
		}
		
		/////////////페이징 처리1////////////////////////////
		int pageSize = 3;
		String pageNum = request.getParameter("pageNum");
		if (pageNum == null)
			pageNum = "1";
		int currentPage = Integer.parseInt(pageNum);
		int startRow = (currentPage - 1) * pageSize + 1;
		int endRow = currentPage * pageSize;
		/////////////페이징 처리1////////////////////////////
		ActionForward forward = null;
		
		List gong = null; // 공모전리스트
		List act = null; // 대외활동리스트
		
		if(cate == 0){
		if (gong_result > 0){
			gong = dao.getiBoardList(startRow, pageSize,0);
			int pageCount = gong_result / pageSize + (gong_result % pageSize != 0 ? 1 : 0);
			request.setAttribute("gong", gong);
			request.setAttribute("pageCount", pageCount);
			forward = new ActionForward();
			forward.setPath("./main/information.jsp");
			forward.setRedirect(false);
			request.setAttribute("pageNum", pageNum);
			return forward;
		}
		}
		
		if(cate == 1){
		if (act_result > 0){
			act = dao.getiBoardList(startRow, pageSize,1);
			int pageCount = act_result / pageSize + (act_result % pageSize != 0 ? 1 : 0);
			request.setAttribute("act", act);
			request.setAttribute("pageCount", pageCount);
			forward = new ActionForward();
			forward.setPath("./infoBoard/iact.jsp");
			forward.setRedirect(false);
			request.setAttribute("pageNum", pageNum);
			return forward;
		}
		}
		
		return null;
	}

}
