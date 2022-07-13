/*package seastu.re_board.action;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import seastu.re_board.db.Re_BoardDAO;

public class reListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("M : reListAction_execute() 호출");
		
		int re_b_num = Integer.parseInt(request.getParameter("re_b_num"));
		int re_b_category = Integer.parseInt(request.getParameter("re_b_category"));
		
		
		//PrintWriter out = response.getWriter();
		
		Re_BoardDAO dao = new Re_BoardDAO();
		List reList = dao.getReList(re_b_num,re_b_category);
		request.setAttribute("reList", reList);
		
		//out.println(reList);
		//out.close();
		
		ActionForward forward = new ActionForward();
		forward.setPath("./boardContent.bo?num="+re_b_num+);
		forward.setRedirect(true);
		
		return forward;
	}

}*/
