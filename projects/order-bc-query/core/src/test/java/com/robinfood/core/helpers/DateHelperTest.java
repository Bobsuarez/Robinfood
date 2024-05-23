package com.robinfood.core.helpers;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DateHelperTest {

    private DateHelper dateHelper = new DateHelper(LocalDateTime.parse("2018-02-27T18:14:01.184"));

    @Test
    public void test_Get_Date_String_When_Pass_Complete_Datetime() {
        assertEquals("2018-02-27", dateHelper.getDateString());
    }

    @Test
    public void test_Get_Time_String_When_Pass_Complete_Datetime() {
        assertEquals("18:14:01", dateHelper.getTimeString());
    }

    @Test
    public void test_Get_DateTime_String_When_Pass_Complete_Datetime() {
        assertEquals("2018-02-27 18:14:01", dateHelper.getDateTimeString());
    }

    @Test
    public void test_Get_Date_Object_When_Pass_Complete_Datetime() {
        final String dateExpected = "2018-02-27";
        assertEquals(dateExpected, DateHelper.getLocalDate(dateExpected).toString());
    }

    @Test
    public void test_Get_Time_Object_When_Pass_Complete_Time() {
        final String timeExpected = "18:14:01";
        assertEquals(timeExpected, DateHelper.getLocalTime(timeExpected).toString());
    }

    @Test
    public void test_Get_DateTime_Object_When_Pass_Complete_Datetime() {
        final String dateTime = "2018-02-27 18:14:01";
        final String dateTimeExpected = "2018-02-27T18:14:01";
        assertEquals(dateTimeExpected, DateHelper.getLocalDateTime(dateTime).toString());
    }
}
