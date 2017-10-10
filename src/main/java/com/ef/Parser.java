package com.ef;

import com.ef.util.FileManager;
import com.ef.entity.LogEntity;
import com.ef.util.LogParser;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.List;

import static java.lang.System.exit;

@EnableTransactionManagement
@SpringBootApplication(scanBasePackages = {"com.ef"}, exclude = JpaRepositoriesAutoConfiguration.class)
public class Parser implements CommandLineRunner {


//    private final LogParser logParser;

    public Parser() {
//        logParser = new LogParser();
    }

    public static void main(String[] args) throws Exception {

        SpringApplication application = new SpringApplication(Parser.class);

        application.run(args);
    }

    @Override
    public void run(String... strings) throws Exception {

        try {
            final String fileName = "Log.txt";
            List<LogEntity> logEntityList = FileManager.readFileByLines(fileName);
        } catch (Exception ex) {
            // file reading errors
        }

//        String log = "127.0.0.1|-|waren|[2000-10-21.33:55:36 -0700]|\"GET /apache_pb.gif HTTP/1.0\"|200|2326";
//        String log1 = "2017-10-01 03:56:04.326|174.129.239.67|\"GET / HTTP/1.1\"|200|\"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.91 Safari/537.36\"";

//        LogEntity logEntity = logParser.parseLogEntryByLine(log1);

        System.out.println("\n\nHello, Spring Boot!\n\n");
        exit(0);
    }
}

