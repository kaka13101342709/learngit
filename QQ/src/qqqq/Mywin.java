package qqqq;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

class Mywin extends WindowAdapter//复写窗口的一些方法
{
	public void windowClosing(WindowEvent e)
	{
		System.out.println("关闭");
		System.exit(0);
	}
	public void windowActivated(WindowEvent e)
	{
		System.out.println("窗口前置");
	}
	public void windowOpened(WindowEvent e)
	{
		System.out.println("打开窗口");
	}
}