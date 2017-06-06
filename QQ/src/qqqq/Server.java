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
		//1.����һ����������socket����ServerSocket��ָ���󶨵Ķ˿ڣ��������˶˿�
		ServerSocket serverSocket = new ServerSocket(10003);
		//2.����accept����������ʼ�������ȴ��ͻ��˵�����
		System.out.println("����������");
		Socket socket = serverSocket.accept();
		//3.��ȡ������������ȡ�ͻ�����Ϣ
		InputStream is = socket.getInputStream();
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(isr);
		String info = null;
		while ((info=br.readLine())!=null){
			System.out.println("�ͻ��ˣ�"+info);
		}
		socket.shutdownInput();//�ر�������
		//4.��ȡ���������Ӧ�ͻ��˵�����
		OutputStream os = socket.getOutputStream();
		PrintWriter pw = new PrintWriter(os);//��װΪ��ӡ��
		pw.write("���");
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
