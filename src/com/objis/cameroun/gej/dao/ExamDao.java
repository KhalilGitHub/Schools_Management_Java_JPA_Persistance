package com.objis.cameroun.gej.dao;



import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;


import com.objis.cameroun.gej.domaine.Examen;
import com.objis.cameroun.gej.domaine.Notes;

//import com.objis.cameroun.gej.connection.ConnectNotes;

public class ExamDao implements ExamIDao{

	EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestionEcole-pu");
	
	EntityManager em = emf.createEntityManager();
	

	public ExamDao() {
		// TODO Auto-generated constructor stub
	}

	public ExamDao(EntityManager em) {
		super();
		this.em = em;
	}
		
	public Examen findNotes(String matricule) throws SQLException 
	{        
	
		Examen examen ;
		 
		List<Notes> lNotes = new ArrayList<Notes>();
		
        Query query = em.createQuery("SELECT e FROM Examen e WHERE e.matricule = :matricule");
        query.setParameter("matricule", matricule);
        lNotes = query.getResultList();
        examen = (Examen)lNotes.get(0);
        
        em.close();
		
		return examen;
	}     
	
	public  int insertNotes(Examen examen) throws SQLException 
	{    
		
		try {
			
			// 2 : Ouverture transaction 
				EntityTransaction tx =  em.getTransaction();
				tx.begin();

			// 4 : Persistance Objet/Relationnel : création d'un enregistrement en base
				 em.persist(examen);
				
				// 5 : Fermeture transaction 
				 tx.commit();
				 
			} catch (Exception e) {
				e.printStackTrace();
				
				return 0;
			} 
		return 1;			    
	}     
	
	public  void updateNotes(Examen examen) throws SQLException 
	{        
		
		try{	
			
			// 2 : Ouverture transaction 
			em.getTransaction().begin();
			
			System.out.println(examen);
			// 4 : Persistance Objet/Relationnel : création d'un enregistrement en base
			//em.merge(examen);
			em.merge(examen);
			 
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
				
				Examen examen = findNotes(matricule);
				
				long examenId = examen.getId();
				Query query = em.createQuery("Delete From Examen i Where i.id = :examenId");
		        query.setParameter("examenId", examenId);
		        query.executeUpdate();	
		
			}
		
			catch(Exception e)
			{	
				System.out.println(e);	
			}
		
	} 
	
	
	
	public  List<Examen> getAllNotes() throws ClassNotFoundException {
		
		 List<Examen> lExamen = new ArrayList<Examen>();
		 //Connection con;
				 
		 try {
				// 2 : Ouverture transaction 
				EntityTransaction tx =  em.getTransaction();
				tx.begin();

				// 4 : obtention de la liste des eleves viaEntityManager
				lExamen = em.createQuery("from Examen" , Examen.class).getResultList();
			
							// 5 : Fermeture transaction 
				 tx.commit();
			
			} catch (Exception e) {
			e.printStackTrace();
			
			} 

		 return lExamen;
	 }

	
	
}
