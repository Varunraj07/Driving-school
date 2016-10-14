package com.onlineShopping;
import java.util.*;
import java.sql.*;
public class Admin {

	public static void main(String[] args) throws SQLException {

		Scanner sc=new Scanner(System.in);
		System.out.println("ADMIN Choose requried operation");
		
		System.out.println("\n\t\t\t1.View Voter Details");
		System.out.println("\n\t\t\t2.Delete VOTER");
		System.out.println("\n\t\t\t3.EXIT");
		System.out.println("Choose your Choice");
		byte choice=sc.nextByte();
	do{	
		switch(choice){
		
		case 1:
			System.out.println("Present Voters Details");
			voterDetails();
			break;
		case 2:
			System.out.println("Select Requried Voter to remove from the voter list");
			deleteVoter();
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
	
	
	public static void voterDetails() throws SQLException{
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","hr","hr");
			st=con.createStatement();

			rs=st.executeQuery("grant create any table to hr");
			rs=st.executeQuery("select * from voter");
					while(rs.next()){
				System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getInt(3)+" "+rs.getString(4)+" "+rs.getLong(5));
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


public static void deleteVoter() throws SQLException{
	Scanner sc=new Scanner(System.in);
	System.out.println("Enter ID to Delete the voter from list");
	int num=sc.nextInt();
	Connection con=null;
	Statement st=null;
	int rs=0;
	try {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","hr","hr");
		st=con.createStatement();

		//rs=st.executeQuery("grant create any table to hr");
		rs=st.executeUpdate("delete voter where id=num");
		System.out.println(rs);
	} catch (ClassNotFoundException | SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	finally{
		st.close();
		//rs.close();
		con.close();
	}

}




}
