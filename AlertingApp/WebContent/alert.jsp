<%@page import="com.vz.usage.AlertingProcess"%>
<%@page import="com.vz.usage.util.CustAlert"%>
<%@page import="java.util.List"%>
<html>
<head>
<META HTTP-EQUIV="refresh" CONTENT="3;url=alert.jsp">
<meta http-equiv="pragma" content="no-cache"><meta http-equiv="cache-control" content="no-cache"><meta http-equiv="expires" content="0">
<style>
thead {color:green;}
tbody {color:blue;}
tfoot {color:red;}
table,th,td
{border:1px solid black;}
.btn {
    float:left;
}

.txt {
    display:block;
}</style>
</head>
<div align="center" class="txt" ><h1>Verizon Usage Alerting System </h1></div>
<table border="1" style="width:80%" cellpadding="0" cellspacing="0" align="center" >
<thead>
<tr>
<th>Customer </th>
<th>Alert Type</th>
<th>Usage</th>
<th>Alert Time</th>
</tr>
</thead>
<tbody>
<%
	
	List<CustAlert> l=AlertingProcess.getAlerts();
if(l!=null && l.size()>0){
	for(CustAlert c:l){
%>
<tr>
<td><%=c.getCustid() %></td>
<td><%=c.getTyp()%></td>
<td><%=c.getUsage()%></td>
<td><%=c.getTim()%></td>
</tr>
<%
	}
}else{%>
<tr><td colspan="4" align="center">No Alerts at this point of time</td></tr>
<%
}
%>
</tbody>
</table>
</html>