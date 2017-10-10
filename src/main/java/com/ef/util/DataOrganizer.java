package com.ef.util;

import com.ef.entity.IpAddress;
import com.ef.entity.LogEntity;
import com.ef.entity.HttpInfoMessage;
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
public class DataOrganizer {

    public void printAll(List<LogEntity> records, String startDate, String duration) {
        getRecordsBtnDuration(records, startDate, duration).forEachOrdered(System.out::println);//iterate over each and print to console
    }

    public HashMap<String, List<LogEntity>> groupRecordsByIpAddress(List<LogEntity> records) {
        return (HashMap<String, List<LogEntity>>) records.stream()
                .collect(Collectors.groupingBy(LogEntity::getIp));
    }

    public HashMap<String, List<LogEntity>> groupRecordsByIpAddress(List<LogEntity> records, String startDate, String duration) {
        return (HashMap<String, List<LogEntity>>) getRecordsBtnDuration(records, startDate, duration)
                .collect(Collectors.groupingBy(LogEntity::getIp));
    }

    public HashMap<String, Long> countRecordsByIpAddress(List<LogEntity> records, String startDate, String duration) {
        return (HashMap<String, Long>) getRecordsBtnDuration(records, startDate, duration)
                .collect(Collectors.groupingBy(LogEntity::getIp, Collectors.counting()));
    }

    public HashMap<String, List<LogEntity>> groupRecordsByIpAddress(List<LogEntity> records, String startDate, String duration, int threshhold) {

        return (HashMap<String, List<LogEntity>>) groupRecordsByIpAddress(records, startDate, duration)
                .entrySet()
                .stream()
                .filter(a -> a.getValue().size() > threshhold)
                .collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue()));
    }

    private LocalDateTime convertDateToLocalTime(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    private LocalDateTime convertDateToLocalTime(String date) {
        return LocalDateTime.of(LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd.HH:mm:ss")),
                LocalDateTime.now().toLocalTime()
        );
    }

    private LocalDateTime computeEndDate(String startDate, String duration) {
        if (duration == "hourly")
            return convertDateToLocalTime(startDate).plusHours(1);
        else if (duration == "daily")
            return convertDateToLocalTime(startDate).plusDays(1);
        return null;
    }

    public Stream<LogEntity> getRecordsBtnDuration(List<LogEntity> records, String startDate, String duration) {

        return records.stream().filter(
                r -> ((convertDateToLocalTime(r.getTime()).isAfter(convertDateToLocalTime(startDate))
                        && convertDateToLocalTime(r.getTime()).isBefore(computeEndDate(startDate, duration))
                ))
        );
    }

    private String returnComment(int serverBlockCode) {

        // validate the status code

        String message = String.valueOf(serverBlockCode) + " " + HttpStatus.valueOf(serverBlockCode).name();
        return message;
    }

    public void mapIpAddressAgainstComment(List<LogEntity> records, String startDate, String duration, int threshhold) {

        List<HttpInfoMessage> comments = new ArrayList<>();


        groupRecordsByIpAddress(records, startDate, duration, threshhold)
                .entrySet()
                .stream().forEach(e -> {
//            Session session = HibernateUtil.getSessionFactory().openSession();
            Session session = null;
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                IpAddress address = new IpAddress(e.getKey());

                ArrayList<IpAddress> addresses = new ArrayList<IpAddress>();
                addresses.add(address);
                e.getValue().stream().distinct()
                        .collect(Collectors.groupingBy(LogEntity::getCode))
                        .entrySet().stream().forEach(leSet -> {

                    HttpInfoMessage comment = new HttpInfoMessage(returnComment(leSet.getKey()), addresses);
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

        //        comments.stream().forEachOrdered(c->System.out.println(
//                c.getIpAddresses().get(0).getAddress()+" : "+c.getComment()
//        ));
        System.out.println("________________________________________");
    }
}
