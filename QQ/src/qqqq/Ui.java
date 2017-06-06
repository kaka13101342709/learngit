package qqqq;

import java.awt.*;
import java.awt.event.*;
import java.net.DatagramSocket;

public class Ui {
	
	Frame f ;
	Button b;
	TextArea ta1;
	TextField ta2;
	public String text = null;
	
	public void Show(){
		f = new Frame("my awt");//创建frame窗体
		
		f.setSize(800, 700);//设置x，y长度
		f.setLocation(300, 200);//设置窗口位置
		
		f.setLayout(new FlowLayout());//把窗体设置为流式布局
		
		b = new Button("发送消息");//定义组件按钮
		ta1 = new TextArea(25,80);
		ta2 = new TextField(80);
					// 把组件添加到窗体中
		f.add(ta1);
		f.add(ta2);
		f.add(b);
		
		myEvent();
		
		f.setVisible(true);//让窗体显示
	}
	public void myEvent(){
		
		b.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e)	
					{
						text = ta2.getText();
						ta1.setText(text);
						ta2.setText("");						
					}
				});
		
		f.addWindowListener(new Mywin()); 
		
	}

}
