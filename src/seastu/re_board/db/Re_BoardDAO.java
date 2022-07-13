package seastu.re_board.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class Re_BoardDAO {
	
	private Connection con = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private String sql = "";
	
	// 디비연결 메서드
	private Connection getCon() throws Exception{
		Context initCTX = new InitialContext();
		DataSource ds = (DataSource) initCTX.lookup("java:comp/env/jdbc/seastu");
		con = ds.getConnection();
		System.out.println("DAO : 디비연결성공");
		
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
			System.out.println("DAO : 디비 연결 자원해제");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	// 디비 자원해제 메서드
	
	
	
	// insertReply(dto)
	public void insertReply(Re_BoardDTO dto, int seq){
		int re_num = 0;
		
		try {
			con = getCon();
			sql = "select max(re_num) from seastu_re_board";
			
			pstmt = con.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				re_num = rs.getInt(1)+1;
			}
			
			System.out.println("DAO : re_num 계산 완료");
			
			sql = "insert into seastu_re_board(re_num,re_b_num,re_name,re_m_id,re_content,re_date,re_ref,re_seq) "
					+ "values(?,?,?,?,?,now(),?,?)";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, re_num);
			pstmt.setInt(2, dto.getRe_b_num());
			pstmt.setString(3, dto.getRe_name());
			pstmt.setString(4, dto.getRe_m_id());
			pstmt.setString(5, dto.getRe_content());
			if(seq == 0 ){ // 댓글. 댓글 ref는 re_num과 동일
				pstmt.setInt(6, re_num);
				pstmt.setInt(7, 0);
				
			} else if(seq == 1){ // 대댓글. 대댓글 ref는 댓글의 re_num와 동일
				pstmt.setInt(6, dto.getRe_num());
				pstmt.setInt(7, 1);
			}
			
			pstmt.executeUpdate();
			
			// 댓글 수 업로드
			sql = "update seastu_board set replycount=(select count(re_num) from seastu_re_board where re_b_num=?) where num=?";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, dto.getRe_b_num());
			pstmt.setInt(2, dto.getRe_b_num());
			
			pstmt.executeUpdate();
				
			
			// 해당 멤버 댓글수 업로드
			sql = "update seastu_member set re_count = (select count(re_num) from seastu_re_board where re_m_id=?) where id=?;";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getRe_m_id());
			pstmt.setString(2, dto.getRe_m_id());
			pstmt.executeUpdate();
			
			System.out.println("DAO : 답글생성완료, replycount 업데이트 완료, 멤버 댓글수 업데이트 완료");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
	}
	// insertReply(dto)
	
	
	
	// getReList(re_b_num,re_b_category) 댓글 목록 가져오기
	public List<Re_BoardDTO> getReList(int re_b_num){
		List<Re_BoardDTO> reList = new ArrayList<>();
		
		try {
			con = getCon();
			sql = "select * from seastu_re_board where re_b_num=? and re_seq=0 order by re_num";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, re_b_num);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				Re_BoardDTO dto = new Re_BoardDTO();
				dto.setRe_name(rs.getString("re_name"));
				dto.setRe_content(rs.getString("re_content"));
				dto.setRe_date(rs.getTimestamp("re_date"));
				dto.setRe_num(rs.getInt("re_num"));
				dto.setRe_m_id(rs.getString("re_m_id"));
				dto.setRe_seq(rs.getInt("re_seq"));
				dto.setRe_b_num(re_b_num);
				dto.setRe_ref(rs.getInt("re_ref"));
				
				reList.add(dto);
				//System.out.println("DAO : reList : "+reList);
				
			}
			System.out.println("DAO : 댓글 리스트 가져오기 완료");
			System.out.println(reList);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		
		return reList;
	}
	// getReList(re_b_num,re_b_category)
	
	
	// getreReplyList(re_b_num,re_b_category) 대댓글 가져오기
	public List<Re_BoardDTO> getreReplyList(int re_b_num){
		List<Re_BoardDTO> reReList = new ArrayList<>();
		
		try {
			con = getCon();
			sql = "select * from seastu_re_board where re_b_num=? and re_seq=1 order by re_num";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, re_b_num);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				Re_BoardDTO dto = new Re_BoardDTO();
				dto.setRe_name(rs.getString("re_name"));
				dto.setRe_content(rs.getString("re_content"));
				dto.setRe_date(rs.getTimestamp("re_date"));
				dto.setRe_num(rs.getInt("re_num"));
				dto.setRe_m_id(rs.getString("re_m_id"));
				dto.setRe_seq(rs.getInt("re_seq"));
				dto.setRe_b_num(re_b_num);
				dto.setRe_ref(rs.getInt("re_ref"));
				
				reReList.add(dto);
				
			}
			System.out.println("DAO : 대댓글 리스트 가져오기 완료");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		
		return reReList;
	}
	// getreReplyList(re_b_num,re_b_category)
	
	// deleteReply(re_num)
	public void deleteReply(int re_num){
		try {
			con =getCon();
			sql = "delete from seastu_re_board where re_num=?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, re_num);
			
			pstmt.executeUpdate();
			
			sql = "update seastu_board set replycount=(select count(re_num) from seastu_re_board where re_b_num=?) where num=?";
			
			System.out.println("DAO : 댓글 삭제 완료");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
	}
	// deleteReply(re_num)

	
	// updateReply(re_upcontent)
	public void updateReply(String re_upcontent, int re_num){
		try {
			con = getCon();
			sql= "update seastu_re_board set re_content=? where re_num=?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, re_upcontent);
			pstmt.setInt(2, re_num);
			
			pstmt.executeUpdate();
			
			System.out.println("DAO : 댓글 수정 완료");
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
	}
	
	// updateReply(re_upcontent)
	
	
	
}
