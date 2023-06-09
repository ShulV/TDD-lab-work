package com.example.mytddproject.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class Utils {
    //получать дату в виде форматированной строки
    public static String getStringFormatDate(Date date) {
        DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        return sdf.format(date);
    }

    //парсить строку с запятой в double (например, 29,33)
    public static double parseFormatDouble(String str) {
        str = str.replace(',', '.');
        return Double.parseDouble(str);
    }

}
