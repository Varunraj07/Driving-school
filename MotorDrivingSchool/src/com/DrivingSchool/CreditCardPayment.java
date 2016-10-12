package com.DrivingSchool;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Scanner;
interface CreditCardAccess
{
	 void cardDetails();
	 void detailsForRegisteredCustomer() throws FileNotFoundException, ClassNotFoundException, IOException;
	 void emailNotification(String loginUsername,String password) throws FileNotFoundException, IOException, ClassNotFoundException;
}
class CreditCardPayment implements CreditCardAccess{
	/**
	 * This method allows the customer to select a package and pay the package amount
	 * @throws FileNotFoundException
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	 public void cardDetails(){
		int CVV;String cardNumber="";
		@SuppressWarnings("resource")
		Scanner scanner=new Scanner(System.in);
		do{
			System.out.println("\nEnter  Credit Card Number ");
			cardNumber=scanner.nextLine();
			if(cardNumber.length()!=12){
				System.out.println("\nEntered Credit Card Number number is invalid");
				System.out.println("Please enter 12 digit Credit Card Number");
			}
		}while(cardNumber.length()!=12);
		do{
			System.out.println("\nEnter CVV Number");
			CVV=scanner.nextInt();
			if(CVV>999||CVV<99)
			
			 {
				System.out.println("\nEntered CVV number is invalid");
				System.out.println("Please enter 3 digit CVV number");
			}
			
		}while(CVV>999||CVV<99);
	}

	public void detailsForRegisteredCustomer() throws FileNotFoundException, ClassNotFoundException, IOException
	{
		CusDetails  cd=new CusDetails();
		Scanner scanner=null;
		try{
			scanner=new Scanner(System.in);
			System.out.println("\nPlease select the package you want"+"\n"+"1.Package Rs5000"+"\n"+"2.Package Rs4000"+"\n"+"3.Package Rs3000");
			int x=scanner.nextInt();
			if(x<=3)
			{
				switch(x)
				{
				case 1:
					cd.get5000PackageDetails();
					cardDetails();
					System.out.println("\nPlease pay Rs 5000");
					break;
				case 2:
					cd.get4000PackageDetails();
					cardDetails();
					System.out.println("\nPlease pay Rs 4000/-");
					break;
				case 3:
					cd.get3000PackageDetails();
					cardDetails();
					System.out.println("\nPlease pay Rs 3000/-");
					break;
				}
			}
			else
			{
				System.out.println("\nPlease select Valid packages");
			}    
		}
		catch(Exception e){

		}
	}
	/**
	 * This method notifies the customer about time slot and provides registration id
	 * @param loginUsername
	 * @param password
	 * @throws ClassNotFoundException 
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public void paymentChoice(){
			int packageSelection;
			@SuppressWarnings("resource")
			Scanner scanner=new Scanner(System.in);
			int registrationId=hashCode();

			packageSelection=scanner.nextInt();
			switch(packageSelection){
			case 5000:
				System.out.println("\nEmail notification: ");
				System.out.println("Your payment is done Rs 5000/-"+"\n"+"your registration id is:"+registrationId);
				System.out.println("Time slot is Morning 6 to 9");
				break;
			case 4000:
				System.out.println("\nEmail notification: ");
				System.out.println("Your payment is done"+"\n"+"your registration id is:"+registrationId);
				System.out.println("Time slot is Morning 7 to 9");
				break;
			case 3000:
				System.out.println("\nEmail notification: ");
				System.out.println("Your payment is done"+"\n"+"your registration id is:"+registrationId);
				System.out.println("Time slot is Morning 8 to 9");
				break;
			default :
				System.out.println("\nYou entered invalid amount \nPlease Choose your selected package amount");
				paymentChoice();
				
			}
		}
	public void emailNotification(String loginUsername,String password) throws FileNotFoundException, IOException, ClassNotFoundException{
		paymentChoice();
	}
}