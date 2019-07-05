package com.objis.cameroun.gej.presentation;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.objis.cameroun.gej.domaine.Examen;
import com.objis.cameroun.gej.service.IService;
import com.objis.cameroun.gej.service.Service;

/**
 * Servlet implementation class ServletListeTousNotes
 */
@WebServlet("/ServletListeTousNotes")
public class ServletListeTousNotes extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletListeTousNotes() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//IService iService = new Service();
		EntityManagerFactory emf = (EntityManagerFactory)getServletContext().getAttribute("emf");		        	        
		EntityManager em = emf.createEntityManager();				
		IService iservice = new Service(em);
		
		List<Examen> lExamen;
		try {
			//iService = new Service();
			lExamen = iservice.getAllNotesService();
			HttpSession session = request.getSession();
			session.setAttribute("lExamen", lExamen);
			
			request.getRequestDispatcher("afficherDetailsNotes.jsp")
			.forward(request, response);
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
