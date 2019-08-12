package com.revature.daoImpls;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.Dao.NotificationDao;
import com.revature.beans.Notification;
import com.revature.util.ConnFactory;

/**
 * <h1>Team KLLJ - Tuition Reimbursement Management System (TRMS) Project 1</h1>
 * The purpose of TRMS is to provide a system that encourages quality knowledge growth
 * relevant to an individual’s expertise.  This program was created to address the
 * problems present in the current system, to provide the best user experience possible,
 * and to provide a more streamlined process for everyone involved.
 * <p>
 * The NotificationDaoImpl implements the NotificationDao interface in order to have
 * access to the basic CRUD methods inside it.  Here is where the methods are defined.
 * @author Justin Hua, Kyle Kolstad, Leonardo Schenck, Levi Applebaum
 * @version 1.0
 * 
 */
public class NotificationDaoImpl implements NotificationDao{

	public static ConnFactory cf = ConnFactory.getInstance();

	@Override
	public void createNotification(int formID, String content) throws SQLException {
		Connection conn = cf.getConnection();
		CallableStatement call = conn.prepareCall("{call CREATE_NOTIFICATION(?, ?)");
		call.setInt(1, formID);
		call.setString(2, content);
		call.execute();
	}
	
	@Override
	public List<Notification> getNotifications(int employeeID) throws SQLException {
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Notification> n = new ArrayList<Notification>();
		Connection conn = cf.getConnection();
		ps = conn.prepareStatement("SELECT * FROM \"NOTIFICATION\" WHERE FORMID IN (SELECT FORMID FROM \"FORM\" WHERE EMPLOYEEID = ?) AND CHECKED = 0");
		ps.setInt(1, employeeID); 
		rs = ps.executeQuery();
		while (rs.next()) {
			n.add(new Notification(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getDate(4), rs.getInt(5)));
		}
		return n;
	}
	
	@Override
	public List<Notification> showAllNotifications(int employeeID) throws SQLException {
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Notification> n = new ArrayList<Notification>();
		Connection conn = cf.getConnection();
		ps = conn.prepareStatement("SELECT * FROM \"NOTIFICATION\" WHERE FORMID IN (SELECT FORMID FROM \"FORM\" WHERE EMPLOYEEID = ?)");
		ps.setInt(1, employeeID);
		rs = ps.executeQuery();
		while (rs.next()) {
			n.add(new Notification(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getDate(4), rs.getInt(5)));
		}
		return n;
	}
	
	@Override
	public void HideNotification(int notificationID) throws SQLException { 
		Connection conn = cf.getConnection();
		CallableStatement call = conn.prepareCall("{call HIDE_NOTIFICATION(?)");
		call.setInt(1, notificationID);
		call.execute();
	}
}
