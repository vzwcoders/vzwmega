"C:\Program Files\MySQL\MySQL Server 5.7\bin\mysql" --user=root --password=root --database=vzusages < loadToMYSQL.sql
ECHO "Running AGGREGRATING THE MONTHLY CALCULATIONS...."
runMonthlyAggr.bat
ECHO "Running UPDATING THE ALERTING TABLE...."
runAlertingUpdate.bat
ECHO "COMPLETED THE PROCESS"