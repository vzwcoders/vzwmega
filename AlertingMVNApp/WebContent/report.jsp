<%@page import="com.vz.usage.util.AlertMetrics"%>
<%@page import="com.sun.corba.se.impl.encoding.OSFCodeSetRegistry.Entry"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.Map"%>
<%@page import="com.vz.usage.ReportAlertCounts"%>
[<% 
Set<AlertMetrics> dat=ReportAlertCounts.getAlertsMetrics();
String s="";
StringBuilder sb=new StringBuilder();
if(dat!=null && dat.size()>0){
for(AlertMetrics ent:dat){
sb.append("{\"letter\":\""+ent.getTyp()+"\",");	
sb.append("\"frequency\":\""+ent.getCount()+"\" },");
}
s=sb.substring(0,sb.length()-1)+"]";
}
out.print(s);
%>