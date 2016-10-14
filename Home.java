package com.onlineShopping;
import java.sql.*;
import java.util.*;
public class Home {
	public static void main(String[] args) throws SQLException{
		
		Scanner sc=new Scanner(System.in);
		System.out.println("\n\t\t\t\t\tWelcome to VOTERS");
		
		System.out.println("\n\t\t\t1.ADMIN");
		System.out.println("\n\t\t\t2.VOTER");
		System.out.println("\n\t\t\t3.EXIT");
		System.out.println("Choose your Choice");
		byte choice=sc.nextByte();
		
	do{	
		switch(choice){
		
		case 1:
			System.out.println("Welcome ADMIN");
			Admin.main(args);
			break;
		case 2:
			System.out.println("Welcome VOTER");
			Voter.main(args);
			break;
		case 3:
			System.out.println("THANK YOU...");
			System.exit(0);
			break;
		default :
			System.out.println("Please Choose Valid operation");
	}
		
	}while(false);	
		
		
		
		
	}


}
