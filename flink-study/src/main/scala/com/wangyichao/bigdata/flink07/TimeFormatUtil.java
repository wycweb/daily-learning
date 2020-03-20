package com.wangyichao.bigdata.flink07;


import org.apache.commons.lang3.time.FastDateFormat;

import java.text.ParseException;
import java.util.Locale;
import java.util.TimeZone;

public class TimeFormatUtil {

    public static String UTCToDateTime(String UTCStr) {

        if (UTCStr == null) {
            return null;
        }

        FastDateFormat inputFormat = FastDateFormat.getInstance("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", TimeZone.getTimeZone("UTC"));
        FastDateFormat outputFormat = FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss", Locale.getDefault());

        Long timeStamp = null;
        try {
            timeStamp = inputFormat.parse(UTCStr).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return outputFormat.format(timeStamp);
    }


    public static Long getTimeCha(String UTCStr1, String UTCStr2) {

        FastDateFormat inputFormat = FastDateFormat.getInstance("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", TimeZone.getTimeZone("UTC"));

        Long result = null;
        try {
            Long timeStamp1 = inputFormat.parse(UTCStr1).getTime();
            Long timeStamp2 = inputFormat.parse(UTCStr2).getTime();

            result = timeStamp1 - timeStamp2;

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return result;
    }
}
