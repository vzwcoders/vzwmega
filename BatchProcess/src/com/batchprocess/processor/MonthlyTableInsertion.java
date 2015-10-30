package com.batchprocess.processor;

import java.sql.Timestamp;
import java.util.Date;

import com.batchprocess.utils.InsertionBasedOnProcessId;
import com.batchprocess.utils.MetricsUtils;
import com.batchprocess.vo.Metrics;

public class MonthlyTableInsertion {

	public static void main(String args[]){

		try
		{
			String processId = args[0];
			//String tableName = "DailyUsage_"+processId.substring(StringUtils.originalIndexOf(processId, '_', 0)+1, StringUtils.originalIndexOf(processId, '_', 3));
			String tableName = "monthly_data_usage";
			Metrics metrics = new Metrics();
			metrics.setProcessId(processId);
			metrics.setSubProcessId("MonthlyTableInsertion");
			metrics.setStartTime(new Timestamp( System.currentTimeMillis()));
			InsertionBasedOnProcessId.insertDataBasedOnTableName(tableName,metrics);
			metrics.setEndTime(new Timestamp( System.currentTimeMillis()));
			MetricsUtils.captureMetrics(metrics);
		}catch(Exception e){
			e.printStackTrace();
		}

	}
}
