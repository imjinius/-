package seastu.member.action;

import javax.servlet.http.*;

import seastu.member.db.MemberDAO;

public class memberDeleteAction implements Action {

	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("M : memberDeleteAction_execute() 호출");
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		
		MemberDAO dao = new MemberDAO();
		dao.deleteMember(id);
		
		ActionForward forward = new ActionForward();
		forward.setPath("./member/bye.jsp");
		forward.setRedirect(false);
		
		return forward;
	}
}
