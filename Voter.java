package com.onlineShopping;
import java.sql.*;
import java.util.*;
public class Voter {
public static void main(String[] args) throws SQLException{
	Scanner sc=new Scanner(System.in);
	System.out.println("Enter Voter ID");
	int id=sc.nextInt();
	
	System.out.println("Enter Voter Name");
	String name=sc.next();
	System.out.println("Enter Voter Age");
	int age=sc.nextInt();
	System.out.println("Enter Voter Gender");
	String gender=sc.next();
	System.out.println("Enter Voter Mobile Number");
	long mobile=sc.nextLong();
	
	
	Connection con=null;
	Statement st=null;
	int rs=0;

	try {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","hr","hr");
		st = con.createStatement();
		rs=st.executeUpdate("insert into voter values("+id+",'"+name+"',"+age+",'"+gender+"',"+mobile+")");
		System.out.println(rs);
	} catch (ClassNotFoundException | SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	finally{
		st.close();
		con.close();
	}
	
}
}
