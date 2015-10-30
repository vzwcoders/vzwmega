package com.batchprocess.processor;

import java.sql.Timestamp;

import com.batchprocess.utils.InsertionBasedOnProcessId;
import com.batchprocess.utils.MetricsUtils;
import com.batchprocess.utils.StringUtils;
import com.batchprocess.vo.Metrics;

public class DailyTableInsertion {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try
		{
			String processId = args[0];
			String tableName = "DailyUsage_"+processId.substring(StringUtils.originalIndexOf(processId, '_', 0)+1, StringUtils.originalIndexOf(processId, '_', 3));
			Metrics metrics = new Metrics();
			metrics.setProcessId(processId);
			metrics.setSubProcessId("DailyTableInsertion");
			metrics.setStartTime(new Timestamp( System.currentTimeMillis()));
			InsertionBasedOnProcessId.insertDataBasedOnTableName(tableName,metrics);
			metrics.setEndTime(new Timestamp( System.currentTimeMillis()));
			MetricsUtils.captureMetrics(metrics);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
