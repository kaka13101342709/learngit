package WordDemo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Server2 implements Runnable {
	
	ServerSocket serverSocket;
    static Socket socket = null;
    InputStream is;
    OutputStream os;
    InputStreamReader isr;
    PrintWriter pw;
    BufferedReader br = null;

public void run() {
	 try {
			serverSocket = new ServerSocket(30000);
			socket = serverSocket.accept();
			System.out.println("2号客户端已连接");
			
			is = socket.getInputStream();
			isr = new InputStreamReader(is);
			br = new BufferedReader(isr);
			
			os = socket.getOutputStream();
     	pw = new PrintWriter(os);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	 
	 
				System.out.println("服务端多线程2执行");
					try {
						while(true){
							String s = br.readLine();
							System.out.println(s);
							new Send().sendmessage1(s);
							
							//jdbc
							String sql = "insert record(Client) values(?)";
							Connection conn = DBUtil.open();
							try {
								PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement(sql);
								pstmt.setString(1,s);
								
								pstmt.executeUpdate();
								
							} catch (SQLException e) {
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
