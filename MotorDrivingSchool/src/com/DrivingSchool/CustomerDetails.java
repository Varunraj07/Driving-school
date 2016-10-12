package com.DrivingSchool;

import java.io.Serializable;


@SuppressWarnings("serial")
public class CustomerDetails extends Details implements Serializable
{
	static int regId;
	@Override
	public  int hashCode() {
		regId++;	
		return regId;
	}
	public CustomerDetails(String userName,String password,String emailId,String mobileNumber,int age/*int amount*/)
	{
		super(userName,password,emailId,mobileNumber,age);


	}
}

