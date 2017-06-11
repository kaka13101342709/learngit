package WordDemo;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class Send {
	static Socket socket1 = Server1.socket;
	static Socket socket2 = Server2.socket;
	 OutputStream os;
	 PrintWriter pw;
	 public void sendmessage1(String s) throws IOException{
		 os = socket1.getOutputStream();
     	 pw = new PrintWriter(os);
		 pw.write(s);
		 pw.flush();
	 }
	 public void sendmessage2(String s) throws IOException{
		 os = socket2.getOutputStream();
     	 pw = new PrintWriter(os);
		 pw.write(s);
		 pw.flush();
	 }
}
