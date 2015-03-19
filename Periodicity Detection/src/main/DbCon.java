package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DbCon {
	public final String dbUrl;
	public final String dbClass;
	public String query;
	public final String dbUser;
	public final String dbPw;
	public ResultSet rs;
	public Connection con;

	public DbCon() {
		super();
		this.dbUrl = "jdbc:mysql://localhost/dmc2012";
		this.dbClass = "com.mysql.jdbc.Driver";   
		this.query = "";
		this.dbUser = "root";
		this.dbPw = "SEds1tKQTBHcW";
	}
		
	public void connect() throws SQLException {
		this.con = DriverManager.getConnection(dbUrl,dbUser,dbPw);
	}
	
	public void disconnect() throws SQLException {
		this.con.close();
	}
	
	public  ResultSet executeSelect(String query) {

		try {
		Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		this.rs = stmt.executeQuery(query);
		}
		
		catch(SQLException e) {
			e.printStackTrace();
		}
		return rs;
		}
	
	public void executeUpdate(String query) {
		try {
		Connection con = DriverManager.getConnection(dbUrl,dbUser,dbPw);
		Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		stmt.execute(query);
		con.close();
		}

		catch(SQLException e) {
			e.printStackTrace();
		}
	}
} 
