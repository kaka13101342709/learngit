package WordDemo;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;



public class ChatServer implements Runnable {
	
	JFrame mainJFrame;
	JTextField msgTextField;
	JTextArea showTextArea;
	JButton sentButton;
	JScrollPane jSPane;
	Container container;
	JPanel panel;
	
	Thread thread = null;
    ServerSocket serverSocket;
    Socket socket;
    InputStream is;
    OutputStream os;
    InputStreamReader isr;
    PrintWriter pw;
    BufferedReader br;

	public ChatServer(){
		//���ý���
				mainJFrame = new JFrame("������������");
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
					serverSocket = new ServerSocket(6666);
					socket = serverSocket.accept();
					
					is = socket.getInputStream();
					isr = new InputStreamReader(is);
					br = new BufferedReader(isr);
					
					os = socket.getOutputStream();
		        	pw = new PrintWriter(os);
		        	showTextArea.append("���ӳɹ�\n");
		        	
		        	thread = new Thread(this);
		        	thread.setPriority(Thread.MAX_PRIORITY);
		        	thread.start();
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					showTextArea.append("����ʧ��\n");
					e.printStackTrace();
				}
	}
	public void run(){
		System.out.println("����˶��߳�ִ��");
		try {
			while(true){
				String s = br.readLine();
				showTextArea.append("�ͻ���˵"+s);
				Thread.sleep(1000);
				
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
		// TODO Auto-generated method stub

	}

	public static void main(String[] args) {
		new ChatServer();

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
					showTextArea.append("�����˵��" + string + "��"  + "\n");
					msgTextField.setText(null);
					//jdbc
					
					String sql = "insert record(Server) values(?)";
					Connection conn = DBUtil.open();
					try {
						PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement(sql);
						pstmt.setString(1,string);
						
						pstmt.executeUpdate();
						
					} catch (SQLException e1) {
						e1.printStackTrace();
					}finally{
						DBUtil.close(conn);
					}
		        }
			}
			
		});
	}

}
