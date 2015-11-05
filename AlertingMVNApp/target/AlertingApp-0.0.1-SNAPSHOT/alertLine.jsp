<%@ page session="true" %>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.vz.usage.util.AlertMetrics"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.Map"%>
<%@page import="com.vz.usage.ReportAlertCounts"%>
<%
Integer ind=(Integer)application.getAttribute("i");
//System.out.println("*** from context index is "+ind);
Set<AlertMetrics> dat=ReportAlertCounts.getAlertsMetricsTim();
List<AlertMetrics> lst=new ArrayList<AlertMetrics>(dat);
if(ind==null){
	//System.out.println("*** from context index is null "+ind);
	application.setAttribute("i", 0);
ind=(Integer)application.getAttribute("i");
	
	AlertMetrics am=lst.get(ind);
	out.print(am.getTyp()+","+am.getCount());
}else{
	ind++;
	ind=ind%lst.size();
	System.out.println("index is not nulll "+ind);
	AlertMetrics am=lst.get(ind);
	application.setAttribute("i", ++ind);
	out.print("{"+"\""+am.getTyp()+"\""+":"+"\""+am.getCount()+"\"}");
}
%>