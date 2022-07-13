package seastu.trade.action;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import seastu.trade.db.TradeDAO;
import seastu.trade.db.TradeDTO;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.http.*;

public class tradeWriteAction implements Action {

	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("M : tradeWriteAction_execute() 호출");
		
		ServletContext ctx = request.getServletContext();
		String realPath = ctx.getRealPath("/tradeUpload");
		
		int maxSize = 50*1024*1024; //50MB
		
		MultipartRequest multi = new MultipartRequest(
				request, 
				realPath, 
				maxSize, 
				"UTF-8",
				new DefaultFileRenamePolicy());
		System.out.println("M : 파일 업로드 완료 ");
		
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		
		TradeDTO dto = new TradeDTO();
		dto.setContent(multi.getParameter("content"));
		dto.setFile(multi.getFilesystemName("file"));
		dto.setPrice(Integer.parseInt(multi.getParameter("price")));
		dto.setSeller(id);
		dto.setTitle(multi.getParameter("title"));

		String[] book_info = multi.getParameter("book_info").split(",");
		dto.setBook_price(Integer.parseInt(book_info[0]));
		dto.setPublisher(book_info[1]);
		
		TradeDAO dao = new TradeDAO();
		dao.insertTrade(dto);
		
		String fileName = multi.getFilesystemName("file");
		// 썸네일 이미지 생성
		File origin_file_name = new File(realPath+"\\"+fileName);
		String ext = realPath.substring(realPath.lastIndexOf(".")+1); // 파일 확장자
		
		File thumb_file_name = new File(origin_file_name.getParent()+File.separator+"t-"+origin_file_name.getName()); // 썸네일 파일명
		int tWidth = 200; // 생성할 썸네일의 너비
		int tHeight = 300;// 생성할 썸네일의 높이
		
		BufferedImage buffer_original_image = ImageIO.read(origin_file_name); // 원본이미지
		BufferedImage buffer_thumbnail_image = new BufferedImage(tWidth, tHeight, BufferedImage.TYPE_3BYTE_BGR); // 썸네일 이미지
		
		Graphics2D graphic = buffer_thumbnail_image.createGraphics();
		graphic.drawImage(buffer_original_image, 0, 0, tWidth, tHeight, null);
		ImageIO.write(buffer_thumbnail_image, "jpg", thumb_file_name);
		System.out.println("썸네일 생성완료");
		
		// 썸네일 이미지 생성
		
			
		ActionForward forward = new ActionForward();
		forward.setPath("./tradeList.tr");
		forward.setRedirect(true);
		
		
		return forward;
	}
}
