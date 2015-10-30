<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.batchprocess.vo.Metrics"%>
<%@page import="com.verizon.hackathon.RetrieveUmetrics"%>
<%@page import="com.vz.usage.AlertingProcess"%>
<%@page import="com.vz.usage.util.CustAlert"%>
<%@page import="java.util.List"%>
<html>
<head>
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
<body>
<div align="center" class="txt" ><h1>Summary of the Process </h1></div>
<% String processid=request.getParameter("p");%>
<div align="center" class="txt" ><h2>Process Name - <%=processid %> </h2></div>
<table border="1" style="width:80%" cellpadding="0" cellspacing="0" align="center" >
<thead>
<tr>
<th>Process Name</th>
<th>Sub Process</th>
<th>Success Count</th>
<th>Failure Count</th>
<th>Start Time</th>
<th>End Time</th>
<th>Time Taken(ms)</th>
<th>Bucket</th>
</tr>
</thead>
<tbody>
<%
	
List<Metrics> l=RetrieveUmetrics.retrieveUmetricsDetails(processid);
System.out.println(" data is "+l);
if(l!=null && l.size()>0){
	for(Metrics c:l){
%>
<tr>
<td><%=c.getProcessId()%></td>
<td><%=c.getSubProcessId()%></td>
<td><%=c.getSuccessCount()%></td>
<td><%=c.getErrorCount()%></td>
<%
SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
System.out.println("time stamp is"+c.getEndTime());
%>
<td><%=c.getStartTime()%></td>
<td><%=c.getEndTime()%></td>
<td><%=(c.getEndTime().getTime()-c.getStartTime().getTime())+""%></td>
<td><%=c.getBucketId()%></td>
</tr>
<%
	}
}else{%>
<tr><td colspan="4" align="center">No Summary for the process id</td></tr>
<%
}
%>
</tbody>
</table>
</body>
</html>