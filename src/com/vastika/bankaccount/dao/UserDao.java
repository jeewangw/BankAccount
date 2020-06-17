package com.vastika.bankaccount.dao;


import java.util.List;

import com.vastika.bankaccount.bankinfo.BankInfo;


public interface UserDao {
	
	void saveUserInfo(BankInfo bankInfo, boolean noDatabase, boolean noTable);
	void deleteUserInfo (long phoneNumber);
	List <BankInfo> displayUserInfo();
	void updateUserInfo(BankInfo bankInfo, double amount, int id);


}
