package com.utility.vault.date;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * A Date Utility class.
 *
 * @author ypdev19
 */
public class DateUtils {

    /**
     * Returns the current date with a yyyy-MM-dd format
     * @return {@code String} current date
     */
    public static String getCurrentDate(final DateTimeFormatter formatter) {
        LocalDate date = LocalDate.now();

        return date.format(formatter);
    }

    /**
     * Returns a future UTC date and time based on the given number of days ahead, hour, and minute.
     *
     * @param daysAhead the number of days ahead from the current date.
     * @param hour the hour (0-23) to set in the future datetime.
     * @param minute the minute (0-59) to set in the future datetime.
     * @return a {@code String} representing the future UTC datetime.
     */
    public static String getFutureDateTime(final int daysAhead, final int hour, final int minute, final DateTimeFormatter formatter) {
        ZonedDateTime dateTime = ZonedDateTime.now(ZoneOffset.UTC)
                .plusDays(daysAhead)
                .withHour(hour).withMinute(minute)
                .withSecond(0)
                .withNano(0);

        return dateTime.format(formatter);
    }
}
