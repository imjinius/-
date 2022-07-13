package seastu.board.action;

import java.io.File;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FilenameUtils;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import seastu.board.db.BoardDAO;
import seastu.board.db.BoardDTO;

public class boardUpdateProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("M : boardUpdateProAction_execute() 호출");
		
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
		
		String oFile = multi.getParameter("oFile");
		if(oFile != null){
			File delFile = new File(realPath+"\\"+oFile);
			delFile.delete();
			System.out.println("M : 기존 파일 삭제 완료");
		}
		
		BoardDTO dto = new BoardDTO();
		dto.setName(multi.getParameter("uName"));
		dto.setTitle(multi.getParameter("uTitle"));
		dto.setContent(multi.getParameter("uContent"));
		dto.setFile(multi.getFilesystemName("uFile"));
		dto.setNum(Integer.parseInt(multi.getParameter("num")));
		
		BoardDAO dao = new BoardDAO();
		dao.updateBoard(dto);
		
		
		ActionForward forward = new ActionForward();
		forward.setPath("./boardList.bo");
		forward.setRedirect(true);
		
		return forward;
		
	}

}
