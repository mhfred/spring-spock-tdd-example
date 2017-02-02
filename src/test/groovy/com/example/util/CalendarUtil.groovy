package com.example.util
/**
 * Created by deadlock on 30/1/17.
 */
class CalendarUtil {
    static Calendar time1100 = create(11, 0)
    static Calendar time1230 = create(12, 30)
    static Calendar time1200 = create(12, 0)
    static Calendar time1300 = create(13, 0)

    static Calendar create(int hour, int minute) {
        def time = Calendar.getInstance(TimeZone.getDefault())
        time.set(2017, 1, 31)
        time.set(Calendar.HOUR_OF_DAY, hour)
        time.set(Calendar.MINUTE, minute)
        time.set(Calendar.SECOND, 0)
        time.set(Calendar.MILLISECOND, 0)

        return time
    }
}