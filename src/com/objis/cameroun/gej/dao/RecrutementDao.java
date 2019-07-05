package com.objis.cameroun.gej.dao;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.objis.cameroun.gej.domaine.Enseignant;
import com.objis.cameroun.gej.domaine.Recrutement;


public class RecrutementDao implements RecrutementIDao{

	EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestionEcole-pu");
	
	EntityManager em = emf.createEntityManager();
	

	
	public RecrutementDao() {
		// TODO Auto-generated constructor stub
	}

	public RecrutementDao(EntityManager em) {
		super();
		this.em = em;
	}
	
	
	public  int insertRecrutement(Recrutement recrutement) throws SQLException 
	{        
		try {
			
			// 2 : Ouverture transaction 
				EntityTransaction tx =  em.getTransaction();
				tx.begin();

			// 4 : Persistance Objet/Relationnel : création d'un enregistrement en base
				 em.persist(recrutement);
				
				// 5 : Fermeture transaction 
				 tx.commit();
				 
			} catch (Exception e) {
				e.printStackTrace();
				
				return 0;
			} 
		return 1;			
		    
	}    

	
	public  Recrutement findRecrutement(String matricule) throws SQLException 
	{        
		
		Recrutement recrutement ;
		 
		List<Recrutement> lRecrutement = new ArrayList<Recrutement>();
		
        Query query = em.createQuery("SELECT r FROM Recrutement r WHERE r.matricule = :id");
        query.setParameter("id", matricule);
        //List<?> list = query.list();
        lRecrutement = query.getResultList();
        recrutement = (Recrutement)lRecrutement.get(0);
        //resultList.forEach(System.out::println);
       
        em.close();
		
		return recrutement;
		
	}   
	 
	
	
	public  void updateRecrutement(Enseignant enseignant, Recrutement recrutement) throws SQLException 
	{        
		
		try{	
			
			// 2 : Ouverture transaction 
			em.getTransaction().begin();
			
			// 4 : Persistance Objet/Relationnel : création d'un enregistrement en base
			em.merge(enseignant);
			em.merge(recrutement);
			 
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
				
				Recrutement recrutement = findRecrutement(mat);
				long recrutementId = recrutement.getId();
				long enseigID = recrutement.getEnseignant().getId();
				
				Query query1 = em.createQuery("Delete From Enseignant e Where e.id = :enseigID");
		        query1.setParameter("enseigID", enseigID);
		        query1.executeUpdate();
				
				
				Query query2 = em.createQuery("Delete From Recrutement i Where i.id = :recrutementId");
				query2.setParameter("recrutementId", recrutementId);
		        query2.executeUpdate();
		        
		        
			
			}
		
			catch(Exception e)
			{	
				e.printStackTrace();	
			}
		    
	} 
	
	
	public  List<Recrutement> getAllRecrutement() throws ClassNotFoundException {
		
		 List<Recrutement> lRecrutement = new ArrayList<Recrutement>();
				 
		 try {
				// 2 : Ouverture transaction 
				EntityTransaction tx =  em.getTransaction();
				tx.begin();

				// 4 : obtention de la liste des eleves viaEntityManager
				lRecrutement = em.createQuery("from Recrutement" , Recrutement.class).getResultList();
			
				// 5 : Fermeture transaction 
				 tx.commit();
			
			} catch (Exception e) {
			e.printStackTrace();
			
			} 

		 return lRecrutement;
		
	 }	 
	
} 

