package com.vz.usage.util;

public class CustAlert {

	int custid;
	int typ;
	String tim;
	int usage;
	
	@Override
	public String toString() {
		return "CustAlert [custid=" + custid + ", typ=" + typ + ", tim=" + tim
				+ ", usage=" + usage + "]";
	}
	public CustAlert(int custid, int typ, String tim, int usage) {
		super();
		this.custid = custid;
		this.typ = typ;
		this.tim = tim;
		this.usage = usage;
	}
	public int getCustid() {
		return custid;
	}
	public void setCustid(int custid) {
		this.custid = custid;
	}
	public int getTyp() {
		return typ;
	}
	public void setTyp(int typ) {
		this.typ = typ;
	}
	public String getTim() {
		return tim;
	}
	public void setTim(String tim) {
		this.tim = tim;
	}
	public int getUsage() {
		return usage;
	}
	public void setUsage(int usage) {
		this.usage = usage;
	}
	
	

}
