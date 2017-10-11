package com.ef;

import com.ef.entity.IpAddress;
import com.ef.service.DataOrganizationHelper;
import com.ef.service.def.IpAddressService;
import com.ef.service.util.FileManager;
import com.ef.entity.LogEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.List;

import static java.lang.System.exit;


@EnableJpaRepositories("com.ef.repository")
@SpringBootApplication(scanBasePackages = {"com.ef"}, exclude = JpaRepositoriesAutoConfiguration.class)
public class Parser implements CommandLineRunner {

    @Autowired
    private IpAddressService ipAddressService;

    public Parser() {

    }

    public static void main(String[] args) throws Exception {

        SpringApplication application = new SpringApplication(Parser.class);
        application.run(args);
    }

    /*
    * print all the IP addresses that is over the threshold
    * with all the http messages
    * */
    public static void printIpWithHttpMessagesOverThreshold(List<IpAddress> ipAddresses) {

        System.out.println("  IP      :      COMMENT  ");
        ipAddresses.forEach(ipAddress -> {
            ipAddress.getHttpInfoMessages().forEach(
                    httpInfoMessage -> {
                        System.out.println(ipAddress.getAddress() + " : " + httpInfoMessage.getStatus());
                    }
            );

            System.out.println();
        });
    }


//        java -cp "parser.jar" com.ef.Parser --startDate=2000-10-21.21:55:36 --duration=hourly --threshold=100
//        java -cp target/parser.jar com.ef.Parser --startDate=2000-10-21.21:55:36 --duration=hourly --threshold=100
//        java -jar target/parser.jar com.ef.Parser --startDate=2000-10-21.21:55:36 --duration=hourly --threshold=100

    @Override
    public void run(String... args) throws Exception {


//        Options options = new Options();
//
//        Option start = new Option("startDate", "startDate", true, "Start date");
//        start.setRequired(true);
//        options.addOption(start);
//
//        Option duration = new Option("duration", "duration", true, "duration");
//        duration.setRequired(true);
//        options.addOption(duration);
//
//        Option threshold = new Option("threshold", "threshold", true, "threshold");
//        threshold.setRequired(true);
//        options.addOption(threshold);
//
//        CommandLineParser parser = new DefaultParser();
//        HelpFormatter formatter = new HelpFormatter();
//        CommandLine cmd;
//
//        try {
//            cmd = parser.parse(options, args);
//        } catch (ParseException e) {
//            System.out.println(e.getMessage());
//            formatter.printHelp("utility-name", options);
//
//            System.exit(1);
//            return;
//        }
//
//        String startDate = cmd.getOptionValue("startDate");
//        String dur = cmd.getOptionValue("duration");
//        String thers = cmd.getOptionValue("threshold");
//
//        System.out.println(startDate);
//        System.out.println(dur);
//        System.out.println(thers);

        List<LogEntity> allRecords = FileManager.readFileByLines("Log.txt");

        List<IpAddress> ipAddresses = DataOrganizationHelper
                .getIpAddressesFromAllRecordsWithDurationAndThresHold(allRecords, "2017-10-01 01:51:12",
                        "daily", 50);

        System.out.println("\nThe IP list size is = " + ipAddresses.size() + "\n");


        ipAddressService.saveAllIpAddressses(ipAddresses);

        exit(0);
    }
}

