package com.objis.cameroun.gej.presentation; 

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException; 
import javax.servlet.ServletException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession; 
import com.objis.cameroun.gej.domaine.UserAccount;
import com.objis.cameroun.gej.service.IService;
import com.objis.cameroun.gej.service.Service;
import com.objis.cameroun.gej.utils.ConnectionMysql;
import com.objis.cameroun.gej.utils.MyUtils; 

@WebServlet(urlPatterns = { "/ServletLogin" })
public class ServletLogin extends HttpServlet 
{    
	private static final long serialVersionUID = 1L;     
	public ServletLogin() 
	{        
		super();    
	}     
	// Show Login page.    
	@Override    protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException 
	{         
		// Forward to /WEB-INF/views/loginView.jsp        
		// (Users can not access directly into JSP pages placed in WEB-INF)        
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/login.jsp");         
		dispatcher.forward(request, response);     
	}     
	// When the user enters userName & password, and click Submit.    
	// This method will be executed.    
	@Override    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException 
	{        
		EntityManagerFactory emf = (EntityManagerFactory)getServletContext().getAttribute("emf");
        
		EntityManager em = emf.createEntityManager();
	
		IService iservice = new Service(em);
		
		String userName = request.getParameter("userName");        
		String password = request.getParameter("password");        
		String rememberMeStr = request.getParameter("rememberMe");        
		boolean remember = "Y".equals(rememberMeStr);         
		UserAccount user = null;        
		boolean hasError = false;        
		String errorString = null;         
		if (userName == null || password == null || userName.length() == 0 || password.length() == 0) 
		{            
			hasError = true;            
			errorString = "le Nom d'Utilisateur et le Mot de Passe sont obligatoires!";        
		} 
		else 
		{            
			
			Connection conn;
			try 
			{        
				//iService = new Service();
				//conn = ConnectionMysql.getInstance();
				// Find the user in the DB.   
				
				user = iservice.findUserService(userName, password);
				
				if (user == null) 
				{                    
					hasError = true;                    
					errorString = "Nom d'utilisateur ou Mot de passe invalid";                
				}            
			} 
			catch (SQLException e) 
			{                
				e.printStackTrace();                
				hasError = true;                
				errorString = e.getMessage();            
			}        
		}        
		// If error, forward to /WEB-INF/views/login.jsp        
		if (hasError) 
		{            
			user = new UserAccount();            
			user.setUserName(userName);            
			user.setPassword(password);             
			// Store information in request attribute, before forward.            
			request.setAttribute("errorString", errorString);            
			request.setAttribute("user", user);             
			// Forward to /WEB-INF/views/login.jsp            
			RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/login.jsp");             
			dispatcher.forward(request, response);        
		}        
		// If no error        
		// Store user information in Session        
		// And redirect to userInfo page.        
		else 
		{            
			HttpSession session = request.getSession();            
			MyUtils.storeLoginedUser(session, user);             
			// If user checked "Remember me".            
			if (remember) 
			{                
				MyUtils.storeUserCookie(response, user);            
			}            // Else delete cookie.            
			else 
			{                
				MyUtils.deleteUserCookie(response);            
			}             
		// Redirect to userInfo page.            
		response.sendRedirect(request.getContextPath() + "/ServletInfoUtilisateur");        
		}    
	} 
}			