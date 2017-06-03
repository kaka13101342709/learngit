package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.PreparedStatement;

public class Test {

	public static void main(String[] args) {
		createTable();
	}
	static void createTable(){
		Connection conn = DBUtil.open();
		String sql = "select * from users";
		PreparedStatement pstmt;
		try {
			pstmt = (PreparedStatement) conn.prepareStatement(sql);   
	       // pstmt.executeUpdate();  
			//ResultSet rs = pstmt.executeQuery();
			/*while(rs.next()){
				int id = rs.getInt(1);
				String username = rs.getString(2);
				String password = rs.getString(3);
				int age = rs.getInt(4);
				boolean sex = rs.getBoolean(5);
				System.out.println(id+","+username+","+password+","+age+","+sex);
			}*/
	        pstmt.close();  
	        conn.close();  
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.close(conn);
		}

	}
}
