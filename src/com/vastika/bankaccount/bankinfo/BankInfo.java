package com.vastika.bankaccount.bankinfo;



public class BankInfo {
	private int id;
	private String accountName;
	private String address;
	private long mobileNumber;
	private String uniqueIdType;
	private double initialBalance;
	public BankInfo(String accountName, String address, long mobileNumber, String uniqueIdType, double initialBalance) {
		super();
		this.accountName = accountName;
		this.address = address;
		this.mobileNumber = mobileNumber;
		this.uniqueIdType = uniqueIdType;
		this.initialBalance = initialBalance;
	}
	public BankInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public long getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(long mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getUniqueIdType() {
		return uniqueIdType;
	}
	public void setUniqueIdType(String uniqueIdType) {
		this.uniqueIdType = uniqueIdType;
	}
	public double getInitialBalance() {
		return initialBalance;
	}
	public void setInitialBalance(double initialBalance) {
		this.initialBalance = initialBalance;
	}
	
	
	

	
}
