package com.wipro.iaf.emms.utility;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyConnection {
	static Connection c;
	static Connection connect()
	{
		try
		{
			System.out.println("inside try");
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Driver Loaded");
			c = DriverManager.getConnection("jdbc:mysql://localhost:3306/maximo","root","root");
			System.out.println("Connected");
		}
		catch (SQLException e) {
			
			e.printStackTrace();
		}
        catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		}
		return c;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		connect();
	}

}
