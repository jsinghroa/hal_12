package com.wipro.iaf.emms.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	static Connection c;

	public static Connection connect() {
		try {
			System.out.println("inside try");
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Driver Loaded");
			c = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/maximo", "root", "aman123");
			System.out.println("Connected");
		} catch (SQLException e) {

			e.printStackTrace();
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		}
		return c;
	}

}
