package com.ef;

import com.ef.entity.IpAddress;
import com.ef.service.DataOrganizationHelper;
import com.ef.service.def.IpAddressService;
import com.ef.service.util.FileManager;
import com.ef.entity.LogEntity;
import com.ef.service.util.LogParser;
import org.apache.commons.cli.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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


    private static SimpleDateFormat formatedDate = new SimpleDateFormat("yyyy-MM-dd.HH:mm:ss", Locale.US);

    public static Date parseDate2(String dateStr) {
        ParsePosition pp = new ParsePosition(0);
        return formatedDate.parse(dateStr, pp);
    }


    // java -cp target/parser.jar -Dloader.main=com.ef.Parser org.springframework.boot.loader.PropertiesLauncher --startDate=2017-10-02.17:14:55 --duration=daily --threshold=100

    // java -jar target/parser.jar com.ef.Parser --startDate=2017-10-02.17:14:55 --duration=daily --threshold=100

    @Override
    public void run(String... args) throws Exception {

//        String startDate = "";
//        String dur = "";
//        int thers = -1;
//
//        {
//            Options options = new Options();
//
//            Option start = new Option("startDate", "startDate", true, "Start date");
//            start.setRequired(true);
//            options.addOption(start);
//
//            Option duration = new Option("duration", "duration", true, "duration");
//            duration.setRequired(true);
//            options.addOption(duration);
//
//            Option threshold = new Option("threshold", "threshold", true, "threshold");
//            threshold.setRequired(true);
//            options.addOption(threshold);
//
//            CommandLineParser parser = new DefaultParser();
//            HelpFormatter formatter = new HelpFormatter();
//            CommandLine cmd;
//
//            try {
//                cmd = parser.parse(options, args);
//            } catch (ParseException e) {
//                System.out.println(e.getMessage());
//                formatter.printHelp("utility-name", options);
//
//                System.exit(1);
//                return;
//            }
//
//            startDate = cmd.getOptionValue("startDate").trim().replace(".", " ");
//            dur = cmd.getOptionValue("duration").trim();
//            thers = (int) Integer.valueOf(cmd.getOptionValue("threshold").trim());
//
//            System.out.println("\n" + "Start date = " + startDate + "\n");
//            System.out.println("\n" + dur + "\n");
//            System.out.println("\n" + thers + "\n");
//        }

        //        List<IpAddress> ipAddresses = DataOrganizationHelper
//                .getIpAddressesFromAllRecordsWithDurationAndThresHold(allRecords, startDate,
//                        dur, thers);

        List<LogEntity> allRecords = FileManager.readFileByLines("Log.txt");

        List<IpAddress> ipAddresses = DataOrganizationHelper
                .getIpAddressesFromAllRecordsWithDurationAndThresHold(allRecords, "2017-10-02 17:14:55",
                        "daily", 100);

        // save the ip addresses and the http messages to the MySQL database
        ipAddressService.saveAllIpAddressses(ipAddresses);

        exit(0);
    }
}

