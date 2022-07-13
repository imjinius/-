package seastu.member.action;

import javax.servlet.http.*;

public class logoutAction implements Action {

	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		session.invalidate();
		
		ActionForward forward = new ActionForward();
		forward.setPath("./seastu.main");
		forward.setRedirect(true);
		System.out.println("M : 로그아웃 완료");
		
		return forward;
	}
}
