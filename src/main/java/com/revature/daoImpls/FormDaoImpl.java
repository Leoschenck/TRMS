package com.revature.daoImpls;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import com.revature.beans.Form;
import com.revature.Dao.FormDao;
import com.revature.util.ConnFactory;

/**
 * <h1>Team KLLJ - Tuition Reimbursement Management System (TRMS) Project 1</h1>
 * The purpose of TRMS is to provide a system that encourages quality knowledge growth
 * relevant to an individual’s expertise.  This program was created to address the
 * problems present in the current system, to provide the best user experience possible,
 * and to provide a more streamlined process for everyone involved.
 * <p>
 * The FormDaoImpl implements the FormDao interface in order to have
 * access to the basic CRUD methods inside it.  Here is where the methods are defined.
 * @author Justin Hua, Kyle Kolstad, Leonardo Schenck, Levi Applebaum
 * @version 1.0
 * 
 */
public class FormDaoImpl implements FormDao {

	ConnFactory cf = ConnFactory.getInstance();

	@Override
	public void createForm(Date courseStart, String location, String description, double cost, String gradingFormat,
			String typeOfEvent, String workRelatedJustification, double workTimeMissed, String linkToFiles,
			int employeeId, int deptId) throws SQLException {
		Connection conn = cf.getConnection();
		CallableStatement call = conn.prepareCall("{call createForm(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
		call.setDate(1, courseStart);
		call.setString(2, location);
		call.setString(3, description);
		call.setDouble(4, cost);
		call.setString(5, gradingFormat);
		call.setString(6, typeOfEvent);
		call.setString(7, workRelatedJustification);
		call.setDouble(8, workTimeMissed);
		call.setString(9, linkToFiles);
		call.setInt(10, employeeId);
		call.setInt(11, deptId);
		call.execute();
	}

	@Override
	public ArrayList<Form> getFormsByUserId(int employeeId) throws SQLException {
		ArrayList<Form> formList = new ArrayList<Form>();
		Connection conn = cf.getConnection();
		PreparedStatement ps = conn.prepareStatement("SELECT * FROM \"FORM\" WHERE employeeid = ?");
		ps.setInt(1, employeeId);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			formList.add(new Form(rs.getInt(1), rs.getDate(2), rs.getDate(3), rs.getString(4), rs.getString(5), rs.getDouble(6),
					rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getInt(11),
					rs.getString(12), rs.getString(13), rs.getDouble(14), rs.getString(15), rs.getInt(16),
					rs.getInt(17)));
		}
		return formList;
	}

	@Override
	public boolean isSupervisor(int formId, int userId) throws SQLException{
		Connection conn = cf.getConnection();
		PreparedStatement ps = conn.prepareStatement("SELECT * FROM \"USER\" WHERE userid = (SELECT employeeid FROM \"FORM\" WHERE formid = ?) AND reportsto = ?");
		ps.setInt(1, formId);
		ps.setInt(2, userId);
		ResultSet rs = ps.executeQuery();
		return rs.next();
	}
	
	@Override
	public boolean isDeptHead(int formId, int userId) throws SQLException{
		Connection conn = cf.getConnection();
		PreparedStatement ps = conn.prepareStatement("SELECT * FROM form WHERE formid = ? AND deptid = (SELECT deptid FROM department WHERE depthead = ?");
		ps.setInt(1, formId);
		ps.setInt(2, userId);
		ResultSet rs = ps.executeQuery();
		return rs.next();
	}
	
	@Override
	public ArrayList<Form> getApprovableFormsByUserId(int employeeId) throws SQLException {
		ArrayList<Form> formList = new ArrayList<Form>();
		Connection conn = cf.getConnection();
		PreparedStatement ps;
		ResultSet rs;
		if (new UserDaoImpl().isBenco(employeeId).equals("1")) {
			ps = conn.prepareStatement("SELECT * FROM Form WHERE state >= 2");
			 rs = ps.executeQuery();
			while (rs.next()) {
				formList.add(new Form(rs.getInt(1), rs.getDate(2), rs.getDate(3), rs.getString(4), rs.getString(5),
						rs.getDouble(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10),
						rs.getInt(11), rs.getString(12), rs.getString(13), rs.getDouble(14), rs.getString(15),
						rs.getInt(16), rs.getInt(17)));
			}
			return formList;
		} else {
		// States: 0 just opened, 1 super approved, 2 dep approved, 3 benco approved, 4 passed
			ps = conn.prepareStatement("SELECT * FROM Form WHERE employeeid IN (SELECT userId from \"USER\" WHERE reportsto = ?) AND status >= 0 OR deptid = (SELECT deptId FROM department WHERE depthead = ?) AND status >= 1");
			ps.setInt(1, employeeId);
			ps.setInt(2, employeeId);
			rs = ps.executeQuery();
			while (rs.next()) {
				formList.add(new Form(rs.getInt(1), rs.getDate(2), rs.getDate(3), rs.getString(4), rs.getString(5),
						rs.getDouble(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10),
						rs.getInt(11), rs.getString(12), rs.getString(13), rs.getDouble(14), rs.getString(15),
						rs.getInt(16), rs.getInt(17)));
		}
		return formList;
		}
	}

	@Override
	public boolean hasApprovableForms(int userId) throws SQLException {
		return (getApprovableFormsByUserId(userId) != null);		
	}
	
	@Override
	public ArrayList<Form> getFormsByStatus(int status) throws SQLException {
		ArrayList<Form> formList = new ArrayList<Form>();
		Connection conn = cf.getConnection();
		PreparedStatement ps = conn.prepareStatement("SELECT * FROM Form WHERE status = ?");
		ps.setInt(1, status);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			formList.add(new Form(rs.getInt(1), rs.getDate(2), rs.getDate(3), rs.getString(4), rs.getString(5), rs.getDouble(6),
					rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getInt(11),
					rs.getString(12), rs.getString(13), rs.getDouble(14), rs.getString(15), rs.getInt(16),
					rs.getInt(17)));
		}
		return formList;
	}

	@Override
	public Form getFormBy(int formid) throws SQLException {
		Connection conn = cf.getConnection();
		PreparedStatement ps = conn.prepareStatement("SELECT * FROM Form WHERE formid = ?");
		ps.setInt(1, formid);
		ResultSet rs = ps.executeQuery();
		Form f = null;
		if (rs.next()) {
			f = new Form(rs.getInt(1), rs.getDate(2), rs.getDate(3), rs.getString(4), rs.getString(5), rs.getDouble(6),
					rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getInt(11),
					rs.getString(12), rs.getString(13), rs.getDouble(14), rs.getString(15), rs.getInt(16),
					rs.getInt(17));
		}
		return f;
	}
	
	@Override
	public void setStatus(int formId, int state) throws SQLException{
		Connection conn = cf.getConnection();
		CallableStatement call = conn.prepareCall("{ call change_status(?, ?)");
		call.setInt(1, formId);
		call.setInt(2, state);
		call.execute();
	}

	@Override
	public HashMap<Integer, String> getDepartments() throws SQLException {
		HashMap<Integer, String> departments = new HashMap<Integer, String>();
		Connection conn = cf.getConnection();
		PreparedStatement ps = conn.prepareStatement("SELECT deptid, deptname FROM department");
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			departments.put(rs.getInt(1), rs.getString(2));
		}
		return departments;
	}
}