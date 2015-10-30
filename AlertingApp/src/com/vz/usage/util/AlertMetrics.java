package com.vz.usage.util;

public class AlertMetrics implements Comparable<AlertMetrics>{
	String typ;
	String count;
	public String getTyp() {
		return typ;
	}
	public void setTyp(String typ) {
		this.typ = typ;
	}
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}
	public AlertMetrics(String typ, String count) {
		super();
		this.typ = typ;
		this.count = count;
	}
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}
	@Override
	public boolean equals(Object obj) {
		AlertMetrics am=(AlertMetrics)obj;
		return getTyp()==am.getTyp();
	}
	@Override
	public int compareTo(AlertMetrics o) {
		return getTyp().compareTo(o.getTyp());
	}
	
}
