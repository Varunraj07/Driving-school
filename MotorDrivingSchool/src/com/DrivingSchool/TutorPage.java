package com.DrivingSchool;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

interface TutorAccess{
	void tutorPage() throws FileNotFoundException, IOException, ClassNotFoundException;
	void registerTutor() throws FileNotFoundException, IOException, ClassNotFoundException;
	void deleteTutor() throws FileNotFoundException, IOException, ClassNotFoundException;
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
	public void tutorPage() throws FileNotFoundException, IOException, ClassNotFoundException {

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
				DrivingSchoolHomePage.displayMainMenu();
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
	 */
	
	 public void registerTutor() throws FileNotFoundException, IOException, ClassNotFoundException
	{
		Scanner scanner=new Scanner(System.in);
		System.out.println("Create tutor username :");
		String tutorName=scanner.nextLine();
		System.out.println("Create tutor password :");
		String tutorPassword=scanner.nextLine();
		System.out.println("Provide emailId :");
		String emailid=scanner.nextLine();
		/*System.out.println("Provide mobilenumber :");
		String mobileNumber=sc.nextLine();*/
		String mobileNumber;
		do{
			System.out.println("Enter Mobile Number");
			mobileNumber=scanner.nextLine();
			if(mobileNumber.length()!=10){
				System.out.println("Entered mobile number is invalid");
				System.out.println("please enter 10 digit mobile number");
			}
		}while(mobileNumber.length()!=10);
		System.out.println("Enter age :");
		int age=scanner.nextInt();
		System.out.println("Enter your Experience :");
		int yearOfExp=scanner.nextInt();

		TutorDetails tutorDetails1 = new TutorDetails(tutorName,tutorPassword,emailid,mobileNumber,age,yearOfExp);

		File file = new File("Tutor.ser");

		if(file.exists())
		{
			ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("Tutor.ser"));
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("TutorTemp.ser"));
			try
			{
				while(true)
				{
					TutorDetails tutorDetails = (TutorDetails)objectInputStream.readObject();
					objectOutputStream.writeObject(tutorDetails);
				}
			}
			catch(EOFException ex)
			{

			}
			
			objectOutputStream.writeObject(tutorDetails1);
			Files.delete(Paths.get("Tutor.ser"));
			File file1 = new File("TutorTemp.ser");
			file1.renameTo(new File("Tutor.ser"));
		}
		else
		{
			@SuppressWarnings("resource")
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("Tutor.ser"));
			objectOutputStream.writeObject(tutorDetails1);
		}
		System.out.println("Successfully registered Tutor Details");
		calSalary(tutorName,tutorPassword);

	}	
	public void deleteTutor()  throws FileNotFoundException, IOException, ClassNotFoundException
	{

        @SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Tutor Name you want to delete ");
        String delTutor=scanner.next();
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("Tutor.ser"));
            ObjectOutputStream objectOutputStream1 = new ObjectOutputStream(new FileOutputStream("TutorTemp.ser"));
            try
            {
                while(true)
                {
                    TutorDetails data = (TutorDetails)objectInputStream.readObject();
                    if(data.userName.equalsIgnoreCase(delTutor))
                        System.out.println(data.userName+" Tutor Record Deleted...");
                    else
                    	objectOutputStream1.writeObject(data);
                }
            }
            catch(Exception ex)
            {

            }
            Files.delete(Paths.get("Tutor.ser"));
            File f = new File("TutorTemp.ser");
            f.renameTo(new File("Tutor.ser"));
            objectInputStream.close();
            objectOutputStream1.close();
        System.out.println("Press 1 to continue ");
        System.out.println("press 2 to go to admin page");
        byte cont=scanner.nextByte();
        if(cont==1)
            System.out.println();
        else if(cont==2)
        	AdminPage.menu();
        }
	

/**
 * This method calculates the salary based on tutor experience
 * 
 * @param userName
 * @throws FileNotFoundException
 * @throws IOException
 */

	@SuppressWarnings("resource")
	
	public void calSalary(String userName,String password) throws FileNotFoundException, IOException/**calculates salary based on tutor's experience**/
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
	 */
	@SuppressWarnings("resource")
	static void validateLogin() throws FileNotFoundException, IOException, ClassNotFoundException
	{
		Scanner scanner=new Scanner(System.in);
		System.out.println("Enter UserName");
		String userName= scanner.nextLine();
		System.out.println("Enter Password");
		String passWord=scanner.next();
		ObjectInputStream objectInputStream=null;
		try {
			objectInputStream = new ObjectInputStream(new FileInputStream("Tutor.ser"));
		} catch (EOFException e1) {
		}
		TutorDetails data;
		boolean uservalidated=false;
		try
		{
			while((data=(TutorDetails)objectInputStream.readObject())!=null)
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
			System.out.println("\nLog in Success");			
			//calSalary(userName,passWord);
			System.out.println("Customer Details are :");
			CusDetails c=new CusDetails();
			c.getCustomerDetails();

		}
		else
		{
			System.out.println("\nInvalid username and password");
			validateLogin();
		}
	}
}