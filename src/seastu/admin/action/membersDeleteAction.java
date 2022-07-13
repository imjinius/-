package seastu.admin.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import seastu.member.db.MemberDAO;

public class membersDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("M : membersDeleteAction_execute() 호출");
		
		String data = request.getParameter("mems");
		String[] mems = data.split(",",0);
		
		MemberDAO dao = new MemberDAO();
		dao.deleteMembers(mems);
		
		ActionForward forward = new ActionForward();
		forward.setPath("./memberManage.ad");
		forward.setRedirect(true);
		
		return forward;
	}

}
