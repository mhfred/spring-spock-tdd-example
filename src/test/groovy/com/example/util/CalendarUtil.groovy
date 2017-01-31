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
        def time = Calendar.getInstance()
        time.set(Calendar.HOUR_OF_DAY, 11)
        time.set(Calendar.MINUTE, 0)
        time.set(Calendar.SECOND, 0)
        time.set(Calendar.MILLISECOND, 0)

        return time
    }
}