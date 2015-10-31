set CLASSPATH=%CLASSPATH%;.;d:\production\libs\monaggr.jar;d:\production\libs\mysql-connector-java-5.1.37-bin.jar;
ECHO "Running Filtering process Started.... with %1 %2 %3 %4"
java  com.batchprocess.processor.DeleteRestrictedValues  %1 %3 
ECHO "Running AGGREGRATING THE MONTHLY CALCULATIONS...."
runLoadDB.bat %1 %2 %3 %4
ECHO "COMPLETED THE PROCESS"