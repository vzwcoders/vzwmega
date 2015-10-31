ECHO "Running MYSQL Load process with file %1 %2 %3 %4"
"C:\Program Files\MySQL\MySQL Server 5.7\bin\mysql" --user=root --password=root --database=vzusages < %4
ECHO "Running MONTHLY Aggregations process...."
runMonthlyAggr.bat %1 %2 %3
