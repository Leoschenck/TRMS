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
		PrintWriter out = resp.getWriter();
		if (s != null) {

			System.out.println(s.getAttribute("userId").toString() + " is the userId of " + s.getAttribute("uName"));
			out.println("<script type=\"text/javascript\">");
			out.println("alert('<%=Session[\"uName\"] %>');");
			out.println("</script>");

			out.write(s.getAttribute("userId").toString());
			req.getRequestDispatcher("/home.html").include(req, resp);
		} else {
			req.getRequestDispatcher("/login").forward(req, resp);
		}
		out.close();
	}
}
