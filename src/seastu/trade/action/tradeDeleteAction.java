package seastu.trade.action;

import java.io.File;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import seastu.trade.db.TradeDAO;

public class tradeDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("M : tradeDeleteAction_execute 호출");
		
		int t_num = Integer.parseInt(request.getParameter("t_num"));
		
		String fileName = request.getParameter("file");
		
		if(fileName != null){
			ServletContext ctx = request.getServletContext();
			String realPath = ctx.getRealPath("/tradeUpload");
			File delFile = new File(realPath+"\\"+fileName);
			File delFile2 = new File(realPath+"\\t-"+fileName);
			delFile.delete();
			delFile2.delete();
			System.out.println("M : 기존 파일 삭제 완료");
		}
		
		TradeDAO dao = new TradeDAO();
		dao.deleteTrade(t_num);
		
		ActionForward forward = new ActionForward();
		forward.setPath("./tradeList.tr");
		forward.setRedirect(true);
		
		return forward;
	}

}
