package com.driving;

import java.io.*;
import java.sql.*;
import java.util.*;
public class CusomerPage {
	
	public void customerPage() throws FileNotFoundException, IOException, ClassNotFoundException, SQLException 
	{
		Scanner scanner=new Scanner(System.in);
		System.out.println("\nPlease Register before you login \n");
		System.out.println("\nIf already registered login directly\n");
		System.out.println("1. Login");
		System.out.println("2. Register");
		System.out.println("3. Package Details");
		System.out.println("Enter your choice: ");
		byte choice =scanner.nextByte();
		switch(choice)
		{
		case 1:
			validateLogin();
			System.out.println("\n");
			break;
		case 2:
			registerCustomer();
			System.out.println("\n");
			break;
		case 3:
			System.exit(0);
			customerPage();
			break;
		}	
	}
	public  void  registerCustomer() throws ClassNotFoundException, IOException, SQLException
	{
		Scanner scanner=new Scanner(System.in);
		System.out.println("Create customer id");
		int id=Integer.parseInt(scanner.nextLine());
		System.out.println("Create customer username :");
		String customerName=scanner.nextLine();
		System.out.println("Create customer password :");
		String customerPassword=scanner.nextLine();
		System.out.println("Provide valid emailId :");
		String customerEmailid=scanner.nextLine();
		String mobileNumber="";
		do{
			try{
			System.out.println("Enter mobilenumber");
			mobileNumber=(scanner.nextLine());
			if(mobileNumber.length()!=10){
				System.out.println("Entered mobile number is invalid");
				System.out.println("please enter 10 digit mobile number");
			}	
			}
			catch(java.lang.NumberFormatException e)
			{
				System.out.println("invalid number format");
			}
		}while(mobileNumber.length()!=10);
		
		int age=0;
		do
		{
		try{
			System.out.println("Enter age :");
			age=Integer.parseInt(scanner.nextLine());
			if(age<18)
			{
				System.out.println("You are not eligible for driving course");
				//System.out.println("");
			}
		}
		catch( java.lang.NumberFormatException e)
		{
			System.out.println("invalid age");
		}
	}while(age<18);
		System.out.println("Enter address");
		String address =scanner.nextLine();
		System.out.println("Enter vechicle type");
		String vechicletype =scanner.nextLine();
		Connection con=null;
		PreparedStatement st=null;
		int rs=0;		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","driving","drive");
			st = con.prepareStatement("insert into customer values(?,?,?,?,?,?,?,?)");
			st.setInt(1,id);
			st.setString(2,customerName);
			st.setString(3, customerPassword);
			st.setString(4,customerEmailid );
			st.setString(5, mobileNumber);
			st.setInt(6, age);
			st.setString(7, address);
			st.setString(8, vechicletype);
			st.executeUpdate();
			System.out.println(rs);
		} catch (NullPointerException | SQLException e) {
			e.printStackTrace();
		}
		finally{
			st.close();
			con.close();
		}
		System.out.println("\nSuccesfully registered Customer Details");
	}
	public  void validateLogin() throws ClassNotFoundException, IOException, SQLException
	{
		@SuppressWarnings("resource")
		Scanner scanner=new Scanner(System.in);
		System.out.println("\nEnter Username");
		String userName= scanner.nextLine();
		System.out.println("Enter Password");
		String passWord=scanner.next();
		Connection con=null;
	        try {
	            Class.forName("oracle.jdbc.driver.OracleDriver");
	             con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","driving","drive");
	            PreparedStatement ps=con.prepareStatement("select * from customer where name=? and password=? ");
	            ps.setString(1,userName);
	            ps.setString(2,passWord);
	           
	        } catch (ClassNotFoundException e) {
	            e.printStackTrace();
	        }
	        finally{
	        con.close();
	        }
	 		if(true)
		{
			System.out.println("\n Login success");
			Payment Payment=new Payment();
			Payment.detailsForRegisteredCustomer();
			Payment.emailNotification(userName,passWord);
		}
	}

	public static void CustomerDetails() throws SQLException{
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","driving","drive");
			st=con.createStatement();

		//	rs=st.executeQuery("grant create any table to driver");
			rs=st.executeQuery("select * from customer");
					while(rs.next()){
				System.out.println(rs.getInt(1)+" \t\t "+rs.getString(2)+" \t\t "+rs.getString(3)+" \t\t "+
					rs.getString(4)+" \t\t "+rs.getString(5)+" \t\t "+rs.getInt(6)+" \t\t "+rs.getString(7)+" \t\t "+rs.getString(8));
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			st.close();
			rs.close();
			con.close();
		}
	
	}
	public static void main(String[] args){

	}
}