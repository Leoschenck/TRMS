package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.beans.Notification;
import com.revature.daoImpls.NotificationDaoImpl;

/**
 * Servlet implementation class GetNotificationsServlet
 */
public class GetNotificationsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		NotificationDaoImpl ndi = new NotificationDaoImpl();
		ObjectMapper mapper = new ObjectMapper();
		PrintWriter out = response.getWriter();
		HttpSession s = request.getSession(false);
		if (s != null) {
			try {
				List<Notification> notifications = ndi.getNotifications((int) s.getAttribute("userId"));
				mapper.writeValue(out, notifications);
				out.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			response.sendRedirect("/TRMS/login");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
