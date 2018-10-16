package kr.co.happy;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class BoardDAO {	
	public static BoardDAO instance = null;
	public static final int LIST_CNT = 10;
	
	private BoardDAO() {}
	
	public static BoardDAO getInstance() {
		if(instance == null) {
			instance = new BoardDAO();
		}
		return instance;
	}
	
	public ArrayList<BoardDTO> getBoardList(int btype, int page) {
		ArrayList<BoardDTO> arrDTO = new ArrayList<BoardDTO>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		int beginNum = ((page - 1) * LIST_CNT) + 1;
		int stopNum = LIST_CNT * page;
		
		String sql = " select * " + 
				" from( " + 
				"    select h.*, row_number() over(order by seq desc) as rnum " + 
				"    from h_board h " + 
				"    where h.btype = ? " + 
				"    ) " + 
				" where rnum between ? and ? ";
		
		try {
			conn = DBConnector.getConn();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, btype);
			ps.setInt(2, beginNum);
			ps.setInt(3, stopNum);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				BoardDTO dto = new BoardDTO();
				dto.setBid(rs.getInt("bid"));
				dto.setSeq(rs.getInt("seq"));
				dto.setBtitle(rs.getString("btitle"));
				dto.setBregdate(rs.getString("bregdate"));
				dto.setBtype(rs.getInt("btype"));
				arrDTO.add(dto);
				
				System.out.println(dto);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnector.close(conn, ps, rs);
		}
		
		return arrDTO;
	}
	
	public int getPageCnt(int btype) {
		int pageCnt = 1;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = " select ceil(count(bid) / ?) as pageCnt "
				+ " from h_board "
				+ " where btype = ? ";
		
		try {
			conn = DBConnector.getConn();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, LIST_CNT);
			ps.setInt(2, btype);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				pageCnt = rs.getInt("pageCnt");
				
				System.out.println(pageCnt);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnector.close(conn, ps, rs);
		}
		
		return pageCnt;
	}
	
	public BoardDTO getBoardDetail(int bid) {
		BoardDTO bt = new BoardDTO();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = " select bid, btype, btitle, bcontent, bregdate, pw "
				+ " from h_board "
				+ " where bid = ? ";
		
		try {
			conn = DBConnector.getConn();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, bid);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				bt.setBid(rs.getInt("bid"));
				bt.setBtype(rs.getInt("btype"));
				bt.setBtitle(rs.getString("btitle"));
				bt.setBcontent(rs.getString("bcontent"));
				bt.setBregdate(rs.getString("bregdate"));
				bt.setPw(rs.getString("pw"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnector.close(conn, ps, rs);
		}		
		
		return bt;
	}
	
	public int setInsert(int btype, String btitle, String bcontent, String pw) {
		int execute = 0;
		Connection conn = null;
		PreparedStatement ps = null;
		
		String sql = " insert into h_board(bid, btype, seq, btitle, bcontent, pw) "
				+ "values ((select nvl(max(bid), 0) + 1 from h_board), ?, (select nvl(max(seq), 0) + 1 from h_board where btype = ?), ?, ?, ?)";
		
		try {
			conn = DBConnector.getConn();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, btype);
			ps.setInt(2, btype);			
			ps.setString(3, btitle);
			ps.setString(4, bcontent);
			ps.setString(5, pw);
			execute = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnector.close(conn, ps);
		}
		
		return execute;
	}
	
	public String pw_check(int bid) {
		String execute = "0";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		
		String sql = " select pw "
				+ " from h_board "
				+ " where bid = ?";
		
		try {
			conn = DBConnector.getConn();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, bid);
			rs = ps.executeQuery();
		
			while(rs.next()) {
				execute = rs.getString("pw");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnector.close(conn, ps, rs);
		}
		
		return execute;
	}
	
	public int delete(int bid) {
		int execute = 0;
		Connection conn = null;
		PreparedStatement ps = null;
		
		String sql = " delete from h_board "
				+ " where bid = ?";
		
		try {
			conn = DBConnector.getConn();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, bid);
			execute = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnector.close(conn, ps);
		}
		
		return execute;
	}
	
	public BoardDTO getOneBid(int bid) {
		BoardDTO dto = new BoardDTO();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		
		String sql = " select bid, btype, seq, btitle, bcontent, pw "
				+ " from h_board "
				+ " where bid = ?";
		
		try {
			conn = DBConnector.getConn();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, bid);
			rs = ps.executeQuery();
		
			while(rs.next()) {
				dto.setBid(rs.getInt("bid"));
				dto.setBtype(rs.getInt("btype"));
				dto.setSeq(rs.getInt("seq"));
				dto.setBtitle(rs.getString("btitle"));
				dto.setBcontent(rs.getString("bcontent"));
				dto.setPw(rs.getString("pw"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnector.close(conn, ps, rs);
		}
		
		return dto;
	}
	
	public int setUpdate(int bid, String btitle, String bcontent, String pw) {
		int execute = 0;
		Connection conn = null;
		PreparedStatement ps = null;
		
		
		String sql = " update h_board "
				+ " set btitle = ?, bcontent = ?, pw = ? "
				+ " where bid = ? ";
		
		try {
			conn = DBConnector.getConn();
			ps = conn.prepareStatement(sql);
			ps.setString(1, btitle);
			ps.setString(2, bcontent);
			ps.setString(3, pw);
			ps.setInt(4, bid);
			execute = ps.executeUpdate();
		
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnector.close(conn, ps);
		}
		
		return execute;
		
	}
}
