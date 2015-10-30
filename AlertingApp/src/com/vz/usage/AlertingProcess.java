package com.vz.usage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.vz.usage.util.CustAlert;

public class AlertingProcess {
	 static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	 private static Connection conn;
	 private static PreparedStatement psstm;
	 static final String DB_URL = "jdbc:mysql://localhost/vzusages";
	 static final String DB_SQL = "SELECT custid, typ, usasize,alert_time FROM alerting where issent!=1  OR issent is NULL limit 100";
	   //  Database credentials
	   static final String USER = "root";
	   static final String PASS = "root";
	/**
	 * @param args
	 */
	public static List<CustAlert> getAlerts() {
		   try{
		      //STEP 2: Register JDBC driver
		      conn = getConnection();
		      System.out.println("Creating statement..xoooo.");
		      ResultSet rs = psstm.executeQuery();
		      List<CustAlert> alertUsers=new ArrayList<CustAlert>();
		      while(rs.next()){
		    	  alertUsers.add(new CustAlert(rs.getInt("custid"), rs.getInt("typ"), rs.getString("alert_time"), rs.getInt("usasize")));
		      }
		      System.out.println("Result "+alertUsers);
		      new AlertProcessed(alertUsers).start();
		      //STEP 6: Clean-up environment
		      rs.close();
		      return alertUsers;
		   }catch(Exception ex){
			   ex.printStackTrace();
		   }
		   return null;
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
