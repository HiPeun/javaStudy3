package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcEx01 {

	public static void main(String[] args) {

		String url = "jdbc:oracle:thin:@10.211.55.3:1521:xe";// 주소는 현장주소 :xe DNS주소가 올수도 있다. //오라클
		String user = "scott"; // 데이터베이스 아이디
		String password = "tiger"; // 데이터베이스 비밀번호 (오라클 깔면서 설정한 비밀번호)
		
		//1. 오라클 드라이버 연결
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			System.out.println("드라이버 연결됨"); // 드라이버 연결시 출력
			
			
			//2. 데이터베이스 연결
			Connection con = DriverManager.getConnection(url, user, password);
			System.out.println("DB 연결됨"); // 데이터베이스 연결시 출력
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}