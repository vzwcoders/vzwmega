set CLASSPATH=%CLASSPATH%;.;d:\production\libs\alerting.jar;d:\production\libs\mysql-connector-java-5.1.37-bin.jar;d:\production\libs\monaggr.jar;
ECHO "Starting AlERTING WITH %1 AND %2"
start java  com.verizon.hackathon.InsertToAlertTableProcess  %1 0 %3
start java  com.verizon.hackathon.InsertToAlertTableProcess  %1 1 %3
start java  com.verizon.hackathon.InsertToAlertTableProcess  %1 2 %3
start java  com.verizon.hackathon.InsertToAlertTableProcess  %1 3 %3
start java  com.verizon.hackathon.InsertToAlertTableProcess  %1 4 %3
start java  com.batchprocess.processor.DailyTableInsertion  %1 %2 %3 