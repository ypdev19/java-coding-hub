package com.utilities.demo;

import com.utilities.demo.util.DateUtils;
import java.time.format.DateTimeFormatter;

/**
 * Main class - project execution
 *
 * @author ypdev19
 */
public class AppRunner {

    private static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss z";
    private static final String SHORT_DATE_FORMAT = "yyyy-MM-dd";

    public static void main(String[] args) {
        // Get current date
        System.out.println(DateUtils.getCurrentDate(DateTimeFormatter.ofPattern(SHORT_DATE_FORMAT)));

        // Get future date
        String futureDate = DateUtils.getFutureDateTime(5, 9, 30, DateTimeFormatter.ofPattern(DATE_TIME_FORMAT));
        System.out.println(futureDate);
    }

}
