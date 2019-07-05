package com.objis.cameroun.gej.presentation;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.servlet.RequestDispatcher;
import com.objis.cameroun.gej.domaine.Eleve;
import com.objis.cameroun.gej.domaine.Inscription;
import com.objis.cameroun.gej.service.IService;
import com.objis.cameroun.gej.service.Service;
//import com.objis.cameroun.ges.connection.ConnectInscription;



/**
 * Servlet implementation class EditInscriptionServlet
 */
@MultipartConfig(maxFileSize = 169999999)
@WebServlet("/ServletModifierInscription")
public class ServletModifierInscription extends HttpServlet {
	
	private static final long serialVersionUID = 1L;     
	
	public ServletModifierInscription() 
	{        super();    }     
	
	// Show Inscription edit page.   
	@Override    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{      
		EntityManagerFactory emf = (EntityManagerFactory)getServletContext().getAttribute("emf");
        
		 EntityManager em = emf.createEntityManager();
	
		 IService iservice = new Service(em);
		//Connection conn = MyUtils.getStoredConnection(request); 
		//Connection conn = null;
		//IService iService = null; 
		
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
		// The Inscription does not exist to edit.        
		// Redirect to InscriptionList page.        
		if (errorString != null && inscription == null) 
		{            
			response.sendRedirect(request.getServletPath() + "/ServletListeTous");            
			return;        
		}         
		
		// Store errorString in request attribute, before forward to views.        
		request.setAttribute("errorString", errorString);        
		request.setAttribute("inscription", inscription); 
		//System.out.println(inscription);
		RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/modifierInscription.jsp");        
		dispatcher.forward(request, response);     
	}     
	
	// After the user modifies the Inscription information, and click Submit.    
	// This method will be executed.    
	@Override    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{   
		
		EntityManagerFactory emf = (EntityManagerFactory)getServletContext().getAttribute("emf");
        
		 EntityManager em = emf.createEntityManager();
	
		 IService iservice = new Service(em);
		
		//Connection conn = MyUtils.getStoredConnection(request);  
		//Connection conn = null;
		//IService iService = null; 
		
		int age = 0;
		int idInscrip = 0;
		int idElev = 0;
		BigDecimal frais = null;
		Date date = null;
		//byte[] fileImage;
		Inscription inscription ;
		Eleve eleve = null;
		
		String idInscripStr = (String) request.getParameter("idInscrip");
		String idElevStr = (String) request.getParameter("idElev");
		String matricule = (String) request.getParameter("matricule");        
		String nom = (String) request.getParameter("nom");  
		String prenom = (String) request.getParameter("prenom");
		String genre = (String) request.getParameter("genre");
		String adresse = (String) request.getParameter("adresse");
		String ageStr = (String) request.getParameter("age");
		String classe = (String) request.getParameter("classe");
		String fraisStr = (String) request.getParameter("frais");
		String dateStr = (String) request.getParameter("date");
		
		
		
		 InputStream inputStream = null;
		 Part filePart = request.getPart("image");
         if (filePart != null) 
         {
             /*// prints out some information for debugging
             System.out.println(filePart.getName());
             System.out.println(filePart.getSize());
             System.out.println(filePart.getContentType());*/
			
             inputStream = filePart.getInputStream();
         }
		
         byte[] contents;
         ByteArrayOutputStream output = new ByteArrayOutputStream();
         byte[] buffer = new byte[1024];
         int count;
         while ((count = inputStream.read(buffer)) != -1)
         {
         	output.write(buffer, 0, count);
         }
         //debugger says myinputstream has blksize 16384, buffcount 12742, and max 127394 here
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
			
			idInscrip = Integer.parseInt(idInscripStr);
			idElev = Integer.parseInt(idElevStr);
			age = Integer.parseInt(ageStr);
			frais = convertStringToBigDecimal(fraisStr);  
			date = new SimpleDateFormat("mm/dd/yyyy").parse(dateStr);
			
		} 
		catch (Exception e) 
		{ 
			e.printStackTrace();       
		}  
		
		eleve = new Eleve(idElev, matricule, nom, prenom, genre, adresse, age, classe);			
		inscription = new Inscription(idInscrip, matricule, eleve, frais, date, blob);
	        
		String errorString = null;         
		try 
		{    
			//iService = new Service();
			//conn = ConnectInscription.getMySQLConnection();
			iservice.updateInscriptionService(eleve, inscription);
			        
		} 
		catch (SQLException e) 
		{            
			e.printStackTrace();            
			errorString = e.getMessage();        
		}        
		
		// Store infomation to request attribute, before forward to views.        
		request.setAttribute("errorString", errorString);        
		request.setAttribute("inscription", inscription);         
		
		// If error, forward to Edit page.        
		if (errorString != null) 
		{            
			RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/modifierInscription.jsp");            
			dispatcher.forward(request, response);        
		}        
		
		// If everything nice.        
		// Redirect to the Inscription listing page.        
		else 
		{            
			response.sendRedirect(request.getContextPath() + "/ServletListeTous");        
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
				System.out.println("Valeur invalide. Valeur par defaut 0.0");
				result = BigDecimal.valueOf(0.0);
			}	
		return result;	
	}
}