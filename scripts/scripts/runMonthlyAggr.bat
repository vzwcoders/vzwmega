set CLASSPATH=%CLASSPATH%;.;d:\production\libs\monaggr.jar;d:\production\libs\mysql-connector-java-5.1.37-bin.jar;
java  com.batchprocess.processor.MonthlyTableInsertion  %1
ECHO "Running UPDATING THE ALERTING TABLE...."
runAlertingUpdate.bat %1
ECHO "COMPLETED THE PROCESS"