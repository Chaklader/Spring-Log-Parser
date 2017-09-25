java.sql.SQLException: Unable to load class: com.mysql.jdbc.Driver from ClassLoader:ParallelWebappClassLoader
  context: ROOT
  delegate: false
----------> Parent Classloader:
java.net.URLClassLoader@515f550a
;ClassLoader:ParallelWebappClassLoader
  context: ROOT
  delegate: false
----------> Parent Classloader:
java.net.URLClassLoader@515f550a


2017-09-25 10:23:51.958  WARN 1520 --- [on(2)-127.0.0.1] o.s.b.a.orm.jpa.DatabaseLookup           : Unable to determine jdbc url from datasource

org.springframework.jdbc.support.MetaDataAccessException: Could not get Connection for extracting meta data; nested exception is org.springframework.jdbc.CannotGetJdbcConnectionException: Could not get JDBC Connection; nested exception is java.sql.SQLException: Unable to load class: com.mysql.jdbc.Driver from ClassLoader:ParallelWebappClassLoader
  context: ROOT
  delegate: false
----------> Parent Classloader:
java.net.URLClassLoader@515f550a
;ClassLoader:ParallelWebappClassLoader
  context: ROOT
  delegate: false
----------> Parent Classloader:
java.net.URLClassLoader@515f550a
