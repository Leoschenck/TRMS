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
 * <h1>Team KLLJ - Tuition Reimbursement Management System (TRMS) Project 1</h1>
 * The purpose of TRMS is to provide a system that encourages quality knowledge growth
 * relevant to an individual’s expertise.  This program was created to address the
 * problems present in the current system, to provide the best user experience possible,
 * and to provide a more streamlined process for everyone involved.
 * <p>
 * Servlet implementation class GetApprovableFormsServlet
 * @author Justin Hua, Kyle Kolstad, Leonardo Schenck, Levi Applebaum
 * @version 1.0
 * 
 */
public class GetApprovableFormsServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		ObjectMapper om = new ObjectMapper();
		HttpSession s = req.getSession(false);
		if(s != null) {
			FormDaoImpl fdi = new FormDaoImpl();
			ArrayList<Form> approvableForms = null;
			try {
				approvableForms = fdi.getApprovableFormsByUserId((int)s.getAttribute("userId"));
				ArrayList<FormWithDeptName> allFormsWithDeptName = new ArrayList<FormWithDeptName>();
				for (Form form : approvableForms) {
					allFormsWithDeptName.add(new FormWithDeptName(form));
				}
				om.writeValue(out, allFormsWithDeptName);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}else {
			resp.sendRedirect("/TRMS/login");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
