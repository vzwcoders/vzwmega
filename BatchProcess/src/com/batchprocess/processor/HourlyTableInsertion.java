package com.batchprocess.processor;

import java.sql.Timestamp;

import com.batchprocess.utils.InsertionBasedOnProcessId;
import com.batchprocess.utils.MetricsUtils;
import com.batchprocess.utils.StringUtils;
import com.batchprocess.vo.Metrics;

public class HourlyTableInsertion {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Started Running HourlyTableInsertion  Aggregrations...Verzion X0001");
		try
		{
			if(args == null || args.length <2){
				System.out.println("Usage : Please provide <Process Id> and <Table name>");
				System.exit(0);
			}
			String processId = args[0];
			String dumpTableName=args[1];
		String tableName = "HourlyUsage_"+processId.substring(StringUtils.originalIndexOf(processId, '_', 0)+1, StringUtils.originalIndexOf(processId, '_', 4));
		Metrics metrics = new Metrics();
		metrics.setProcessId(processId);
		metrics.setSubProcessId("HourlyTableInsertion");
		metrics.setStartTime(new Timestamp( System.currentTimeMillis()));
		InsertionBasedOnProcessId.insertDataBasedOnTableName(tableName,metrics,dumpTableName);
		metrics.setEndTime(new Timestamp( System.currentTimeMillis()));
		MetricsUtils.captureMetrics(metrics);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
