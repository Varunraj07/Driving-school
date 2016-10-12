package com.DrivingSchool;

import java.io.Serializable;

 @SuppressWarnings("serial")
public class Details implements Serializable
 {
	String userName;
	String password;
	String emailId;
	String mobileNumber;
	int age;
	
	public Details(String userName,String password,String emailId,String mobileNumber,int age)
	{
		this.userName=userName;
		this.password=password;
		this.emailId=emailId;
		this.mobileNumber=mobileNumber;
		this.age=age;
	}

}
 