package com.infamous.fdsa.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseHelper {

	
	protected String url;
	protected String user;
	protected String password;
	protected String classname;
	
	protected static DatabaseHelper ins;
	public Connection connection;
	
	private DatabaseHelper(){
		getInfor();
		try {
			connection=getConnection();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public synchronized static DatabaseHelper getInstance(){
		if(ins==null){
			return new DatabaseHelper();
		}
		return ins;
	}
	
	public Connection getConnection() throws SQLException, ClassNotFoundException{
		Class.forName(classname);
		return (Connection) DriverManager
				.getConnection(url,user,password);
	}
	
	private void getInfor(){
	
		try {
			Properties properties = new Properties();

			InputStream input = getClass().getResourceAsStream("config.properties");

			if (input == null) {
				System.out.println("Cannot find config");
				return;
			}
			
			properties.load(input);
		
			classname = properties.getProperty("classname");

			url = properties.getProperty("url");

			user = properties.getProperty("username");

			password = properties.getProperty("password");
			
			classname=properties.getProperty("classname");
			
			System.out.println(url+user+password);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public static void main(String... args){
		DatabaseHelper dd=DatabaseHelper.getInstance();
	}
}
