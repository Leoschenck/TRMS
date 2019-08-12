package com.revature.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.daoImpls.FormDaoImpl;
import com.revature.daoImpls.NotificationDaoImpl;
import com.revature.daoImpls.UserDaoImpl;

/**
 * <h1>Team KLLJ - Tuition Reimbursement Management System (TRMS) Project 1</h1>
 * The purpose of TRMS is to provide a system that encourages quality knowledge growth
 * relevant to an individual’s expertise.  This program was created to address the
 * problems present in the current system, to provide the best user experience possible,
 * and to provide a more streamlined process for everyone involved.
 * <p>
 * Servlet implementation class ShowFormsServlet
 * @author Justin Hua, Kyle Kolstad, Leonardo Schenck, Levi Applebaum
 * @version 1.0
 * 
 */
public class ShowFormsServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession s = request.getSession(false);
		FormDaoImpl fdi = new FormDaoImpl();
		UserDaoImpl udi = new UserDaoImpl();
		ObjectMapper mapper = new ObjectMapper();
		if (s != null) {
			try {
			XhrGrading grade = mapper.readValue(request.getInputStream(), XhrGrading.class);
			if (grade.getPassed() == 1) {
				try {
					if(udi.getUserById((int)s.getAttribute("userId")).getRmnReimbursement() >= 0) {
						fdi.setStatus(grade.getFormId(), 4);
					} else {
						//TODO create two notifications for user & benco? Benco is "If any user is below 0, make it pop up"
						NotificationDaoImpl ndi = new NotificationDaoImpl();
						ndi.createNotification(grade.getFormId(), "Your grade for form " + grade.getFormId() + " could not be accepted, as noone agreed to you overdrawing your maximum amount of reimbursement per year. Please contact your benco of trust for aid!");
						
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// TODO Process the money being withdrawn, don't overdraw blablabla - how can
				// the benco approve of overdrawing? if string == null, don't overdraw?
			} else if (grade.getPassed() == 0) {
				fdi.setStatus(grade.getFormId(), -1);
			}
			} catch (Exception e) {
			}
		} else {
			response.sendRedirect("/TRMS/login");
		}
	}
}

/**
 * <h1>Team KLLJ - Tuition Reimbursement Management System (TRMS) Project 1</h1>
 * The purpose of TRMS is to provide a system that encourages quality knowledge growth
 * relevant to an individual’s expertise.  This program was created to address the
 * problems present in the current system, to provide the best user experience possible,
 * and to provide a more streamlined process for everyone involved.
 * <p>
 * This class is set up to store data that can be accessed and
 * manipulated through its setter and getter methods.
 * @author Justin Hua, Kyle Kolstad, Leonardo Schenck, Levi Applebaum
 * @version 1.0
 * 
 */
class XhrGrading {
	
	//Private data variables.
	private int formId, passed;

	/**
	 * Default Constructor.
	 */
	public XhrGrading() {
		super();
	}
	
	/**
	 * This method creates an XhrApprovalObject object and initializes the data variables.
	 * @param id
	 * @param passed
	 */
	public XhrGrading(int id, int passed) {
		super();
		this.formId = id;
		this.passed = passed;
	}

	//Getter and setter methods.
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