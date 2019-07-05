package com.objis.cameroun.gej.presentation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.objis.cameroun.gej.domaine.Recrutement;
import com.objis.cameroun.gej.service.IService;
import com.objis.cameroun.gej.service.Service;
import com.objis.cameroun.gej.utils.Util;


/**
 * Servlet implementation class ServletListEleve
 */
@WebServlet("/ServletListEnseignants")
public class ServletListEnseignants extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletListEnseignants() {
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
		
		
		//IService iService = new Service();
		
		List<Recrutement> listRecrutement;
		
		List<String> lImageStr = new ArrayList();
		try {
			listRecrutement = iservice.getAllRecrutementService();
		
			HttpSession session = request.getSession();
			for(Recrutement recrutement: listRecrutement)
			{
				String imageBase64 = Util.parseBlobToString(recrutement.getImage());
				lImageStr.add(imageBase64);
				//
			}
			
			session.setAttribute("lImageStr", lImageStr);
			session.setAttribute("listRecrutement", listRecrutement);
			
			
			request.getRequestDispatcher("listEnseigants.jsp").forward(request, response);
			
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
