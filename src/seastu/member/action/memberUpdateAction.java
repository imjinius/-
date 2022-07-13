package seastu.member.action;

import javax.servlet.http.*;

import seastu.member.db.MemberDAO;

public class memberUpdateAction implements Action {

	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("M : memberUpdateAction_execute() 호출");
		
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		ActionForward forward = null;
		
		if(request.getParameter("id")!=null){
			MemberDAO dao = new MemberDAO();
			request.setAttribute("dto", dao.updateMember(request.getParameter("id")));
			forward = new ActionForward();
			forward.setPath("./member/memberUpdate.jsp");
			forward.setRedirect(false);
			return forward;
		}
		
		MemberDAO dao = new MemberDAO();
		request.setAttribute("dto", dao.updateMember(id));
		
		forward = new ActionForward();
		forward.setPath("./member/memberUpdate.jsp");
		forward.setRedirect(false);
		
		return forward;
	}
}
