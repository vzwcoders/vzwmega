package com.batchprocess.processor;

import java.sql.Timestamp;
import java.util.Date;

import com.batchprocess.utils.InsertionBasedOnProcessId;
import com.batchprocess.utils.MetricsUtils;
import com.batchprocess.utils.StringUtils;
import com.batchprocess.vo.Metrics;

public class HourlyTableInsertion {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String processId = args[0];
		String tableName = "HourlyUsage_"+processId.substring(StringUtils.originalIndexOf(processId, '_', 0)+1, StringUtils.originalIndexOf(processId, '_', 4));
		Metrics metrics = new Metrics();
		metrics.setProcessId(processId);
		metrics.setSubProcessId("HourlyTableInsertion");
		metrics.setStartTime(new Timestamp( System.currentTimeMillis()));
		InsertionBasedOnProcessId.insertDataBasedOnTableName(tableName,metrics);
		metrics.setEndTime(new Timestamp( System.currentTimeMillis()));
		MetricsUtils.captureMetrics(metrics);
	}

}
