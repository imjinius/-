package seastu.board.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import seastu.board.db.BoardDAO;

public class reCountAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("M : reCountAction_execute() 호출");
		
		int num = Integer.parseInt(request.getParameter("num"));
		
		PrintWriter out = response.getWriter();
		
		BoardDAO dao = new BoardDAO();
		int count = dao.countReply(num);
		
		out.println(count);
		out.close();
		
		
		return null;
	}

}
