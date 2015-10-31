package com.batchprocess.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.batchprocess.connection.DBConnection;
import com.batchprocess.vo.Metrics;

public class InsertionBasedOnProcessId {

	public static void insertDataBasedOnTableName(String tableName, Metrics metrics, String dumpTableName){
		Connection conn = null;
		Statement stmt = null;
		PreparedStatement pstmtCreateTable = null;
		PreparedStatement pstmtInsertTable = null;
		PreparedStatement pstmtUpdateTable = null;
		int successRowCount = 0;
		int failureRowCount = 0;
		ResultSet rs = null;
		try {
			conn = DBConnection.getConnection();
			stmt = conn.createStatement();
			String QUERY="select custid,sum(udr_size) as size from "+dumpTableName+" group by custid";
			System.out.println("Executing query "+QUERY);
			rs = stmt.executeQuery(QUERY);
			long startTime = System.currentTimeMillis();
			int custId = 0;
			long udrSize = 0;

			String createTableCheckQuery = PropertyFileLoader
					.getPropertyValue("dailyTableCreationQuery").replace("TABLE_NAME", tableName);
			
			String insertTableQuery = PropertyFileLoader
					.getPropertyValue("dailyTableInsertQuery").replace("TABLE_NAME", tableName);
			System.out.println("Insert able query "+insertTableQuery);
			String updateTableQuery = PropertyFileLoader
					.getPropertyValue("updateDailyTableQuery").replace("TABLE_NAME", tableName);
			pstmtCreateTable = conn.prepareStatement(createTableCheckQuery);
			pstmtInsertTable = conn.prepareStatement(insertTableQuery);
			pstmtUpdateTable = conn.prepareStatement(updateTableQuery);

			int bucketNum = 0;
			boolean createTableCheck = false;
			int updateRecStatus = 0;
			if(!createTableCheck){
				try {
					pstmtCreateTable.execute();
				} catch (Exception e1) {
				}
				createTableCheck=true;
			}
			while (rs.next()) {
				custId = rs.getInt("custid");
				udrSize = rs.getLong("size");
				bucketNum = custId % 10;
				pstmtUpdateTable.setLong(1, udrSize);
				pstmtUpdateTable.setInt(2, custId);
				pstmtUpdateTable.setInt(3, bucketNum);
				updateRecStatus = pstmtUpdateTable.executeUpdate();
				if(updateRecStatus == 0){
					pstmtInsertTable.setInt(1, custId);
					pstmtInsertTable.setLong(2, udrSize);
					pstmtInsertTable.setInt(3, bucketNum);
					try {
						pstmtInsertTable.executeUpdate();
						successRowCount++;
					} catch (Exception e) {
						
						System.out.println("Error occured for :" + custId
								+ " --> bucknum :" + bucketNum);
						//e.printStackTrace();
						try{
							System.out.println("Updating row");
						updateRecStatus = pstmtUpdateTable.executeUpdate();
						successRowCount++;
						}catch(Exception e1) {
							//ignore for now
							failureRowCount++;
							System.out.println("Got duplicat row while update");
						}
					}
				}else{
					successRowCount++;
				}
			}

			long endTime = System.currentTimeMillis();
			long millsec = endTime - startTime;
			long sec = millsec / 1000;
			long min = sec / 60;
			System.out.println("Time taken for "+metrics.getSubProcessId()+" process :"
					+ millsec + " milli -->Seconds :" + sec + " --> min :"
					+ min);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			metrics.setSuccessCount(successRowCount);
			metrics.setErrorCount(failureRowCount);
			try {
				pstmtCreateTable.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				pstmtInsertTable.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				pstmtUpdateTable.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}

	}
}
