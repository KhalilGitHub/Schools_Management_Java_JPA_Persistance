package com.objis.cameroun.gej.utils;

import java.sql.*;


public class ConnectMysql {
	
	
	public static Connection getConnection()
	{
		Connection myConn = null;
		try {
			
			String driver = "com.mysql.jdbc.Driver";
			String url = "jdbc:mysql://localhost/bdlycee";
			String username = "root";
			String password = "hamsaad12";
			Class.forName(driver).newInstance();
			
			myConn = DriverManager.getConnection(url, username, password);
			System.out.println("Connected");
			
		} 
		catch (Exception e) {
			System.out.println(e);
		}
		return myConn;
		
		
	}
	
}
