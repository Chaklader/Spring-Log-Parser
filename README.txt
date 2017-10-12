Documentation:
--------------

The project is to develop a parser tool in Java that parses web server access log file
and stores all the log entities to the MySQL database. Afterwards, it checks if a given
IP makes more than a certain number of requests for the given duration and load those
IPs with the respective info to the MySQL database. The tool is developed with Java,
Spring boot and the maven is used as the build tool.

The example of the server log file that has to parse provided below,

2017-10-01 03:56:04.326|174.129.239.67|"GET / HTTP/1.1"|200|"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.91 Safari/537.36"

I have used a generated server log file can be found in the root of the project namely "Log.txt".
An executable JAR file from this app and can be found in the directory namely "deliverable". The
JAR file has to run in the same directory of the server log file "Log.txt" with the command like,

    java -cp parser.jar -Dloader.main=com.ef.Parser org.springframework.boot.loader.PropertiesLauncher --startDate=2017-10-02.17:14:55 --duration=daily --threshold=100

    OR,

    java -jar parser.jar com.ef.Parser --startDate=2017-10-02.17:14:55 --duration=daily --threshold=100

Using these commands, the tool will find any IPs that made more than 100 requests starting from
2017-10-02 17:14:55 to 2017-10-03 17:14:55 (one day) and print them to console AND also load them
to another MySQL table with comments on why it's blocked.

After the tool is executed, it creates 4 tables in the MySQL database namely all_log_entries, ip_address,
http_info_message and ip_addr_status. The table ip_addr_status maps the IP addresses send requests more
than certain threshold with their corresponding status Id from the http_info_message table. As mentioned, it
prints in the console of the Ip addresses over the threshold value with corresponding messages:


IP      :      COMMENT
----------------------
177.132.239.67 : 403_Forbidden
177.132.239.67 : 404_Not Found
177.132.239.67 : 405_Method Not Allowed
177.132.239.67 : 406_Not Acceptable

178.133.239.67 : 403_Forbidden
178.133.239.67 : 404_Not Found
178.133.239.67 : 405_Method Not Allowed
178.133.239.67 : 406_Not Acceptable

175.130.239.67 : 403_Forbidden
175.130.239.67 : 404_Not Found
175.130.239.67 : 405_Method Not Allowed
175.130.239.67 : 406_Not Acceptable

176.131.239.67 : 403_Forbidden
176.131.239.67 : 404_Not Found
176.131.239.67 : 405_Method Not Allowed
176.131.239.67 : 406_Not Acceptable

174.129.239.67 : 403_Forbidden
174.129.239.67 : 404_Not Found
174.129.239.67 : 405_Method Not Allowed
174.129.239.67 : 406_Not Acceptable


SQL
---

MySQL query to find IPs that mode more than a certain number of requests for a given time period. For Example,
Write SQL to find IPs that made more than 100 requests starting from 2017-01-01.13:00:00 to 2017-01-01.14:00:00.

    SELECT ip_address, COUNT(ip_address) AS hits FROM LogParser.all_log_entries
	WHERE start_time BETWEEN "2017-01-01.13:00:00" AND "2017-01-01.14:00:00"
	GROUP BY (ip_address) HAVING hits  > 100;

MySQL query to find requests made by a given IP.

Say, I would like find the requests made by the IP address '174.129.239.67'. The SQL query
will be,

SELECT id, status_code, start_time FROM LogParser.all_log_entries  where ip_address = '174.129.239.67';