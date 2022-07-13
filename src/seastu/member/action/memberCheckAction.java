package seastu.member.action;

import javax.servlet.http.*;

import seastu.member.db.MemberDAO;

public class memberCheckAction implements Action {

	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("M : memberCheckAction_execute 호출");
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String back = request.getParameter("back");
		System.out.println(back);
		
		MemberDAO dao = new MemberDAO();
		int result = dao.checkMember(id, pw);
		
		ActionForward forward = null;
		if (result == 1) {
				HttpSession session = request.getSession();
				session.setAttribute("id", id);
				forward = new ActionForward();
				forward.setPath(back);
				forward.setRedirect(true);
		} else {
			forward = new ActionForward();
			forward.setPath("./member/failLogin.jsp?result=-1");
			forward.setRedirect(false);
		}
		
		return forward;
	}
}
