package seastu.member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import seastu.member.db.MemberDAO;

public class memberIdCheckAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("M : memberIdCheckAction_execute() 호출");
		
		String id = request.getParameter("id");
		//System.out.println("M : id : "+id);
		
		PrintWriter out = response.getWriter();
		
		MemberDAO dao = new MemberDAO();
		int result = dao.checkMemberId(id); // 0 : id 없음(가능) 1 : id 존재(불가능)
		System.out.println("M : result : "+result);
		
		out.print(result);
		out.close();
		
		return null;
	}

}
