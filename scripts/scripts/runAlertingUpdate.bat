set CLASSPATH=%CLASSPATH%;.;d:\production\libs\alerting.jar;d:\production\libs\mysql-connector-java-5.1.37-bin.jar;d:\production\libs\monaggr.jar;
ECHO "Starting AlERTING WITH %1 AND %2"
start java  com.verizon.hackathon.InsertToAlertTableProcess  %1 0
start java  com.verizon.hackathon.InsertToAlertTableProcess  %1 1
start java  com.verizon.hackathon.InsertToAlertTableProcess  %1 2
start java  com.verizon.hackathon.InsertToAlertTableProcess  %1 3
start java  com.verizon.hackathon.InsertToAlertTableProcess  %1 4
start java  com.verizon.hackathon.InsertToAlertTableProcess  %1 5
start java  com.verizon.hackathon.InsertToAlertTableProcess  %1 6
start java  com.verizon.hackathon.InsertToAlertTableProcess  %1 7
start java  com.verizon.hackathon.InsertToAlertTableProcess  %1 8
start java  com.verizon.hackathon.InsertToAlertTableProcess  %1 9
start java  com.batchprocess.processor.DailyTableInsertion  %1