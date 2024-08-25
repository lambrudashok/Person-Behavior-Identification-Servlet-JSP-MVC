package org.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DBHelper {

	public static String path;
	protected DBConfig config = DBConfig.getInstance(path);
	protected Connection con = config.getConnection();
	protected PreparedStatement ps = config.getPreparedStatement();
	protected ResultSet rs = config.getResultSet();
	
}
