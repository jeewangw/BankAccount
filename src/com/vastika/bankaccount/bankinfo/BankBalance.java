package com.vastika.bankaccount.bankinfo;

public class BankBalance {
	
	private int accountInfoID;
	private double depositeAmount;
	private double withdrawAmount;
	private double balance;
	
	public BankBalance() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getAccountInfoID() {
		return accountInfoID;
	}

	public void setAccountInfoID(int accountInfoID) {
		this.accountInfoID = accountInfoID;
	}

	public double getDepositeAmount() {
		return depositeAmount;
	}

	public void setDepositeAmount(double depositeAmount) {
		this.depositeAmount = depositeAmount;
	}

	public double getWithdrawAmount() {
		return withdrawAmount;
	}

	public void setWithdrawAmount(double withdrawAmount) {
		this.withdrawAmount = withdrawAmount;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}
	

}
