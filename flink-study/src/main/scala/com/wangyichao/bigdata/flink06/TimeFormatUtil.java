package com.wangyichao.bigdata.flink06;


import org.apache.commons.lang3.time.FastDateFormat;

import java.text.ParseException;
import java.util.Locale;
import java.util.TimeZone;

public class TimeFormatUtil {

    public static String UTCToDateTime(String UTCStr) {
        FastDateFormat outputFormat = FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss", Locale.getDefault());

        if (UTCStr == null) {
            return null;
        }

        Long timestamp = getUTCTimestamp(UTCStr);

        return outputFormat.format(timestamp);
    }

    public static Long getUTCTimestamp(String UTCStr) {
        if (UTCStr == null) {
            return null;
        }

        FastDateFormat inputFormat = FastDateFormat.getInstance("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", TimeZone.getTimeZone("UTC"));

        Long timeStamp = null;
        try {
            timeStamp = inputFormat.parse(UTCStr).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return timeStamp;
    }

    public static String timeStampToUTC(Long timestamp) {
        FastDateFormat outputFormat = FastDateFormat.getInstance("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", TimeZone.getTimeZone("UTC"));

        return outputFormat.format(timestamp);
    }

    public static void main(String[] args) {
//        System.out.println(timeStampToUTC(1584076176124));
    }
}
