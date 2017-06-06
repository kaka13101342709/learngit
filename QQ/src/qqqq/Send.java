package qqqq;

import java.io.*;
import java.net.*;

public class Send implements Runnable{
	Ui u = new Ui();
	private DatagramSocket ds;
	public Send(DatagramSocket ds)
	{
		this.ds = ds;
	}
	
	public void run()
	{
		try
		{
			
			while(!(u.text.equals("886")))
			{
				if("886".equals(u.text)) break;
				
				byte[] buf = u.text.getBytes();
				
				DatagramPacket dp = 
						new DatagramPacket(buf,buf.length,InetAddress.getByName("172.18.64.159"),60000);
				
				
				ds.send(dp);
			}
			
		}
		catch(Exception e)
		{
			throw new RuntimeException("∑¢ÀÕ ß∞‹");
		}
	}
	
}
