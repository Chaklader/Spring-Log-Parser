package com.ef;

import com.ef.entity.IpAddress;
import com.ef.service.util.DataOrganizationHelper;
import com.ef.service.def.HttpInfoMessageService;
import com.ef.service.def.IpAddressService;
import com.ef.service.def.LogEntityService;
import com.ef.service.util.FileManager;
import com.ef.entity.LogEntity;
import org.apache.commons.cli.*;
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

    @Autowired
    private HttpInfoMessageService httpInfoMessageService;

    @Autowired
    private LogEntityService logEntityService;

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

    @Override
    public void run(String... args) throws Exception {

        /*Options options = new Options();

        Option start = new Option("startDate", "startDate", true, "Start date");
        start.setRequired(true);
        options.addOption(start);

        Option duration = new Option("duration", "duration", true, "duration");
        duration.setRequired(true);
        options.addOption(duration);

        Option threshold = new Option("threshold", "threshold", true, "threshold");
        threshold.setRequired(true);
        options.addOption(threshold);

        CommandLineParser parser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();
        CommandLine cmd;

        try {
            cmd = parser.parse(options, args);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
            formatter.printHelp("utility-name", options);

            System.exit(1);
            return;
        }

        String startDate = cmd.getOptionValue("startDate").trim().replace(".", " ");
        String dur = cmd.getOptionValue("duration").trim();
        int thers = (int) Integer.valueOf(cmd.getOptionValue("threshold").trim());*/


        List<LogEntity> allRecords = FileManager.readFileByLines("Log.txt");

        // save all the log entities to the MySQL database
        logEntityService.saveAllLogEntities(allRecords);

        List<IpAddress> ipAddresses = DataOrganizationHelper
                .getIpAddressesFromAllRecordsWithDurationAndThresHold(allRecords, "2017-10-02 17:14:55",
                        "daily", 100);

        printIpWithHttpMessagesOverThreshold(ipAddresses);

        // save the ip addresses and the http messages to the MySQL database
        ipAddressService.saveAllIpAddressses(ipAddresses);

        exit(0);
    }
}

