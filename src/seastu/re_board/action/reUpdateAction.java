package seastu.re_board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import seastu.re_board.db.Re_BoardDAO;

public class reUpdateAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("M : reUpdateAction_execute() 호출");
		
		String re_upcontent = request.getParameter("re_upcontent");
		int re_num = Integer.parseInt(request.getParameter("re_num"));
		
		Re_BoardDAO dao = new Re_BoardDAO();
		dao.updateReply(re_upcontent,re_num);
		
		return null;
	}

}
