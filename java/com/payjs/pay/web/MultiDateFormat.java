package com.payjs.pay.web;

import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @Author chengtianqi
 * @create 2020/6/14 13:59
 */
public class MultiDateFormat extends SimpleDateFormat {

    private List<SimpleDateFormat> dateFormats = new ArrayList<>(0);

    /**
     * Instantiates a new Multi date format.
     *
     * @param formats the formats
     */
    public MultiDateFormat(String... formats) {
        super();
        if (formats == null || formats.length == 0) {
            dateFormats = Arrays.asList(
                    new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"),
                    new SimpleDateFormat("yyyy-MM-dd HH:mm"),
                    new SimpleDateFormat("yyyy-MM-dd HH"),
                    new SimpleDateFormat("yyyy-MM-dd")
            );
        } else {
            for (String format : formats) {
                dateFormats.add(new SimpleDateFormat(format));
            }
        }
    }

    @Override
    public StringBuffer format(Date date, StringBuffer toAppendTo, FieldPosition fieldPosition) {
        for (final DateFormat dateFormat : dateFormats) {
            try {
                String res;
                if ((res = dateFormat.format(date)) != null) {
                    toAppendTo.append(res);
                    return toAppendTo;
                }
            } catch (Exception e) {

            }
        }
        return toAppendTo;
    }

    @Override
    public Date parse(String source, ParsePosition pos) {
        Date res;
        for (final DateFormat dateFormat : dateFormats) {
            try {
                if ((res = dateFormat.parse(source, pos)) != null) {
                    return res;
                }
            } catch (Exception e) {

            }
        }
        return null;
    }

}
