package com.driving;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.sql.*;

import com.driving.TutDetails.AdminPage;



interface TutorAccess{
	void tutorPage() throws FileNotFoundException, IOException, ClassNotFoundException, SQLException;
	void registerTutor() throws FileNotFoundException, IOException, ClassNotFoundException, SQLException;
	void deleteTutor() throws FileNotFoundException, IOException, ClassNotFoundException, SQLException;
	void calSalary(String userName,String password) throws FileNotFoundException, IOException;
}
public class TutorPage implements TutorAccess {

	@SuppressWarnings("resource")
	/**
	 * This method allows tutor either to login or register
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public void tutorPage() throws FileNotFoundException, IOException, ClassNotFoundException, SQLException {

		Scanner sc=new Scanner(System.in);
		System.out.println("\nPlease Register before you login");
		System.out.println("\nIf already registered login directly");
		System.out.println("1. Login");
		System.out.println("2. Register");
		System.out.println("3. Exit");
		System.out.println("Enter your choice: ");
		int choice = Integer.parseInt(sc.nextLine());
		do{
			switch(choice)
			{
			case 1:
				validateLogin();
				System.out.println("\nEnter your choice: ");
				System.out.println("1. Login");
				System.out.println("2. Register");
				System.out.println("3. Logout");
				
				choice = Integer.parseInt(sc.nextLine());
				break;
			case 2:
				registerTutor();
				System.out.println("\nEnter your choice: ");
				System.out.println("1. Login");
				System.out.println("2. Register");
				System.out.println("3. Logout");
				choice = Integer.parseInt(sc.nextLine());
				break;
			case 3:
				System.out.println("\nThank you");
				HomePage.displayMainMenu();
				break;
			default:
				System.out.println("\nInvalid option.....\n Choose valid option");
			}
		}while(choice!=4);
	}
	/**
	 * This method allows the tutor to register into the file Tutor.ser
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 * @throws SQLException 
	 */
	
	 public void registerTutor() throws FileNotFoundException, IOException, ClassNotFoundException, SQLException
	{
		 	Scanner scanner=new Scanner(System.in);
			System.out.println("Create customer id");
			int id=Integer.parseInt(scanner.nextLine());
			System.out.println("Create customer username :");
			String tutorName=scanner.nextLine();
			System.out.println("Create customer password :");
			String tutorPassword=scanner.nextLine();
			System.out.println("Provide valid emailId :");
			String tutorEmailid=scanner.nextLine();
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
			System.out.println("Enter Driving Exp");
			int exp=Integer.parseInt(scanner.nextLine());
			
			Connection con=null;
			PreparedStatement st=null;
			int rs=0;		
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","driving","drive");
				st = con.prepareStatement("insert into tutor values(?,?,?,?,?,?,?,?)");
				st.setInt(1,id);
				st.setString(2,tutorName);
				st.setString(3, tutorPassword);
				st.setString(4,tutorEmailid );
				st.setString(5, mobileNumber);
				st.setInt(6, age);
				st.setString(7, address);
				st.setInt(8, exp);
				st.executeUpdate();
				System.out.println(rs);
			} catch (NullPointerException | SQLException e) {
				e.printStackTrace();
			}
			finally{
				st.close();
				con.close();
			}
		
		System.out.println("Successfully registered Tutor Details");
		
	}	
	public void deleteTutor()  throws FileNotFoundException, IOException, ClassNotFoundException, SQLException
	{

        @SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Tutor Name you want to delete ");
        int delTutor=scanner.nextInt();
           
        
        Connection con=null;
    	PreparedStatement st=null;
    	int rs=0;
    	try {
    		Class.forName("oracle.jdbc.driver.OracleDriver");
    		con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","driving","drive");
    		st = con.prepareStatement("delete from tutor where id=?");
			st.setInt(1,delTutor);
    		System.out.println(rs);
    		st.executeUpdate();
    	} catch (ClassNotFoundException | SQLException e) {
    		
    		e.printStackTrace();
    	}
    	finally{
    		st.close();
    		con.close();
    	}

        System.out.println("Press 1 to continue ");
        System.out.println("press 2 to go to admin page");
        byte cont=scanner.nextByte();
        if(cont==1)
            System.out.println();
        else if(cont==2)
        	AdminPage.adminPage();
        }
	

/**
 * This method calculates the salary based on tutor experience
 * 
 * @param userName
 * @throws FileNotFoundException
 * @throws IOException
 */

	@SuppressWarnings("resource")
	
	public  void calSalary(String userName,String password) throws FileNotFoundException, IOException/**calculates salary based on tutor's experience**/
	{
		ObjectInputStream objectInputStream=null;
		try {
			objectInputStream = new ObjectInputStream(new FileInputStream("Tutor.ser"));
		} catch (EOFException e1) {}

		TutorDetails data;
		try
		{
			while((data=(TutorDetails)objectInputStream.readObject())!=null)
			{
				if(data.userName.equalsIgnoreCase(userName))
				{
					if(data.yearOfExp<2)
					{
						System.out.println(data.userName+" Your Salary is 10000");
					}
					else if(data.yearOfExp>2&&data.yearOfExp<5){
						System.out.println(data.userName+" Your Salary is 20000");
					}
					else{
						System.out.println(data.userName+" Your Salary is 30000");
					}
				}
			}
		}
		catch(Exception e)
		{
		}
}
	/**
	 * This method validates the user login against the file in Tutor.ser
	 * 
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 * @throws SQLException 
	 */
	@SuppressWarnings({ "resource", "unused" })
	static void validateLogin() throws FileNotFoundException, IOException, ClassNotFoundException, SQLException
	{
		Scanner scanner=new Scanner(System.in);
		System.out.println("\nEnter Username");
		String userName= scanner.nextLine();
		System.out.println("Enter Password");
		String passWord=scanner.next();
		Connection con=null;
	        try {
	            Class.forName("oracle.jdbc.driver.OracleDriver");
	             con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","driving","drive");
	            PreparedStatement ps=con.prepareStatement("select * from tutor where name=? and password=? ");
	            ps.setString(1,userName);
	            ps.setString(2,passWord);
	           
	        } catch (ClassNotFoundException e) {
	            e.printStackTrace();
	        }
	        finally{
	        con.close();
	        }
	 	
	        if(true){
	        System.out.println("\nLog in Success");	
	       /* TutorPage tp=new TutorPage();
			tp.calSalary(userName,passWord);*/
			System.out.println("Customer Details are :");
			CusomerPage.CustomerDetails();
		
		}
			else
		{
			System.out.println("\nInvalid username and password");
			validateLogin();
		}
	}
	public static void TuterDetails() throws SQLException{
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","driving","drive");
			st=con.createStatement();

		//	rs=st.executeQuery("grant create any table to driver");
			rs=st.executeQuery("select * from tutor");
					while(rs.next()){
				System.out.println(rs.getInt(1)+" \t\t "+rs.getString(2)+" \t\t "+rs.getString(3)+" \t\t "+
					rs.getString(4)+" \t\t "+rs.getString(5)+" \t\t "+rs.getInt(6)+" \t\t "+rs.getString(7)+" \t\t "+rs.getInt(8));
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
}