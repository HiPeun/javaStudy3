package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.hanul.common.DBManager;

import dto.MemberDTO;

public class MemberDAO {

	// 회원 등록
	public int insertMember(MemberDTO mDto) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int result = 0;
		try {

			con = DBManager.getConnection();
			String sql = "INSERT INTO member VALUES(?, ?, ?, ?, sysdate)";

			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, mDto.getId());
			pstmt.setString(2, mDto.getPw());
			pstmt.setString(3, mDto.getName());
			pstmt.setString(4, mDto.getEmail());

			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, null);
			

		}

		return result;
	}

	// 회원 수정 부분 ㄴ
	public int updateMember(MemberDTO mDto) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			con = DBManager.getConnection();
			String sql = "UPDATE member SET pw=?, name=?, email=? WHERE id=?";

			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, mDto.getPw());
			pstmt.setString(2, mDto.getName());
			pstmt.setString(3, mDto.getEmail());
			pstmt.setString(4, mDto.getId());

			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, null);
			
		}

		return result;
	}

	public MemberDTO getMemberById(String id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MemberDTO member = null;

		try {
			con = DBManager.getConnection();
			String sql = "SELECT * FROM member WHERE id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				String memberId = rs.getString("id");
				String password = rs.getString("pw");
				String name = rs.getString("name");
				String email = rs.getString("email");
				member = new MemberDTO(memberId, password, name, email);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		DBManager.close(con, pstmt, rs);
		
		}
		return member;
	} // 회원 삭제 부분

	public int deleteMember(MemberDTO mDto) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			con = DBManager.getConnection();
			String sql = "DELETE FROM member WHERE id=? AND pw=?";

			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, mDto.getId());
			pstmt.setString(2, mDto.getPw());

			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, null);
		}

		return result;
	}

	// 회원 검색 부분
	public MemberDTO selectMember(MemberDTO mDto) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MemberDTO member = null;
		try {
			con = DBManager.getConnection();
			String sql = "SELECT * FROM member WHERE id=? AND pw=?";

			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, mDto.getId());
			pstmt.setString(2, mDto.getPw());

			rs = pstmt.executeQuery();

			if (rs.next()) {
				// 회원 정보가 존재할 경우 MemberDTO 객체를 생성하고 정보를 설정합니다.
				member = new MemberDTO();
				member.setId(rs.getString("id"));
				member.setPw(rs.getString("pw"));
				member.setName(rs.getString("name"));
				member.setEmail(rs.getString("email"));
				member.setIndate(rs.getDate("indate"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 자원 해제
		
			DBManager.close(con, pstmt, rs);
			
		}

		// 조회된 회원 정보를 담은 MemberDTO 객체를 반환합니다.
		return member;
	}

	// 회원 로그인 부분

	public int LoginMember(MemberDTO mDto) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			con = DBManager.getConnection();
			String sql = "SELECT * FROM member WHERE id=? AND pw=?";

			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, mDto.getId());
			pstmt.setString(2, mDto.getPw());

			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		DBManager.close(con, pstmt, null);
		}

		return result;
	}
	// 회원 목록 조회 부분

	public List<MemberDTO> listMembers() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<MemberDTO> memberList = new ArrayList<>();

		try {
			con = DBManager.getConnection();
			String sql = "SELECT * FROM member";

			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			// while 문으로 회원목록을 다 조회한다.
			while (rs.next()) {
				// 회원 정보를 가져와서 MemberDTO 객체 생성
				String id = rs.getString("id");
				String pw = rs.getString("pw");
				String name = rs.getString("name");
				String email = rs.getString("email");
				MemberDTO member = new MemberDTO(id, pw, name, email);

				// 리스트에 회원 추가
				memberList.add(member);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, rs);
		}

		// 회원 목록 반환
		return memberList;
	}
	
	public boolean isExistingId(String id) {
		//값을 초기화 시켜 빈 박스로 만들어야 새로운 값을 담을 수 있기 때문에 초기화를 시켜준다. 
	    Connection con = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    boolean exists = false;
	    try {
	        con = DBManager.getConnection(); //내가 만든 DBManager을 con에 담는다. 
	        String sql = "SELECT COUNT(*) AS cnt FROM member WHERE id = ?";  //
	        
	        pstmt = con.prepareStatement(sql);
	        pstmt.setString(1, id);
	        
	        rs = pstmt.executeQuery();
	        if (rs.next()) {
	            int count = rs.getInt("cnt");
	            exists = count > 0;
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        DBManager.close(con, pstmt, rs);
	    }
	    return exists;
	}


	public boolean isExistingId1(String id) {
		// TODO Auto-generated method stub
		return false;
	}

	public MemberDTO comparisonMember(String id) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
