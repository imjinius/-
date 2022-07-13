package seastu.board.action;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import seastu.board.db.BoardDAO;
import seastu.board.db.iBoardDTO;

public class iboardWriteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("M : iboardWriteAction_execute() 호출");
		
		ServletContext ctx = request.getServletContext();
		String realPath = ctx.getRealPath("/iBoardUpload");
		
		int maxSize = 50*1024*1024; //50MB
		
		MultipartRequest multi = new MultipartRequest(
				request, 
				realPath, 
				maxSize, 
				"UTF-8",
				new DefaultFileRenamePolicy());
		System.out.println("M : 파일 업로드 완료 ");
		String fileName = multi.getFilesystemName("image");
		
		iBoardDTO dto = new iBoardDTO();
		dto.setI_title(multi.getParameter("title"));
		dto.setI_content(multi.getParameter("content"));
		dto.setImage(fileName);
		dto.setCategory(Integer.parseInt(multi.getParameter("category")));
		
		BoardDAO dao = new BoardDAO();
		dao.insert_iBoard(dto);
		
		// 썸네일 이미지 생성
		File origin_file_name = new File(realPath+"\\"+fileName);
		String ext = realPath.substring(realPath.lastIndexOf(".")+1); // 파일 확장자
		
		File thumb_file_name = new File(origin_file_name.getParent()+File.separator+"t-"+origin_file_name.getName()); // 썸네일 파일명
		int tWidth = 305; // 생성할 썸네일의 너비
		int tHeight = 400;// 생성할 썸네일의 높이
		
		BufferedImage buffer_original_image = ImageIO.read(origin_file_name); // 원본이미지
		BufferedImage buffer_thumbnail_image = new BufferedImage(tWidth, tHeight, BufferedImage.TYPE_3BYTE_BGR); // 썸네일 이미지
		
		Graphics2D graphic = buffer_thumbnail_image.createGraphics();
		graphic.drawImage(buffer_original_image, 0, 0, tWidth, tHeight, null);
		ImageIO.write(buffer_thumbnail_image, "jpg", thumb_file_name);
		System.out.println("썸네일 생성완료");
		
		// 썸네일 이미지 생성
		
		
		ActionForward forward = new ActionForward();
		forward.setPath("./iboardList.bo");
		forward.setRedirect(true);
		
		
		return forward;
	}

}
