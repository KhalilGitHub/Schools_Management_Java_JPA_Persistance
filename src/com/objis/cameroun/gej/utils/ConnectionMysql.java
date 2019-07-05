package com.objis.cameroun.gej.utils;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionMysql {
	
	public static Connection connection;
	
	public static Connection getInstance() throws SQLException {
		
		if (connection==null) {  
			
			connection = ConnectMysql.getConnection();
			
		}
		
		return connection;
		
	}

}
