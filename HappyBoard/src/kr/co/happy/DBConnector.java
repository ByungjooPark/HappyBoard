package kr.co.happy;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBConnector {
	private DBConnector() {}
	
	public static Connection getConn() throws SQLException, ClassNotFoundException {
		Connection conn = null;
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String id = "hr";
		String pw = "hkitedu";
		
		Class.forName("oracle.jdbc.driver.OracleDriver");
		conn = DriverManager.getConnection(url, id, pw);
		
		System.out.println("DB연결");
		
		return conn;
	}
	
	public static void close(Connection conn, PreparedStatement ps, ResultSet rs) {
		if(rs != null) {
			try {
				rs.close();
			} catch (Exception e) {	
				
			}
		}
		
		if(ps != null) {
			try {
				ps.close();
			} catch (Exception e) {	
				
			}
		}
		
		if(conn != null) {
			try {
				conn.close();
			} catch (Exception e) {	
				
			}
		}
	}
	
	public static void close(Connection conn, PreparedStatement ps) {
		close(conn, ps, null);
	}
}
