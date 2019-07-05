package com.objis.cameroun.gej.presentation;


import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.objis.cameroun.gej.domaine.Examen;
import com.objis.cameroun.gej.domaine.Notes;
import com.objis.cameroun.gej.service.IService;
import com.objis.cameroun.gej.service.Service;

/**
 * Servlet implementation class ServletInsererNotes
 */
@WebServlet("/ServletInsererNotes")
public class ServletInsererNotes extends HttpServlet {
	private static final long serialVersionUID = 1L;  
	
	public ServletInsererNotes() 
	{        super();    }     
	// Show Inscription creation page.    
	
	@Override    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{         
		RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/InsererNotes.jsp");        
		dispatcher.forward(request, response);    
	}     
	
	// When the user enters the Inscription information, and click Submit.    
	// This method will be called.    
	
	@Override    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{        
		//Connection conn = MyUtils.getStoredConnection(request);  
		
		Notes notes ;
		//Notes note = null;
		Examen examen = null;
		//IService iService = null;
		
		BigDecimal francais = null;
		BigDecimal anglais = null;
		BigDecimal math = null;
		BigDecimal physique = null;
		BigDecimal biologie = null;
		BigDecimal histoire = null;
		BigDecimal geo = null;
		BigDecimal ePS = null;
	
		int semestre = 0;
		Date date = null; 
		
		String matricule = (String) request.getParameter("matricule");   
		String dateStr = (String) request.getParameter("date");
		String annee = (String) request.getParameter("annee");  
		String semestreStr = (String) request.getParameter("semestre");
		String francaisStr = (String) request.getParameter("francais");
		String anglaisStr = (String) request.getParameter("anglais");
		String mathStr = (String) request.getParameter("math");
		String physiqueStr = (String) request.getParameter("physique");
		String biologieStr = (String) request.getParameter("biologie");
		String histoireStr = (String) request.getParameter("histoire");
		String geoStr = (String) request.getParameter("geo");
		String epsStr = (String) request.getParameter("eps");
	
		
        
		try 
		{            
			date = new SimpleDateFormat("dd/mm/yyyy").parse(dateStr);
			semestre = Integer.parseInt(semestreStr);
			francais = convertStringToBigDecimal(francaisStr); 
			anglais = convertStringToBigDecimal(anglaisStr);			
			math = convertStringToBigDecimal(mathStr);
			physique = convertStringToBigDecimal(physiqueStr);
			biologie = convertStringToBigDecimal(biologieStr);
			histoire = convertStringToBigDecimal(histoireStr);
			geo = convertStringToBigDecimal(geoStr);
			ePS = convertStringToBigDecimal(epsStr);
		
			
		} 
		catch (Exception e) 
		{ 
			e.printStackTrace();       
		}  
		
		BigDecimal totalNote = BigDecimal.ZERO; 
		//BigDecimal test = new BigDecimal(0.0);
		BigDecimal[] noteElev = new BigDecimal[10];
		
		noteElev[0] =  francais;
		noteElev[1] =  anglais;
		noteElev[2] =  math;
		noteElev[3] =  physique;
		noteElev[4] =  biologie;
		noteElev[5] =  histoire;
		noteElev[6] =  geo;
		noteElev[7] =  ePS;
				
		
		
		for(BigDecimal note : noteElev)
		//for(int i = 0; i <= noteElev.length; i++)
		{
			
			if(note != null)
			{
				totalNote = totalNote.add(note);
			}
		}	
		
		BigDecimal moyenne = null;
		BigDecimal length = new BigDecimal("8");
		
		moyenne = totalNote.divide(length);
		
		 examen = new Examen( matricule, date,  annee, semestre, francais,  anglais,  math,  physique,  biologie, histoire,  geo,  ePS,  totalNote,  moyenne);
	
		EntityManagerFactory emf = (EntityManagerFactory)getServletContext().getAttribute("emf");
        
		 EntityManager em = emf.createEntityManager();
	
		 IService iservice = new Service(em);
						
		String errorString = null;         
		
		// Inscription ID is the string literal [a-zA-Z_0-9]        
		// with at least 1 character        
		String regex = "\\w+";         
		if (matricule == null || !matricule.matches(regex)) 
		{            
			errorString = "Code d'Inscriotion invalide !!!";        
		}         
		if (errorString == null) 
		{            
			try 
			{    
				//iService = new Service();
				//conn = ConnectInscription.getMySQLConnection();
				iservice.insertExamenService(examen);
			} 
			catch (SQLException e) 
			{                
				e.printStackTrace();                
				errorString = e.getMessage();            
			}        
		}         
		
		// Store infomation to request attribute, before forward to views.        
		request.setAttribute("errorString", errorString);        
		request.setAttribute("examen", examen);         
		
		// If error, forward to Edit page.        
		if (errorString != null) 
		{            
			RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/InsererNotes.jsp");            
			dispatcher.forward(request, response);        
		}        
		
		// If everything nice.        
		// Redirect to the Inscription listing page.        
		else 
		{            
			response.sendRedirect(request.getContextPath() + "/ServletListeTousNotes");        
		}    
	} 
	
	protected BigDecimal convertStringToBigDecimal(String bdStr)
	 {
		 BigDecimal result = null;
		 try
			{
				double valueDouble = Double.parseDouble(bdStr);
				result = BigDecimal.valueOf(valueDouble);
			}
			catch(Exception ex)
			{
				System.out.println("Valeur invalid. Valeur par defaut to 0.0");
				result = BigDecimal.valueOf(0.0);
			}	
		return result;	
	}
	
}