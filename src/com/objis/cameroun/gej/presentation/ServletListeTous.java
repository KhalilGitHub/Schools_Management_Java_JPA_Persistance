package com.objis.cameroun.gej.presentation;

import java.io.IOException;
import java.util.List;

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


/**
 * Servlet implementation class Inscription List Servlet
 */
@WebServlet("/ServletListeTous")
public class ServletListeTous extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletListeTous() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		EntityManagerFactory emf = (EntityManagerFactory)getServletContext().getAttribute("emf");		        	        
		EntityManager em = emf.createEntityManager();				
		IService iservice = new Service(em);		 
		
		//IService iService = null; 
		
		 RequestDispatcher view = request.getRequestDispatcher("/listerTousEleves.jsp");
		 List<Inscription> lproduct;
		try {
			//iService = new Service();
			lproduct = iservice.getAllInscriptionsService();
			request.setAttribute("listProducts", lproduct);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		 
		 view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);   
	}

}
