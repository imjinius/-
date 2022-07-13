package seastu.re_board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import seastu.re_board.db.Re_BoardDAO;

public class reDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("M : reDeleteAction_execute() 호출");
		
		int re_num = Integer.parseInt(request.getParameter("re_num"));
		System.out.println("M : re_num : "+re_num);
		
		Re_BoardDAO dao = new Re_BoardDAO();
		dao.deleteReply(re_num);
		
		
		return null;
	}

}
