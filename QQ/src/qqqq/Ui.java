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
		f = new Frame("my awt");//����frame����
		
		f.setSize(800, 700);//����x��y����
		f.setLocation(300, 200);//���ô���λ��
		
		f.setLayout(new FlowLayout());//�Ѵ�������Ϊ��ʽ����
		
		b = new Button("������Ϣ");//���������ť
		ta1 = new TextArea(25,80);
		ta2 = new TextField(80);
					// �������ӵ�������
		f.add(ta1);
		f.add(ta2);
		f.add(b);
		
		myEvent();
		
		f.setVisible(true);//�ô�����ʾ
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
