package qqqq;

import java.net.*;

public class ChatDemo {

	public static void main(String[] args) throws Exception {
		DatagramSocket sendSocket = new DatagramSocket();
		DatagramSocket receSocket = new DatagramSocket(60000);
		
		//Ui u = new Ui();
		//u.Show();
		
		new Thread(new Send(sendSocket)).start();
		new Thread(new Receive(receSocket)).start();
		
		
	}

}
