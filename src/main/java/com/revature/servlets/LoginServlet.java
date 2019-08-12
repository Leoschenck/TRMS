package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.beans.User;
import com.revature.daoImpls.FormDaoImpl;
import com.revature.daoImpls.UserDaoImpl;

/**
 * <h1>Team KLLJ - Tuition Reimbursement Management System (TRMS) Project 1</h1>
 * The purpose of TRMS is to provide a system that encourages quality knowledge growth
 * relevant to an individual’s expertise.  This program was created to address the
 * problems present in the current system, to provide the best user experience possible,
 * and to provide a more streamlined process for everyone involved.
 * <p>
 * Servlet implementation class LoginServlet
 * @author Justin Hua, Kyle Kolstad, Leonardo Schenck, Levi Applebaum
 * @version 1.0
 * 
 */
public class LoginServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/login.html").forward(req, resp);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter pw = resp.getWriter();
		try {
			// Retrieves any employees who match the username/password
			UserDaoImpl udi = new UserDaoImpl();
			FormDaoImpl fdi = new FormDaoImpl();
			String userName = req.getParameter("username");
			User u = udi.loginUser(userName, req.getParameter("password"));
			int userId = u.getId();
			// Sends the user to the home screen if the username and password match a user
			// or tells him there is no match and directs him back to the login.
			if (userId > 0) {
				HttpSession ses = req.getSession();
				ses.setAttribute("userId", userId);
				ses.setAttribute("isBenco", udi.isBenco(userId));
				ses.setAttribute("userName", userName);
				ses.setAttribute("remainingReimbursementAmount", u.getRmnReimbursement());
				String hasApprovablesString = fdi.hasApprovableForms(userId)?"1":"0";
				ses.setAttribute("hasApprovables", hasApprovablesString);
				resp.addCookie(new Cookie("userName", userName));
				resp.addCookie(new Cookie("remainingReimbursementAmount", u.getRmnReimbursement()+""));
				resp.addCookie(new Cookie("isBenco", udi.isBenco(userId)));
				resp.addCookie(new Cookie("hasApprovables", hasApprovablesString));
				resp.sendRedirect("/TRMS/home");
			}
			else {
				resp.setContentType("text/html");
				pw.println("<script type=\"text/javascript\">window.alert('Username and password do not match!')</script>");
				req.getRequestDispatcher("/login.html").forward(req, resp);
			}
			pw.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}