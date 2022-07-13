package seastu.board.action;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import seastu.board.db.BoardDAO;
import seastu.board.db.BoardDTO;

import java.io.File;

import javax.servlet.ServletContext;
import javax.servlet.http.*;

import org.apache.commons.io.FilenameUtils;

public class boardWriteAction implements Action {

	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("M : boardWriteAction_execute() 호출");
		
		ServletContext ctx = request.getServletContext();
		String realPath = ctx.getRealPath("/boardUpload");
		
		int maxSize = 50*1024*1024; //50MB
		
		MultipartRequest multi = new MultipartRequest(
				request, 
				realPath, 
				maxSize, 
				"UTF-8",
				new DefaultFileRenamePolicy());
		System.out.println("M : 파일 업로드 완료 ");
		
		BoardDTO dto = new BoardDTO();
		dto.setName(multi.getParameter("name"));
		dto.setTitle(multi.getParameter("title"));
		dto.setContent(multi.getParameter("content"));
		dto.setFile(multi.getFilesystemName("file"));
		
		HttpSession session = request.getSession();
		dto.setId((String) session.getAttribute("id"));

		BoardDAO dao = new BoardDAO();
		dao.insertBoard(dto);
		
			
		ActionForward forward = new ActionForward();
		forward.setPath("./boardList.bo");
		forward.setRedirect(true);
		
		
		return forward;
	}
}
