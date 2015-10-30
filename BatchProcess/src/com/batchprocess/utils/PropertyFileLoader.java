package com.batchprocess.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyFileLoader {

	public static Properties prop= null;
	public static String getPropertyValue(String key){
	
		InputStream input = null;
		
	
		try {
			if(null == prop){
				
				prop = new Properties();
				input = new FileInputStream("D:/BatchProcess.properties");
		
				// load a properties file
				prop.load(input);
			}
			
	
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		if(null != prop && null != prop.getProperty(key)){
			return prop.getProperty(key).trim();
		}else{
			return null;
		}

	}
}
