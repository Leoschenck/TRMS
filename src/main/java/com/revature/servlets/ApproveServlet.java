package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

public class ApproveServlet extends HttpServlet {
		private static final long serialVersionUID = 1L;
	     
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			doPost(request, response);
		}

		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			int formId = Integer.parseInt(request.getParameter("id"));
			HttpSession ses = request.getSession(false);
			int empId = (int) ses.getAttribute("userid");
			
			PrintWriter pw = response.getWriter();
			try {
				String[] info = getServletContext().getInitParameter("dbInfo").split(",");
				FormDAOImpl fdi = new FormDAOImpl(info);
				UserDAOImpl edi = new UserDAOImpl(info);
				
				User user = edi.getUser(userId);
				Form f = fdi.retrieveForm(formId);
				
				if(user.isBenCo()) {
					f.setBenCoApproval(true);
					f.setStatus(3);
				}
				if(user.isHead())
					f.setHeadApproval(true);
				if(user.isSupervisor())
					f.setSupervisorApproval(true);
				
				fdi.updateForm(f);
				
				request.getRequestDispatcher("/home.html").include(request, response);
			}catch(SQLException e) {
				pw.print("System is down! Please try again later!");
				// request.getRequestDispatcher("/home").include(request, response);
				e.printStackTrace();
				pw.close();
			}
		}

	}
