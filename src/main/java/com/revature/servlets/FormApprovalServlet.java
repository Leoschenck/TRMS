package com.revature.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.beans.Form;
import com.revature.beans.User;
import com.revature.daoImpls.FormDaoImpl;
import com.revature.daoImpls.NotificationDaoImpl;
import com.revature.daoImpls.UserDaoImpl;

/**
 * Servlet implementation class FormApprovalServlet
 */
public class FormApprovalServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/approval.html").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession s = request.getSession(false);
		FormDaoImpl fdi = new FormDaoImpl();
		UserDaoImpl udi = new UserDaoImpl();
		NotificationDaoImpl ndi = new NotificationDaoImpl();
		ObjectMapper mapper = new ObjectMapper();
		int status;
		if (s != null) {
			XhrApprovalObject xhrApproval = mapper.readValue(request.getInputStream(), XhrApprovalObject.class);
			status = xhrApproval.getStatus();
			// TODO do approvals / denials, set strings
			// sets the status to the correct status depending on the relationship between
			// the logged in user and the form he chose to interact with.
			if (xhrApproval.getApproved() == 1) {
				// If the state is 0 and the user is the supervisor, we set the status to one or
				// two, depending on if he also is the depthead
				if (xhrApproval.getStatus() == 0
						&& fdi.isSupervisor(xhrApproval.getFormId(), (int) s.getAttribute("userId"))) {
					status = (fdi.isDeptHead(xhrApproval.getFormId(), (int) s.getAttribute("userId"))) ? 2 : 1;

				} else if (xhrApproval.getStatus() == 1
						&& fdi.isDeptHead(xhrApproval.getFormId(), (int) s.getAttribute("userId"))) {
					status = 2;
				} else if (xhrApproval.getStatus() == 2 && (int) s.getAttribute("isBenco") == 1) {

					status = 3;
				}
				fdi.setStatus(xhrApproval.getFormId(), status);
			} else if ((status = xhrApproval.getApproved()) == -1) { // Denied the Form!

				try { // lmao don't even try to understand this mess! jkjk - it get's the userid of
						// the forms owner and updates his remaining balance to blurg I HATE JAVASCRIPT
						// AND EVERYTHING HERE AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA
					int userIdOfForm = udi.getUserIdByFormId(xhrApproval.getFormId());
					ndi.createNotification(xhrApproval.getFormId(),
							"Your Application was denied. \n\n" + xhrApproval.getAttachedReasoning());
					double newAmount = calculateNewAmount(fdi.getFormBy(xhrApproval.getFormId()),
							udi.getUserById(userIdOfForm));
					udi.changeReimbursementAmount(newAmount, userIdOfForm);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				fdi.setStatus(xhrApproval.getFormId(), status);
			} else if (xhrApproval.getApproved() == 0) { // Add a Question! Display them in open forms? where status != -1 && 3 && 4
				try {
					ndi.createNotification(xhrApproval.getFormId(), "Open Question about your form "
							+ xhrApproval.getFormId() + "\n\n" + xhrApproval.getAttachedReasoning());
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			// TODO insert other input situations like question, denial and reimbursement
			// change

		} else {
			response.sendRedirect("/TRMS/login");
		}
	}

	private double calculateNewAmount(Form f, User u) {
		double newAmount = u.getRmnReimbursement();
		switch (f.getTypeOfEvent()) {
		case "1":
			newAmount = 0.8 * f.getCost();
			break;
		case "2":
			newAmount = 0.6 * f.getCost();
			break;
		case "3":
			newAmount = 0.75 * f.getCost();
			break;// document.getElementById("reimbamtEst").value = parseFloat(
		case "4":
			newAmount = f.getCost();
			break;

		case "5":
			newAmount = 0.9 * f.getCost();
			break;
		case "6":
			newAmount = 0.3 * f.getCost();
			break;
		}
		return newAmount + u.getRmnReimbursement();
	}

	/*
	 * private String stringifyJson(ServletInputStream inStream) throws IOException{
	 * StringBuffer sb = new StringBuffer(); int b = '0'; //sb.append("{\"");
	 * while((b = inStream.read()) != -1) {
	 * 
	 * switch((char)b) { case('='): sb.append("\":\""); break; case('&'):
	 * sb.append("\",\""); break; case('+'): sb.append(" "); break; default:
	 * sb.append((char)b); }
	 * 
	 * sb.append((char)b); } //sb.append("\"}"); System.out.println(sb); return
	 * sb.toString(); }
	 */
}

class XhrApprovalObject {
	private int formId;
	private int approved;
	private String attachedReasoning;
	private int subsAmt;
	private int status;

	public int getSubsAmt() {
		return subsAmt;
	}

	public void setSubsAmt(int subsAmt) {
		this.subsAmt = subsAmt;
	}

	public int getFormId() {
		return formId;
	}

	public void setFormId(int formId) {
		this.formId = formId;
	}

	public int getApproved() {
		return approved;
	}

	public void setApproved(int approved) {
		this.approved = approved;
	}

	public String getAttachedReasoning() {
		return attachedReasoning;
	}

	public void setAttachedReasoning(String attachedReasoning) {
		this.attachedReasoning = attachedReasoning;
	}

	public XhrApprovalObject() {
		super();
		// TODO Auto-generated constructor stub
	}

	public XhrApprovalObject(int formId, int approved, String attachedReasoning, int newReimbursementAmount,
			int status) {
		super();
		this.formId = formId;
		this.approved = approved;
		this.attachedReasoning = attachedReasoning;
		this.subsAmt = newReimbursementAmount;
		this.status = status;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}