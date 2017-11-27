package ma.jberrich.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ma.jberrich.bean.Dept;
import ma.jberrich.bean.Emp;
import ma.jberrich.dao.DeptDAO;
import ma.jberrich.dao.EmpDAO;
import ma.jberrich.dao.commons.DAOProvider;
import ma.jberrich.model.Employe;
import ma.jberrich.model.ListeEmployes;

public class TraitementEmploye extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private EmpDAO  edao = DAOProvider.getDAO(EmpDAO.class);
	private DeptDAO sdao = DAOProvider.getDAO(DeptDAO.class);
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nomEmp = request.getParameter("nom");
		int ageEmp = Integer.parseInt(request.getParameter("age"));
		String fonctionEmp = request.getParameter("fonction");
		int salaireEmp = Integer.parseInt(request.getParameter("salaire"));
		String nomService = request.getParameter("service");
		
		Dept service = sdao.findByName(nomService);
		
		Emp employe = new Emp();
		employe.setEname(nomEmp);
		employe.setAge(ageEmp);
		employe.setJob(fonctionEmp);
		employe.setSal(salaireEmp);
		employe.setDeptno(service.getDeptno());
		
		int empno = edao.insert(employe);
		employe.setEmpno(empno);
		
		List<Emp> emps = edao.findAll();
		ListeEmployes employes = new ListeEmployes();
		for(Emp emp:emps) {
			Employe bean = new Employe();
			bean.setId(emp.getEmpno());
			bean.setNom(emp.getEname());
			bean.setAge(emp.getAge());
			bean.setFonction(emp.getJob());
			bean.setSalaire(emp.getSal());
			bean.setService(sdao.find(emp.getDeptno()).getDname());
			employes.getEmployes().add(bean);
		}
		request.setAttribute("liste", employes);
		
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/infoEmployes.jsp");
		dispatcher.forward(request, response);
	}

}
