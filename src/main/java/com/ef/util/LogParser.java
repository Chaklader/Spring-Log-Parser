package com.ef.util;

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

    private final static LogEntryValidator valid = new LogEntryValidator();

    private static final SimpleDateFormat formatedDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);

    public LogParser() {
    }

    public static Date parseDate2(String dateStr) {
        ParsePosition pp = new ParsePosition(0);
        return formatedDate.parse(dateStr, pp);
    }

    /*
    * parse the log entries text lines
    * */
    public static LogEntity parseLogEntryByLine(String log) {

        Pattern pattern = Pattern.compile(Pattern.quote("|"));
        String[] data = pattern.split(log);

        Date date = parseDate2(data[0]);
        String IpAddress = valid.iPAddressValidate(data[1]) ? data[1] : null;
        Integer code = Integer.valueOf(data[3]);

        System.out.println(code);
        System.out.println(String.valueOf(code).trim());
        System.out.println("Status is = " + valid.codeValidator(String.valueOf(code).trim()));

        return new LogEntity(IpAddress, date, code);
    }
}
