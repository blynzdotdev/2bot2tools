package me.permafreez;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Test {

	public static void main(String[] args) {
		
		String url = "jdbc:sqlite:/home/dani1/test/test.sql";
		Connection conn = null;
		
		try {
			conn = DriverManager.getConnection(url);
			System.out.println("Connection successful");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		String dc = "lol#6869";
		String mc = "zsdav";
		
		Test test = new Test();
		
		test.insert(dc, mc, conn);
	}
	
	public void insert(String dc, String mc, Connection conn) {
		String sql = "INSERT INTO test(dc,mc) VALUES(?,?)";
		String update = "update test set mc = ? where dc = ?";
		
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, dc);
			pstmt.setString(2, mc);
			pstmt.executeUpdate();
		} catch (Exception e) {
			try (PreparedStatement pstmt = conn.prepareStatement(update)) {
			pstmt.setString(2, dc);
			pstmt.setString(1, mc);
			pstmt.executeUpdate();
			} catch (Exception e2) {
				System.out.println(e2.getMessage());
			}
		}
	}
	
}
