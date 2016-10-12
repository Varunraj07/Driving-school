package com.DrivingSchool;

import java.io.Serializable;

@SuppressWarnings("serial")
public class TutorDetails extends Details implements Serializable
{
	int yearOfExp;
	static int regId;
	@Override
	public  int hashCode() {
		regId++;	
		return regId;
	}
	public TutorDetails(String userName,String password,String emailId,String mobileNumber,int age,int yearOfExp)
	{
		super(userName,password,emailId,mobileNumber,age);
		this.yearOfExp=yearOfExp;
	}
}



