package com.objis.cameroun.gej.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.objis.cameroun.gej.domaine.Examen;
import com.objis.cameroun.gej.domaine.UserAccount;

public class LoginDao implements LoginIDao{
	
EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestionEcole-pu");
	
	EntityManager em = emf.createEntityManager();
	

	public LoginDao() {
		// TODO Auto-generated constructor stub
	}

	public LoginDao(EntityManager em) {
		super();
		this.em = em;
	}
	public  UserAccount findUser( String userName, String passWord) throws SQLException 
	{    
		
		
		UserAccount user ;
		
		List<UserAccount> lUsers;
		
        Query query = em.createQuery("SELECT e FROM UserAccount e  WHERE e.userName = :userName and e.password =:passWord");
        query.setParameter("userName", userName);
        query.setParameter("passWord", passWord);
        lUsers = query.getResultList();
        user =(UserAccount)lUsers.get(0);
        
        em.close();
        if(user != null)
        {
        	return user;
        }
	
		return null;    
	}     
	
}
