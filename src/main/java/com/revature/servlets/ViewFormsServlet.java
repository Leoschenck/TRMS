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
import com.revature.beans.Form;
import com.revature.daoImpls.FormDaoImpl;

/**
 * Servlet implementation class ViewFormsServlet
 */
public class ViewFormsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewFormsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		HttpSession s = request.getSession(false);
        ObjectMapper om = new ObjectMapper();
		System.out.println("In viewForms doget");
        PrintWriter out = response.getWriter();
        FormDaoImpl fdi = new FormDaoImpl();
        
        if ( s != null) {
            int id = om.readValue(s.getAttribute("userId").toString(), Integer.class);
           // List<Form> allForms = null;
            try {
            	om.writeValue(out, fdi.getFormsByUserId(id));
            	//allForms = new FormDaoImpl().getFormsByUserId((int)s.getAttribute("userId"));
    			response.setContentType("application/json");
    			response.setCharacterEncoding("UTF-8");
                //user = new FormDaoImpl().getUserById((int)s.getAttribute("userId"));
            } catch (SQLException e) {
            	
                e.printStackTrace();
            }
            out.flush();
        }
        else {
        	System.out.println("viewforms no session");
        	 response.sendRedirect("/TRMS/login");
             System.out.println("No session");
        }
        out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
