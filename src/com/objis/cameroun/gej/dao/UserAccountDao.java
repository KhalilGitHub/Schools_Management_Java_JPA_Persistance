package com.objis.cameroun.gej.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.objis.cameroun.gej.domaine.Enseignant;
import com.objis.cameroun.gej.domaine.Recrutement;
import com.objis.cameroun.gej.domaine.UserAccount;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class UserAccountDao implements UserAccountIDao{

		private EntityManager em;
		public UserAccountDao() {
			// TODO Auto-generated constructor stub
		}

		public UserAccountDao(EntityManager em) {
			super();
			this.em = em;
		}

		@Override
		public int saveUserAccountDao(UserAccount useraccount) {
			try {
			
					// 2 : Ouverture transaction 
						EntityTransaction tx =  em.getTransaction();
						tx.begin();

					// 4 : Persistance Objet/Relationnel : création d'un enregistrement en base
						 em.persist(useraccount);
						
						// 5 : Fermeture transaction 
						 tx.commit();
						 
					} catch (Exception e) {
						e.printStackTrace();
						
						return 0;
					} 
				return 1;
				
			}
			
		
		
		public  UserAccount findRecrutement(String matricule) throws SQLException 
		{        
			
			UserAccount userAccount ;
			 
			List<UserAccount> lRecrutement = new ArrayList<UserAccount>();
			
	        Query query = em.createQuery("SELECT u FROM UserAccount u WHERE u.matricule = :id");
	        query.setParameter("id", matricule);
	        //List<?> list = query.list();
	        lRecrutement = query.getResultList();
	        userAccount = (UserAccount)lRecrutement.get(0);
	        //resultList.forEach(System.out::println);
	       
	        em.close();
			
			return userAccount;
			
		}   
	
		public  void updateRecrutement(UserAccount userAccount) throws SQLException 
		{        
			
			try{	
				
				// 2 : Ouverture transaction 
				em.getTransaction().begin();
				
				// 4 : Persistance Objet/Relationnel : création d'un enregistrement en base
				//em.merge(examen);
				em.merge(userAccount);
				 
				// 5 : Fermeture transaction 
				 em.getTransaction().commit();
				
				}
			
				catch(Exception e)
				{	
					e.printStackTrace();
					
				}
			    
		}    
		
		
		public  void deleteRecrutement(String mat) throws SQLException 
		{    
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestionEcole-pu");
			
			EntityManager em = emf.createEntityManager();
			
			try{	
				
					// 2 : Ouverture transaction 
					em.getTransaction().begin();
					
					UserAccount userAccount = findRecrutement(mat);
					
					long userAccounttId = userAccount.getId();
					Query query = em.createQuery("Delete From UserAccount i Where i.id = :userAccounttId");
					query.setParameter("userAccounttId", userAccounttId);
			        query.executeUpdate();
				
				}
			
				catch(Exception e)
				{	
					e.printStackTrace();	
				}
			    
		} 
		
		
		public List<UserAccount> getAllUserAccountDao(){
	
		List<UserAccount> listUser= new ArrayList<UserAccount>();
		
		try {

			// 2 : Ouverture transaction 
			EntityTransaction tx =  em.getTransaction();
			tx.begin();

			// 4 : obtention de la liste des eleves viaEntityManager
			listUser = em.createQuery("from UserAccount" , UserAccount.class).getResultList();
		
			// 5 : Fermeture transaction 
			 tx.commit();
		
		} catch (Exception e) {
		e.printStackTrace();
		
		} 

		return listUser;
		
	}

}
