package com.vz.usage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;
import java.util.TreeSet;

import com.vz.usage.util.AlertMetrics;

public class ReportAlertCounts {
	 static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	 private static Connection conn;
	 private static PreparedStatement psstm;
	 private static PreparedStatement psstmAlert;
	 static final String DB_URL = "jdbc:mysql://localhost/vzusages";
	 static final String DB_SQL = "SELECT count(typ),typ FROM vzusages.alerting   group by typ";
	 static final String DB_SQL_ALERTS="SELECT DATE_FORMAT(a.alert_time,'%c %e %Y %h:%i %s') tim,count(a.alert_time) FROM vzusages.alerting a group by tim";
	   //  Database credentials
	   static final String USER = "root";
	   static final String PASS = "root";
	/**
	 * @param args
	 */
	public static Set<AlertMetrics> getAlertsMetrics() {
		   try{
		      //STEP 2: Register JDBC driver
		      conn = getConnection();
		      System.out.println("Creating statement...");
		     ResultSet rs=psstm.executeQuery();
		     Set<AlertMetrics> dat=new TreeSet<>();     
		     while(rs.next()){
		    	 dat.add(new AlertMetrics(rs.getString(2), rs.getString(1)));
		     }
		      System.out.println("Reporting data from db  "+dat);
		      //STEP 6: Clean-up environment
		      rs.close();
		      //psstm.close();
		      return dat;
		   }catch(Exception ex){
			   ex.printStackTrace();
		   }
		   return null;
	}
	public static Set<AlertMetrics> getAlertsMetricsTim() {
		   try{
		      //STEP 2: Register JDBC driver
		      conn = getConnection();
		      System.out.println("Creating statement...");
		     ResultSet rs=psstmAlert.executeQuery();
		     Set<AlertMetrics> dat=new TreeSet<>();     
		     while(rs.next()){
		    	 dat.add(new AlertMetrics(rs.getString(1), rs.getString(2)));
		     }
		      System.out.println("Reporting data from db  "+dat);
		      //STEP 6: Clean-up environment
		      rs.close();
		      //psstm.close();
		      return dat;
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
		  psstmAlert=conn.prepareStatement(DB_SQL_ALERTS);
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
