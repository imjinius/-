package seastu.board.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import seastu.board.db.BoardDAO;

public class thumbCheckAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("M : thumbCheckAction_execute() 호출");
		
		int t_b_num = Integer.parseInt(request.getParameter("b_num"));
		String t_m_id = request.getParameter("id");
		
		BoardDAO dao = new BoardDAO();
		int result = dao.checkThumb(t_b_num,t_m_id);
		
		PrintWriter out = response.getWriter();
		out.println(result);
		out.close();
		
		
		return null;
	}

}
