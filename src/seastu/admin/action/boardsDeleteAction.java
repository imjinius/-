package seastu.admin.action;

import java.io.File;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import seastu.board.db.BoardDAO;
import seastu.member.db.MemberDAO;

public class boardsDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("M : boardsDeleteAction_execute 호출");
		
		String data = request.getParameter("boards");
		String[] boards = data.split(",",0);
		int[] nums = new int[boards.length];
		for(int i=0;i<boards.length;i++){
			nums[i] = Integer.parseInt(boards[i]);
		}
		
		BoardDAO dao = new BoardDAO();
		List<String> fileNames = dao.deleteBoards(nums);
		
		if(fileNames != null){
			ServletContext ctx = request.getServletContext();
			String realPath = ctx.getRealPath("/boardUpload");
			System.out.println(realPath);
			for(int i=0;i<fileNames.size();i++){
				File delFile = new File(realPath+"\\"+fileNames.get(i));
				delFile.delete();
			}
			System.out.println("M : 기존 파일 삭제 완료");
		}

		
		ActionForward forward = new ActionForward();
		forward.setPath("./boardManage.ad");
		forward.setRedirect(true);
		
		return forward;
	}

}
