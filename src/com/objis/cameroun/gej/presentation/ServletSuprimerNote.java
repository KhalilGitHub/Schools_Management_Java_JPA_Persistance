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

import com.objis.cameroun.gej.service.IService;
import com.objis.cameroun.gej.service.Service;

/**
 * Servlet implementation class ServletSuprimerNote
 */
@WebServlet("/ServletSuprimerNote")
public class ServletSuprimerNote extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletSuprimerNote() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
   	{        
   		
   		EntityManagerFactory emf = (EntityManagerFactory)getServletContext().getAttribute("emf");
           
   		 EntityManager em = emf.createEntityManager();
   	
   		 IService iservice = new Service(em);
   		String mat = (String) request.getParameter("matricule");         
   		String errorString = null;         
   		try 
   		{   
   			
   			iservice.deleteNotesService(mat);
   			       
   		} 
   		catch(SQLException e) 
   		{            
   			e.printStackTrace();            
   			errorString = e.getMessage();        
   		}                  
   		
   		// If has an error, redirecte to the error page.        
   		if (errorString != null) 
   		{            
   			// Store the information in the request attribute, before forward to views.            
   			request.setAttribute("errorString", errorString);            //             
   			RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/erreurSupprimerInscription.jsp");            
   			dispatcher.forward(request, response);        
   		}        
   		
   		// If everything nice.        
   		// Redirect to the Inscription listing page.                
   		else 
   		{            
   			response.sendRedirect(request.getContextPath() + "/ServletListeTousNotes");        
   		}     
   	}     
   	
   	@Override    
   	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
   	{        
   		doGet(request, response);    
   	} 
   }