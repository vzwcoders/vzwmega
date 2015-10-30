package com.batchprocess.processor;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import com.batchprocess.connection.DBConnection;

public class TestProcess {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Statement stmt=null;
		ResultSet rs = null;
		Connection conn = null;
		try
		{
			conn = DBConnection.getConnection();
			stmt = conn.createStatement();
			rs = stmt
					.executeQuery("select custid,sum(udr_size) as size from udrs group by custid");
			while(rs.next()){
				
			}
			}catch(Exception e){
			
		}
	}

}
