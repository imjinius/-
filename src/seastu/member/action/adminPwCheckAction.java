package seastu.member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import seastu.member.db.MemberDAO;

public class adminPwCheckAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("M : adminPwCheckAction_execute() 호출");
		
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		// 관리자 계정 비밀번호 받아서 memberManage에서 비교
		MemberDAO dao = new MemberDAO();
		String adminPw = dao.getAdminPw(id);
				
		PrintWriter out = response.getWriter();
		out.println(adminPw);
		out.close();
		return null;
	}

}
