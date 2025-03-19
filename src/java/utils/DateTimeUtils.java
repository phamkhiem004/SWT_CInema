/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateTimeUtils {

    private static final String TIMESTAMP_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private static final String DATE_FORMAT = "yyyy-MM-dd";

    /**
     * Converts a string to java.sql.Timestamp.
     *
     * @param dateString the date string to be converted
     * @return the Timestamp, or null if the conversion fails
     */
    public static Timestamp stringToTimestamp(String dateString) {
        java.util.Date parsedDate = stringToDate(dateString, TIMESTAMP_FORMAT);
        if (parsedDate != null) {
            return new Timestamp(parsedDate.getTime());
        }
        return null;
    }

    /**
     * Converts a string to java.sql.Date.
     *
     * @param dateString the date string to be converted
     * @return the Date, or null if the conversion fails
     */
    public static Date stringToDate(String dateString) {
        java.util.Date parsedDate = stringToDate(dateString, DATE_FORMAT);
        if (parsedDate != null) {
            return new Date(parsedDate.getTime());
        }
        return null;
    }

    /**
     * Converts a string to java.util.Date based on a specified format.
     *
     * @param dateString the date string to be converted
     * @param format the format to be used for parsing
     * @return the Date, or null if the conversion fails
     */
    private static java.util.Date stringToDate(String dateString, String format) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        try {
            return dateFormat.parse(dateString);
        } catch (ParseException e) {
            return null;
        }
    }
}