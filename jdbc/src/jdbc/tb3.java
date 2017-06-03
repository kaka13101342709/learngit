package jdbc;

import java.util.List;

public interface tb3 {
	public void add(users u);
	public void update(users u);
	public void delete(users u);
	public users getUsersById(users u);
	public List<users> query();
}
