package com.objis.cameroun.gej.presentation;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.Blob;
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
import javax.servlet.http.Part;
import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;

import com.objis.cameroun.gej.domaine.Eleve;
import com.objis.cameroun.gej.domaine.Enseignant;
import com.objis.cameroun.gej.domaine.Inscription;
import com.objis.cameroun.gej.domaine.Recrutement;
import com.objis.cameroun.gej.service.IService;
import com.objis.cameroun.gej.service.Service;

/**
 * Servlet implementation class ServletModifierEnseignant
 */
@WebServlet("/ServletModifierEnseignant")
public class ServletModifierEnseignant extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletModifierEnseignant() {
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
		//Connection conn = MyUtils.getStoredConnection(request); 
		//Connection conn = null;
		//IService iService = null; 
		
		String matricule = (String) request.getParameter("matricule");         
		Recrutement recrutement = null;         
		String errorString = null;         
		try 
		{   
			//iService = new Service();
			//conn = ConnectInscription.getMySQLConnection();
			recrutement = iservice.findRecrutementService(matricule);
			      
		} 
		catch (SQLException e) 
		{            
			e.printStackTrace();            
			errorString = e.getMessage();        
		}         
		
		// If no error.        
		// The Inscription does not exist to edit.        
		// Redirect to InscriptionList page.        
		if (errorString != null && recrutement == null) 
		{            
			response.sendRedirect(request.getServletPath() + "/ServletListEnseignants");            
			return;        
		}         
		
		// Store errorString in request attribute, before forward to views.        
		request.setAttribute("errorString", errorString);        
		request.setAttribute("recrutement", recrutement); 
		//System.out.println(inscription);
		RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/modifierEnseignant.jsp");        
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
		
		
		int idRecrut = 0;
		int idEnseig = 0;
		int nombreMatiere = 0;
		int nombreHeure = 0;
		
		BigDecimal fraisParHeure = null;
		BigDecimal salaire = null;
		Date date = null;
		Recrutement recrutement = null;
		Enseignant enseignant = null;
		
		String idRecrutStr = (String) request.getParameter("idRecrut");
		String idEnseigStr = (String) request.getParameter("idEnseig");
		
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
		String dateStr = (String) request.getParameter("date");
		
		System.out.println(idRecrutStr + " " + idEnseigStr + " " + nom + " " + nombreHeureStr);
		
		 InputStream inputStream = null;
		 Part filePart = request.getPart("image");
         if (filePart != null) 
         {
            
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
			
			idRecrut = Integer.parseInt(idRecrutStr);
			idEnseig = Integer.parseInt(idEnseigStr);
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
		
		enseignant = new Enseignant(idEnseig ,  nom,  prenom,  genre,  adresse , nombreMatiere,  premiereMatiere,  deuxiemeMatiere);		
		recrutement = new Recrutement(idRecrut, nombreHeure, matricule, enseignant,  fraisParHeure,  salaire, date, blob);
	        
		String errorString = null;         
		try 
		{    
			iservice.updateRecrutementService(enseignant, recrutement);    
		} 
		catch (SQLException e) 
		{            
			e.printStackTrace();            
			errorString = e.getMessage();        
		}        
		
		// Store infomation to request attribute, before forward to views.        
		request.setAttribute("errorString", errorString);        
		request.setAttribute("recrutement", recrutement);         
		
		// If error, forward to Edit page.        
		if (errorString != null) 
		{            
			RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/modifierEnseignant.jsp");            
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
				System.out.println("Valeur invalide. Valeur par defaut 0.0");
				result = BigDecimal.valueOf(0.0);
			}	
		return result;	
	}
}