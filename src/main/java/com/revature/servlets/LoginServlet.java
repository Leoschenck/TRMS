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

import com.revature.daoImpls.FormDaoImpl;
import com.revature.daoImpls.UserDaoImpl;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Forwards get requests
		req.getRequestDispatcher("/login.html").forward(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("In login servlet..." + req.getParameter("username") + "  ||  " + req.getParameter("password"));
		PrintWriter pw = resp.getWriter();
		try {
			// Retrieves any employees who match the username/password
			UserDaoImpl udi = new UserDaoImpl();
			FormDaoImpl fdi = new FormDaoImpl();
			String userName = req.getParameter("username");
			int userId = udi.loginUser(userName, req.getParameter("password"));
			
			// Sends the user to the home screen if the username and password match a user
			// or tells him there is no match and directs him back to the login.
			if (userId > 0) {
				HttpSession ses = req.getSession();
				ses.setAttribute("userId", userId);
				ses.setAttribute("isBenco", udi.isBenco(userId));
				ses.setAttribute("userName", userName);
				String hasApprovablesString = fdi.hasApprovableForms(userId)?"1":"0";
				ses.setAttribute("hasApprovables", hasApprovablesString);
				resp.addCookie(new Cookie("userName", userName));
				resp.addCookie(new Cookie("isBenco", udi.isBenco(userId)));
				resp.addCookie(new Cookie("hasApprovables", hasApprovablesString));
				System.out.println("User logged in! Id = " + userId);
				
				resp.sendRedirect("/TRMS/home");
			}
			else {
				System.out.println("login denied");
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
