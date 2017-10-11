package com.ef.service.util;

import com.ef.entity.LogEntity;
import com.ef.validator.LogEntryValidator;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Pattern;

/**
 * Created by Chaklader on Oct, 2017
 */
public class LogParser {

    private static SimpleDateFormat formatedDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);

    public static Date parseDate(String dateStr) {
        ParsePosition pp = new ParsePosition(0);
        return formatedDate.parse(dateStr, pp);
    }

    /*
    * parse the log entries from String
    * ,acquire the values of date, ip address and http status code
    * and the return as the log entry
    * */
    public static LogEntity parseLogEntryByLine(String line) {

        Pattern pattern = Pattern.compile(Pattern.quote("|"));
        String[] data = pattern.split(line);

        Date date = parseDate(data[0]);
        String IpAddress = LogEntryValidator.iPAddressValidate(data[1]) ? data[1] : null;
        Integer code = Integer.valueOf(data[3]);

        return new LogEntity(IpAddress, date, code);
    }
}
