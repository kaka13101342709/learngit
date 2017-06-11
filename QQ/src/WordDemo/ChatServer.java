package WordDemo;

public class ChatServer{
		
	public ChatServer(){
		new Thread(new Server1()).start();  
		new Thread(new Server2()).start();
		        
	}
	

	public static void main(String[] args) {
		new ChatServer();
	}
		  
}


