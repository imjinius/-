package seastu.member.db;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.naming.*;
import javax.sql.DataSource;

import seastu.board.db.BoardDTO;

public class MemberDAO {
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

	
	// insertMember(dto)
	public void insertMember(MemberDTO dto){
    
        try{
            con = getCon();
            sql = "insert into seastu_member values (?,?,?,?,?,?,?,now(),0,0)";
            pstmt = con.prepareStatement(sql);
            
            pstmt.setString(1, dto.getId());
            pstmt.setString(2, dto.getPw());
            pstmt.setString(3, dto.getName());
            pstmt.setString(4, dto.getBirth());
            pstmt.setString(5, dto.getTel());
            pstmt.setInt(6, dto.getZip_code());
            pstmt.setString(7, dto.getAddr());
            
            pstmt.executeUpdate();
            
            System.out.println("DAO : 회원가입 처리 완료");
        } catch(Exception e){
            e.printStackTrace();
        } finally {
        	closeDB();
        }
    }
	// insertMember(MemberDTO dto)

	
	
	// checkMember(String id, String pw)
	public int checkMember(String id, String pw){
    
        int result = 0;
        try{
            con = getCon();
            sql = "select id from seastu_member where id=?";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, id);
            rs = pstmt.executeQuery();
            if(rs.next()){
                sql = "select pw from seastu_member where id=?";
                pstmt = con.prepareStatement(sql);
                pstmt.setString(1, id);
                rs = pstmt.executeQuery();
                if(rs.next()){
                	if(pw.equals(rs.getString(1))){
                		result = 1;
                	}
                }
                	
            }
        } catch(Exception e){
            e.printStackTrace();
        } finally {
        	closeDB();
        }
        return result;
    }
	// checkMember(String id, String pw)


	// updateMember(String id)
	public MemberDTO updateMember(String id){
		MemberDTO dto = null;
    
        try{
            con = getCon();
            sql = "select * from seastu_member where id=?";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, id);
            rs = pstmt.executeQuery();
            if(rs.next()){
            
                dto = new MemberDTO();
                dto.setAddr(rs.getString("addr"));
                dto.setBirth(rs.getString("birth"));
                dto.setId(id);
                dto.setName(rs.getString("name"));
                dto.setPw(rs.getString("pw"));
                dto.setTel(rs.getString("tel"));
                dto.setZip_code(rs.getInt("zip_code"));
                dto.setW_count(rs.getInt("w_count"));
                dto.setRe_count(rs.getInt("re_count"));
                System.out.println("DAO : 회원정보 가져오기 완료");
            }
        }catch(Exception e){
            e.printStackTrace();
        } finally {
        	closeDB();
        }
        return dto;
    }
	// updateMember(String id)

	
	// checkMemberId(String pw)
	public int checkMemberId(String id){
		int result = 0;
		
		try{
			con = getCon();
			sql = "select id from seastu_member where id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			
			if(rs.next())
				result = 1; // id 존재
			
			System.out.println("DAO : result : "+result);
		}catch(Exception e){
			e.printStackTrace();
		} finally {
			closeDB();
		}
		return result;
	}
	// checkMemberId(String pw)
	

	// memberUpdatePro(dto)
	public void memberUpdatePro(MemberDTO dto){
    
        try{
            con = getCon();
            sql = "update seastu_member set name=?,birth=?,tel=?,zip_code=?,addr=? where id=?";
            pstmt = con.prepareStatement(sql);
            
            pstmt.setString(1, dto.getName());
            pstmt.setString(2, dto.getBirth());
            pstmt.setString(3, dto.getTel());
            pstmt.setInt(4, dto.getZip_code());
            pstmt.setString(5, dto.getAddr());
            pstmt.setString(6, dto.getId());
            
            pstmt.executeUpdate();
            
            System.out.println("DAO : 회원정보 수정 완료");
        }catch(Exception e){
            e.printStackTrace();
        } finally {
        	closeDB();
        }
    }
	// memberUpdatePro(dto)
	
	//memberPwUpdate(updatePw)
	public void memberPwUpdate(String id, String pw){
	    
        try{
            con = getCon();
            sql = "update seastu_member set pw=? where id=?";
            pstmt = con.prepareStatement(sql);
            
            pstmt.setString(1, pw);
            pstmt.setString(2, id);
            
            pstmt.executeUpdate();
            
            System.out.println("DAO : 비밀번호 수정 완료");
        }catch(Exception e){
            e.printStackTrace();
        } finally {
        	closeDB();
        }
    }
	//memberPwUpdate(updatePw)
	
	// deleteMember(String id)
	public void deleteMember(String id){
    
        try{
            con = getCon();
            
            sql = "delete from seastu_member where id=?";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, id);
            pstmt.executeUpdate();
            
            System.out.println("DAO : 회원정보 삭제 완료");
        } catch(Exception e){
            e.printStackTrace();
        } finally {
        	closeDB();
        }
    }
	// deleteMember(String id)

	
	
	// getMemberList
	public List<MemberDTO> getMemberList(int startRow, int pageSize){
		List<MemberDTO> memberList = new ArrayList<MemberDTO>();
		try {
			con = getCon();
			sql= "select * from seastu_member limit ?,?";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, startRow - 1);
			pstmt.setInt(2, pageSize);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				MemberDTO dto = new MemberDTO();
				dto.setAddr(rs.getString("addr"));
				dto.setBirth(rs.getString("birth"));
				dto.setId(rs.getString("id"));
				dto.setName(rs.getString("name"));
				dto.setPw(rs.getString("pw"));
				dto.setReg_date(rs.getTimestamp("reg_date"));
				dto.setTel(rs.getString("tel"));
				dto.setZip_code(rs.getInt("zip_code"));
				dto.setW_count(rs.getInt("w_count"));
				dto.setRe_count(rs.getInt("re_count"));
				
				memberList.add(dto);
			}
			
			
			
			System.out.println("DAO : 회원 정보 조회 완료");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		
		return memberList;
		
	}
	
	// getMemberList
	
	
	// deleteMembers(mems)
	public void deleteMembers(String[] mems){
		try {
			con = getCon();
			
			for(int i=0;i<mems.length;i++){
				sql = "delete from seastu_member where id=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, mems[i]);
				
				pstmt.executeUpdate();
				System.out.println("DAO : "+mems[i]+"님 회원탈퇴 완료");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		
		
	}
	
	// deleteMembers(mems)
	
	// getMemberCount()
	public int getMemberCount(){
		int result = 0;
		
		try {
			con = getCon();
			sql = "select count(id) from seastu_member";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				result = rs.getInt(1);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		
		return result;
	}
	// getMemberCount()
	
	
	// getAdminPw()
	public String getAdminPw(String id){
		String pw = null;
		try {
			con = getCon();
			sql = "select pw from seastu_member where id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				pw = rs.getString(1);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		
		return pw;
		
	}
	// getAdminPw()
	
	// getSearchCount()
	public int getSearchCount(int key, String content){
		int result = 0;
		
		try {
			con = getCon();
			
			if(key == 1){
				sql = "select count(id) from seastu_member where id like ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, "%"+content+"%");
			}
			if(key == 2){
				sql = "select count(name) from seastu_member where name like ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, "%"+content+"%");
			}
			
			rs = pstmt.executeQuery();
			if(rs.next()){
				result = rs.getInt(1);
			}
			System.out.println("DAO : result : "+result);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		
		return result;
	}
	// getSearchCount()
	

	
	// searchMemberList(s_keyword,s_content)
	public List<MemberDTO> searchMemberList(int startRow, int pageSize, int key, String s_content) {
		List<MemberDTO> searchList = new ArrayList<MemberDTO>();

		try {
			con = getCon();
			
			if(key == 1){
				sql = "select * from seastu_member where id like ? order by id limit ?,?";
				pstmt = con.prepareStatement(sql);
				
				pstmt.setString(1, "%"+s_content+"%");
				pstmt.setInt(2, startRow - 1);
				pstmt.setInt(3, pageSize);
				
				rs = pstmt.executeQuery();
				
				while (rs.next()) {
					MemberDTO dto = new MemberDTO();
					dto.setAddr(rs.getString("addr"));
					dto.setBirth(rs.getString("birth"));
					dto.setId(rs.getString("id"));
					dto.setName(rs.getString("name"));
					dto.setPw(rs.getString("pw"));
					dto.setReg_date(rs.getTimestamp("reg_date"));
					dto.setTel(rs.getString("tel"));
					dto.setZip_code(rs.getInt("zip_code"));
					dto.setW_count(rs.getInt("w_count"));
					dto.setRe_count(rs.getInt("re_count"));
					
					searchList.add(dto);
				}
				
			}
			if(key == 2){
				sql = "select * from seastu_member where name like ? order by id limit ?,?";
				pstmt = con.prepareStatement(sql);
				
				pstmt.setString(1, "%"+s_content+"%");
				pstmt.setInt(2, startRow - 1);
				pstmt.setInt(3, pageSize);
				
				rs = pstmt.executeQuery();
				
				while (rs.next()) {
					MemberDTO dto = new MemberDTO();
					dto.setAddr(rs.getString("addr"));
					dto.setBirth(rs.getString("birth"));
					dto.setId(rs.getString("id"));
					dto.setName(rs.getString("name"));
					dto.setPw(rs.getString("pw"));
					dto.setReg_date(rs.getTimestamp("reg_date"));
					dto.setTel(rs.getString("tel"));
					dto.setZip_code(rs.getInt("zip_code"));
					dto.setW_count(rs.getInt("w_count"));
					dto.setRe_count(rs.getInt("re_count"));
					
					searchList.add(dto);
				}
				
			}

			System.out.println(" DAO : 게시판 검색 글 가져오기 완료");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}

		return searchList;
	}
	// searchMemberList(s_keyword,s_content)

}
