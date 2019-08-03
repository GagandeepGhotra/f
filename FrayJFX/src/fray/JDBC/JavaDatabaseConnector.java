package fray.JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class JavaDatabaseConnector {
	
	private String databaseSite = "sql3.freesqldatabase.com";
	private String databaseName = "sql3282320";
	private String databaseUsername = "sql3282320";
	private String databasePassword = "vAuzhDvkue";
	
	private Connection con;
	
	public JavaDatabaseConnector() {
		try {
			this.con = DriverManager.getConnection("jdbc:mysql://" + databaseSite + "/" + databaseName, databaseUsername, databasePassword);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Please check your internet connection!");
			e.printStackTrace();
		}
	}

	public String getDatabaseSite() {
		return databaseSite;
	}

	public void setDatabaseSite(String databaseSite) {
		this.databaseSite = databaseSite;
	}

	public String getDatabaseName() {
		return databaseName;
	}

	public void setDatabaseName(String databaseName) {
		this.databaseName = databaseName;
	}

	public String getDatabaseUsername() {
		return databaseUsername;
	}

	public void setDatabaseUsername(String databaseUsername) {
		this.databaseUsername = databaseUsername;
	}

	public String getDatabasePassword() {
		return databasePassword;
	}

	public void setDatabasePassword(String databasePassword) {
		this.databasePassword = databasePassword;
	}

	public Connection getCon() throws SQLException {
		return con;
	}

	public void setCon(Connection con) {
		this.con = con;
	}

}
