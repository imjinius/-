package seastu.chat.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class ChatDAO {
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
	
	
	// sendChat(t_num,dto)
	public void sendChat(int t_num, ChatDTO dto){
		String receiver = null;
		int c_num = 0;
		
		try {
			con = getCon();
			sql = "select max(c_num) from seastu_chat";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				c_num = rs.getInt(1)+1;
			}
			
			
			sql = "select seller from seastu_trade where t_num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, t_num);
			
			rs = pstmt.executeQuery();
			if(rs.next()){
				receiver = rs.getString(1);
			}
			
			sql = "insert into seastu_chat values(?,?,?,?,?,0,now())";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, c_num);
			pstmt.setInt(2, t_num);
			pstmt.setString(3, dto.getSender());
			pstmt.setString(4, receiver);
			pstmt.setString(5, dto.getContent());
			
			pstmt.executeUpdate();
			
			System.out.println("DAO : 채팅 보내기 완료");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
	}
	// sendChat(t_num,dto)
	
	
	// sendReply(c_t_num,dto)
	public void sendReply(int c_t_num, ChatDTO dto){
		int c_num = 0;
		
		try {
			con = getCon();
			sql = "select max(c_num) from seastu_chat";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				c_num = rs.getInt(1)+1;
			}
			
			sql = "insert into seastu_chat values(?,?,?,?,?,1,now())";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, c_num);
			pstmt.setInt(2, c_t_num);
			pstmt.setString(3, dto.getSender());
			pstmt.setString(4, dto.getReceiver());
			pstmt.setString(5, dto.getContent());
			
			pstmt.executeUpdate();
			
			System.out.println("DAO : 답장 보내기 완료");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
	}
	
	// sendReply(c_t_num,dto)
	
	
	// getChatContent(sender)
	public List<ChatDTO> getChatContent(String id, String talker, int c_t_num){
		List<ChatDTO> chatContents = new ArrayList<ChatDTO>();
		try {
			con = getCon();
			sql = "select * from seastu_chat where sender=? and receiver=? and c_t_num=? or sender=? and receiver=? and c_t_num=?"
					+ " order by c_num";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, talker);
			pstmt.setInt(3, c_t_num);
			pstmt.setString(4, talker);
			pstmt.setString(5, id);
			pstmt.setInt(6, c_t_num);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				ChatDTO dto = new ChatDTO();
				dto.setC_num(rs.getInt("c_num"));
				dto.setContent(rs.getString("content"));
				dto.setReceiver(rs.getString("receiver"));
				dto.setSend_date(rs.getTimestamp("send_date"));
				dto.setSender(rs.getString("sender"));
				dto.setChat_ref(rs.getInt("chat_ref"));
				dto.setC_t_num(rs.getInt("c_t_num"));
				
				chatContents.add(dto);
			}
			System.out.println("DAO : 쪽지 내용 저장 완료");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		return chatContents;
	}
	// getChatContent(sender)
	
	
	// getChatList(id)
	public List<ChatDTO> getChatList(String id){
		List<ChatDTO> chatLists = new ArrayList<ChatDTO>();
		
		try {
			con = getCon();
			sql = "select * from seastu_chat where receiver=? and chat_ref=0 or sender=? and chat_ref=0";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, id);
			
			rs = pstmt.executeQuery();
			while(rs.next()){
				ChatDTO dto = new ChatDTO();
				dto.setC_num(rs.getInt("c_num"));
				dto.setContent(rs.getString("content"));
				dto.setReceiver(rs.getString("receiver"));
				dto.setSend_date(rs.getTimestamp("send_date"));
				dto.setSender(rs.getString("sender"));
				dto.setChat_ref(rs.getInt("chat_ref"));
				dto.setC_t_num(rs.getInt("c_t_num"));
				
				chatLists.add(dto);
			}
			System.out.println("DAO: 채팅리스트 가져오기 완료");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		return chatLists;
	}
	// getChatList(id)

	
	// chatCheck();
	public int chatCheck(String id, int c_t_num){
		int result = 0;
		try {
			con = getCon();
			sql = "select sender from seastu_chat where sender=? and c_t_num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setInt(2, c_t_num);
			
			rs = pstmt.executeQuery();
			if(rs.next()){
				result = 1;
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		
		
		return result;
	}
	// chatCheck();
	
}
