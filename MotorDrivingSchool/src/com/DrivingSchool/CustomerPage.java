package com.DrivingSchool;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.nio.file.Files;
import java.nio.file.Paths;
interface CustomerAccess
{
	void customerPage() throws FileNotFoundException, IOException, ClassNotFoundException;
	void registerCustomer() throws ClassNotFoundException, IOException;
	void validateLogin() throws ClassNotFoundException, IOException;
}
public class CustomerPage implements CustomerAccess
{

	@SuppressWarnings("resource")
	/**
	 * This method allows the customer either to register or login
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public void customerPage() throws FileNotFoundException, IOException, ClassNotFoundException 
	{
		CusDetails cusd=new CusDetails(); 
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
			customerPage();
			break;
		case 2:
			registerCustomer();
			System.out.println("\n");
			customerPage();
			break;
		case 3:
			cusd.get5000PackageDetails();
			cusd.get4000PackageDetails();
			cusd.get3000PackageDetails();
			customerPage();
			break;
		}	

	}
	@SuppressWarnings("static-access")
	/**
	 * This method allows the customer to register and select the packages
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public void  registerCustomer() throws ClassNotFoundException, IOException
	{
		Scanner scanner=new Scanner(System.in);

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
			
			
			
			/*if(age<18)
			{
				System.out.println("You are not eligible for driving course");
				//System.out.println("");
			}*/}while(age<18);
		

		CusDetails cusDetails=new CusDetails();
		cusDetails.get5000PackageDetails();

		cusDetails.get4000PackageDetails();
		cusDetails.get3000PackageDetails();		
		CustomerDetails customerDetails = new CustomerDetails(customerName,customerPassword,customerEmailid,mobileNumber,age);
		
		ArrayList<CustomerDetails> cus=new 	ArrayList<CustomerDetails>();
		Scanner sc=new Scanner(System.in);
		cus.add(customerDetails);
	
			
			File file = new File("Customer.ser");
		
		if(file.exists())
		{
			ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("Customer.ser"));
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("CustomerTemp.ser"));
			try
			{
				while(true)
				{
					CustomerDetails s = (CustomerDetails)objectInputStream.readObject();
					objectOutputStream.writeObject(s);
				}
			}
			catch(EOFException ex)
			{

			}

			for(CustomerDetails c:cus){
				objectOutputStream.writeObject(c);}
			Files.delete(Paths.get("Customer.ser"));
			File f = new File("CustomerTemp.ser");
			f.renameTo(new File("Customer.ser"));

		}
		else
		{
			@SuppressWarnings("resource")
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("Customer.ser"));
			for(CustomerDetails c:cus){
			objectOutputStream.writeObject(c);}
		}

		System.out.println("\nSuccesfully registered Customer Details");
	}
	


	/*static void duplicateUser()
	{

	}*/
	@SuppressWarnings("static-access")

	public void validateLogin() throws ClassNotFoundException, IOException
	{
		@SuppressWarnings("resource")
		Scanner scanner=new Scanner(System.in);
		System.out.println("\nEnter Username");
		String userName= scanner.nextLine();

		System.out.println("Enter Password");
		String passWord=scanner.next();

		ObjectInputStream objectInputStream=null;
		try {
			objectInputStream = new ObjectInputStream(new FileInputStream("Customer.ser"));
		} catch (IOException e1) 
		{
		}
		CustomerDetails data;
		boolean uservalidated=false;
		try
		{
			while((data=(CustomerDetails)objectInputStream.readObject())!=null)
			{
				if(data.userName.equalsIgnoreCase(userName)&& data.password.equalsIgnoreCase(passWord))
				{
					uservalidated=true;
				}
			}
		}
		catch(Exception e)
		{
		}
		if(uservalidated)
		{
			System.out.println("\n Login success");
			CreditCardPayment creditCardPayment=new CreditCardPayment();

			creditCardPayment.detailsForRegisteredCustomer();


			creditCardPayment.emailNotification(userName,passWord);
		}
		else
		{
			System.out.println("\nInvalid customer username and password");
			validateLogin();
		}
		System.out.println("Press 1 to  logOut");
		byte logout=scanner.nextByte();
		if(logout==1){
			System.out.println("logout success");
			DrivingSchoolHomePage.displayMainMenu();
		}
	}

}