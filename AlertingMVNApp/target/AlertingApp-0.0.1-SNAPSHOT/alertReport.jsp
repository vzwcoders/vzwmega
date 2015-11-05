<%@page import="com.vz.usage.util.AlertMetrics"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.Map"%>
<%@page import="com.vz.usage.ReportAlertCounts"%>
<html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="https://code.highcharts.com/highcharts.js"></script>
<script src="https://code.highcharts.com/modules/data.js"></script>
<script src="https://code.highcharts.com/modules/drilldown.js"></script>
</head>
<body>
<div id="container" style="min-width: 310px; height: 400px; margin: 0 auto"></div>
<%
Set<AlertMetrics> dat=ReportAlertCounts.getAlertsMetrics();
String s="";
StringBuilder sb=new StringBuilder();
%>
<script>
$(function () {
    // Create the chart
    $('#container').highcharts({
        chart: {
            type: 'column'
        },
        title: {
            text: 'Verizon Alert Reporting By Category'
        },
        subtitle: {
            text: 'VZW Coders Alert Reporting'
        },
        xAxis: {
            type: 'category'
        },
        yAxis: {
            title: {
                text: 'No. of Alerts by the Category'
            }

        },
        legend: {
            enabled: true
        },
        plotOptions: {
            series: {
                borderWidth: 0,
                dataLabels: {
                    enabled: true,
                    format: '{point.y}'
                }
            }
        },

        tooltip: {
            headerFormat: '<span style="font-size:11px">{series.name}</span><br>',
            pointFormat: '<span style="color:{point.color}">{point.name}</span>: <b>{point.y}</b> of total<br/>'
        },

        series: [{
            name: 'Alerts',
            colorByPoint: true,
            data: [
                   <% 
                   if(dat!=null && dat.size()>0){
                	   for(AlertMetrics ent:dat){
                   %>
                   {
                       name: '<%=ent.getTyp()%>',
                       y: <%=ent.getCount()%>
                   },
                   <%}
                   }
                   %>
                   ]
        }]
    });
});
</script>
</body>