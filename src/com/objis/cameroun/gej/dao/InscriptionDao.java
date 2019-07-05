package com.objis.cameroun.gej.dao;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Persistence;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import com.objis.cameroun.gej.domaine.Eleve;
import com.objis.cameroun.gej.domaine.Inscription;


import java.sql.Date;

import javax.persistence.Query;

import org.hibernate.Hibernate;
import org.hibernate.Session;
//import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

//import org.hibernate.Query; 

//import org.hibernate.Query; 

import org.apache.log4j.Logger; 

import org.hibernate.boot.registry.StandardServiceRegistryBuilder; 

import org.hibernate.cfg.Configuration; 

import org.hibernate.service.ServiceRegistry; 


public class InscriptionDao implements InscriptionIDao{
	
	
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestionEcole-pu");
	
	EntityManager em = emf.createEntityManager();
	

	public InscriptionDao() {
		// TODO Auto-generated constructor stub
	}

	public InscriptionDao(EntityManager em) {
		super();
		this.em = em;
	}
		
	public  int insertInscription(Inscription inscription) throws SQLException 
	{    
		
		try {
			
			// 2 : Ouverture transaction 
				EntityTransaction tx =  em.getTransaction();
				tx.begin();

			// 4 : Persistance Objet/Relationnel : création d'un enregistrement en base
				 em.persist(inscription);
				
				// 5 : Fermeture transaction 
				 tx.commit();
				 
			} catch (Exception e) {
				e.printStackTrace();
				
				return 0;
			} 
		return 1;			    
	}     
	
	public Inscription findInscription(String matricule) throws SQLException 
	{        
	
		Inscription inscription ;
		 
		List<Inscription> lInscription = new ArrayList<Inscription>();
		
        Query query = em.createQuery("SELECT e FROM Inscription e WHERE e.matricule = :id");
        query.setParameter("id", matricule);
        lInscription = query.getResultList();
        inscription = (Inscription)lInscription.get(0);
        
        em.close();
		
		return inscription;
	}     
	
	public  void updateInscription(Eleve eleve, Inscription inscription) throws SQLException 
	{        
		
		try{	
			
			// 2 : Ouverture transaction 
			em.getTransaction().begin();
			
			System.out.println(inscription);
			// 4 : Persistance Objet/Relationnel : création d'un enregistrement en base
			em.merge(eleve);
			em.merge(inscription);
			 
			// 5 : Fermeture transaction 
			 em.getTransaction().commit();
			
			}
		
			catch(Exception e)
			{	
				System.out.println(e);
				
			}
		
	}     
	
	
	public  void deleteInscription(String matricule) throws SQLException 
	{    
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestionEcole-pu");
		
		EntityManager em = emf.createEntityManager();
		
		try{	
			
			// 2 : Ouverture transaction 
			em.getTransaction().begin();
			
			Inscription inscription = findInscription(matricule);
			
			long inscripId = inscription.getId();
			long eleveID = inscription.getEleve().getId();
			
			Query query = em.createQuery("Delete From Inscription i Where i.id = :inscripId");
	        query.setParameter("inscripId", inscripId);
	        query.executeUpdate();
	        
	        Query query2 = em.createQuery("Delete From Eleve e Where e.id = :eleveID");
	        query2.setParameter("eleveID", eleveID);
	        query2.executeUpdate();
	        
			// 5 : Fermeture transaction 
			 em.getTransaction().commit();
			
			}
		
			catch(Exception e)
			{	
				System.out.println(e);	
			}
		
	} 
	
	
	
	public  List<Inscription> getAllInscriptions() throws ClassNotFoundException {
		
		 List<Inscription> lInscription = new ArrayList<Inscription>();
		 //Connection con;
				 
		 try {
				// 2 : Ouverture transaction 
				EntityTransaction tx =  em.getTransaction();
				tx.begin();

				// 4 : obtention de la liste des eleves viaEntityManager
				lInscription = em.createQuery("from Inscription" , Inscription.class).getResultList();
			
							// 5 : Fermeture transaction 
				 tx.commit();
			
			} catch (Exception e) {
			e.printStackTrace();
			
			} 

		 return lInscription;
	 }

} 