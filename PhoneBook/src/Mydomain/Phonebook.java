package Mydomain;

import java.io.*;
import java.util.*;

public class Phonebook {
	public static void main (String args []) throws Exception{
		int choice = 1;
		Menu menu = new Menu();
		System.out.println("********�绰������ϵͳ********");
		while (choice!=0){
			menu.ShouMenu();
			Scanner in = new Scanner(System.in);
			choice = in.nextInt();
			switch (choice){
			case 1: menu.Add();
				break;
			case 2: menu.Search();
				break;
			case 3:	menu.Change();
				break;
			case 4: menu.Delete();
				break;
			case 5: menu.Show();
				break;
			case 0: 
				System.out.println("��ӭ�´�ʹ��");
				System.exit(0);
			default :
				System.out.println("��������ȷ��ѡ��");
			}
		}
	}
}
