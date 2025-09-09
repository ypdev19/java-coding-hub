package com.utilities.demo;

import com.utilities.demo.util.DateUtils;
import org.junit.jupiter.api.Test;

import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

public class DateUtilsTest {

    @Test
    public void testGetCurrentDate_NotNull() {
        String currentDate = DateUtils.getCurrentDate(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        assertNotNull(currentDate);
    }

    @Test
    public void testGetCurrentDate_Format() {
        String currentDate = DateUtils.getCurrentDate(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        assertTrue(currentDate.matches("\\d{4}-\\d{2}-\\d{2}"));
    }

    @Test
    public void testGetFutureDateTime_NotNull() {
        String futureDateTime = DateUtils.getFutureDateTime(2, 9, 0, DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm z"));
        assertNotNull(futureDateTime);
    }

    @Test
    public void testGetFutureDateTime_Format_ISO() {
        String futureDateTime = DateUtils.getFutureDateTime(
                2, 9, 0,
                DateTimeFormatter.ISO_OFFSET_DATE_TIME
        );

        assertTrue(futureDateTime.matches("\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}(Z|[+-]\\d{2}:\\d{2})"));
    }

}
