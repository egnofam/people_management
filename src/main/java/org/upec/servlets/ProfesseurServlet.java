package org.upec.servlets;

import java.io.IOException;
import org.upec.beans.Professeur;
import org.upec.gestionbdd.GestionBDD;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ProfesseurServlet
 */
public class ProfesseurServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//private GestionBDD gbdd = new GestionBDD();
    /**
     * Default constructor. 
     */
    public ProfesseurServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		this.getServletContext().getRequestDispatcher("/WEB-INF/formprofesseur.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	    Professeur professeur = new Professeur();
	    
	    professeur.setId(Integer.parseInt(request.getParameter("id")));
		professeur.setNom(request.getParameter("nom"));
		professeur.setPrenom(request.getParameter("prenom"));
		professeur.setDateNaissance(request.getParameter("dateNaissance"));
		professeur.setDiplome(request.getParameter("diplome"));
		//request.setAttribute("professeur", professeur);
		
		HttpSession session = request.getSession();
		session.setAttribute("professeurSession", professeur);
		GestionBDD gbdd = new GestionBDD();
		
		try {
			gbdd.ajouterProfesseur(professeur.getId(),professeur.getDiplome());
			gbdd.ajouterPersonnel(professeur.getId(), professeur.getNom(), professeur.getPrenom(), professeur.getDateNaissance());
			/*for (Modul module : professeur.getModules()) {
				gbdd.ajouterModule(professeur.getId(),module.getTitre(),module.getNbHeuresCours(), module.getNbHeureTd(), module.getNbHeureTp());
			}*/
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		
	}

}
