package seastu.re_board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import seastu.re_board.db.Re_BoardDAO;
import seastu.re_board.db.Re_BoardDTO;

public class reInsertAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("M : reInsertAction_execute() 호출");	
		
		int re_b_num = Integer.parseInt(request.getParameter("re_b_num"));
		
		// seq, re_num 둘 다 0인 경우 댓글, 대댓글일 경우 re_num 값이 오기 때문.
		int seq = Integer.parseInt(request.getParameter("seq"));
		int re_num=0;
		
		if(request.getParameter("re_num") != null){
			re_num = Integer.parseInt(request.getParameter("re_num"));
		}
		
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		
		Re_BoardDTO dto = new Re_BoardDTO();
		dto.setRe_b_num(Integer.parseInt(request.getParameter("re_b_num")));
		dto.setRe_name(request.getParameter("re_name"));
		dto.setRe_content(request.getParameter("re_content"));
		dto.setRe_m_id(id);
		dto.setRe_num(re_num);
		
		Re_BoardDAO dao = new Re_BoardDAO();
		dao.insertReply(dto,seq);
		
		ActionForward forward = new ActionForward();
		forward.setPath("./boardContent.bo?num="+re_b_num);
		forward.setRedirect(true);
		
		return forward;
	}

}
