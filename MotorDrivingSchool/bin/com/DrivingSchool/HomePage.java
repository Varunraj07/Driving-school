package com.driving;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

import com.driving.TutDetails.AdminPage;



public class HomePage {

	public static void main(String args[]) throws FileNotFoundException, ClassNotFoundException, IOException, SQLException
	{
		displayMainMenu();
	}
	/**
	 * This method displays the home page of motor driving school
	 * @throws FileNotFoundException
	 * @throws ClassNotFoundException
	 * @throws IOException
	 * @throws SQLException 
	 */
	@SuppressWarnings("resource")
	static void displayMainMenu() throws FileNotFoundException, ClassNotFoundException, IOException, SQLException
	{
Scanner scanner=new Scanner(System.in);
		
		System.out.println("\t\t\tWELCOME TO MAVSCM DRIVING SCHOOL");
		System.out.println("\t\t**********************************************");
		System.out.println("\n1.Admin page \n2.Customer page \n3.Tutor page\n4.Assign Tutor\n5.Exit");

		System.out.println("\nEnter your choice :");
		int choice=scanner.nextInt();
		switch(choice)
		{
		case 1:
			AdminPage.adminPage();
			break;
		case 2:
			CusomerPage cp = new CusomerPage();
			cp.customerPage();
			break;
		case 3:
			TutorPage tp=new TutorPage();
			tp.tutorPage();
			break;
		case 4:
			//TutorPage tp=new TutorPage();
			//tp.tutorPage();
			break;
		default :
			System.out.println("\nThank you");
			HomePage.displayMainMenu();
			
		}
	}

}
