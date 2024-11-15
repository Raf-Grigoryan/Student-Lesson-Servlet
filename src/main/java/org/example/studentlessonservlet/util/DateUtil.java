package org.example.studentlessonservlet.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    private final static SimpleDateFormat sqlTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final SimpleDateFormat dateTimeLocalFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");

    public static Date sqlStringTimeToDate(String sql) throws ParseException {
        return sqlTimeFormat.parse(sql);
    }

    public static String dateToSqlTimeString(Date date) {
        return sqlTimeFormat.format(date);
    }

    public static Date webTimeStringToDate(String webTime) throws ParseException {
        try {
            return dateTimeLocalFormat.parse(webTime);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String dateToWebTimeString(Date date) {
        return dateTimeLocalFormat.format(date);
    }
}
