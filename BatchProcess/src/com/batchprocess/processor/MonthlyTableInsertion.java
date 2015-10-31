package com.batchprocess.processor;

import java.sql.Timestamp;

import com.batchprocess.utils.InsertionBasedOnProcessId;
import com.batchprocess.utils.MetricsUtils;
import com.batchprocess.utils.PropertyFileLoader;
import com.batchprocess.vo.Metrics;

public class MonthlyTableInsertion {

	public static void main(String args[]){
		System.out.println("Started Running Monthly Talbe Aggregrations...Verzion X0003");
		try
		{
			if(args == null || args.length <3){
				System.out.println("Usage : Please provide <Process Id> , <Table name> and <properties file>");
				System.exit(0);
			}
			String processId = args[0];
			String dumpTableName=args[1];
			String propsFile=args[2];
			//String tableName = "DailyUsage_"+processId.substring(StringUtils.originalIndexOf(processId, '_', 0)+1, StringUtils.originalIndexOf(processId, '_', 3));
			String tableName = "monthly_data_usage";
			Metrics metrics = new Metrics();
			metrics.setProcessId(processId);
			metrics.setSubProcessId("MonthlyTableInsertion");
			metrics.setStartTime(new Timestamp( System.currentTimeMillis()));
			PropertyFileLoader.init(propsFile);
			InsertionBasedOnProcessId.insertDataBasedOnTableName(tableName,metrics,dumpTableName);
			metrics.setEndTime(new Timestamp( System.currentTimeMillis()));
			MetricsUtils.captureMetrics(metrics);
		}catch(Exception e){
			e.printStackTrace();
		}

	}
}
