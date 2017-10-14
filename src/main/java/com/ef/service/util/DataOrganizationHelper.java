package com.ef.service.util;

import com.ef.entity.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.httpclient.HttpStatus;
import org.springframework.data.mapping.Association;

/**
 * Created by Chaklader on Oct, 2017
 */
public class DataOrganizationHelper {

    /*
    * Get a list of the Log entity and group them by the Ip address
    * */
    public HashMap<String, List<LogEntity>> groupRecordsByIpAddress(List<LogEntity> records) {

        HashMap<String, List<LogEntity>> stringListHashMap = (HashMap<String, List<LogEntity>>) records.stream()
                .collect(Collectors.groupingBy(LogEntity::getIp));

        return stringListHashMap;
    }

    /*
    * get the map grouped by IP addresses with
    * the records, start date and the duration
    * */
    public static HashMap<String, List<LogEntity>> groupRecordsByIpAddress(List<LogEntity> records, String startDate, String duration) {

        if (records == null || records.isEmpty() ||
                startDate == null || startDate.isEmpty() ||
                duration == null || duration.isEmpty()) {

            return new HashMap<String, List<LogEntity>>();
        }

        Stream<LogEntity> logEntityStream = getRecordsBetweenDur(records, startDate, duration).filter(Objects::nonNull);

        if (logEntityStream == null) {
            return new HashMap<String, List<LogEntity>>();
        }

        // here is the bug
        HashMap<String, List<LogEntity>> stringListHashMap = (HashMap<String, List<LogEntity>>) logEntityStream
                .collect(Collectors.groupingBy(LogEntity::getIp));

        return stringListHashMap;
    }

    /*
    * Get the map of Ip address and the List of the Log entries
    * from the list of all log entries, start, duration and occurance
    * threashold
    * */
    public static HashMap<String, List<LogEntity>> groupRecordsByIpAddress(List<LogEntity> records, String startDate, String duration, int threshhold) {

        if (records == null || records.isEmpty() ||
                startDate == null || startDate.isEmpty() ||
                duration == null || duration.isEmpty() ||
                threshhold <= 0) {

            return new HashMap<String, List<LogEntity>>();
        }

        HashMap<String, List<LogEntity>> groupRecordsMap = groupRecordsByIpAddress(records, startDate, duration);

        if (groupRecordsMap == null || groupRecordsMap.isEmpty()) {
            return new HashMap<String, List<LogEntity>>();
        }

        HashMap<String, List<LogEntity>> stringListHashMap = (HashMap<String, List<LogEntity>>) groupRecordsMap
                .entrySet()
                .stream()
                .filter(a -> a.getValue().size() > threshhold)
                .collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue()));

        return stringListHashMap;
    }

    /*
    * Get the ending time in local date format from the start and the duration
    * */
    private static LocalDateTime computeEndDate(String startDate, String duration) {

        if (duration == "hourly") {
            return DateAndTimeManager.convertDateToLocalTime(startDate).plusHours(1);
        } else if (duration == "daily") {
            return DateAndTimeManager.convertDateToLocalTime(startDate).plusDays(1);
        }
        return null;
    }

    /*
    * get the list of the log entries between start
    * and the end time from all the log entries
    * */
    public static Stream<LogEntity> getRecordsBetweenDur(List<LogEntity> records, String startDate, String duration) {

        Stream<LogEntity> logEntityStream = records.stream().filter(
                record -> ((DateAndTimeManager.convertDateToLocalTime(record.getTime()).isAfter(DateAndTimeManager.convertDateToLocalTime(startDate))
                        && DateAndTimeManager.convertDateToLocalTime(record.getTime()).isBefore(computeEndDate(startDate, duration))
                )));

        return logEntityStream;
    }

    /*
    * retrun the customized comment from the http server code
    * */
    private static String returnMessageFromHttpServerCode(int serverBlockCode) {
        return String.valueOf(serverBlockCode) + "_" + HttpStatus.getStatusText(serverBlockCode);
    }

    /*
    * Get the list of the IpAddresses with start time, duration, threshold from all the records
    * all the Ip address has associated with the list of corresponding http messages
    * */
    public static List<IpAddress> getIpAddressesFromAllRecordsWithDurationAndThresHold(List<LogEntity> records,
                                                                                       String startDate, String duration, int threshhold) {
        if (records == null || records.isEmpty() ||
                startDate == null || startDate.isEmpty() ||
                duration == null || duration.isEmpty() ||
                threshhold <= 0) {

            return new ArrayList<IpAddress>();
        }

        HashMap<String, List<LogEntity>> groupRecordsByIpAddressMap = groupRecordsByIpAddress(records, startDate, duration, threshhold);

        if (groupRecordsByIpAddressMap == null || groupRecordsByIpAddressMap.isEmpty()) {
            return new ArrayList<IpAddress>();
        }

        ArrayList<IpAddress> ipAddresses = new ArrayList<IpAddress>();

        groupRecordsByIpAddressMap.entrySet().stream().forEach(ipGroupedRecord -> {

            try {

                final List<HttpInfoMessage> httpInfoMessages = new ArrayList<>();
                String ipAddressString = ipGroupedRecord.getKey();

                ipGroupedRecord.getValue().stream().distinct()
                        .collect(Collectors.groupingBy(LogEntity::getCode))
                        .entrySet().stream().forEach(httpStatusCodeGroupedRecord -> {

                    String httpMessage = returnMessageFromHttpServerCode((int) Integer.valueOf(httpStatusCodeGroupedRecord.getKey()));
                    HttpInfoMessage httpInfoMessage = new HttpInfoMessage(Long.valueOf(httpStatusCodeGroupedRecord.getKey()), httpMessage);
                    httpInfoMessages.add(httpInfoMessage);
                });

                IpAddress ipAddressEntity = new IpAddress(ipAddressString, httpInfoMessages);
                ipAddresses.add(ipAddressEntity);

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        return ipAddresses;
    }


//    public static List<Ip> getIpFromAllRecordsWithDurationAndThresHold(List<LogEntity> records,
//                                                                       String startDate,
//                                                                       String duration,
//                                                                       int threshhold) {
//
//        HashMap<String, List<LogEntity>> groupRecordsByIpAddressMap =
//                groupRecordsByIpAddress(records, startDate, duration, threshhold);
//
//        List<Ip> ips = new ArrayList<>();
//
//        groupRecordsByIpAddressMap.entrySet().stream().forEach(
//                stringListEntry -> {
//
//                    try {
//
//                        List<Message> messages = new ArrayList<>();
//                        List<IMAssociation> imAssociations = new ArrayList<>();
//                        String address = stringListEntry.getKey();
//
//                        stringListEntry.getValue().stream().distinct().
//                                collect(Collectors.groupingBy(LogEntity::getCode))
//                                .entrySet().stream().forEach(
//
//                                integerListEntry -> {
//
//                                    String message = returnMessageFromHttpServerCode
//                                            ((int) Integer.valueOf(integerListEntry.getKey()));
//
//                                    IMAssociation imAssociation = new IMAssociation(Long.valueOf(integerListEntry.getKey()),
//                                            message);
//                                    imAssociations.add(imAssociation);
//                                }
//                        );
//
//                        Ip ip = new Ip(address, imAssociations);
//                        ips.add(ip);
//
//                    } catch (Exception ex) {
//                        ex.printStackTrace();
//                    }
//                }
//        );
//
//        return ips;
//    }
}
