package seastu.admin.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import seastu.member.db.MemberDAO;

public class memberSearchAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("M : memberSearchAction_execute() 호출");
		
		int key = Integer.parseInt(request.getParameter("s_keyword")); // 1: id, 2: name
		String s_content = request.getParameter("s_content");
		
		MemberDAO dao = new MemberDAO();
		int result = dao.getSearchCount(key,s_content);
		/////////////페이징 처리1////////////////////////////
		int pageSize = 5;
		String pageNum = request.getParameter("pageNum");
		if (pageNum == null)
			pageNum = "1";
		int currentPage = Integer.parseInt(pageNum);
		int startRow = (currentPage - 1) * pageSize + 1;
		int endRow = currentPage * pageSize;
		/////////////페이징 처리1////////////////////////////
		
		if(result>0){
			request.setAttribute("searchList", dao.searchMemberList(startRow, pageSize, key, s_content));
		}
		
		////////////페이징 처리2///////////////////////////////////////////
		int pageCount = result / pageSize + (result % pageSize != 0 ? 1 : 0);
		int pageBlock = 5;
		int startPage = ((currentPage - 1) / pageBlock) * pageBlock + 1;
		int endPage = (startPage + pageBlock) - 1;
		if (endPage > pageCount)
			endPage = pageCount;
		////////////페이징 처리2///////////////////////////////////////////
		
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("result", result);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		
		ActionForward forward = new ActionForward();
		forward.setPath("./admin/memberSearchList.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

}
