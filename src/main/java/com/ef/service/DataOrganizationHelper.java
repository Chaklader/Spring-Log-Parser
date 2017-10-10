package com.ef.service;

import com.ef.entity.IpAddress;
import com.ef.entity.LogEntity;
import com.ef.entity.HttpInfoMessage;
import com.ef.validator.LogEntryValidator;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


import org.springframework.http.*;

/**
 * Created by Chaklader on Oct, 2017
 */
public class DataOrganizationHelper {

    public HashMap<String, List<LogEntity>> groupRecordsByIpAddress(List<LogEntity> records) {
        return (HashMap<String, List<LogEntity>>) records.stream()
                .collect(Collectors.groupingBy(LogEntity::getIp));
    }

    public HashMap<String, List<LogEntity>> groupRecordsByIpAddress(List<LogEntity> records, String startDate, String duration) {
        return (HashMap<String, List<LogEntity>>) getRecordsBetweenDur(records, startDate, duration)
                .collect(Collectors.groupingBy(LogEntity::getIp));
    }

    /*
    * Get the map as Ip address as the key and the number of
    * occurance as the value within certain duration
    * */
    public HashMap<String, Long> countRecordsByIpAddress(List<LogEntity> records, String startDate, String duration) {
        return (HashMap<String, Long>) getRecordsBetweenDur(records, startDate, duration)
                .collect(Collectors.groupingBy(LogEntity::getIp, Collectors.counting()));
    }

    /*
    * Get the map of Ip address and the List of the Log entries
    * from the list of all log entries, start, duration and occurance
    * threashold
    * */
    public HashMap<String, List<LogEntity>> groupRecordsByIpAddress(List<LogEntity> records, String startDate, String duration, int threshhold) {

        return (HashMap<String, List<LogEntity>>) groupRecordsByIpAddress(records, startDate, duration)
                .entrySet()
                .stream()
                .filter(a -> a.getValue().size() > threshhold)
                .collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue()));
    }

    /*
    * Get the date in the local time
    * */
    private LocalDateTime convertDateToLocalTime(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    /*
    * Get the date in the local time from the date String
    * */
    private LocalDateTime convertDateToLocalTime(String date) {
        return LocalDateTime.of(LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                LocalDateTime.now().toLocalTime()
        );
    }

    /*
    * Get the ending time in local date format from the start and the duration
    * */
    private LocalDateTime computeEndDate(String startDate, String duration) {
        if (duration == "hourly") {
            return convertDateToLocalTime(startDate).plusHours(1);
        } else if (duration == "daily") {
            return convertDateToLocalTime(startDate).plusDays(1);
        }
        return null;
    }

    /*
    * get the list of the log entries between start
    * and the end time from all the log entries
    * */
    public Stream<LogEntity> getRecordsBetweenDur(List<LogEntity> records, String startDate, String duration) {

        return records.stream().filter(
                record -> ((convertDateToLocalTime(record.getTime()).isAfter(convertDateToLocalTime(startDate))
                        && convertDateToLocalTime(record.getTime()).isBefore(computeEndDate(startDate, duration))
                ))
        );
    }

    /*
    * retrun customized comment from the http server code
    * */
    private String returnMessageFromHttpServerCode(int serverBlockCode) {

        if (LogEntryValidator.codeValidator(String.valueOf(serverBlockCode))) {
            return String.valueOf(serverBlockCode) + "_" + HttpStatus.valueOf(serverBlockCode).name();
        }

        return null;
    }

    /*
    * print the log entries to the console from the start and the duration
    * from all the log entries parsed from the file
    * */
    public void printAll(List<LogEntity> records, String startDate, String duration) {
        getRecordsBetweenDur(records, startDate, duration).forEachOrdered(System.out::println);
    }

    public void mapIpAddressAgainstComment(List<LogEntity> records, String startDate, String duration, int threshhold) {

        List<HttpInfoMessage> comments = new ArrayList<>();

        groupRecordsByIpAddress(records, startDate, duration, threshhold)
                .entrySet()
                .stream().forEach(hMapValue -> {


            //            Session session = HibernateUtil.getSessionFactory().openSession();
            Session session = null;
            Transaction transaction = null;

            try {

                transaction = session.beginTransaction();
                IpAddress address = new IpAddress(hMapValue.getKey());

                ArrayList<IpAddress> addresses = new ArrayList<IpAddress>();
                addresses.add(address);

                hMapValue.getValue().stream().distinct()
                        .collect(Collectors.groupingBy(LogEntity::getCode))
                        .entrySet().stream().forEach(leSet -> {

                    HttpInfoMessage comment = new HttpInfoMessage(Long.valueOf(leSet.getKey()), returnMessageFromHttpServerCode(leSet.getKey()), addresses);
//                    HttpInfoMessage comment = new HttpInfoMessage(returnMessageFromHttpServerCode(leSet.getKey()));
                    comments.add(comment);
                    session.saveOrUpdate(comment);
                });

                transaction.commit();
            } catch (Exception ex) {
                transaction.rollback();
                ex.printStackTrace();
            } finally {
                session.close();
            }
        });

        System.out.println("_______________________________________");
        System.out.println("BLOCKED IP vs COMMENT AGGREGATE LOGS ");
        System.out.println("________________________________________");
        System.out.println("  IP      :      COMMENT  ");
        System.out.println("________________________________________");

        comments.stream().forEachOrdered(c -> System.out.println(
                c.getIpAddresses().get(0).getAddress() + " : " + c.getStatus()
        ));
        System.out.println("________________________________________");
    }
}
