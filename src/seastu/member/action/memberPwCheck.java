package seastu.member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import seastu.member.db.MemberDAO;

public class memberPwCheck implements Action {

	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("M : memberPwCheck.execute() 호출");
		String pw = request.getParameter("pw");
		String delete = "";
		String update = "";
		String pwupdate = "";
		
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		
		if (request.getParameter("delete") != null)
			delete = request.getParameter("delete");
		else if (request.getParameter("update") != null)
			update = request.getParameter("update");
		else if (request.getParameter("pwupdate") != null)
			pwupdate = request.getParameter("pwupdate");
		
		ActionForward forward = null;
		MemberDAO dao = new MemberDAO();
		int result = dao.checkMember(id,pw);
		
		if (update.equals("true") && result == 1) {// update 페이지에서 왔고, 비밀번호가 맞을경우
			forward = new ActionForward();
			forward.setPath("./memberUpdate.mem");
			forward.setRedirect(true);
		} else if (pwupdate.equals("true") && result == 1) {// delete 페이지에서 왔고, 비밀번호가 맞을 경우
			forward = new ActionForward();
			forward.setPath("./memberPwUpdate.mem");
			forward.setRedirect(true);
		} else if (delete.equals("true") && result == 1) {// delete 페이지에서 왔고, 비밀번호가 맞을 경우
			forward = new ActionForward();
			forward.setPath("./memberDeletePro.mem");
			forward.setRedirect(true);
		} else {// 패스워드가 틀린 경우를 처리 하기 위해서 result=0을 get방식으로 보냄
			forward = new ActionForward();
			forward.setPath("./member/failLogin.jsp?result=0");
			forward.setRedirect(false);
		}
		
		
		
		return forward;
	}
}
