package qqqq;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

class Mywin extends WindowAdapter//��д���ڵ�һЩ����
{
	public void windowClosing(WindowEvent e)
	{
		System.out.println("�ر�");
		System.exit(0);
	}
	public void windowActivated(WindowEvent e)
	{
		System.out.println("����ǰ��");
	}
	public void windowOpened(WindowEvent e)
	{
		System.out.println("�򿪴���");
	}
}