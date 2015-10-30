"C:\Program Files\MySQL\MySQL Server 5.7\bin\mysql" --user=root --password=root --database=vzusages < loadToMYSQL.sql
ECHO "Running MONTHLY Aggregations process...."
runMonthlyAggr.bat %1
