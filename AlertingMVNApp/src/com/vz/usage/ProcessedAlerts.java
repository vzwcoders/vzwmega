package com.vz.usage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.vz.usage.util.CustAlert;

public class ProcessedAlerts {
	 static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	 private static Connection conn;
	 private static PreparedStatement psstm;
	 static final String DB_URL = "jdbc:mysql://localhost/vzusages";
	 static final String DB_SQL = "Update alerting set issent=1 Where custid=? ";
	   //  Database credentials
	   static final String USER = "root";
	   static final String PASS = "root";
	/**
	 * @param args
	 */
	public static void updateAlerts(List<CustAlert> alerts) {
		   try{
		      //STEP 2: Register JDBC driver
		      conn = getConnection();
		      System.out.println("Creating statement..."+alerts);
		      for(CustAlert c:alerts){
		    	  psstm.setInt(1,c.getCustid());
		    	  int cou = psstm.executeUpdate();
		      }
		      System.out.println("Result complete ");
		      //STEP 6: Clean-up environment
		     // psstm.close();
		   }catch(Exception ex){
			   ex.printStackTrace();
		   }
	}
	private static Connection getConnection() throws ClassNotFoundException,
			SQLException {
		if(conn!=null)return conn;
		Class.forName("com.mysql.jdbc.Driver");
		  System.out.println("Connecting to database...");
		  conn = DriverManager.getConnection(DB_URL,USER,PASS);
		  psstm = conn.prepareStatement(DB_SQL);
		return conn;
	}
private static void cleanUp(){
	try {
		psstm.close();
		conn.close();
	} catch (SQLException e) {
		e.printStackTrace();
	}
}
}
