package com.driving;

public class TutorDetails extends Details 
{
	int yearOfExp;
	static int regId;
	@Override
	public  int hashCode() {
		regId++;	
		return regId;
	}
	public TutorDetails(int id,String userName,String password,String emailId,String mobileNumber,int age,int yearOfExp,String address)
	{
		super(id,userName,password,emailId,mobileNumber,age,address);
		this.yearOfExp=yearOfExp;
	}
}



