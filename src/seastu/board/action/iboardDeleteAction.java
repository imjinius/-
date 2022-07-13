package seastu.board.action;

import java.io.File;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import seastu.board.db.BoardDAO;

public class iboardDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("M : iboardDeleteAction_execute 호출");
		
		int i_num = Integer.parseInt(request.getParameter("i_num"));
		String fileName = request.getParameter("file");
		
		if(fileName != null){
			ServletContext ctx = request.getServletContext();
			String realPath = ctx.getRealPath("/iBoardUpload");
			File delFile = new File(realPath+"\\"+fileName);
			File delFile2 = new File(realPath+"\\t-"+fileName);
			delFile.delete();
			delFile2.delete();
			System.out.println("M : 기존 파일 삭제 완료");
		}
		
		BoardDAO dao = new BoardDAO();
		dao.deleteIBoard(i_num);
		
		ActionForward forward = new ActionForward();
		forward.setPath("./iboardList.bo");
		forward.setRedirect(true);
		
		return forward;
	}

}
