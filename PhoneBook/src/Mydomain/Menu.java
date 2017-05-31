package Mydomain;

import java.util.*;
import java.io.*;

public class Menu {
	FileWriter fw = null;
	BufferedWriter bw = null;
	Reader fr = null;
	BufferedReader br= null;
	ArrayList<Data> list = new ArrayList<Data>();
	Data c = new Data ();
	Scanner in = new Scanner(System.in);

	protected void ShouMenu(){
		System.out.println("请输入你想要的功能对应的数字");
		System.out.println("1.添加联系人信息");
		System.out.println("2.查询联系人信息");
		System.out.println("3.改写联系人信息");
		System.out.println("4.删除联系人信息");
		System.out.println("5.查看所有联系人信息");
		System.out.println("0.退出系统");
	}
	protected void Add() throws IOException{
		System.out.println("请输入姓名");
		String name = in.next();
		System.out.println("请输入电话号码");
		String number = in.next();
		c = new Data ();
		c.SetName(name);
		c.SetNumber(number);
		list.add(c);
		try{
			fw = new FileWriter("text.txt",true);
			bw = new BufferedWriter(fw);			
			bw.write(c.GetName()+"-"+c.GetNumber(),0,c.GetName().length()+c.GetNumber().length()+1);
			bw.newLine();
		}
		catch(Exception e){
			System.out.println(e);
		}
		finally{
			try{
				bw.flush();
				bw.close();
			}
			catch (IOException e){
				e.printStackTrace();
			}
		}
		
	}
	protected void Change() throws IOException{
		try{
			fr = new FileReader("text.txt");
			br = new BufferedReader(fr);
			String temp;
			while ((temp=br.readLine())!=null){
				c = new Data();
				String message[] = temp.split("-");
				c.SetName(message[0]);
				c.SetNumber(message[1]);
				list.add(c);
				}
			}
		catch(Exception e){
			System.out.println(e);
		}
		finally{
			try{
				br.close();
			}
			catch(Exception e){
				System.out.println(e);
			}
		}
		System.out.println("请输入姓名");
		String input = in.next();
		for (int j = 0; j<list.size();j++){
			if (list.get(j).GetName().equals(input)) 
			{
				System.out.println(list.get(j).GetName()+"-"+list.get(j).GetNumber());
				System.out.println("请输入新的电话号码");
				String inputnumber = in.next();
				//c = new Data();
				list.get(j).SetNumber(inputnumber);
				System.out.println("修改成功");
				break;
			}
		}
		try{
			File f = new File("text.txt");
			f.createNewFile();
			fw = new FileWriter("text.txt");
			bw = new BufferedWriter(fw);
			for (int i = 0; i<list.size();i++){
				c = new Data();
				bw.write(list.get(i).GetName());
				bw.write("-");
				bw.write(list.get(i).GetNumber());
				bw.newLine();
			}
		}
		catch(Exception e){
			System.out.println(e);
		}
		finally{
			try{
				bw.flush();
				bw.close();
				fr.close();
			}
			catch(Exception e){
				System.out.println(e);
			}
		}
		
		
		
		
	}
	protected void Delete() throws IOException{
		int flag=0;
		System.out.println("请输入姓名");
		try{
			fr = new FileReader("text.txt");
			br = new BufferedReader(fr);
			String temp;
			while ((temp=br.readLine())!=null){
				Data c = new Data ();
				String message[] = temp.split("-");
				c.SetName(message[0]);
				c.SetNumber(message[1]);
				list.add(c);
				}
			}
		catch(Exception e){
			System.out.println(e);
		}
		finally{
			try{
				br.close();
			}
			catch(Exception e){
				System.out.println(e);
			}
		}
			
				
				String input = in.next();
				try{
					File f = new File("text.txt");
					f.delete();
					fw = new FileWriter("text.txt",true);
					bw = new BufferedWriter(fw);
					for (int j = 0; j<list.size();j++){
						if (list.get(j).GetName().equals(input)) 
						{
							list.remove(j);
							flag=1;
						}
					}
					if (flag==1){
						File f1 = new File("text.txt");
						f1.createNewFile();
						for (int i = 0; i<list.size();i++){
							bw.write(list.get(i).GetName());
							bw.write("-");
							bw.write(list.get(i).GetNumber());
							bw.newLine();
						}
					}
					else{
						System.out.println("没找到");
					}
				}
				catch(Exception e){
					System.out.println(e);
				}
				finally{
					try{
						bw.flush();
						bw.close();
					}
					catch (IOException e){
						e.printStackTrace();
					}
				
			}
	}
	protected void Search() throws IOException{
		try{
			fr = new FileReader("text.txt");
			br = new BufferedReader(fr);
			String temp;
			while ((temp=br.readLine())!=null){
				c = new Data();
				String message[] = temp.split("-");
				c.SetName(message[0]);
				c.SetNumber(message[1]);
				list.add(c);
				}
			}
		catch(Exception e){
			System.out.println(e);
		}
		finally{
			try{
				br.close();
			}
			catch(Exception e){
				System.out.println(e);
			}
		}
		System.out.println("请输入姓名");
		int flag=0;
		String input = in.next();
		for (int j = 0; j<list.size();j++){
			if (list.get(j).GetName().equals(input)) 
			{
				System.out.println(list.get(j).GetName()+"-"+list.get(j).GetNumber());
				flag=1;
				break;
			}
		}
		if (flag==0){
			System.out.println("没找到");
		}
	}
	protected void Show()throws Exception{
		try{
			fr = new FileReader("text.txt");
			br = new BufferedReader(fr);
			String temp;
			list.clear();
			while ((temp=br.readLine())!=null){
				c= new Data();
				String message[] = temp.split("-");
				c.SetName(message[0]);
				c.SetNumber(message[1]);
				list.add(c);
				//System.out.println(c.GetName());
				}
			}
		catch(Exception e){
			System.out.println(e);
		}
		finally{
			try{
				br.close();
			}
			catch(Exception e){
				System.out.println(e);
			}
		}
		for (int i = 0; i<list.size();i++){
			System.out.println(list.get(i).GetName()+"-"+list.get(i).GetNumber());
		}
	}
}
