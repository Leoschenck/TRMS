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

import com.revature.daoImpls.UserDaoImpl;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Forwards get requests
		req.getRequestDispatcher("/login.html").forward(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("In login servlet..." + req.getParameter("username") + "  ||  "+ req.getParameter("password"));
		try {
			// Retrieves any employees who match the username/password
			UserDaoImpl udi = new UserDaoImpl();
			int userId = udi.loginUser(req.getParameter("username"), req.getParameter("password"));
			resp.setContentType("text/html");

			// Sends the user to the home screen if the username and password match an
			// user
			if (userId >= 0) {
				HttpSession ses = req.getSession(true);
				ses.setAttribute("userId", userId);
				System.out.println("User logged in! Id = " + userId);
				resp.sendRedirect("/TRMS/home");
			}
			// Lets the user know the username and password don't match any user records
			else {
				PrintWriter pw = resp.getWriter();
				resp.setContentType("text/html");
				pw.println("<script type=\"text/javascript\">");
				pw.println("alert('Username and password do not match!');");
				pw.println("</script>");
				resp.addCookie(new Cookie("SessionUserName", req.getParameter("username")));
				req.getRequestDispatcher("/login.html").include(req, resp);
				pw.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
