package com.vastika.bankaccount.dao;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.SQLException;

public class CreateDatabase {
	
	public static final String URL = "jdbc:mysql://localhost:3306/";
	public static final String USER_NAME = "root";
	public static final String PASSWORD = "";
	public static final String DRIVER_NAME = "com.mysql.jdbc.Driver";
	public static final String SQL="create database bankInfo_db";
	

	public boolean  createDatabase() {
		boolean created = false;
		
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
			created = true;
			System.out.println("Database created");
			
		}
		catch(ClassNotFoundException | SQLException e){
			created = false;
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
		
		
		return created;
		

	}

}
