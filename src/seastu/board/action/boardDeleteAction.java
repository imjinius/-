package seastu.board.action;

import java.io.File;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import seastu.board.db.BoardDAO;

public class boardDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("M : boardDeleteAction_execute() 호출");
		
		int num = Integer.parseInt(request.getParameter("num"));
		String fileName = request.getParameter("file");
		
		if(fileName != null){
			ServletContext ctx = request.getServletContext();
			String realPath = ctx.getRealPath("/boardUpload");
			File delFile = new File(realPath+"\\"+fileName);
			delFile.delete();
			System.out.println("M : 기존 파일 삭제 완료");
		}
		
		BoardDAO dao = new BoardDAO();
		dao.deleteBoard(num);
		
		ActionForward forward = new ActionForward();
		forward.setPath("./boardList.bo");
		forward.setRedirect(true);
		
		
		return forward;
	}

}
