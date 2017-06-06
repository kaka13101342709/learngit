package qqqq;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	public static void main(String[] args) throws IOException {
		//1.创建一个服务器端socket，即ServerSocket，指定绑定的端口，并监听此端口
		ServerSocket serverSocket = new ServerSocket(10003);
		//2.调用accept（）方法开始监听，等待客户端的链接
		System.out.println("服务器启动");
		Socket socket = serverSocket.accept();
		//3.获取输入流，并读取客户端信息
		InputStream is = socket.getInputStream();
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(isr);
		String info = null;
		while ((info=br.readLine())!=null){
			System.out.println("客户端："+info);
		}
		socket.shutdownInput();//关闭输入流
		//4.获取输出流，响应客户端的请求
		OutputStream os = socket.getOutputStream();
		PrintWriter pw = new PrintWriter(os);//包装为打印流
		pw.write("你好");
		pw.flush();
		
		pw.close();
		os.close();
		br.close();
		isr.close();
		is.close();
		socket.close();
		serverSocket.close();
	}

}
