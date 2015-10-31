ECHO "Running UPDATING MONTHLY AGGREGATIONS...."
set CLASSPATH=%CLASSPATH%;.;d:\production\libs\monaggr.jar;d:\production\libs\mysql-connector-java-5.1.37-bin.jar;
java  com.batchprocess.processor.MonthlyTableInsertion  %1 %2 %3
runAlertingUpdate.bat %1 %2 %3
ECHO "COMPLETED THE PROCESS"