package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.beans.Form;
import com.revature.beans.User;
import com.revature.daoImpls.FormDaoImpl;
import com.revature.daoImpls.UserDaoImpl;

public class ApproveServlet extends HttpServlet {
		private static final long serialVersionUID = 1L;
	     
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			doPost(request, response);
		}

		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		}

	}
