package jdbc;

public class users {
	private int id;
	private String username;
	
	public int getId(){
		return id;
	}
	public String getName(){
		return username;
	}
	public void setId(int id){
		this.id = id;
	}
	public void setName(String username){
		this.username = username;
	}
	public String toString(){
		return id+"..."+username;
	}
}
