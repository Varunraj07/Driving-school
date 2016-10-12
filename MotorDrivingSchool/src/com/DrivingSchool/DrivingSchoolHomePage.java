package com.DrivingSchool;

//import java.awt.DisplayMode;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class DrivingSchoolHomePage {

	public static void main(String args[]) throws FileNotFoundException, ClassNotFoundException, IOException
	{
		displayMainMenu();
	}
	/**
	 * This method displays the home page of motor driving school
	 * @throws FileNotFoundException
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	@SuppressWarnings("resource")
	static void displayMainMenu() throws FileNotFoundException, ClassNotFoundException, IOException
	{
Scanner scanner=new Scanner(System.in);
		
		System.out.println("\t\t\tWELCOME TO MAVSCM DRIVING SCHOOL");
		System.out.println("\t\t**********************************************");
		System.out.println("\n1.Admin page \n2.Customer page \n3.Tutor page\n4.Exit");

		System.out.println("\nEnter your choice :");
		int choice=scanner.nextInt();
		switch(choice)
		{
		case 1:
			AdminPage.adminPage();
			break;
		case 2:
			CustomerPage cp=new CustomerPage();
			cp.customerPage();
			break;
		case 3:
			TutorPage tp=new TutorPage();
			tp.tutorPage();
			break;
		default :
			System.out.println("\nThank you");
			DrivingSchoolHomePage.displayMainMenu();;
			
		}
	}

}
