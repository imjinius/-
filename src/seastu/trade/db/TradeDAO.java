package seastu.trade.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import seastu.board.db.BoardDTO;

public class TradeDAO {

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
	
	
	
	// insertTrade(dto)
	public void insertTrade(TradeDTO dto) {
		int t_num = 0;
		try {
			con = getCon();
			sql = "select max(t_num) from seastu_trade";

			pstmt = con.prepareStatement(sql);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				t_num = rs.getInt(1) + 1;
			}
			System.out.println("DAO : t_num : " + t_num);

			sql = "insert into seastu_trade values(?,?,?,?,0,?,?,?,?,now())";

			pstmt = con.prepareStatement(sql);

			pstmt.setInt(1, t_num);
			pstmt.setString(2, dto.getTitle());
			pstmt.setString(3, dto.getContent());
			pstmt.setString(4, dto.getSeller());
			pstmt.setString(5, dto.getPublisher());
			pstmt.setInt(6, dto.getBook_price());
			pstmt.setInt(7, dto.getPrice());
			pstmt.setString(8, dto.getFile());

			pstmt.executeUpdate();

			System.out.println("DAO : 중고거래 글 생성 완료");
			
			// 해당 id의 글 쓴 갯수 업로드
			sql = "update seastu_member set w_count=(select count(num) from seastu_board where id=?) where id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getSeller());
			pstmt.setString(2, dto.getSeller());
			
			pstmt.executeUpdate();
			
			System.out.println("DAO : 회원 글 작성 갯수 업로드 완료");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
	}
	// insertBoard(dto)
	
	
	// getTradeCount()
	public int getTradeCount() {

		int result = 0;
		try {
			con = getCon();
			sql = "select count(t_num) from seastu_trade";
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
	
	// getTradeCount()
	
	
	// getTradeList()
	public List<TradeDTO> getTradeList(int startRow, int pageSize) {
		List<TradeDTO> tradeList = new ArrayList<TradeDTO>();
		try {
			
			con = getCon();
			sql = "select * from seastu_trade order by t_num desc limit ?,?";
			pstmt = con.prepareStatement(sql);

			pstmt.setInt(1, startRow - 1);
			pstmt.setInt(2, pageSize);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				TradeDTO dto = new TradeDTO();
				dto.setContent(rs.getString("content"));
				dto.setFile(rs.getString("file"));
				dto.setPrice(rs.getInt("price"));
				dto.setReg_date(rs.getTimestamp("reg_date"));
				dto.setSeller(rs.getString("seller"));
				dto.setStatus(rs.getInt("status"));
				dto.setT_num(rs.getInt("t_num"));
				dto.setTitle(rs.getString("title"));
				dto.setBook_price(rs.getInt("book_price"));
				dto.setPublisher(rs.getString("publisher"));
				
				tradeList.add(dto);
			}

			System.out.println(" DAO : 중고거래 글 목록 저장 완료");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}

		return tradeList;
	}
	// getTradeList()
	
	
	// getTradeContent()
	public TradeDTO getTradeContent(int t_num){
		TradeDTO dto = null;
		
		try {
			con = getCon();
			sql = "select * from seastu_trade where t_num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, t_num);
			
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				
				dto = new TradeDTO();
				dto.setContent(rs.getString("content"));
				dto.setFile(rs.getString("file"));
				dto.setT_num(rs.getInt("t_num"));
				dto.setSeller(rs.getString("seller"));
				dto.setReg_date(rs.getTimestamp("reg_date"));
				dto.setTitle(rs.getString("title"));
				dto.setPrice(rs.getInt("price"));
				dto.setStatus(rs.getInt("status"));
				dto.setBook_price(rs.getInt("book_price"));
				dto.setPublisher(rs.getString("publisher"));
			}
			
			System.out.println("DAO : 글 내용 가져오기 완료");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		
		return dto;
	}
	// getTradeContent()
	
	
	// updateTrade(dto)
	public void updateTrade(TradeDTO dto){
		try {
			con = getCon();
			sql = "update seastu_trade set title=?,content=?,status=?,price=? where t_num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getTitle());
			pstmt.setString(2, dto.getContent());
			pstmt.setInt(3, dto.getStatus());
			pstmt.setInt(4, dto.getPrice());
			pstmt.setInt(5, dto.getT_num());
			
			pstmt.executeUpdate();
			
			System.out.println("DAO : 중고거래 글 수정 완료");
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		
	}
	// updateTrade(dto)
	
	
	// deleteTrade(t_num)
	public void deleteTrade(int t_num){
		try {
			con = getCon();
			sql = "delete from seastu_trade	where t_num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, t_num);
			
			pstmt.executeUpdate();
			
			System.out.println("DAO : 글 삭제 완료");
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		
	}
	// deleteTrade(t_num)
	
}
