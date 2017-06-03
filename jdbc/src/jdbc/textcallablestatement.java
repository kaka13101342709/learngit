package jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class textcallablestatement {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test();
	}
	static void test(){
		Connection conn = DBUtil.open();
		try {
			CallableStatement cstmt = conn.prepareCall("{call allusers()}");
			ResultSet rs = cstmt.executeQuery();
			while(rs.next()){
				int id = rs.getInt(1);
				String username = rs.getString(2);
				String password = rs.getString(3);
				int age = rs.getInt(4);
				boolean sex = rs.getBoolean(5);
				System.out.println(id+","+username+","+password+","+age+","+sex);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
