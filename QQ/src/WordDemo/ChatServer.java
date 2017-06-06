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
		//设置界面
				mainJFrame = new JFrame("服务端聊天程序");
				container = mainJFrame.getContentPane();//先吧组件添加到容器container，再把容器内容添加到框架Jframe
				//聊天信息展示框
				showTextArea = new JTextArea();
				showTextArea.setEditable(false);//不可编辑
				showTextArea.setLineWrap(true);//自动换行
				jSPane = new JScrollPane(showTextArea);
				
				//聊天信息输入框
				msgTextField = new JTextField();
				msgTextField.setColumns(30);
				 // 发送按键
		        sentButton = new JButton("发送");
		        // 嵌板有聊天信息输入框和发送按键
		        panel = new JPanel();
		        panel.setLayout(new FlowLayout());
		        panel.add(msgTextField);
		        panel.add(sentButton);
		     // 容器包含聊天信息展示框和嵌板
		        container.add(jSPane, BorderLayout.CENTER);
		        container.add(panel, BorderLayout.SOUTH);
		     // 主界面，要定义在后面
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
		        	showTextArea.append("链接成功\n");
		        	
		        	thread = new Thread(this);
		        	thread.setPriority(Thread.MAX_PRIORITY);
		        	thread.start();
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					showTextArea.append("链接失败\n");
					e.printStackTrace();
				}
	}
	public void run(){
		System.out.println("服务端多线程执行");
		try {
			while(true){
				String s = br.readLine();
				showTextArea.append("客户端说"+s);
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
					showTextArea.append("服务端说（" + string + "）"  + "\n");
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
