package com.batchprocess.processor;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import com.batchprocess.utils.DateUtils;
import com.batchprocess.utils.MetricsUtils;
import com.batchprocess.utils.PropertyFileLoader;
import com.batchprocess.vo.Metrics;

public class DeleteRestrictedValues {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Metrics metrics = new Metrics();
		
		metrics.setSubProcessId("FilteringProcess");
		metrics.setStartTime(new Timestamp( System.currentTimeMillis()));
		try
		{	
			String processId = args[0];
			
			String customerIdsList[] = null;
			String webSites[]=null;
			String timeFrames[]=null;
			String initialTimeFrame = null;
			String finalTimeFrame = null;
			Set<String> custmerIdsSet = null;
			Set<String> webSitesSet = null;
			metrics.setProcessId(processId);
			String customerIds = PropertyFileLoader.getPropertyValue("customerIds");
			if(null != customerIds){
				customerIdsList = customerIds.split(",");
				custmerIdsSet= new HashSet<String>(Arrays.asList(customerIdsList));
				//System.out.println(custmerIdsSet.toString());
			}
			
			String websites = PropertyFileLoader.getPropertyValue("websites");
			if(null != websites){
				webSites = websites.split(",");
				webSitesSet= new HashSet<String>(Arrays.asList(webSites));
			}
			
			String timeFrame= PropertyFileLoader.getPropertyValue("timeFrames");
			if(null != timeFrame){
				timeFrames = timeFrame.split("-");
				initialTimeFrame = timeFrames[0];
				finalTimeFrame = timeFrames[1];
				DateUtils.initialize(initialTimeFrame, finalTimeFrame);
			}

			String inputFilePath = PropertyFileLoader.getPropertyValue("inputFileNamePath");
			String validFilePath = PropertyFileLoader.getPropertyValue("validRecFilePath");
			String invalidFilePath = PropertyFileLoader.getPropertyValue("invalidRecFilePath");
			
			readFile(inputFilePath, validFilePath, invalidFilePath, custmerIdsSet, webSitesSet,initialTimeFrame,finalTimeFrame,metrics);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		metrics.setEndTime(new Timestamp( System.currentTimeMillis()));
		MetricsUtils.captureMetrics(metrics);
	}
	
	public static void readFile(String inputFileName,String validRecFileName,String invalidRecFileName, Set<String> custmerIdsSet,Set<String> webSitesSet,String initialTimeFrame, String finalTimeFrame, Metrics m ) {
		String thisLine = null;
		OutputStream ois1 = null;
		OutputStream ois2 = null;
		BufferedWriter bw1 = null;
		BufferedWriter bw2 = null;
		try {
			long startTime = System.currentTimeMillis();
			// open input stream test.txt for reading purpose.
			InputStream fis = new FileInputStream(inputFileName);
			BufferedReader br = new BufferedReader(new InputStreamReader(fis));
			ois1 = new FileOutputStream(validRecFileName);
			ois2 = new FileOutputStream(invalidRecFileName);
			bw1 = new BufferedWriter(new OutputStreamWriter(ois1));
			bw2 = new BufferedWriter(new OutputStreamWriter(ois2));
			String data[] = null;
			String time[] = null;
			String startedTime = null;
			String endTime1 = null;
			int validCount=0,invalidCount=0;
			for (String line = br.readLine(); line != null; line = br
					.readLine()) {
				data = line.split(",");
				
				if(null!=initialTimeFrame && null!=finalTimeFrame){
					time = data[4].trim().split(" ");
					startedTime = time[1];
					time = data[5].trim().split(" ");
					endTime1 = time[1];
				}
				if(custmerIdsSet.contains(data[0]) || webSitesSet.contains(data[2]) ||
						(null!=initialTimeFrame && null!=finalTimeFrame && DateUtils.checkForValidTime(startedTime, endTime1))){
					bw2.write(line+"\n");
					invalidCount++;
				}else{
					bw1.write(line+"\n");
					validCount++;
				}
			}
			m.setSuccessCount(validCount);
			m.setErrorCount(invalidCount);
			br.close();
			bw1.close();
			bw2.close();
		    long endTime = System.currentTimeMillis();
		    long millsec = endTime-startTime;
		    long sec = millsec/1000;
		    long min = sec/60;
		    System.out.println("Total time taken to filter:"+millsec+" milli -->Seconds :"+sec+" --> min :"+min);
			System.out.println();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
