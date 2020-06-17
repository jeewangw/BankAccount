package com.vastika.bankaccount.bankinfo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import com.vastika.bankaccount.dao.UserDao;
import com.vastika.bankaccount.dao.UserDaoImpl;

public class Bank {
	
	public static void main(String[] args) throws IOException {
		
		System.out.println("Welcome to Vastika Bank.\nPlease select the option from following menu. ");
		
		
		//Creating an object of UserDao Implement
		UserDao userDao = new UserDaoImpl();
		
		// br object is created of BufferedReader to ask input from user
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		
	
		String decision ="yes";
		
		do {
			System.out.println("1. Insert New User Data.\n2. Update Old User Data. \n3. Delete User Data. \n4. View All User Info. ");
			
			try {
			int choice = Integer.parseInt(br.readLine());
			//Switch Statement to ask user which selection to make
			switch (choice) {
			case 1:
				// Account name of user
				System.out.println("Enter Account Name");
				String accountName = br.readLine();
				
				// Address of user
				System.out.println("Enter User's address");
				String address = br.readLine();
				
				//Mobile of user
				System.out.println("Enter User Mobile Number");
				long mobileNumber = Long.parseLong(br.readLine());
				
				// Unique ID type of user
				System.out.println("What ID type do the user have? Passport/ Citizenship/ Driving License?");
				String uniqueIdType = br.readLine();
				
				// Initial Balance of user
				System.out.println("Enter the initial balance of User");
				double initialBalance = Double.parseDouble(br.readLine());
				
				//Creating an object of BankInfo
				BankInfo bankInfo = new BankInfo (accountName,address, mobileNumber, uniqueIdType, initialBalance);
				
				//Set values to default constructor
				bankInfo.setAccountName(accountName);
				bankInfo.setMobileNumber(mobileNumber);
				bankInfo.setUniqueIdType(uniqueIdType);
				
				// is Database and Table already created?
				System.out.println("Do you want the program to create database? if no enter 'false'");
				boolean noDatabase = Boolean.parseBoolean(br.readLine());
				System.out.println("Do you want the program to create table? if no enter 'false'");
				boolean noTable = Boolean.parseBoolean(br.readLine());
				//boolean noDatabase = false;
				//boolean noTable = false;
				
				//Save values to table
				userDao.saveUserInfo(bankInfo, noDatabase, noTable);
				
				break;
				
			case 2:
				 
				//User Mobile number
				System.out.println("Enter the User id whose data you want to update");
				int id = Integer.parseInt(br.readLine());
				
				System.out.println("1. WithDraw Money\n2. Deposite Money");
				int pick =  Integer.parseInt(br.readLine());
			
				
				if (pick == 1 ) {
					
					//Withdraw Money
					
					System.out.println("Enter the amount you want to withdraw");
					double amount =  Double.parseDouble(br.readLine());
					
					bankInfo = new BankInfo ();
					userDao.updateUserInfo(bankInfo, amount, id);
					
					System.out.println("Successfully withdrawed!!");
					
					break;
					
				} else if (pick == 2) {
					
					//Deposite Money
					
					System.out.println("Enter the amount you want to deposite");
					double amount =  Double.parseDouble(br.readLine());
					
					bankInfo = new BankInfo ();
					userDao.updateUserInfo(bankInfo, -amount, id);
					
				System.out.println("Successfully deposited!!");
					
					break;
					
				} else {
					break;
				}
				
				//Update Record
				//userDao.updateUserInfo(mobileNumber, newBalance);
				
			case 3:
				//User Mobile number
				System.out.println("Enter the User Mobile Number whose data you want to delete");
				mobileNumber = Integer.parseInt(br.readLine());
				
				//Delete Record
				userDao.deleteUserInfo(mobileNumber);
				
				break;
				
			case 4:
				//User Mobile number
				//System.out.println("Enter the User Mobile Number whose data you want to display");
				//mobileNumber = Integer.parseInt(br.readLine());
				
				//Display Record
				List <BankInfo> userList = userDao.displayUserInfo();
				
				
				userList.forEach( u -> {
					System.out.println("User Name is: " + u.getAccountName());
					System.out.println("User Id is: " + u.getAddress());
					System.out.println("User Mobile number is: " + u.getMobileNumber());
					System.out.println("User Unique ID Type is: " + u.getUniqueIdType());
					System.out.println("User Initial Balance is: " + u.getInitialBalance());
					System.out.println("__________________________________________");
				});
				
				break;

			default:
				System.out.println("WRONG INPUT!!!");
				break;
			}
			System.out.println("Do you want to continue using the application? Enter 'yes' if then");
			 decision = br.readLine();
			 
			}
			catch (Exception e) {
				System.out.println("CHECK YOUR INPUT!!! ONLY DIGITS!!!");
				System.out.println("____________________________________________________");
			}
			
		} while (decision.equalsIgnoreCase("yes"));
		
		
		System.out.println("Thank you for using the application!!!");
	
	
	}


}
