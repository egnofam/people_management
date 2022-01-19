package org.upec.servlets;

import java.io.IOException;

import org.upec.beans.Modul;
import org.upec.beans.Professeur;
import org.upec.gestionbdd.GestionBDD;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ModuleServlet
 */
public class ModuleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ModuleServlet() {
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.getServletContext().getRequestDispatcher("/WEB-INF/module.jsp").forward(request, response);
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		Professeur professeur = new Professeur();
		professeur = (Professeur) session.getAttribute("professeurSession");
		GestionBDD gbdd = new GestionBDD();
		Modul module = new Modul();
		module.setTitre(request.getParameter("titre"));
		module.setNbHeuresCours(Integer.parseInt(request.getParameter("nb_heures_cours")));
		module.setNbHeureTd(Integer.parseInt(request.getParameter("nb_heures_td")));
		module.setNbHeureTp(Integer.parseInt(request.getParameter("nb_heures_tp")));
		
		try {
			gbdd.ajouterModule(professeur.getId(), module.getTitre(), module.getNbHeuresCours(), module.getNbHeureTd(),module.getNbHeureTp());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		doGet(request, response);
	}

}
