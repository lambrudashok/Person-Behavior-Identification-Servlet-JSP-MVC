package org.repository;
import java.sql.*;
import java.util.Properties;
import java.io.*;

public class DBConfig {

	private static Connection con;
	private static PreparedStatement ps;
	private static ResultSet rs;
	private static DBConfig db = null;
	
	private DBConfig(String path) {
		try {
			FileInputStream fin = new FileInputStream(path);
			Properties p = new Properties();
			p.load(fin);
			String username = p.getProperty("db.username");
			String password = p.getProperty("db.password");
			String url = p.getProperty("db.url");
			String driverClass = p.getProperty("driver.classname");
			
			Class.forName(driverClass);
			con = DriverManager.getConnection(url,username,password);
			
		}catch(Exception e) {
			System.out.println("error :"+e);
		}
	}
	
	public static DBConfig getInstance(String path) {
		if(db == null) {
			db = new DBConfig(path);
		}
		return db;
	}
	public Connection getConnection() {
		return con;
	}
	public PreparedStatement getPreparedStatement() {
		return ps;
	}
	public ResultSet getResultSet() {
		return rs;
	}
}
