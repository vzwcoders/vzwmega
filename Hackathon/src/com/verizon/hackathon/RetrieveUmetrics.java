package com.verizon.hackathon;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.batchprocess.vo.Metrics;

public class RetrieveUmetrics {

	public static Connection conn = null;
	public static Statement stmt = null;
	public static PreparedStatement pstmt = null;
	public static ResultSet rs = null;

	public static void main(String[] args) {
		RetrieveUmetrics.retrieveUmetricsDetails("TEST_2015_10_30_13:26:00:00");
	}
	public static List<Metrics> retrieveUmetricsDetails(String procId) {
		String processId = procId;
		// Establish Database Connection
		conn = DBConnection.getConnection();

		try {
			stmt = conn.createStatement();
			rs = stmt
					.executeQuery("select processid,start_time,end_time,subproc,succ_cou,error_cou,bucket_id from umetrics where processid = "
							+ "'" + processId + "'");
			ArrayList<Metrics> metrics = new ArrayList<Metrics>();
			while (rs.next()) {
				Metrics vo = new Metrics();
				vo.setProcessId(rs.getString("processid"));
				vo.setStartTime(rs.getTimestamp("start_time"));
				vo.setEndTime(rs.getTimestamp("end_time"));
				vo.setSubProcessId(rs.getString("subproc"));
				vo.setSuccessCount(rs.getInt("succ_cou"));
				vo.setErrorCount(rs.getInt("error_cou"));
				vo.setBucketId(rs.getInt("bucket_id"));
				metrics.add(vo);

			}
			rs.close();
			return metrics;
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			
		}// end try
		return null;
	}

}
