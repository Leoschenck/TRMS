package com.revature.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.daoImpls.FormDaoImpl;
import com.revature.daoImpls.UserDaoImpl;

/**
 * Servlet implementation class FormApprovalServlet
 */
public class FormApprovalServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/approval.html").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//String input = stringifyJson(request.getInputStream());
		HttpSession s = request.getSession(false);
		FormDaoImpl fdi = new FormDaoImpl();
		UserDaoImpl udi = new UserDaoImpl();
		if(s != null) {
			
			
			//ifapproved set form to state according to position
			
			//fdi.setState(request.)
			//fdi.setState(, state);
			
		}else {
			response.sendRedirect("/TRMS/login");
		}		
	}

	private String stringifyJson(ServletInputStream inStream) throws IOException{
		StringBuffer sb = new StringBuffer();
		int b = '0';
		//sb.append("{\"");
		while((b = inStream.read()) != -1) {
			/*
			switch((char)b) {
			case('='): sb.append("\":\""); break;
			case('&'): sb.append("\",\""); break;
			case('+'): sb.append(" "); break;
			default: sb.append((char)b);
			}
			*/
			sb.append((char)b);
		}
		//sb.append("\"}");
		System.out.println(sb);
		return sb.toString();
	}
}
