package com.objis.cameroun.gej.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.objis.cameroun.gej.domaine.Mensualite;


public class MensualiteDao {
	
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestionEcole-pu");
	
	EntityManager em = emf.createEntityManager();
	

	public MensualiteDao() {
		// TODO Auto-generated constructor stub
	}

	public MensualiteDao(EntityManager em) {
		super();
		this.em = em;
	}
		
	public  int insertNotes(Mensualite mensualite) throws SQLException 
	{    
		
		try {
			
			// 2 : Ouverture transaction 
				EntityTransaction tx =  em.getTransaction();
				tx.begin();

			// 4 : Persistance Objet/Relationnel : création d'un enregistrement en base
				 em.persist(mensualite);
				
				// 5 : Fermeture transaction 
				 tx.commit();
				 
			} catch (Exception e) {
				e.printStackTrace();
				
				return 0;
			} 
		return 1;			    
	}     
	
	public Mensualite findMensualite(String matricule) throws SQLException 
	{        
	
		Mensualite mensualite ;
		 
		List<Mensualite> lMensualite = new ArrayList<Mensualite>();
		
        Query query = em.createQuery("SELECT e FROM Examen e WHERE e.matricule = :id");
        query.setParameter("id", matricule);
        lMensualite = query.getResultList();
        mensualite = (Mensualite)lMensualite.get(0);
        
        em.close();
		
		return mensualite;
	}     
	
	public  void updateNotes(Mensualite mensualite) throws SQLException 
	{        
		
		try{	
			
			// 2 : Ouverture transaction 
			em.getTransaction().begin();
			
			System.out.println(mensualite);
			// 4 : Persistance Objet/Relationnel : création d'un enregistrement en base
			//em.merge(examen);
			em.merge(mensualite);
			 
			// 5 : Fermeture transaction 
			 em.getTransaction().commit();
			
			}
		
			catch(Exception e)
			{	
				System.out.println(e);
				
			}
		
	}     
	
	
	public  void deleteNotes(String matricule) throws SQLException 
	{    
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestionEcole-pu");
		
		EntityManager em = emf.createEntityManager();
		
		try{	
			
				// 2 : Ouverture transaction 
				em.getTransaction().begin();
				
				Mensualite mensualite = findMensualite(matricule);
				
				long mensualiteId = mensualite.getId();
				Query query = em.createQuery("Delete From Examen i Where i.id = :mensualiteId");
				query.setParameter("mensualiteId", mensualiteId);
		        query.executeUpdate();
			
			}
		
			catch(Exception e)
			{	
				System.out.println(e);	
			}
		
	} 
	
	public  List<Mensualite> getAllNotes() throws ClassNotFoundException {
		
		 List<Mensualite> lExamen = new ArrayList<Mensualite>();
		 //Connection con;
				 
		 try {
				// 2 : Ouverture transaction 
				EntityTransaction tx =  em.getTransaction();
				tx.begin();

				// 4 : obtention de la liste des eleves viaEntityManager
				lExamen = em.createQuery("from Mensualite" , Mensualite.class).getResultList();
			
							// 5 : Fermeture transaction 
				 tx.commit();
			
			} catch (Exception e) {
			e.printStackTrace();
			
			} 

		 return lExamen;
	 }
}
