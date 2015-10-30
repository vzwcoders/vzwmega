package com.verizon.hackathon;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import com.batchprocess.connection.DBConnection;
import com.batchprocess.utils.MetricsUtils;
import com.batchprocess.vo.Metrics;


public class InsertToAlertTableProcess {

	public static void main(String[] args) {
		Connection conn = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;
	    ResultSet rs = null;
		int i = 0;
		int j = 0;
		int typ = 0;
		String processId=args[0];
		int bucketVal=Integer.parseInt(args[1]);
		
		
		try {
			// Register JDBC driver
			/*Class.forName("com.mysql.jdbc.Driver").newInstance();
			// Open a connection
			System.out.println("Connecting to a selected database...");
			conn = DriverManager
					.getConnection("jdbc:mysql://localhost/vzusages?"
							+ "user=root&password=hackathon");
			System.out.println("Connected database successfully...");*/
  
			// Execute a query
			
			conn=DBConnection.getConnection();
			stmt = conn.createStatement();
			
			rs = stmt
					.executeQuery("select custid,udr_size , CASE"
							+ " when udr_size>=42700 and udr_size<1000000 then 70"
							+ " when udr_size>=1000000 and udr_size<10000000 then 75"
							+ " when udr_size>=10000000 and udr_size<100000000 then 80"
							+ " when udr_size>=100000000 and udr_size<1000000000 then 90" +
							"   ELSE 100  END as typ "
							+ " from monthly_data_usage where bucket_id="
							+ bucketVal+"  AND udr_size >427400");
			System.out.println("Inserting records into the table...");
			long ins_start = System.currentTimeMillis();

			Timestamp start = new Timestamp(System.currentTimeMillis());
			System.out.println("Start time "+start);
			String sql = "insert into alerting (custid,typ,usasize,alert_time,bucket_id) values(?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			int custid = 0;
			String usasize = "";

			while (rs.next()) {
				custid = rs.getInt("custid");
				usasize = rs.getString("udr_size");
				typ = rs.getInt("typ");
				pstmt.setInt(1, custid);
				pstmt.setInt(2, typ);
				pstmt.setString(3, usasize);
				pstmt.setTimestamp(4,
						new Timestamp(new java.util.Date().getTime()));
				pstmt.setInt(5, bucketVal);

				try {
					pstmt.executeUpdate();
					i = i + 1;
				} catch (Exception e) {

					j = j + 1;

					// e.printStackTrace();
				}

			}
			System.out.println("Rows not updated in table=" + j);
			System.out.println("Inserted records into the table...");
			System.out.println("Number of rows inserted=" + i);
			System.out.println("Insert complete for ALERTING table");
			long ins_end = System.currentTimeMillis();
			System.out.println("Time take to insert" + i + " rows="+ (ins_end - ins_start)+" end "+ins_end);
			Timestamp end = new Timestamp(System.currentTimeMillis());
			
			Metrics vo=new Metrics();
			vo.setProcessId(processId);
			vo.setSubProcessId("ALERT_TABLE_PROCESS");
			vo.setBucketId(bucketVal);
			vo.setStartTime(start);
			vo.setEndTime(end);
			vo.setErrorCount(j);
			vo.setSuccessCount(i);
			MetricsUtils.captureMetrics(vo);
			
			/*// insert into umetrics table..
			System.out.println("Insert started in umetrics table...");
			String sql1 = "insert into umetrics(name,start_time,end_time,category) values(?,?,?,?)";
			pstmt1 = conn.prepareStatement(sql1);
			pstmt1.setString(1, "Alerting");
			pstmt1.setTimestamp(2, start);
			pstmt1.setTimestamp(3, end);
			pstmt1.setString(4, "Alert Category");
			pstmt1.executeUpdate();*/
			System.out.println("Insert complete in umetrics table...");
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			// finally block used to close resources
			try {
				if (stmt != null)
					conn.close();
			} catch (SQLException se) {
			}// do nothing
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}// end finally try
		}// end try
		
	}// end main
}
