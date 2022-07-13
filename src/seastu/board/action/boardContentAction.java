
package seastu.board.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import seastu.board.db.BoardDAO;
import seastu.board.db.BoardDTO;
import seastu.re_board.db.Re_BoardDAO;


public class boardContentAction implements Action{
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response)throws Exception{
    	
        System.out.println("M : boardContentAction_execute() 호출");
        
        int num = Integer.parseInt(request.getParameter("num"));
        HttpSession session = request.getSession();
        String id = (String)session.getAttribute("id");
        
        BoardDAO dao = new BoardDAO();
        BoardDTO dto = new BoardDTO();
        dto = dao.getBoardContent(num,id);
        
        String file = dto.getFile();
        String imageFile = null;
        int result = 0;
        
        if(file != null){
        	imageFile = file.substring(file.lastIndexOf('.')+1);
        	 if(imageFile.equals("jpg") || imageFile.equals("png") || imageFile.equals("gif")){
             	result = 1;
             }
        }
       
        
        request.setAttribute("dto", dto);
        request.setAttribute("s_id", id); // 세션id(게시판 아이디와 구별해주기 위함)
        request.setAttribute("result", result);
        
		Re_BoardDAO reDAO = new Re_BoardDAO();
		
		List reList = reDAO.getReList(num);
		request.setAttribute("reList", reList);
		//System.out.println("M : reList : "+reList);
		
		List reReList = reDAO.getreReplyList(num);
		request.setAttribute("reReList", reReList);
		//System.out.println("M : reReList : "+reReList);
		
        
        ActionForward forward = new ActionForward();
        forward.setPath("./board/boardContent.jsp");
        forward.setRedirect(false);
        
        return forward;
    }
}
