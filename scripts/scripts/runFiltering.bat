set CLASSPATH=%CLASSPATH%;.;d:\production\libs\monaggr.jar;d:\production\libs\mysql-connector-java-5.1.37-bin.jar;
java  com.batchprocess.processor.DeleteRestrictedValues  %1
ECHO "Running AGGREGRATING THE MONTHLY CALCULATIONS...."
runLoadDB.bat %1
ECHO "COMPLETED THE PROCESS"