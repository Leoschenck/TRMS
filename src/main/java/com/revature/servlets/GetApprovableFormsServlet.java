package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.beans.Form;
import com.revature.daoImpls.FormDaoImpl;

/**
 * Servlet implementation class GetApprovableFormsServlet
 */
public class GetApprovableFormsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = resp.getWriter();
		ObjectMapper om = new ObjectMapper();
		HttpSession s = req.getSession(false);
		if(s != null) {
			FormDaoImpl fdi = new FormDaoImpl();
			ArrayList<Form> approvableForms = null;
			try {
				approvableForms = fdi.getApprovableFormsByUserId((int)s.getAttribute("userId"));
			} catch (SQLException e) {
				e.printStackTrace();
			}
			om.writeValue(out, approvableForms);
		}else {
			resp.sendRedirect("/TRMS/login");
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
