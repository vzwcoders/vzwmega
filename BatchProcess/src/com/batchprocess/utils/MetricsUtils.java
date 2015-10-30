package com.batchprocess.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import com.batchprocess.connection.DBConnection;
import com.batchprocess.vo.Metrics;

public class MetricsUtils {

	public static SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public static void captureMetrics(Metrics metrics){
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		
		try
		{
			conn = DBConnection.getConnection();
			 sql = PropertyFileLoader.getPropertyValue("metricsQuery");
			 pstmt = conn.prepareStatement(sql);
			 pstmt.setString(1, metrics.getProcessId());
			 pstmt.setString(2, metrics.getSubProcessId());
			 pstmt.setInt(3, metrics.getSuccessCount());
			 pstmt.setInt(4, metrics.getErrorCount());
			 pstmt.setInt(5, metrics.getBucketId());
			 pstmt.setTimestamp(6,metrics.getStartTime());
			 pstmt.setTimestamp(7,  metrics.getEndTime());
			 pstmt.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
