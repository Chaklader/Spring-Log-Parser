package com.ef.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Chaklader on Oct, 2017
 */
public class LogEntryValidator {

    private Pattern pattern;
    private Matcher matcher;

    private static final String IP_ADDRESS_PATTERN =
            "^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
                    "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
                    "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
                    "([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";

    private static final String STATUS_CODE_PATTERN = "[1-5][0-9]{2}";

    public LogEntryValidator() {

    }

    public boolean iPAddressValidate(final String ip) {
        return validator(ip, IP_ADDRESS_PATTERN);
    }

    public boolean codeValidator(final String text) {
        return validator(text, STATUS_CODE_PATTERN);
    }

    public boolean validator(final String text, String pat) {
        pattern = Pattern.compile(pat);
        matcher = pattern.matcher(text);
        return matcher.matches();
    }
}