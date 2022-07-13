package seastu.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import seastu.member.db.MemberDAO;
import seastu.member.db.MemberDTO;

public class memberUpdateProAction implements Action {

	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("M : memberUpdateProAction.execute() 호출");
		
		int num = Integer.parseInt(request.getParameter("num"));
		
		if(num == 0){
			MemberDTO dto = new MemberDTO();
			dto.setAddr(request.getParameter("addr"));
			dto.setBirth(request.getParameter("birth"));
			dto.setId(request.getParameter("id"));
			dto.setName(request.getParameter("name"));
			dto.setTel(request.getParameter("tel"));
			dto.setZip_code(Integer.parseInt(request.getParameter("zip_code")));
			
			MemberDAO dao = new MemberDAO();
			dao.memberUpdatePro(dto);
		}
		
		if(num ==1){
			String updatePw = request.getParameter("updatePw");
			HttpSession session = request.getSession();
			String id = (String) session.getAttribute("id");
			
			MemberDAO dao = new MemberDAO();
			dao.memberPwUpdate(id,updatePw);
		}
		
		
		ActionForward forward = new ActionForward();
		forward.setPath("./seastu.main");
		forward.setRedirect(true);
		
		return forward;
	}
}
