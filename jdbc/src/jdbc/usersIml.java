package jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

public class usersIml implements tb3 {
	public void add(users u){
		String sql = "insert tb3(username) values(?)";
		Connection conn = DBUtil.open();
		try {
			PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement(sql);
			pstmt.setString(1, u.getName());
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.close(conn);
		}
		
	}

	@Override
	public void update(users u) {
		String sql = "update tb3 set username=? where id=?";	
		Connection conn = DBUtil.open();
		try {
			PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement(sql);
			pstmt.setString(1, u.getName());
			pstmt.setInt(2, u.getId());
		
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.close(conn);
		}
		
	}

	@Override
	public void delete(users u) {
		String sql = "delete from tb3 where id=?";	
		Connection conn = DBUtil.open();
		try {
			PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement(sql);
			
			pstmt.setInt(1, u.getId());
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.close(conn);
		}
		
		
	}

	@Override
	public users getUsersById(users u) {
		String sql = "select id,username from tb3";	
		Connection conn = DBUtil.open();
		try {
			Statement stmt = (Statement) conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			if(rs.next()){
				int id = rs.getInt(1);
				String username = rs.getString(2);
				users u1 = new users();
				u1.setId(id);
				u1.setName(username);
				
				return u1;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.close(conn);
		}
		
		return null;
	}

	@Override
	public List<users> query() {
		String sql = "select id,username from tb3";	
		Connection conn = DBUtil.open();
		List<users> list = new ArrayList<users>();
		try {
			PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				int id = rs.getInt(1);
				String username = rs.getString(2);
				users u1 = new users();
				u1.setId(id);
				u1.setName(username);
				list.add(u1);
				
				//System.out.println(list);
				
			}
			System.out.println(list);
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.close(conn);
		}
		
		return null;
	}
}
