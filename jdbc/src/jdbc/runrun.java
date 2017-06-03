package jdbc;

import java.util.List;

public class runrun {

	public static void main(String[] args) {
		tb3 dao = new usersIml();
		users u = new users();
		u.setName("kakaxx");
		List<users> list = dao.query();
 
	}

}
