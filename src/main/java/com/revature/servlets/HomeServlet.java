package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class HomeServlet extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("in doget homeservlet");
		HttpSession s = req.getSession(false);
		if (s != null) {

			System.out.println(s.getAttribute("userId").toString() + " is the userId of " + s.getAttribute("userName"));

			req.getRequestDispatcher("/home.html").forward(req, resp);
		} else {
			req.getRequestDispatcher("/login").forward(req, resp);
		}
	}
}
