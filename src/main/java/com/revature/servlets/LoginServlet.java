package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.daoImpls.UserDaoImpl;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Forwards get requests
		req.getRequestDispatcher("/login.html").forward(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("In login servlet..." + req.getParameter("username") + "  ||  "+ req.getParameter("password"));
		PrintWriter pw = resp.getWriter();
		try {
			// Retrieves any employees who match the username/password
			UserDaoImpl udi = new UserDaoImpl();
			int userId = udi.loginUser(req.getParameter("username"), req.getParameter("password"));
			String uName = req.getParameter("username");
			String password = req.getParameter("password");

			// Sends the user to the home screen if the username and password match a user
			// or tells him there is no match and directs him back to the login.
			if (userId >= 0) {
				HttpSession ses = req.getSession();
				ses.setAttribute("userId", userId);
				ses.setAttribute("uName", uName);
				ses.setAttribute("password", password);
				System.out.println("User logged in! Id = " + userId);
				resp.sendRedirect("/TRMS/home");
			}
			else {
				resp.setContentType("text/html");
				pw.println("<script type=\"text/javascript\">");
				pw.println("alert('Username and password do not match!');");
				pw.println("</script>");
				req.getRequestDispatcher("/login.html").forward(req, resp);
			}
			pw.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
