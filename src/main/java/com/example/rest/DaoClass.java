package com.example.rest;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

public class DaoClass {

	public static Connection connection = null;
	static PreparedStatement preparedStatement;

	public static Connection connectionSetup() throws SQLException, ClassNotFoundException {

		Class.forName("com.mysql.jdbc.Driver");

		connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sakila", "root", "root");

		if (connection != null) {
			System.out.println("Connection Succesful");

		} else {
			System.out.println("Failed ");
		}
		return connection;
	}

	public static void insertRecords(byte[] content) throws SQLException {
		int size = content.length;
		InputStream inputStreamData = null;
		byte[] b = new byte[size];
		try {
			inputStreamData = new ByteArrayInputStream(content);
			inputStreamData.read(b);
			String data = new String(b);
			String out[] = data.split("\n");
			for (int i = 1; i < out.length; i++) {
				preparedStatement = connection.prepareStatement("INSERT INTO DBUSER(AGENCY_ID,NAME)VALUES(?,?)");
				preparedStatement.setString(1, (out[i].split(","))[0]);
				preparedStatement.setString(2, (out[i].split(","))[1]);
				preparedStatement.executeUpdate();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static HashMap<String, String> retrieveRecord() throws SQLException {

		Statement stmt = connection.createStatement();
		ResultSet rs = stmt.executeQuery("select * from DBUSER");

		String id, name;
		HashMap<String, String> hm = new HashMap<String, String>();
		while (rs.next()) {
			id = rs.getString("AGENCY_ID");
			name = rs.getString("NAME");
			hm.put(id, name);
		}

		return hm;
	}

}