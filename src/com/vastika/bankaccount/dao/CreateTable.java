package com.vastika.bankaccount.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateTable {

	public static final String URL = "jdbc:mysql://localhost:3306/bankInfo_db";
	public static final String USER_NAME = "root";
	public static final String PASSWORD = "";
	public static final String DRIVER_NAME = "com.mysql.jdbc.Driver";
	//public static final String SQL="create table account_info_tbl(id int not null auto_increment, accountName varchar(50),address varchar(50), mobileNumber long,uniqueIdType varchar(50),initialBalance double, primary key(id))";
	public static final String SQL="create table account_balance_tbl(account_info_id int not null , deposite_amount double,withdraw_amount double, balance double, primary key(account_info_id))";
	
	public void createTable() {
			
			Connection con = null;
			Statement st = null;
			
			try {
				//1.register the driver
				Class.forName(DRIVER_NAME);
				//2.get the object Connection using DriverManager
				con = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
				//3. get the statement object using Connection
				st = con.createStatement();
				//4.execute the query using statement
				st.executeUpdate(SQL);
				System.out.println("Table created");
				
			}
			catch(ClassNotFoundException | SQLException e){
				e.printStackTrace();
			}
			finally {
				try {
					//5.close the connection
					con.close();
					st.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			
			
			

		}


}
