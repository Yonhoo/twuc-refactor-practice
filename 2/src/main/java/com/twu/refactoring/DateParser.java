package com.twu.refactoring;

import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.TimeZone;

public class DateParser {
    public static final String yearStringException = "Year string is less than 4 characters";
    public static final String yearNumberException = "Year is not an integer";
    public static final String yearRangeException = "Year cannot be less than 2000 or more than 2012";
    public static final String monthStringException = "Month string is less than 2 characters";
    public static final String monthNumberException = "Month is not an integer";
    public static final String monthRangeException = "Month cannot be less than 1 or more than 12";
    public static final String dateStringException = "Date string is less than 2 characters";
    public static final String dateNumberException = "Date is not an integer";
    public static final String dateRangeException = "Date cannot be less than 1 or more than 31";
    public static final String hourStringException = "Hour string is less than 2 characters";
    public static final String hourNumberException = "Hour is not an integer";
    public static final String hourRangeException = "Hour cannot be less than 0 or more than 23";
    public static final String minuteStringException = "Minute string is less than 2 characters";
    public static final String minuteNumberException = "Minute is not an integer";
    public static final String minuteRangeException = "Minute cannot be less than 0 or more than 59";
    private final String dateAndTimeString;
    private static final HashMap<String, TimeZone> KNOWN_TIME_ZONES = new HashMap<String, TimeZone>();

    static {
        KNOWN_TIME_ZONES.put("UTC", TimeZone.getTimeZone("UTC"));
    }

    /**
     * Takes a date in ISO 8601 format and returns a date
     *
     * @param dateAndTimeString - should be in format ISO 8601 format
     *                          examples -
     *                          2012-06-17 is 17th June 2012 - 00:00 in UTC TimeZone
     *                          2012-06-17TZ is 17th June 2012 - 00:00 in UTC TimeZone
     *                          2012-06-17T15:00Z is 17th June 2012 - 15:00 in UTC TimeZone
     */
    public DateParser(String dateAndTimeString) {
        this.dateAndTimeString = dateAndTimeString;
    }

    public Date parse() {
        int year, month, date, hour, minute;

        year = getDatePart(0,4,yearStringException,yearNumberException,
                yearRangeException,2000,2012);

        month = getDatePart(5,7,monthStringException,monthNumberException,
                monthRangeException,1,12);

        date = getDatePart(8,10,dateStringException,dateNumberException,
                dateRangeException,1,31);


        if (dateAndTimeString.charAt(11) == 'Z') {
            hour = 0;
            minute = 0;
        } else {

            hour = getDatePart(11,13,hourStringException,hourNumberException,
                    hourRangeException,0,23);

            minute = getDatePart(14,16,minuteStringException,minuteNumberException,
                    minuteRangeException,0,59);

        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeZone(TimeZone.getTimeZone("UTC"));
        calendar.set(year, month - 1, date, hour, minute, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    private int getDatePart(int lo,int hi,
                            String stringException,String numberException,
                            String rangeException,
                            int min,int max) {
        int part;
        try {
            String yearString = dateAndTimeString.substring(lo, hi);
            part = Integer.parseInt(yearString);
        } catch (StringIndexOutOfBoundsException e) {
            throw new IllegalArgumentException(stringException);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(numberException);
        }

        if (part < min || part > max)
            throw new IllegalArgumentException(rangeException);

        return part;
    }
}
