package seastu.admin.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import seastu.board.db.BoardDAO;

public class boardBobUpdateAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("M : boardBobUpdateAction_execute 호출");
		
		int num = Integer.parseInt(request.getParameter("num"));
		int bob = Integer.parseInt(request.getParameter("bob"));
		System.out.println("M : num : "+num+", bob : "+bob);
		BoardDAO dao = new BoardDAO();
		dao.updateBob(num,bob);
		
		ActionForward forward = new ActionForward();
		forward.setPath("./boardManage.ad");
		forward.setRedirect(true);
		
		return forward;
	}

}
