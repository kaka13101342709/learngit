package WordDemo;

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.*;

public class ChatClient implements Runnable{
	JFrame mainJFrame;
	JTextField msgTextField;
	JTextArea showTextArea;
	JButton sentButton;
	JScrollPane jSPane;
	Container container;
	JPanel panel;
	
	Thread thread = null;

    Socket socket;
    InputStream is;
    OutputStream os;
    InputStreamReader isr;
    PrintWriter pw;
    BufferedReader br;
	
	public ChatClient(int port){
		//���ý���
		mainJFrame = new JFrame("�ͻ����������");
		container = mainJFrame.getContentPane();//�Ȱ������ӵ�����container���ٰ�����������ӵ����Jframe
		//������Ϣչʾ��
		showTextArea = new JTextArea();
		showTextArea.setEditable(false);//���ɱ༭
		showTextArea.setLineWrap(true);//�Զ�����
		jSPane = new JScrollPane(showTextArea);
		
		//������Ϣ�����
		msgTextField = new JTextField();
		msgTextField.setColumns(30);
		 // ���Ͱ���
        sentButton = new JButton("����");
        // Ƕ����������Ϣ�����ͷ��Ͱ���
        panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.add(msgTextField);
        panel.add(sentButton);
     // ��������������Ϣչʾ���Ƕ��
        container.add(jSPane, BorderLayout.CENTER);
        container.add(panel, BorderLayout.SOUTH);
     // �����棬Ҫ�����ں���
        mainJFrame.setSize(500, 400);
        mainJFrame.setVisible(true);
        mainJFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myEvent();

        try {
            // �����׽������ӵ�������
        	socket = new Socket("localhost",port);
        	os = socket.getOutputStream();
        	pw = new PrintWriter(os);
        	
        	is = socket.getInputStream();
			isr = new InputStreamReader(is);
			br = new BufferedReader(isr);
			
        	showTextArea.append("���ӳɹ�\n");
        	   
            // �����߳��ں�̨�����նԷ�����Ϣ
            thread = new Thread(this);
            thread.setPriority(Thread.MAX_PRIORITY);
            thread.start();

        } catch (IOException e) {
            showTextArea.append("�Բ���û�����ӵ�������\n" +"");
        }

    }
		
			
	public void run() {
		System.out.println("�ͻ��˶��߳�ִ��");
		while(true){
			try {
				showTextArea.append( "�����˵" + br.readLine());
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} catch (IOException e) {
				e.printStackTrace();
				showTextArea.append("����ʧ��");
			}
		}

	}
public void myEvent(){
	mainJFrame.addWindowListener(new WindowAdapter()
	{
		public void windowClosing(WindowEvent e)
		{
			System.exit(0);
		}
		
	});
	sentButton.addMouseListener(new MouseAdapter()
	{
		public void mouseClicked(MouseEvent e)
		{			
			String string = msgTextField.getText();
	        if (string.length() > 0) {
	            pw.write(string);
				pw.flush();
				showTextArea.append("�ͻ���˵��" + string + "��"  + "\n");
				msgTextField.setText(null);
	        }
		}
		
	});
	
}

}
