package com.objis.cameroun.gej.dao;

import java.util.List;
import com.objis.cameroun.gej.domaine.UserAccount;


public interface UserAccountIDao {

	public int saveUserAccountDao(UserAccount user);
	public List<UserAccount> getAllUserAccountDao();
	
}
