package com.revature.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.daoImpls.FormDaoImpl;
import com.revature.daoImpls.UserDaoImpl;

/**
 * Servlet implementation class ShowFormsServlet
 */
public class ShowFormsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ShowFormsServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("in doget showformservlet");
		HttpSession s = req.getSession(false);
		if (s != null) {

			while (req.getInputStream().read() != -1) {
			}

			req.getRequestDispatcher("/showforms.html").include(req, resp); // TODO homepage

		} else {
			resp.sendRedirect("/TRMS/login");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession s = request.getSession(false);
		FormDaoImpl fdi = new FormDaoImpl();
		ObjectMapper mapper = new ObjectMapper();
		if (s != null) {

			XhrGrading grade = mapper.readValue(request.getInputStream(), XhrGrading.class);
			if (grade.getPassed() == 1) {
				fdi.setState(grade.getFormId(), 4);
				// TODO Process the money being withdrawn, don't overdraw blablabla - how can
				// the benco approve of overdrawing? if string == null, dont overdraw?
			} else if (grade.getPassed() == 0) {
				fdi.setState(grade.getFormId(), -1);
			}

		} else {
			response.sendRedirect("/TRMS/login");
		}
	}

}

class XhrGrading {
	private int formId;
	private int passed;

	public XhrGrading(int id, int passed) {
		super();
		this.formId = id;
		this.passed = passed;
	}

	public XhrGrading() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getFormId() {
		return formId;
	}

	public void setFormId(int id) {
		this.formId = id;
	}

	public int getPassed() {
		return passed;
	}

	public void setPassed(int passed) {
		this.passed = passed;
	}

}