package seastu.board.db;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.naming.*;
import javax.servlet.ServletContext;
import javax.sql.DataSource;

public class BoardDAO {

	// 공통변수 선언
	private Connection con = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private String sql = "";

	// 디비연결 메서드
	private Connection getCon() throws Exception {
		// 1.2. 디비연결

		// 1) 프로젝트 정보를 초기화
		Context initCTX = new InitialContext();
		// 2) 프로젝트에 저장된 리소스 정보를 불러오기
		DataSource ds = (DataSource) initCTX.lookup("java:comp/env/jdbc/seastu");
		// 3) 디비연결
		con = ds.getConnection();

		System.out.println("DAO  : 디비연결 성공(커넥션풀)");
		System.out.println("DAO : " + con);

		return con;
	}

	// 디비연결 메서드

	// 디비 자원해제 메서드
	public void closeDB() {
		try {
			if (rs != null)
				rs.close();
			if (con != null)
				con.close();
			if (pstmt != null)
				pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	// 디비 자원해제 메서드

	// insertBoard(dto)
	public void insertBoard(BoardDTO dto) {
		int num = 0;
		try {
			con = getCon();
			sql = "select max(num) from seastu_board";

			pstmt = con.prepareStatement(sql);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				num = rs.getInt(1) + 1;
			}
			System.out.println("DAO : num : " + num);

			sql = "insert into seastu_board(num,title,content,name,id,file,thumb,reg_date,readcount,replycount,bob) values(?,?,?,?,?,?,0,now(),0,0,0)";

			pstmt = con.prepareStatement(sql);

			pstmt.setInt(1, num);
			pstmt.setString(2, dto.getTitle());
			pstmt.setString(3, dto.getContent());
			pstmt.setString(4, dto.getName());
			pstmt.setString(5, dto.getId());
			pstmt.setString(6, dto.getFile());

			pstmt.executeUpdate();

			System.out.println("DAO : 게시판 글 생성 완료");
			
			// 해당 id의 글 쓴 갯수 업로드
			sql = "update seastu_member set w_count=(select count(num) from seastu_board where id=?) where id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getId());
			pstmt.setString(2, dto.getId());
			
			pstmt.executeUpdate();
			
			System.out.println("DAO : 회원 글 작성 갯수 업로드 완료");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
	}
	// insertBoard(dto)

	// getBoardCount()
	public int getBoardCount() {

		int result = 0;
		try {
			con = getCon();
			sql = "select count(num) from seastu_board";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next())
				result = rs.getInt(1);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		return result;
	}

	// getBoardCount()

	// getBoardList()
	public List<BoardDTO> getBoardList() {
		List<BoardDTO> boardList = new ArrayList<BoardDTO>();

		try {
			con = getCon();
			sql = "select * from seastu_board";

			pstmt = con.prepareStatement(sql);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				BoardDTO dto = new BoardDTO();
				dto.setContent(rs.getString("content"));
				dto.setFile(rs.getString("file"));
				dto.setId(rs.getString("id"));
				dto.setName(rs.getString("name"));
				dto.setNum(rs.getInt("num"));
				dto.setReg_date(rs.getTimestamp("reg_date"));
				dto.setThumb(rs.getInt("thumb"));
				dto.setTitle(rs.getString("title"));
				dto.setReplycount(rs.getInt("replycount"));
				dto.setBob(rs.getInt("bob"));

				boardList.add(dto);

			}

			System.out.println("DAO : 게시판 글 목록 저장 완료");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}

		return boardList;
	}
	// getBoardList()

	// getBoardList(int startRow, int pageSize)
	public List<BoardDTO> getBoardList(int startRow, int pageSize) {
		List<BoardDTO> boardList = new ArrayList<BoardDTO>();
		int replycount = 0;

		try {
			
			con = getCon();
			sql = "select * from seastu_board order by num desc limit ?,?";
			pstmt = con.prepareStatement(sql);

			pstmt.setInt(1, startRow - 1);
			pstmt.setInt(2, pageSize);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				BoardDTO dto = new BoardDTO();
				dto.setContent(rs.getString("content"));
				dto.setFile(rs.getString("file"));
				dto.setName(rs.getString("name"));
				dto.setNum(rs.getInt("num"));
				dto.setId(rs.getString("id"));
				dto.setReg_date(rs.getTimestamp("reg_date"));
				dto.setThumb(rs.getInt("thumb"));
				dto.setTitle(rs.getString("title"));
				dto.setReadcount(rs.getInt("readcount"));
				dto.setBob(rs.getInt("bob"));
				
				sql = "select count(re_num) from seastu_re_board where re_b_num=?";
				PreparedStatement pstmt2 = con.prepareStatement(sql);

				pstmt2.setInt(1, rs.getInt("num"));

				ResultSet rs2 = pstmt2.executeQuery();
				
				if(rs2.next()){
					replycount = rs2.getInt(1);
				}
				
				dto.setReplycount(replycount);

				boardList.add(dto);
			}

			System.out.println(" DAO : 게시판 글 목록 저장 완료");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}

		return boardList;
	}
	// getBoardList(int startRow, int pageSize)

	// getBoardContent(int num)
	public BoardDTO getBoardContent(int num) {
		BoardDTO dto = null;
		
		try {
			con = getCon();
			sql = "select * from seastu_board where num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				
				dto = new BoardDTO();
				dto.setContent(rs.getString("content"));
				dto.setFile(rs.getString("file"));
				dto.setName(rs.getString("name"));
				dto.setNum(rs.getInt("num"));
				dto.setId(rs.getString("id"));
				dto.setReg_date(rs.getTimestamp("reg_date"));
				dto.setThumb(rs.getInt("thumb"));
				dto.setTitle(rs.getString("title"));
				dto.setReadcount(rs.getInt("readcount"));
				dto.setReplycount(rs.getInt("replycount"));
				dto.setBob(rs.getInt("bob"));
			}
			
			System.out.println("DAO : 글 내용 가져오기 완료");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		
		return dto;
	}
	// getBoardContent(int num)
	
	
	
	// getBoardContent(int num,String id)
	public BoardDTO getBoardContent(int num,String id) {
		BoardDTO dto = null;

		try {
			con = getCon();
			
			sql = "select id from seastu_board where num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				if(id != null){
					if(!id.equals(rs.getString(1))){ // 같은 id로 글 클릭 시 조회수 증가 X
						sql = "update seastu_board set readcount = readcount+1 where num=?";
						pstmt = con.prepareStatement(sql);
						pstmt.setInt(1, num);
						
						pstmt.executeUpdate();
						
						System.out.println("DAO : 조회수 1증가");
						
					}
				} else {
					sql = "update seastu_board set readcount = readcount+1 where num=?";
					pstmt = con.prepareStatement(sql);
					pstmt.setInt(1, num);
					
					pstmt.executeUpdate();
					
					System.out.println("DAO : 조회수 1증가");
				}
			}
			

			sql = "select * from seastu_board where num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);

			rs = pstmt.executeQuery();

			if (rs.next()) {

				dto = new BoardDTO();
				dto.setContent(rs.getString("content"));
				dto.setFile(rs.getString("file"));
				dto.setName(rs.getString("name"));
				dto.setNum(rs.getInt("num"));
				dto.setId(rs.getString("id"));
				dto.setReg_date(rs.getTimestamp("reg_date"));
				dto.setThumb(rs.getInt("thumb"));
				dto.setTitle(rs.getString("title"));
				dto.setReadcount(rs.getInt("readcount"));
				dto.setReplycount(rs.getInt("replycount"));
				dto.setBob(rs.getInt("bob"));
			}

			System.out.println("DAO : 글 내용 가져오기 완료");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}

		return dto;
	}
	// getBoardContent(int num,String id)
	
	// getBoardCount()
	public int getBoardCount(String search) {
		int result = 0;

		try {
			con = getCon();
			sql = "select count(num) from seastu_board where title like ?";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, "%"+search+"%");
			
			rs = pstmt.executeQuery();
			
			if (rs.next())
				result = rs.getInt(1);
			
			System.out.println("DAO : result : "+result);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		return result;
	}

	// getBoardCount()

	
	
	// getBoardList(int startRow, int pageSize, String search)
	public List<BoardDTO> getBoardList(int startRow, int pageSize, String search) {
		List<BoardDTO> boardList = new ArrayList<BoardDTO>();

		try {
			con = getCon();
			sql = "select * from seastu_board where title like ? order by num desc limit ?,?";
			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, "%"+search+"%");
			pstmt.setInt(2, startRow - 1);
			pstmt.setInt(3, pageSize);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				BoardDTO dto = new BoardDTO();
				dto.setContent(rs.getString("content"));
				dto.setFile(rs.getString("file"));
				dto.setName(rs.getString("name"));
				dto.setNum(rs.getInt("num"));
				dto.setId(rs.getString("id"));
				dto.setReg_date(rs.getTimestamp("reg_date"));
				dto.setThumb(rs.getInt("thumb"));
				dto.setTitle(rs.getString("title"));
				dto.setReadcount(rs.getInt("readcount"));
				dto.setReplycount(rs.getInt("replycount"));
				dto.setBob(rs.getInt("bob"));

				boardList.add(dto);
			}

			System.out.println(" DAO : 게시판 검색 글 가져오기 완료");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}

		return boardList;
	}
	// getBoardList(int startRow, int pageSize)
	
	
	
	// getBoardList(startRow, pageSize, align_op)
	public List<BoardDTO> getAlignBoardList(int startRow, int pageSize, String align_op) {
		List<BoardDTO> boardList = new ArrayList<BoardDTO>();
		
		try {
			con = getCon();
			
			if(align_op.equals("readcount")){
				sql = "select * from seastu_board order by readcount desc limit ?,?";
				pstmt = con.prepareStatement(sql);
				
				pstmt.setInt(1, startRow);
				pstmt.setInt(2, pageSize);
				
				rs = pstmt.executeQuery();
				
				while (rs.next()) {
					BoardDTO dto = new BoardDTO();
					dto.setContent(rs.getString("content"));
					dto.setFile(rs.getString("file"));
					dto.setName(rs.getString("name"));
					dto.setNum(rs.getInt("num"));
					dto.setId(rs.getString("id"));
					dto.setReg_date(rs.getTimestamp("reg_date"));
					dto.setThumb(rs.getInt("thumb"));
					dto.setTitle(rs.getString("title"));
					dto.setReadcount(rs.getInt("readcount"));
					dto.setReplycount(rs.getInt("replycount"));
					dto.setBob(rs.getInt("bob"));
					
					boardList.add(dto);
				}
				
			}
			
			if(align_op.equals("replycount")){
				sql = "select * from seastu_board order by replycount desc limit ?,?";
				pstmt = con.prepareStatement(sql);
				
				pstmt.setInt(1, startRow);
				pstmt.setInt(2, pageSize);
				
				rs = pstmt.executeQuery();
				
				while (rs.next()) {
					BoardDTO dto = new BoardDTO();
					dto.setContent(rs.getString("content"));
					dto.setFile(rs.getString("file"));
					dto.setName(rs.getString("name"));
					dto.setNum(rs.getInt("num"));
					dto.setId(rs.getString("id"));
					dto.setReg_date(rs.getTimestamp("reg_date"));
					dto.setThumb(rs.getInt("thumb"));
					dto.setTitle(rs.getString("title"));
					dto.setReadcount(rs.getInt("readcount"));
					dto.setReplycount(rs.getInt("replycount"));
					dto.setBob(rs.getInt("bob"));
					
					boardList.add(dto);
				}
				
			}
			if(align_op.equals("thumb")){
				sql = "select * from seastu_board order by thumb desc limit ?,?";
				pstmt = con.prepareStatement(sql);
				
				pstmt.setInt(1, startRow);
				pstmt.setInt(2, pageSize);
				
				rs = pstmt.executeQuery();
				
				while (rs.next()) {
					BoardDTO dto = new BoardDTO();
					dto.setContent(rs.getString("content"));
					dto.setFile(rs.getString("file"));
					dto.setName(rs.getString("name"));
					dto.setNum(rs.getInt("num"));
					dto.setId(rs.getString("id"));
					dto.setReg_date(rs.getTimestamp("reg_date"));
					dto.setThumb(rs.getInt("thumb"));
					dto.setTitle(rs.getString("title"));
					dto.setReadcount(rs.getInt("readcount"));
					dto.setReplycount(rs.getInt("replycount"));
					dto.setBob(rs.getInt("bob"));
					
					boardList.add(dto);
				}
				
			}
			
			System.out.println(" DAO : 게시판 정렬 글 가져오기 완료");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		
		return boardList;
	}
	// getBoardList(int startRow, int pageSize)
	
	
	// updateBoard(dto)
	public void updateBoard(BoardDTO dto){
		try {
			con = getCon();
			sql = "update seastu_board set name=?,title=?,content=?,file=?,reg_date=now() where num=?"; 
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, dto.getName());
			pstmt.setString(2, dto.getTitle());
			pstmt.setString(3, dto.getContent());
			pstmt.setString(4, dto.getFile());
			pstmt.setInt(5, dto.getNum());
			
			pstmt.executeUpdate();
			
			System.out.println("DAO : 글 정보 수정 완료");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	// updateBoard(dto)
	
	
	// deleteBoard(num)
	public void deleteBoard(int num){
		try {
			con = getCon();
			sql = "delete from seastu_board where num=?";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, num);
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
	} 
	
	
	// deleteBoard(num)
	
	
	
	// checkThumb(b_num,id,b_category)
	public int checkThumb(int b_num, String id){
		int result = 0;
		
		try {
			con = getCon();
			sql = "select count(t_num) from seastu_thumb_board where t_b_num=? and t_m_id=?";
			pstmt = con.prepareStatement(sql);
			
			System.out.println("DAO : checkThumb : "+b_num+", "+id);
			
			pstmt.setInt(1, b_num);
			pstmt.setString(2, id);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				if(rs.getInt(1)>0){
					result = 1;
				}
			}
			
			System.out.println("DAO : 좋아요 조회 완료 : "+result);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		
		return result;
	}
	
	// checkThumb(b_num,id,b_category)

	
	// updateThumb(b_num,id,b_category)
	public void updateThumb(int t_b_num,String t_m_id){
		int t_num = 0;
		
		try {
			con = getCon();
			sql = "select max(t_num) from seastu_thumb_board";
			pstmt = con.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				t_num = rs.getInt(1)+1;
			}
			
			sql = "insert into seastu_thumb_board values (?,?,?)";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, t_num);
			pstmt.setInt(2, t_b_num);
			pstmt.setString(3, t_m_id);
			
			pstmt.executeUpdate();
			
			sql = "update seastu_board set thumb=thumb+1 where num=?";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, t_b_num);
			
			pstmt.executeUpdate();
			
			pstmt = con.prepareStatement(sql);
			
			System.out.println("DAO : 좋아요 수 1 증가");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
	}
	
	
	// updateThumb(b_num,id,b_category)
	
	
	
	// deleteThumb(b_num,id,b_category)
	public void deleteThumb(int t_b_num,String t_m_id){
		int like_num = 0;
		
		try {
			con = getCon();
			sql = "delete from seastu_thumb_board where t_b_num=? and t_m_id=?";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, t_b_num);
			pstmt.setString(2, t_m_id);
			
			pstmt.executeUpdate();
			
			sql = "update seastu_board set thumb=thumb-1 where num=?";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, t_b_num);
			
			pstmt.executeUpdate();
			
			pstmt = con.prepareStatement(sql);
			
			System.out.println("DAO : 좋아요 수 1 감소");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
	}
	// deleteThumb(b_num,id,b_category)
	
	
	// countThumb(num)
	public int countThumb(int num){
		int count = 0;
		
		try {
			con = getCon();
			sql = "select thumb from seastu_board where num=?";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, num);
			
			rs = pstmt.executeQuery();
			if(rs.next())
				count = rs.getInt(1);
			
			System.out.println("DAO : 좋아요 수 가져오기 완료");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		
		return count;
	}
	// countThumb(num)
	
	
	
	// countReply(num)
	public int countReply(int num){
		int count = 0;
		
		try {
			con = getCon();
			sql = "select count(re_num) from seastu_re_board where re_b_num=?";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, num);
			
			rs = pstmt.executeQuery();
			
			if(rs.next())
				count = rs.getInt(1);
			
			sql = "update seastu_board set replycount=? where num=?";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, count);
			pstmt.setInt(2, num);
			
			pstmt.executeUpdate();
			
			System.out.println("DAO : 댓글 수 가져오기 완료");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		
		return count;
	}
	// countReply(num)
		
	
	// deleteBoards(boards)
	public List<String> deleteBoards(int[] nums){
		List<String> fileNames = new ArrayList<String>();
		try {
			con = getCon();
			
			for(int i=0;i<nums.length;i++){
				sql = "select file from seastu_board where num=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, nums[i]);
				rs = pstmt.executeQuery();
				if(rs.next()){
					fileNames.add(rs.getString(1));
				}
			}
			
			for(int i=0;i<nums.length;i++){
				sql = "delete from seastu_board where num=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, nums[i]);
				pstmt.executeUpdate();
			}
			
			System.out.println("DAO : 글 삭제 모두 완료");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		return fileNames;
	}
		
	// deleteBoards(boards)
	
	// updateBob(num)
	public void updateBob(int num, int bob){
		try {
			con = getCon();
			sql = "update seastu_board set bob=? where num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, bob);
			pstmt.setInt(2, num);
			pstmt.executeUpdate();
			
			System.out.println("DAO : 인기글 업데이트 완료");
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
	}
	// updateBob(num)
	
	
	
	/////////////////////////////////////////info 게시판////////////////////////////////////////////////////////////////
	
	// insert_iBoard(dto)
	public int insert_iBoard(iBoardDTO dto){
		int i_num = 0;
		try {
			con = getCon();
			sql = "select max(i_num) from seastu_info_board";

			pstmt = con.prepareStatement(sql);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				i_num = rs.getInt(1) + 1;
			}
			System.out.println("DAO : num : " + i_num);

			sql = "insert into seastu_info_board values(?,?,?,?,0,now(),?)";

			pstmt = con.prepareStatement(sql);

			pstmt.setInt(1, i_num);
			pstmt.setString(2, dto.getI_title());
			pstmt.setString(3, dto.getI_content());
			pstmt.setString(4, dto.getImage());
			pstmt.setInt(5, dto.getCategory());

			pstmt.executeUpdate();

			System.out.println("DAO : 정보게시판 글 생성 완료");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		
		return i_num;
	}
	
	// insert_iBoard(dto)
	
	
	
	// getiBoardCount
	public int getiBoardCount(int category){
		int result = 0;
		try {
			con = getCon();
			sql = "select count(i_num) from seastu_info_board where category=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, category);
			
			rs = pstmt.executeQuery();
			if (rs.next())
				result = rs.getInt(1);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		
		
		return result;
	}
	
	// getiBoardCount
	
	
	// getiBoardList(startRow, pageSize)
	public List<iBoardDTO> getiBoardList(int startRow, int pageSize, int category) {
		List<iBoardDTO> iBoardList = new ArrayList<iBoardDTO>();

		try {
			
			con = getCon();
			sql = "select * from seastu_info_board where category=? order by i_num desc limit ?,?";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, category);
			pstmt.setInt(2, startRow - 1);
			pstmt.setInt(3, pageSize);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				iBoardDTO dto = new iBoardDTO();
				
				dto.setI_content(rs.getString("i_content"));
				dto.setI_num(rs.getInt("i_num"));
				dto.setI_title(rs.getString("i_title"));
				dto.setImage(rs.getString("image"));
				dto.setReadcount(rs.getInt("readcount"));
				dto.setReg_date(rs.getTimestamp("reg_date"));
				dto.setCategory(rs.getInt("category"));

				iBoardList.add(dto);
			}

			System.out.println(" DAO : 정보 게시판 글 목록 저장 완료");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}

		return iBoardList;
	}
	// getiBoardList(startRow, pageSize)
	
	
	// getiBoardContent(i_num)
	public iBoardDTO getiBoardContent(int i_num) {
		iBoardDTO dto = null;

		try {
			con = getCon();
			sql = "update seastu_info_board set readcount = readcount+1 where i_num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, i_num);

			pstmt.executeUpdate();

			System.out.println("DAO : 조회수 1증가");

			sql = "select * from seastu_info_board where i_num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, i_num);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				dto = new iBoardDTO();
				dto.setI_content(rs.getString("i_content"));
				dto.setI_num(rs.getInt("i_num"));
				dto.setI_title(rs.getString("i_title"));
				dto.setImage(rs.getString("image"));
				dto.setReadcount(rs.getInt("readcount"));
				dto.setReg_date(rs.getTimestamp("reg_date"));
			}

			System.out.println("DAO : 글 내용 가져오기 완료");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}

		return dto;
	}
	// getiBoardContent(i_num)
	
	
	// getUpdateiboard(i_num)
	public iBoardDTO getUpdateiboard(int i_num){
		iBoardDTO dto = null;
		
		try {
			con = getCon();
			sql = "select * from seastu_info_board where i_num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, i_num);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				dto = new iBoardDTO();
				dto.setI_content(rs.getString("i_content"));
				dto.setI_num(rs.getInt("i_num"));
				dto.setI_title(rs.getString("i_title"));
				dto.setImage(rs.getString("image"));
				dto.setReadcount(rs.getInt("readcount"));
				dto.setReg_date(rs.getTimestamp("reg_date"));
				dto.setCategory(rs.getInt("category"));
			}

			System.out.println("DAO : 글 내용 가져오기 완료");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		return dto;
	}
	// getUpdateiboard(i_num)
	
	
	// updateIBoard(dto)
	public void updateIBoard(iBoardDTO dto){
		try {
			con = getCon();
			sql = "update seastu_info_board set i_title=?,i_content=?,image=?,category=?,reg_date=now() where i_num=? ";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getI_title());
			pstmt.setString(2, dto.getI_content());
			pstmt.setString(3, dto.getImage());
			pstmt.setInt(4, dto.getCategory());
			pstmt.setInt(5, dto.getI_num());
			
			pstmt.executeUpdate();
			
			System.out.println("DAO : 정보게시판 글 업데이트 완료");
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
	}
	
	// updateIBoard(dto)
	
	
	// deleteIBoard(i_num)
	public void deleteIBoard(int i_num){
		try {
			con = getCon();
			sql = "delete from seastu_info_board where i_num=?";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, i_num);
			
			pstmt.executeUpdate();
			
			System.out.println("DAO : 정보게시판 글삭제완료");
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		
	}
	// deleteIBoard(i_num)
}