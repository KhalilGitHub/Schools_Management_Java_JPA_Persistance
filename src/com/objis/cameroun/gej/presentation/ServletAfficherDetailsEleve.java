package com.objis.cameroun.gej.presentation;

import java.io.IOException;
import java.sql.SQLException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.objis.cameroun.gej.domaine.Inscription;
import com.objis.cameroun.gej.service.IService;
import com.objis.cameroun.gej.service.Service;
import com.objis.cameroun.gej.utils.Util;




@WebServlet("/ServletAfficherDetailsEleve")
public class ServletAfficherDetailsEleve extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	public ServletAfficherDetailsEleve() {      super();  }   
	
	  
		@Override    
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
		{        
			EntityManagerFactory emf = (EntityManagerFactory)getServletContext().getAttribute("emf");
	          
			 EntityManager em = emf.createEntityManager();
		
			 IService iservice = new Service(em);
			
			
			String matricule = (String) request.getParameter("matricule");         
			Inscription inscription = null;         
			String errorString = null;         
			try 
			{   
				//iService = new Service(); 
				//conn = ConnectInscription.getMySQLConnection();
				inscription = iservice.findInscriptionService(matricule);
							
			} 
			catch (SQLException e) 
			{            
				e.printStackTrace();            
				errorString = e.getMessage();        
			}         			
	
			// If no error.        
			// The product does not exist to edit.        
			// Redirect to Inscription List page.        
			if (errorString != null && inscription == null) 
			{            
				response.sendRedirect(request.getServletPath() + "/ServletListeTous");            
				return;        
			}     
			
			String imageBase64 = Util.parseBlobToString(inscription.getImage());
			// Store errorString in request attribute, before forward to views.        
			request.setAttribute("errorString", errorString);        
			request.setAttribute("inscription", inscription);  
			request.setAttribute("imageBase64", imageBase64);
			//request.setAttribute("image64", image64);
			RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/afficherDetailsEleve.jsp");        
			dispatcher.forward(request, response);     										 
		}     
		
		// After the user modifies the Inscription information, and click Submit.    
		// This method will be executed.    
		@Override    
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
		{        
			doGet(request, response);	
		}
			
	}