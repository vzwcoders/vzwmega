TRUNCATE udrs;
LOAD DATA LOCAL INFILE 'D:\\validFile.csv' INTO TABLE udrs FIELDS TERMINATED BY ',' (custid,udr_size,start_time,endi_time,website,dvcid);