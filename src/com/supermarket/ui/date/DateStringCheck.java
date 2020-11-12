package com.supermarket.ui.date;

import com.supermarket.ui.jframeutil.CheckOfNull;

import java.text.SimpleDateFormat;

@SuppressWarnings("all")
public class DateStringCheck {
    public static boolean isDateString(String datevalue) {
        if (new CheckOfNull().check(datevalue)) {
            return false;
        }
        try {
            SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date dd = fmt.parse(datevalue);
            if (datevalue.equals(fmt.format(dd))) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }
}
