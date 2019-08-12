package com.revature.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnFactory {
	// Singleton factory
	private static ConnFactory cf = new ConnFactory();

	private ConnFactory() {

	}

	public static synchronized ConnFactory getInstance() {
		if (cf == null)
			cf = new ConnFactory();
		return cf;
	}
	public Connection getConnection() {
		Connection conn = null;
		Properties prop = new Properties();
		try {
			InputStream input = ConnFactory.class.getClassLoader().getResourceAsStream("database.properties");
			prop.load(input);
			Class.forName(prop.getProperty("driver"));
			conn = DriverManager.getConnection(prop.getProperty("url"), prop.getProperty("user"), prop.getProperty("password"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}
}
