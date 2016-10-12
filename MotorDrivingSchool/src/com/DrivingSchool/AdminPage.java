package com.DrivingSchool;


import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Properties;
import java.util.Scanner;

interface AdminAccess
{
	 void getCustomerDetails() throws FileNotFoundException, IOException, ClassNotFoundException;
	 void get5000PackageDetails();
	 void get4000PackageDetails();
	 void get3000PackageDetails();
}
interface Admin
{
void adminPage();
void adminMenu();
}
class CusDetails implements AdminAccess
{
	/**
	 * This method displays the details of registered customers from the file customer.ser 
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public  void getCustomerDetails() throws FileNotFoundException, IOException, ClassNotFoundException
	{
		FileInputStream fileinputstream =new FileInputStream("Customer.ser");
		ObjectInputStream objectinputstream =new ObjectInputStream(fileinputstream);
		CustomerDetails data;
		System.out.println("=======================================================================================================");
		System.out.println("Serial Number \t Customer name \t\t Email id \t\t\t Age \t\t Contact number ");
		System.out.println("=======================================================================================================");
		try
		{
			while(true)
			{	
				data=(CustomerDetails)objectinputstream.readObject();
				System.out.println(data.hashCode()+" \t\t "+data.userName+" \t\t "+data.emailId+" \t\t "+data.age+" \t\t "+data.mobileNumber);

			}
		}
		catch(EOFException e){
			System.out.println("-------------------------------------------------------------------------------------------------------");

		}
		finally
		{
			fileinputstream.close();
			objectinputstream.close();
		}}


	
	public  void get5000PackageDetails()
	{
		System.out.println("\nPackage 1 :"+"\n"+"Daily 3 hrs training for 1 month and licence assistance");
		System.out.println("and package cost is : Rs 5000/- and Time slot is Morning 6 to 9");
	}
	public void get4000PackageDetails(){
		System.out.println("\nPackage 2 :"+"\n"+"Daily 2 hrs training for 1 month and licence assistance");
		System.out.println("and package cost is : Rs4000/- and Time slot is Morning 8 to 11");
	}
	public void get3000PackageDetails(){
		System.out.println("\nPackage 3"
				+ " :"+"\n"+"Daily 2 hrs training for 1 month ");
		System.out.println("and package cost is : Rs3000/- and Time slot is Morning 10 to 11");
	}

}
class TutDetails{
	/**
	 * This method displays the registered tutor details from the file Tutor.ser 
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */

	static void getTutorDetails() throws FileNotFoundException, IOException, ClassNotFoundException
	{	

		FileInputStream fileinputstream =new FileInputStream("Tutor.ser");
		ObjectInputStream objectinputstream =new ObjectInputStream(fileinputstream);
		TutorDetails data;
		System.out.println("================================================================================================================");
		System.out.println("Serial Number \t Tutor name \t\t Email_id \t\t\t Age \t\t Contact number ");
		System.out.println("================================================================================================================");
		try
		{
			while(true)
			{	
				data=(TutorDetails)objectinputstream.readObject();

				System.out.println(data.hashCode()+" \t\t "+data.userName+" \t\t "+data.emailId+" \t\t "+data.age+" \t\t "+data.mobileNumber);

			}

		}


		catch(EOFException e)
		{
			System.out.println("------------------------------------------------------------------------------------------------------------");

		}
		finally
		{
			objectinputstream.close();
		}

	}

}

public class AdminPage {


	@SuppressWarnings("resource")
	/**
	 * This method validates the admin and provides the information of registered customers and tutors
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */

	public static void adminPage() throws FileNotFoundException, IOException, ClassNotFoundException {
		System.out.println("\nWelcome to Admin Page");
		Properties properties=new Properties();
		properties.load(new FileInputStream("DB.properties"));
		Scanner scanner=new Scanner(System.in);
		System.out.println("\nEnter Username :");
		String username=scanner.nextLine();
		if(properties.getProperty("Username").contentEquals(username))
		{
			System.out.println("\nEnter Password");
			String password=scanner.next();
			if(properties.getProperty("Password").contentEquals(password))
			{
				System.out.println("\nWelcome to home page \n \tSelect from below options \n1.Customer Details\n2.Tutor Details\n3.Delete tutuor\n4.logout\nChoose the option");
			}
			else{
				System.out.println("\nIncorrect Password please try again");
				AdminPage.adminPage();
			}
		}
		else{
			System.out.println("\nIncorrect Username try again");
			AdminPage.adminPage();
		}

		adminMenu();

	}
	static void menu()throws FileNotFoundException, ClassNotFoundException, IOException{
		System.out.println("\nWelcome to home page \n \tSelect from below options \n1.Customer Details\n2.Tutor Details\n3.Delete tutuor\n4.logout\nChoose the option");
		
		adminMenu();
		
		
	}

	static void adminMenu() throws FileNotFoundException, ClassNotFoundException, IOException{
		Scanner scanner=new Scanner(System.in);
		byte adminChoice=scanner.nextByte();
		do{
			switch(adminChoice){
			case 1:

				System.out.println("\nCUSTOMER DETAILS ");
				CusDetails c=new CusDetails();

				c.getCustomerDetails();
				/*System.out.println("\nEnter your choice:\n1.Customer Details\n2.Tutor Details\n3.Logout");
				adminChoice=scanner.nextByte();*/
				menu();
				break;

			case 2:
				System.out.println("\nTUTOR DETAILS ");
				TutDetails.getTutorDetails();
				/*System.out.println("\nEnter your choice:\n1.Customer Details\n2.Tutor Details\n3.Delete Tutor\n4.logout");
				adminChoice=scanner.nextByte();*/
				menu();
				break;
			case 3:
				System.out.println("\nDelete tutor");
				TutorPage t=new TutorPage();
				t.deleteTutor();
				break;
			/*case 4:
				System.out.println("\nDelete customer");
				CustomerPage.deleteCustomer();
				break;
				*/
			case 4:
				System.out.println("\nThank you");
				DrivingSchoolHomePage.displayMainMenu();
				break;
			default:
				System.out.println("\nYou should have selected either 1 or 2 or 3");
			}
		}while(adminChoice!=5);
	}
}
