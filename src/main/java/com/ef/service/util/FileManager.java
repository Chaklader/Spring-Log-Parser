package com.ef.service.util;

import com.ef.entity.LogEntity;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Chaklader on Oct, 2017
 */
public class FileManager {

    public static List<LogEntity> logEntities = new ArrayList<>();

    /*
    * read the log file line by line and returns the
    * list of the Log entities
    * */
    public static List<LogEntity> readFileByLines(String fileName) {

        List<String> lines = new ArrayList<>();

        try (BufferedReader br = Files.newBufferedReader(Paths.get(fileName))) {

            //br returns as stream and convert it into a List
            lines = br.lines().collect(Collectors.toList());

        } catch (IOException e) {
            e.printStackTrace();
        }

        lines.forEach(line -> {

            LogEntity logEntity = LogParser.parseLogEntryByLine(line);
            logEntities.add(logEntity);
        });

        return logEntities;
    }
}
