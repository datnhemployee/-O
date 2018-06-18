/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tools;

import java.io.IOException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 *
 * @author DAT
 */
public class TimeTool {

    public static java.util.Date getDate(java.sql.Date date, Time time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeZone(TimeZone.getTimeZone(ZoneId.of("Asia/Ho_Chi_Minh")));
        calendar.setTime(date);
        calendar.add(Calendar.MILLISECOND, (int) time.getTime());
        return calendar.getTime();
    }

    public static Period getPeriodOfTwo(Date d1, Date d2) {
        Calendar c = Calendar.getInstance();
        c.setTime(d1);
        LocalDate d3 = LocalDate.of(
                c.get(Calendar.YEAR),
                c.get(Calendar.MONTH),
                c.get(Calendar.DAY_OF_MONTH));

        c.setTime(d2);
        LocalDate d4 = LocalDate.of(
                c.get(Calendar.YEAR),
                c.get(Calendar.MONTH),
                c.get(Calendar.DAY_OF_MONTH));
        return Period.between(d3, d4);
    }
    
    public static Period getPeriodFromNow(Date d) {
        Calendar c = Calendar.getInstance();
        c.setTime(d);
        LocalDate d1 = LocalDate.of(
                c.get(Calendar.YEAR),
                c.get(Calendar.MONTH),
                c.get(Calendar.DAY_OF_MONTH));

        c.setTimeInMillis(System.currentTimeMillis());
        LocalDate d2 = LocalDate.of(
                c.get(Calendar.YEAR),
                c.get(Calendar.MONTH),
                c.get(Calendar.DAY_OF_MONTH));
        return Period.between(d2, d1);
    }

    public static Date getNearestDate(Date d1, Date d2) {
        if (d1 == null) {
            return d2;
        }
        if (d2 == null) {
            return d1;
        }

        Date today = new Date(System.currentTimeMillis());

        if (d1.before(today)) {
            if (d2.before(today)) {
                return null;
            }
            return d2;
        }
        if (d2.before(today)) {
            return d1;
        }
        if (d1.before(d2)) {
            return d1;
        }
        return d2;
    }

    public static Date getDateLongestPeriod(Date d1, Date d2) {
        if (getNearestDate(d1, d2).compareTo(d2) == 0) {
            return d1;
        }
        return d2;
    }

    public static boolean exportToFile_LastestLogIn(Date d) throws Exception {
        String path
                = ReadTxt.currentFolderPath + "\\LastestDate.txt";
        ReadTxt.Instance.createNewFile(path);

        Calendar c = Calendar.getInstance();
        c.setTime(d);
        ReadTxt.Instance.insert(c.get(Calendar.DAY_OF_MONTH), path);
        ReadTxt.Instance.insert(c.get(Calendar.MONTH), path);
        ReadTxt.Instance.insert(c.get(Calendar.YEAR), path);
        return true;
    }

    public static Date getLastestDateLogin() throws Exception {
        String path
                = ReadTxt.currentFolderPath + "\\LastestDate.txt";
        if (!ReadTxt.Instance.isFileExist(path)) {
            ReadTxt.Instance.createNewFile(path);
            return new Date(System.currentTimeMillis());
        }
        ArrayList<Integer> rs
                = ReadTxt.Instance.toIntArray(
                        ReadTxt.Instance.selectAll(path));
        Calendar c = Calendar.getInstance();
        c.set(Calendar.DAY_OF_MONTH, 0);
        c.set(Calendar.MONTH, 1);
        c.set(Calendar.YEAR, 2);
        return new Date(c.getTimeInMillis());
    }
}
