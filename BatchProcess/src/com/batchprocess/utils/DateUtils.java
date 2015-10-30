package com.batchprocess.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateUtils {
	
	public static Calendar iniTimeCal;
	public static Calendar finTimeCal;
	public static String iniTimeStr;
	public static String finTimeStr;
	
	public static void initialize(String initialTime, String finalTime) throws Exception{

		if(null == iniTimeStr && null == finTimeStr){
			iniTimeStr = initialTime;
			finTimeStr = finalTime;
			
            java.util.Date inTime = new SimpleDateFormat("HH:mm:ss").parse(initialTime);
            iniTimeCal = Calendar.getInstance();
            iniTimeCal.setTime(inTime);
            
            java.util.Date finTime = new SimpleDateFormat("HH:mm:ss").parse(finalTime);
            finTimeCal = Calendar.getInstance();
            finTimeCal.setTime(finTime);

		}
	}
	
	public static boolean checkForValidTime(String givenStartTime, String givenEndTime)throws Exception{
		
		boolean valid1 = false;
		boolean valid2 = false;
		
        //Current Time
        java.util.Date checkTime = new SimpleDateFormat("HH:mm:ss").parse(givenStartTime);
        Calendar calendar3 = Calendar.getInstance();
        calendar3.setTime(checkTime);

        //Current Time1
        java.util.Date checkTime1 = new SimpleDateFormat("HH:mm:ss").parse(givenEndTime);
        Calendar calendar4 = Calendar.getInstance();
        calendar4.setTime(checkTime1);

        
        if (finTimeStr.compareTo(iniTimeStr) < 0) {
            finTimeCal.add(Calendar.DATE, 1);
            calendar3.add(Calendar.DATE, 1);
            calendar4.add(Calendar.DATE, 1);
        }

        java.util.Date actualTime = calendar3.getTime();
        if ((actualTime.after(iniTimeCal.getTime()) || actualTime.compareTo(iniTimeCal.getTime()) == 0) 
                && actualTime.before(finTimeCal.getTime())) {
            valid1 = true;
        }
        
        java.util.Date actualTime1 = calendar4.getTime();
        if ((actualTime1.after(iniTimeCal.getTime()) || actualTime1.compareTo(iniTimeCal.getTime()) == 0) 
                && actualTime1.before(finTimeCal.getTime())) {
            valid2 = true;
        }
        
        if(valid1 && valid2){
        	return true;
        }else{
        	return false;
        }

	}

	public static boolean isTimeBetweenTwoTime(String initialTime, String finalTime, String currentTime, String currentTime1) throws ParseException {
        String reg = "^([0-1][0-9]|2[0-3]):([0-5][0-9]):([0-5][0-9])$";
        boolean valid1 = false;
        boolean valid2 = false;
        if (initialTime.matches(reg) && finalTime.matches(reg) && currentTime.matches(reg) && currentTime1.matches(reg)) {
            boolean valid = false;
            //Start Time
            java.util.Date inTime = new SimpleDateFormat("HH:mm:ss").parse(initialTime);
            Calendar calendar1 = Calendar.getInstance();
            calendar1.setTime(inTime);

            //Current Time
            java.util.Date checkTime = new SimpleDateFormat("HH:mm:ss").parse(currentTime);
            Calendar calendar3 = Calendar.getInstance();
            calendar3.setTime(checkTime);

            //Current Time1
            java.util.Date checkTime1 = new SimpleDateFormat("HH:mm:ss").parse(currentTime1);
            Calendar calendar4 = Calendar.getInstance();
            calendar4.setTime(checkTime1);

            //End Time
            java.util.Date finTime = new SimpleDateFormat("HH:mm:ss").parse(finalTime);
            Calendar calendar2 = Calendar.getInstance();
            calendar2.setTime(finTime);

            if (finalTime.compareTo(initialTime) < 0) {
                calendar2.add(Calendar.DATE, 1);
                calendar3.add(Calendar.DATE, 1);
                calendar4.add(Calendar.DATE, 1);
            }

            java.util.Date actualTime = calendar3.getTime();
            if ((actualTime.after(calendar1.getTime()) || actualTime.compareTo(calendar1.getTime()) == 0) 
                    && actualTime.before(calendar2.getTime())) {
                valid1 = true;
            }
            
            java.util.Date actualTime1 = calendar4.getTime();
            if ((actualTime1.after(calendar1.getTime()) || actualTime1.compareTo(calendar1.getTime()) == 0) 
                    && actualTime1.before(calendar2.getTime())) {
                valid2 = true;
            }
            
            if(valid1 && valid2){
            	return true;
            }else{
            	return false;
            }

       } else {
            throw new IllegalArgumentException("Not a valid time, expecting HH:MM:SS format");
        }

    }
	
	public void readDateFromProcessId(String processId){
		
		//String formatedString = processId.substring(beginIndex, endIndex)
	}
	
	public static void main(String ars[]){
		/*
	
		"07:00:00" - "17:30:00" - "15:30:00" [current] - true
		"17:00:00" - "21:30:00" - "16:30:00" [current] - false
		"23:00:00" - "04:00:00" - "02:00:00" [current] - true
		"00:30:00" - "06:00:00" - "06:00:00" [current] - false
		*/
		DateUtils du = new DateUtils();
		String initialTime = "07:00:00";
		String finalTime = "17:30:00";
		String verifyTime1 = "09:30:00";
		String verifyTime2 = "10:30:00";
		try {
			long startTime = System.currentTimeMillis();
			System.out.println(du.isTimeBetweenTwoTime(initialTime, finalTime, verifyTime1,verifyTime2));
			System.out.println("Time taken :"+(System.currentTimeMillis()-startTime));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
