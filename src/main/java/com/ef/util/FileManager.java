package com.ef.util;

import com.ef.entity.LogEntity;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Chaklader on Oct, 2017
 */
public class FileManager {

    public static List<LogEntity> logEntities;

    public FileManager() {
        logEntities = new ArrayList<>();
    }

    public static List<LogEntity> readFileByLines(String fileName) {

        List<String> lines = new ArrayList<>();

        try (BufferedReader br = Files.newBufferedReader(Paths.get(fileName))) {

            //br returns as stream and convert it into a List
            lines = br.lines().collect(Collectors.toList());

        } catch (IOException e) {
            e.printStackTrace();
        }

        lines.forEach(line -> {
            logEntities.add(LogParser.parseLogEntryByLine(line));
        });

        return logEntities;
    }
}
