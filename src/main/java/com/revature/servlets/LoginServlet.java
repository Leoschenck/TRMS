package com.revature.servlets;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Forwards get requests
		req.getRequestDispatcher("/home.html").forward(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("In login servlet...");
		try {
			String[] info = getServletContext().getInitParameter("dbInfo").split(",");
			// Retrieves any employees who match the username/password
			EmployeeDAOImpl edi = new EmployeeDAOImpl(info);
			Employee emp = edi.employeeLogin(req.getParameter("email"), req.getParameter("password"));
			resp.setContentType("text/html");

			// Sends the user to the home screen if the username and password match an
			// employee
			if (emp.getId() != 0) {
				HttpSession ses = req.getSession(true);
				ses.setAttribute("userid", emp.getId());
				resp.sendRedirect("/ReimbursementSystem/home");
			}
			// Lets the user know the username and password don't match any employee records
			else {
				PrintWriter pw = resp.getWriter();
				resp.setContentType("text/html");
				pw.println("<script type=\"text/javascript\">");
				pw.println("alert('Username and Password do not match');");
				pw.println("</script>");
				req.getRequestDispatcher("/login.html").include(req, resp);
				pw.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
