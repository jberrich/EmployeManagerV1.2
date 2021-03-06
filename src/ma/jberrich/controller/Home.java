package ma.jberrich.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ma.jberrich.dao.DeptDAO;
import ma.jberrich.dao.commons.DAOProvider;
import ma.jberrich.model.ListeServices;

public class Home extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private DeptDAO sdao = DAOProvider.getDAO(DeptDAO.class);
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ListeServices services = new ListeServices();
		services.setServices(sdao.findAll());
		request.setAttribute("liste", services);
		
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/saisieEmploye.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
