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
		// 1.�����ͻ���Socket��ָ����������ַ�Ͷ˿�
		Socket socket = new Socket("localhost",10003);
		//2.��ȡ����������ͻ��˷�����Ϣ
		OutputStream os = socket.getOutputStream();//�ֽ�������
		PrintWriter pw = new PrintWriter(os);
		pw.write("�û�����admin�����룺123");
		pw.flush();
		socket.shutdownOutput();
		
		//��ȡ������
		InputStream is = socket.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String info = null;
		while((info = br.readLine())!=null){
			System.out.println("���ǿͻ��ˣ�������˵��"+info);
			
		}
		
		br.close();
		pw.close();
		os.close();
		socket.close();
	}

}
