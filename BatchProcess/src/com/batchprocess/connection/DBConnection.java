package com.batchprocess.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.batchprocess.utils.PropertyFileLoader;

public class DBConnection {
	 public static Connection conn = null;
	 
	 public static Connection getConnection(){
		 try {
			 if(null == conn || conn.isClosed()){
   		         Class.forName(PropertyFileLoader.getPropertyValue("driverClass")).newInstance();
		
			     conn =
			        DriverManager.getConnection(PropertyFileLoader.getPropertyValue("connectionURL"));
		
			    // System.out.println("connection successful");
			 }
		 } catch (SQLException ex) {
		     // handle any errors
		     System.out.println("SQLException: " + ex.getMessage());
		     System.out.println("SQLState: " + ex.getSQLState());
		     System.out.println("VendorError: " + ex.getErrorCode());
		 } catch(Exception e){
			 e.printStackTrace();
		 }
		 return conn;
	 }

}
