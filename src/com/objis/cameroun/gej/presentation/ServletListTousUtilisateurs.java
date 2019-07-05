package com.objis.cameroun.gej.presentation;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;
import javax.servlet.http.HttpSession;

import com.objis.cameroun.gej.domaine.UserAccount;
import com.objis.cameroun.gej.service.IService;
import com.objis.cameroun.gej.service.Service;

/**
 * Servlet implementation class ServletListTousUtilisateurs
 */
@WebServlet("/ServletListTousUtilisateurs")
public class ServletListTousUtilisateurs extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletListTousUtilisateurs() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
				EntityManagerFactory emf =
				 (EntityManagerFactory)getServletContext().getAttribute("emf");
				        
				        
				 EntityManager em = emf.createEntityManager();
					
					
				 IService iservice = new Service(em);
		
			
			
			List<UserAccount> lUsers;
			
			lUsers = iservice.getAllUserAccountService();
			HttpSession session = request.getSession();
			session.setAttribute("lUsers", lUsers);
			
			request.getRequestDispatcher("listDesUtilisateurs.jsp")
			.forward(request, response);
			
			
		}
	
		/**
		 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
		 */
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			// TODO Auto-generated method stub
			doGet(request, response);
		}

}
