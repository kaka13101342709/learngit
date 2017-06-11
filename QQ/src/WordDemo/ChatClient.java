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
		//设置界面
		mainJFrame = new JFrame("客户端聊天程序");
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
            // 创建套接字连接到服务器
        	socket = new Socket("localhost",port);
        	os = socket.getOutputStream();
        	pw = new PrintWriter(os);
        	
        	is = socket.getInputStream();
			isr = new InputStreamReader(is);
			br = new BufferedReader(isr);
			
        	showTextArea.append("链接成功\n");
        	   
            // 创建线程在后台来接收对方的消息
            thread = new Thread(this);
            thread.setPriority(Thread.MAX_PRIORITY);
            thread.start();

        } catch (IOException e) {
            showTextArea.append("对不起，没能连接到服务器\n" +"");
        }

    }
		
			
	public void run() {
		System.out.println("客户端多线程执行");
		while(true){
			try {
				showTextArea.append( "服务端说" + br.readLine());
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} catch (IOException e) {
				e.printStackTrace();
				showTextArea.append("链接失败");
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
				showTextArea.append("客户端说（" + string + "）"  + "\n");
				msgTextField.setText(null);
	        }
		}
		
	});
	
}

}
