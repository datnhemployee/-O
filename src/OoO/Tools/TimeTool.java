/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OoO.Tools;

import java.sql.Time;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 *
 * @author DAT
 */
public class TimeTool {
    public static java.util.Date getDate(java.sql.Date date, Time time) {
        Calendar calendar=Calendar.getInstance();
        calendar.setTimeZone(TimeZone.getTimeZone(ZoneId.of("Asia/Ho_Chi_Minh")));
        calendar.setTime(date);
        calendar.add(Calendar.MILLISECOND, (int) time.getTime());
        return calendar.getTime();
    }
}
