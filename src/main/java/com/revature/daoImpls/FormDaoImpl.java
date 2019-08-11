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
import com.revature.util.ConnFactory;

public class FormDaoImpl {

	ConnFactory cf = ConnFactory.getInstance();

	public void createForm(Date courseStart, String location, String description, double cost, String gradingFormat,
			String typeOfEvent, String workRelatedJustification, double workTimeMissed, String linkToFiles,
			int employeeId, int deptId) {
		Connection conn = cf.getConnection();
		try {
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
			System.out.println("form was successfully created! This call comes from FormDaoImpl");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ArrayList<Form> getFormsByUserId(int employeeId) throws SQLException {
		ArrayList<Form> formList = new ArrayList<Form>();
		Connection conn = cf.getConnection();
		PreparedStatement ps = conn.prepareStatement("SELECT * FROM Form WHERE employeeid = " + employeeId);
		ResultSet rs = ps.executeQuery();
		Form f = null;
		while (rs.next()) {
			f = new Form(rs.getInt(1), rs.getDate(2), rs.getDate(3), rs.getString(4), rs.getString(5), rs.getDouble(6),
					rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getInt(11),
					rs.getString(12), rs.getString(13), rs.getDouble(14), rs.getString(15), rs.getInt(16),
					rs.getInt(17));
			formList.add(f);
		}
		return formList;
	}

	public ArrayList<Form> getApprovableFormsByUserId(int employeeId) throws SQLException {
		ArrayList<Form> formList = new ArrayList<Form>();
		Connection conn = cf.getConnection();

		PreparedStatement ps = conn.prepareStatement("SELECT bencoid FROM benco WHERE bencoid = " + employeeId);
		ResultSet rs0 = ps.executeQuery();
		if (rs0.next()) {
			PreparedStatement ps0 = conn.prepareStatement(
					"SELECT * FROM Form WHERE state >= 2");
			ResultSet rs = ps.executeQuery();
			Form f = null;
			while (rs.next()) {
				f = new Form(rs.getInt(1), rs.getDate(2), rs.getDate(3), rs.getString(4), rs.getString(5),
						rs.getDouble(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10),
						rs.getInt(11), rs.getString(12), rs.getString(13), rs.getDouble(14), rs.getString(15),
						rs.getInt(16), rs.getInt(17));
				formList.add(f);
			}
			return formList;
		}
		// States: 0 just opened, 1 super approved, 2 dep approved, 3 benco approved, 4 passed
		ps = conn.prepareStatement(
				"SELECT * FROM Form WHERE employeeid IN (SELECT userId from \"USER\" WHERE reportsto =" + employeeId
						+ ") AND state >= 0 OR deptid = (SELECT deptId FROM department WHERE depthead =" + employeeId + ") AND state >= 1");
		ResultSet rs1 = ps.executeQuery();
		Form f = null;
		while (rs1.next()) {
			f = new Form(rs1.getInt(1), rs1.getDate(2), rs1.getDate(3), rs1.getString(4), rs1.getString(5),
					rs1.getDouble(6), rs1.getString(7), rs1.getString(8), rs1.getString(9), rs1.getString(10),
					rs1.getInt(11), rs1.getString(12), rs1.getString(13), rs1.getDouble(14), rs1.getString(15),
					rs1.getInt(16), rs1.getInt(17));
			formList.add(f);
		}

		return formList;
	}

	public ArrayList<Form> getFormsByStatus(int status) throws SQLException {
		ArrayList<Form> formList = new ArrayList<Form>();
		Connection conn = cf.getConnection();
		PreparedStatement ps = conn.prepareStatement("SELECT * FROM Form WHERE status = " + status);
		ResultSet rs = ps.executeQuery();
		Form f = null;
		while (rs.next()) {
			f = new Form(rs.getInt(1), rs.getDate(2), rs.getDate(3), rs.getString(4), rs.getString(5), rs.getDouble(6),
					rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getInt(11),
					rs.getString(12), rs.getString(13), rs.getDouble(14), rs.getString(15), rs.getInt(16),
					rs.getInt(17));
			formList.add(f);
		}
		return formList;
	}

	public Form getFormBy(int formid) throws SQLException {
		Connection conn = cf.getConnection();
		PreparedStatement ps = conn.prepareStatement("SELECT * FROM Form WHERE formid = " + formid);
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
	
	public void setState(int formId, int state) {
		Connection conn = cf.getConnection();
		try {
			CallableStatement call = conn.prepareCall("{ call change_status(?, ?) }");
			call.setInt(1, formId);
			call.setInt(2, state);
			call.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

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