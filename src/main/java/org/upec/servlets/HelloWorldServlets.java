package org.upec.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class HelloWorldServlets
 */
public class HelloWorldServlets extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public HelloWorldServlets() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String nom = request.getParameter("nom");
		String age = request.getParameter("age");
		
		request.setAttribute("nom",nom);
		request.setAttribute("age", age);
		this.getServletContext().getRequestDispatcher("/WEB-INF/index.jsp").forward(request,response);
		//response.getWriter().append("Hello Worlds From a Servlet").append(request.getContextPath());
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		/*String nom = request.getParameter("nom");
		String age = request.getParameter("age");
		
		request.setAttribute("nom",nom);
		request.setAttribute("age", age);
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);*/
		
		
	}

}
