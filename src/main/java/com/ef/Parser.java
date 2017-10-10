package com.ef;

import com.ef.util.FileManager;
import com.ef.entity.LogEntity;
import com.ef.util.LogParser;
import org.apache.commons.cli.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.lang.System.exit;

@EnableTransactionManagement
//@ComponentScan({"com.ef"})
@EntityScan("com.ef.entity")
@EnableJpaRepositories("com.ef.repository")
@SpringBootApplication(scanBasePackages = {"com.ef"}, exclude = JpaRepositoriesAutoConfiguration.class)
public class Parser implements CommandLineRunner {

    public Parser() {
    }

    public static void main(String[] args) throws Exception {

        SpringApplication application = new SpringApplication(Parser.class);
        application.run(args);
    }

    @Override
    public void run(String... args) throws Exception {

//        java -cp "parser.jar" com.ef.Parser --startDate=2000-10-21.21:55:36 --duration=hourly --threshold=100

        Options options = new Options();

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

        String startDate = cmd.getOptionValue("startDate");
        String dur = cmd.getOptionValue("duration");
        String thers = cmd.getOptionValue("threshold");

        System.out.println(startDate);
        System.out.println(dur);
        System.out.println(thers);


        exit(0);
    }
}

