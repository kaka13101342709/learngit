package qqqq;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

	public static void main(String[] args) throws Exception {
		// 1.创建客户端Socket，指定服务器地址和端口
		Socket socket = new Socket("localhost",10003);
		//2.获取输入流，向客户端发送信息
		OutputStream os = socket.getOutputStream();//字节流输入
		PrintWriter pw = new PrintWriter(os);
		pw.write("用户名：admin，密码：123");
		pw.flush();
		socket.shutdownOutput();
		
		//获取输入流
		InputStream is = socket.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String info = null;
		while((info = br.readLine())!=null){
			System.out.println("我是客户端，服务器说："+info);
			
		}
		
		br.close();
		pw.close();
		os.close();
		socket.close();
	}

}
