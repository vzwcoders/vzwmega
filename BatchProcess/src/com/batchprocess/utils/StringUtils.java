package com.batchprocess.utils;

public class StringUtils {

	public static int originalIndexOf(String str, char c, int n) {
	    int pos = str.indexOf(c, 0);
	    while (n-- > 0 && pos != -1)
	        pos = str.indexOf(c, pos+1);
	    return pos;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String str = "Test_2015_10_30_13_12_14";
		System.out.println(str.substring(StringUtils.originalIndexOf(str, '_', 0)+1, StringUtils.originalIndexOf(str, '_', 3)));
	}

}
