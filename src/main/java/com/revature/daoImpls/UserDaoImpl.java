package com.revature.daoImpls;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.revature.beans.User;
import com.revature.Dao.UserDao;
import com.revature.util.ConnFactory;

/**
 * <h1>Team KLLJ - Tuition Reimbursement Management System (TRMS) Project 1</h1>
 * The purpose of TRMS is to provide a system that encourages quality knowledge growth
 * relevant to an individual’s expertise.  This program was created to address the
 * problems present in the current system, to provide the best user experience possible,
 * and to provide a more streamlined process for everyone involved.
 * <p>
 * The UserDaoImpl implements the UserDao interface in order to have
 * access to the basic CRUD methods inside it.  Here is where the methods are defined.
 * @author Justin Hua, Kyle Kolstad, Leonardo Schenck, Levi Applebaum
 * @version 1.0
 * 
 */
public class UserDaoImpl implements UserDao{

	ConnFactory cf = ConnFactory.getInstance();

	@Override
	public ArrayList<User> getUsers() throws SQLException {
		ArrayList<User> userList = new ArrayList<User>();
		Connection conn = cf.getConnection();
		PreparedStatement stmt = conn.prepareStatement("SELECT * FROM \"USER\"");
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			userList.add(new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
					rs.getDouble(6), rs.getInt(7), rs.getString(8), rs.getString(9)));
		}
		return userList;
	}

	@Override
	public User loginUser(String username, String password) throws SQLException {
		Connection conn = cf.getConnection();
		PreparedStatement ps = conn.prepareStatement("SELECT * FROM \"USER\" WHERE UserName = '" + username + "' AND Password = '" + password + "'");
		ResultSet rs = ps.executeQuery();
		User u = new User();
		u.setId(-1);
		if (rs.next()) {
			u = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
					rs.getDouble(6), rs.getInt(7), rs.getString(8), rs.getString(9));
			}
		return u;
	}

	
	public void changeReimbursementAmount(double newAmount, int userId) throws SQLException {
		Connection conn = cf.getConnection();
		CallableStatement call = conn.prepareCall("{ call setReimbursementAmount(?, ?) }");
		call.setDouble(1, newAmount);
		call.setInt(2, userId);
		call.execute();
	}

	public void resetReimbursementAllUsers() throws SQLException{
		Connection conn = cf.getConnection();
		CallableStatement call = conn.prepareCall("{ call resetReimbursementAmount() }");
		call.execute();
	}

	@Override
	public String isBenco(int userid) throws SQLException{
		Connection conn = cf.getConnection();
		PreparedStatement ps = conn.prepareStatement("SELECT bencoid FROM benco WHERE bencoid = ?");
		ps.setInt(1, userid);
		ResultSet rs = ps.executeQuery();
		return rs.next()?"1":"0";
	}

	public int getUserIdByFormId(int formid) throws SQLException {
		Connection conn = cf.getConnection();
		PreparedStatement ps = conn.prepareStatement("SELECT employeeid FROM form WHERE formid = ?");
		ps.setInt(1, formid);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			return rs.getInt(1);
		}
		return -1;
	}

	@Override
	public User getUserById(int id) throws SQLException {
		Connection conn = cf.getConnection();
		PreparedStatement ps = conn.prepareStatement("SELECT * FROM \"USER\" WHERE userid = ?");
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		User u = null;
		if (rs.next()) {
			u = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
					rs.getDouble(6), rs.getInt(7), rs.getString(8), rs.getString(9));
		}
		return u;
	}

	@Override
	public User getBenCo() throws SQLException {
		Connection conn = cf.getConnection();
		PreparedStatement ps = conn.prepareStatement("SELECT * FROM \"USER\" WHERE userid = (SELECT bencoid FROM BENCO)");
		ResultSet rs = ps.executeQuery();
		User u = null;
		if (rs.next()) {
			u = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
					rs.getDouble(6), rs.getInt(7), rs.getString(8), rs.getString(9));
		}
		return u;
	}
}