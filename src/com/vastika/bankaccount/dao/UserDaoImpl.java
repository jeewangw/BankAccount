package com.vastika.bankaccount.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.vastika.bankaccount.bankinfo.BankBalance;
import com.vastika.bankaccount.bankinfo.BankInfo;
import com.vastika.bankaccount.dao.UserDao;



public class UserDaoImpl implements UserDao{

	@Override
	public void saveUserInfo(BankInfo bankInfo, boolean noDatabase, boolean noTable) {
		try {
			
			if (noDatabase) {
				
				//Object of CreateDatabase
				  CreateDatabase cb = new CreateDatabase ();
				  cb.createDatabase();
				
			}

			if (noTable) {
				
				//Object of CreateTable
				CreateTable ct = new CreateTable();
				ct.createTable();
				
			}
			
			
			
			//jdbc code...
			Connection con = CP.createC();
			String q="insert into account_info_tbl( accountName,address,mobileNumber,uniqueIdType,initialBalance)values(?,?,?,?,?)";
			String t="insert into account_balance_tbl( account_info_id,deposite_amount,withdraw_amount,balance)values(?,?,?,?)";
			
			//Prepared Statement
			PreparedStatement pstmt= con.prepareStatement(q);
			
			//Set the values of parameter
			pstmt.setString(1, bankInfo.getAccountName());
			pstmt.setString(2, bankInfo.getAddress());
			pstmt.setLong(3, bankInfo.getMobileNumber());
			pstmt.setString(4, bankInfo.getUniqueIdType());
			pstmt.setDouble(5, bankInfo.getInitialBalance());
			
			//execute
			pstmt.executeUpdate();
			
			String SELECT_QUERY ="Select * from account_info_tbl ORDER BY ID DESC LIMIT 1 ";
			
			//Prepared Statement
			 pstmt= con.prepareStatement(SELECT_QUERY);
			
			ResultSet set = pstmt.executeQuery();

			
			while (set.next()) {
				
				bankInfo.setId(set.getInt(1));
				bankInfo.setAccountName(set.getString(2));
				bankInfo.setAddress(set.getString(3));
				bankInfo.setMobileNumber(set.getLong(4));
				bankInfo.setUniqueIdType(set.getString(5));
				bankInfo.setInitialBalance(set.getDouble(6));
			}
			
		
			
			//Prepared Statement
			PreparedStatement pst= con.prepareStatement(t);
			
			//Set the values of parameter
			pst.setInt(1,bankInfo.getId());
			pst.setDouble(2, 0.0);
			pst.setDouble(3, 0.0);
			pst.setDouble(4, bankInfo.getInitialBalance());
			
			
			//execute
			pst.executeUpdate();
			

			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}

	@Override
	public void deleteUserInfo(long phoneNumber) {

			try {
				
				//jdbc code...
				Connection con = CP.createC();
				String q="delete from account_info_tbl where mobileNumber=?";
				
				//Prepared Statement
				PreparedStatement pstmt= con.prepareStatement(q);
				
				
				//Set the values of parameter
				pstmt.setLong(1, phoneNumber);
				
				//execute
				pstmt.executeUpdate();
				
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
			
		}

	@Override
	public List<BankInfo> displayUserInfo() {
			
			List <BankInfo> userList = new ArrayList <BankInfo> ();
			try {
				BankInfo bf = new BankInfo();
				
				//jdbc code...
				Connection con = CP.createC();
				String q="Select * from account_info_tbl  ";
				
				//Prepared Statement
				PreparedStatement pstmt= con.prepareStatement(q);
				
				//Set the values of parameter
				//pstmt.setLong(1, phoneNumber);
				
				ResultSet set = pstmt.executeQuery();

				
				while (set.next()) {
					
					bf.setAccountName(set.getString(2));
					bf.setAddress(set.getString(3));
					bf.setMobileNumber(set.getLong(4));
					bf.setUniqueIdType(set.getString(5));
					bf.setInitialBalance(set.getDouble(6));
					userList.add(bf);
				}
				
				
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
			return userList;
		}

	@Override
	public void updateUserInfo(BankInfo bankInfo, double amount, int id) {
		try {
			
			//jdbc code...
			Connection con = CP.createC();
			
			String SELECT_QUERY ="Select * from account_info_tbl where id = ? ";
			String SELECT_Balance_QUERY ="Select * from account_balance_tbl where account_info_id = ? ";
			
			//Prepared Statement
			PreparedStatement pstmt= con.prepareStatement(SELECT_QUERY);
			
			//Set the values of parameter
			pstmt.setLong(1, id);
			
			ResultSet set = pstmt.executeQuery();

			
			while (set.next()) {
				
				
				bankInfo.setAccountName(set.getString(2));
				bankInfo.setAddress(set.getString(3));
				bankInfo.setMobileNumber(set.getLong(4));
				bankInfo.setUniqueIdType(set.getString(5));
				bankInfo.setInitialBalance(set.getDouble(6));
			}
			
			String q="Update account_info_tbl set initialBalance = ? where id = ?";		
			String r="Update account_balance_tbl set balance = ?, deposite_amount = ? where account_info_id = ?";
			String s="Update account_balance_tbl set balance = ?, withdraw_amount = ? where account_info_id = ?";
			
			//3. get the statement object using Connection
			 pstmt= con.prepareStatement(q);

			//Set the values of parameter
			pstmt.setDouble(1, bankInfo.getInitialBalance() - amount);
			pstmt.setLong(2, id);
			
			//execute
			pstmt.executeUpdate();
			
			//Prepared Statement
			 pstmt= con.prepareStatement(SELECT_Balance_QUERY);
			
			//Set the values of parameter
			pstmt.setLong(1, id);
			
			 set = pstmt.executeQuery();
			 
			 BankBalance bb = new BankBalance();
			
			while (set.next()) {
				bb.setAccountInfoID(set.getInt(1));
				bb.setDepositeAmount(set.getDouble(2));
				bb.setWithdrawAmount(set.getDouble(3));
				bb.setBalance(set.getDouble(4));
			}
			
			if (amount > 0) {
				
				//3. get the statement object using Connection
				 pstmt= con.prepareStatement(s);

				//Set the values of parameter
				pstmt.setDouble(1, bankInfo.getInitialBalance() - amount);
				pstmt.setDouble(2,  bb.getWithdrawAmount() + amount);
				pstmt.setInt(3, id);
				
				//execute
				pstmt.executeUpdate();
				
			} else {
				//3. get the statement object using Connection
				 pstmt= con.prepareStatement(r);

				//Set the values of parameter
				pstmt.setDouble(1, bankInfo.getInitialBalance() - amount);
				pstmt.setDouble(2,  bb.getDepositeAmount()  -amount);
				pstmt.setInt(3, id);
				
				//execute
				pstmt.executeUpdate();
				
			}
			System.out.println("Data Updated");
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			 
		}
		
		
	}
	}

		
