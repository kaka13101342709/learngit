package WordDemo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Server1 implements Runnable {
	
	ServerSocket serverSocket;
    static Socket socket ;
    InputStream is ;
    //OutputStream os;
    InputStreamReader isr;
    PrintWriter pw;
    BufferedReader br = null;
 
public void run() {
	 try {
			serverSocket = new ServerSocket(10000);
			socket = serverSocket.accept();

			System.out.println("1�ſͻ�������");
			is = socket.getInputStream();
			isr = new InputStreamReader(is);
			br = new BufferedReader(isr);
			
			//os = socket.getOutputStream();
     	//pw = new PrintWriter(os);
		} catch (IOException e1) {
			System.out.println("�쳣1");
			e1.printStackTrace();
		}
				System.out.println("����˶��߳�1ִ��");
					try {
						while(true){
							String s = br.readLine();
							System.out.println(s);
							new Send().sendmessage2(s);
							//jdbc
							String sql = "insert record(Client) values(?)";
							Connection conn = DBUtil.open();
							try {
								PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement(sql);
								pstmt.setString(1,s);
								
								pstmt.executeUpdate();
								
							} catch (SQLException e) {
								System.out.println("�쳣2");
								e.printStackTrace();
							}finally{
								DBUtil.close(conn);
							}
							
							
							}
						
						} catch (Exception e) {
						System.out.println(e);
					}
				}		
}
