package com.driving;

public class CustomerDetails extends Details
{
	/*static int regId;
	@Override
	public  int hashCode() {
		regId++;	
		return regId;
	}*/
	String vechicletype;
	public CustomerDetails(int id,String userName,String password,String emailId,String mobileNumber,int age,String address,String vechicletype)
	{
		super(id,userName,password,emailId,mobileNumber,age,address);
		this.vechicletype=vechicletype;


	}
}
