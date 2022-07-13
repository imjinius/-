package seastu.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import seastu.member.db.MemberDAO;
import seastu.member.db.MemberDTO;

public class memberInsertAction implements Action {

	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("memberInsertAction_execute() 호출");

		MemberDTO dto = new MemberDTO();
		dto.setZip_code(Integer.parseInt(request.getParameter("zip_code")));
		dto.setAddr(request.getParameter("addr"));
		dto.setBirth(request.getParameter("birth"));
		dto.setId(request.getParameter("id"));
		dto.setName(request.getParameter("name"));
		dto.setPw(request.getParameter("pw"));
		dto.setTel(request.getParameter("tel"));

		MemberDAO dao = new MemberDAO();
		dao.insertMember(dto);

		ActionForward forward = new ActionForward();
		forward.setPath("./login.mem?back=./seastu.main");
		forward.setRedirect(true);

		return forward;
	}
}
