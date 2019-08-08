package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;

public class HomeServlet extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("in doget homeservlet");
		HttpSession s = req.getSession(false);
		if (s != null) {

			System.out.println(s.getAttribute("userId").toString() + " is the userId of " + s.getAttribute("userName"));

			req.getRequestDispatcher("/form.html").include(req, resp); // TODO homepage
		} else {
			resp.sendRedirect("/TRMS/login");
		}
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("in doPOSt homeservlet");
		req.getRequestDispatcher("/form").forward(req, resp);
		/*
		ObjectMapper mapper = new ObjectMapper();

		int b = '0';
		System.out.println("=============");
		while ((b = req.getInputStream().read()) != -1) {
			System.out.print((char) b + " ");
		}

		System.out.println("\n=============");
*/
	}

}
