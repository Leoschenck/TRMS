package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class HomeServlet extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("in doget homeservlet");
		HttpSession s = req.getSession(false);
		if (s!= null) {
			
			PrintWriter out = resp.getWriter();
			out.write(s.getAttribute("userId").toString());
			System.out.println(s.getAttribute("userId").toString());
			Cookie CK[] = req.getCookies();
			Cookie ck = CK[0];
			System.out.println(ck.getName());
			System.out.println(ck.getValue());
			//for(int i = 0; i < CK.length; i++)
		}
			
			// Forwards get requests
			req.getRequestDispatcher("/home.html").forward(req, resp);
	}
}
