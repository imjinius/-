package seastu.board.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import seastu.board.db.BoardDAO;
import seastu.board.db.iBoardDTO;
import seastu.re_board.db.Re_BoardDAO;

public class iboardContentAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("M : iboardContentAction_execute() 호출");
		
		int i_num = Integer.parseInt(request.getParameter("i_num"));
        
        BoardDAO dao = new BoardDAO();
        iBoardDTO dto = new iBoardDTO();
        dto = dao.getiBoardContent(i_num);
        
        request.setAttribute("dto", dto);
        
        ActionForward forward = new ActionForward();
        forward.setPath("./infoBoard/iboardContent.jsp");
        forward.setRedirect(false);
        
        return forward;
	}

}
