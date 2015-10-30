package com.verizon.hackathon;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	 public static Connection conn = null;
	 
	 public static Connection getConnection(){
		 try {
			 if(null == conn){
   		         Class.forName("com.mysql.jdbc.Driver").newInstance();
		
			     conn =
			        DriverManager.getConnection("jdbc:mysql://localhost/vzusages?" +
			                                    "user=root&password=root");
		
			     System.out.println("connection successful");
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

