package com.objis.cameroun.gej.presentation;

import java.io.ByteArrayOutputStream;


import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.objis.cameroun.gej.domaine.Enseignant;
import com.objis.cameroun.gej.domaine.Recrutement;
import com.objis.cameroun.gej.service.IService;
import com.objis.cameroun.gej.service.Service;
//import com.objis.cameroun.gej.connection.ConnectInscription;



import javax.servlet.http.Part;
import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;

/**
 * Servlet implementation class ServletInscription
 */

@MultipartConfig(maxFileSize = 169999999)
@WebServlet("/SerletEnregistrerEnseignant")
public class SerletEnregistrerEnseignant extends HttpServlet {
	private static final long serialVersionUID = 1L;     
	public SerletEnregistrerEnseignant() 
	{        super();    }     
	// Show Inscription creation page.    
	
	@Override    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{         
		RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/EnregisterEnseignant.jsp");        
		dispatcher.forward(request, response);    
	}     
	
	// When the user enters the Inscription information, and click Submit.    
	// This method will be called.    
	
	@Override    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{        
		//Connection conn = MyUtils.getStoredConnection(request);  
		
		//Connection conn;
		int nombreMatiere = 0;
		int nombreHeure = 0;
		BigDecimal fraisParHeure = null;
		BigDecimal salaire = null;
		Date date = null;
		//byte[] fileImage;
		Recrutement recrutement ;
		Enseignant enseignant = null;
		IService iService = null; 
		
		String matricule = (String) request.getParameter("matricule");        
		String nom = (String) request.getParameter("nom");  
		String prenom = (String) request.getParameter("prenom");
		String genre = (String) request.getParameter("genre");
		String adresse = (String) request.getParameter("adresse");
		
		String nombreMatieresStr = (String) request.getParameter("nombreMatieres");
		String premiereMatiere = (String) request.getParameter("premiereMatiere");
		String deuxiemeMatiere = (String) request.getParameter("deuxiemeMatiere");
		String nombreHeureStr = (String) request.getParameter("nombreHeure");
		String fraisParHeureStr = (String) request.getParameter("fraisParHeure");
		
		
		//System.out.println(matricule + " " + nom + " " + prenom + " " + genre + " " + adresse + " " + nombreMatieresStr + " " + premiereMatiere + " " + deuxiemeMatiere + " " + nombreHeureStr + " " + fraisParHeureStr);
		
		String dateStr = (String) request.getParameter("date");
	
		 InputStream inputStream = null;
		 Part filePart = request.getPart("image");
         if (filePart != null) 
         { 
             inputStream = filePart.getInputStream();
         }
		
         //System.out.println(inputStream);
         byte[] contents;
         ByteArrayOutputStream output = new ByteArrayOutputStream();
         byte[] buffer = new byte[1024];
         int count;
         while ((count = inputStream.read(buffer)) != -1)
         {
         	output.write(buffer, 0, count);
         }
     
         contents = output.toByteArray();
         Blob blob = null;
         try 
         {
         	blob = new SerialBlob(contents);
         } 
         catch (SerialException e) {e.printStackTrace();}
         catch (SQLException e) {e.printStackTrace();}
         
		try 
		{            
			nombreMatiere = Integer.parseInt(nombreMatieresStr);
			nombreHeure = Integer.parseInt(nombreHeureStr);
			fraisParHeure = convertStringToBigDecimal(fraisParHeureStr);  
			date = new SimpleDateFormat("mm/dd/yyyy").parse(dateStr);
			salaire = fraisParHeure.multiply(new BigDecimal(nombreHeure * 4));
			
		} 
		catch (Exception e) 
		{ 
			e.printStackTrace();       
		}  
		//Recrutement(matricule, enseignant, nombreHeureEnseigner, fraisParHeure, salaire, date, photo)
		////Matricule, Nom, Prenom, Genre, Adresse, NombreMatieres, PremiereMatiere,  DeuxiemeMatiere, NombreHeure, FraisParHeure, Salaire, Date, Image)
		
		EntityManagerFactory emf = (EntityManagerFactory)getServletContext().getAttribute("emf");
        
		 EntityManager em = emf.createEntityManager();
	
		 IService iservice = new Service(em);
		
		
		enseignant = new Enseignant(nom, prenom, genre, adresse, nombreMatiere, premiereMatiere, deuxiemeMatiere);			
		recrutement = new Recrutement(matricule, enseignant, nombreHeure, fraisParHeure, salaire, date, blob);
		
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
				iservice.insertRecrutementService(recrutement);
			} 
			catch (SQLException e) 
			{                
				e.printStackTrace();                
				errorString = e.getMessage();            
			}        
		}         
		
		// Store infomation to request attribute, before forward to views.        
		request.setAttribute("errorString", errorString);
		
		//System.out.println(recrutement);
		
		request.setAttribute("recrutement", recrutement);         
		
		// If error, forward to Edit page.        
		if (errorString != null) 
		{            
			RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/EnregisterEnseignant.jsp");            
			dispatcher.forward(request, response);        
		}        
		
		// If everything nice.        
		// Redirect to the Inscription listing page.        
		else 
		{            
			response.sendRedirect(request.getContextPath() + "/ServletListEnseignants");        
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