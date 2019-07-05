package com.objis.cameroun.gej.presentation;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.objis.cameroun.gej.domaine.UserAccount;
import com.objis.cameroun.gej.service.IService;
import com.objis.cameroun.gej.service.Service;


/**
 * Servlet implementation class ServletCreateLogin
 */
@WebServlet("/ServletCreateLogin")
public class ServletCreateLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletCreateLogin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{         
		RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/creerUserAccount.jsp");        
		dispatcher.forward(request, response);    
	}     
	
	// When the user enters the Inscription information, and click Submit.    
	// This method will be called.    
	
	@Override    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{        
		
		UserAccount user; 
		
		String nom;
		String prenom;
		String genre;
		String adresse;
		String tel;
		String fonction;
		String userName;   
		String level;   
		String password;    
		
		
		nom		 = (String) request.getParameter("nom");
		prenom	 = (String) request.getParameter("prenom");
		genre	 = (String) request.getParameter("genre");
		adresse	 = (String) request.getParameter("adresse");
		tel	 = (String) request.getParameter("tel");
		fonction = (String) request.getParameter("fonction");
		userName = (String) request.getParameter("userName");
		level = (String) request.getParameter("level");
		password = (String) request.getParameter("password");
		
		System.out.println(tel);
		
		user = new UserAccount(nom, prenom, fonction, genre, adresse, userName, level, password, tel);
		
		 EntityManagerFactory emf =
		 (EntityManagerFactory)getServletContext().getAttribute("emf");
		        
		        
		 EntityManager em = emf.createEntityManager();
			
			
		 IService iservice = new Service(em);
		
		
		
		System.out.println(user);
				
		String errorString = null;         
		
		// userName is the string literal [a-zA-Z_0-9]        
		// with at least 1 character        
		String regex = "\\w+";         
		if (userName == null || !userName.matches(regex)) 
		{            
			errorString = "Nom d'utisateur invalide !!!";        
		}         
		if (errorString == null) 
		{            
			iservice.saveUserAccountService(user);        
		}         
		
		// Store infomation to request attribute, before forward to views.        
		request.setAttribute("errorString", errorString);        
		request.setAttribute("user", user);         
		
		// If error, forward to Edit page.        
		if (errorString != null) 
		{            
			RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/creerUserAccount.jsp");            
			dispatcher.forward(request, response);        
		}        
		
		// If everything nice.        
		// Redirect to the Inscription listing page.        
		else 
		{            
			response.sendRedirect(request.getContextPath() + "/ServletListTousUtilisateurs");        
		}    
	} 	
}