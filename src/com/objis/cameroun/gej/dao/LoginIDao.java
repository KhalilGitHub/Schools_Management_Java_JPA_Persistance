package com.objis.cameroun.gej.dao;

import java.sql.Connection;
import java.sql.SQLException;

import com.objis.cameroun.gej.domaine.UserAccount;

public interface LoginIDao {
	
	public  UserAccount findUser(String userName, String password) throws SQLException;

}
