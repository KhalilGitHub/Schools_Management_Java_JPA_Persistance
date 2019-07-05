package com.objis.cameroun.gej.presentation;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.sql.Connection;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.objis.cameroun.gej.domaine.Recrutement;
import com.objis.cameroun.gej.service.IService;
import com.objis.cameroun.gej.service.Service;





/**
 * Servlet implementation class ModifDeleteProduct
 */
@WebServlet("/ServletModifSupEnseignant")
public class ServletModifSupEnseignant extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	 
	public ServletModifSupEnseignant() {        super();    }     
	
	@Override    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
	{        
		EntityManagerFactory emf = (EntityManagerFactory)getServletContext().getAttribute("emf");
        
		 EntityManager em = emf.createEntityManager();
	
		 IService iservice = new Service(em);
		
		String errorString = null;        
		List<Recrutement> list = null;        
		try 
		{            
			
			list = iservice.getAllRecrutementService();
			     
		} 
		catch (ClassNotFoundException e) 
		{            
			e.printStackTrace();            
			errorString = e.getMessage();        
		}        
		// Store info in request attribute, before forward to views        
		request.setAttribute("errorString", errorString); 
		
		request.setAttribute("listRecrutement", list);  
		
		// Forward to /WEB-INF/views/InscriptionListView.jsp        
		RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/modifSupRecrutement.jsp");        
		try {
			dispatcher.forward(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}    
	}     
	
	@Override    
	protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException 
	{        
		doGet(request, response);    
	} 
} 

