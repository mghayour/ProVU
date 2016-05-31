/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package helper;

import java.io.Serializable;
import org.joda.time.DateTime;

public class PersianDateTime implements Serializable{

    protected int year = 0;
    protected int month = 0;
    protected int day = 0;
    protected int hour = 0;
    protected int minute = 0;
    protected int second = 0;
    
    protected long timestamp = 0;


    private PersianDateTime(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    private PersianDateTime(int year, int month, int day, int hour, int minute, int second) {
        this(year, month, day);
        this.hour = hour;
        this.minute = minute;
        this.second = second;
    }
    
    public PersianDateTime(DateTime ed) {
        int g_days_in_month[] = new int[]{31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        int j_days_in_month[] = new int[]{31, 31, 31, 31, 31, 31, 30, 30, 30, 30, 30, 29};
        int i;
        int gy = ed.getYear() - 1600;
        int gm = ed.getMonthOfYear() - 1;
        int gd = ed.getDayOfMonth() - 1;
        int g_day_no = 365 * gy + (int) ((gy + 3) / 4) - (int) ((gy + 99) / 100) + ((int) ((gy + 399) / 400));
        for (i = 0; i < gm; ++i) {
            g_day_no += g_days_in_month[i];
        }
        if (gm > 1 && ((gy % 4 == 0 && gy % 100 != 0) || (gy % 400 == 0))) {
            g_day_no++;
        }
        g_day_no += gd;
        this.day = g_day_no - 79;
        int j_np = (int) (this.day / 12053);
        this.day = this.day % 12053;
        this.year = 979 + 33 * j_np + 4 * (int) (this.day / 1461);
        this.day %= 1461;
        if (this.day >= 366) {
            this.year += (int) ((this.day - 1) / 365);
            this.day = (this.day - 1) % 365;
        }
        for (i = 0; i < 11 && this.day >= j_days_in_month[i]; ++i) {
            this.day -= j_days_in_month[i];
        }
        this.month = i + 1;
        this.day++;

        this.hour = ed.getHourOfDay();
        this.minute = ed.getMinuteOfHour();
        this.second = ed.getSecondOfMinute();
        this.timestamp = ed.getMillis();
    }

    public PersianDateTime (long timestamp) {
        this(new DateTime(timestamp));
    }

    public static PersianDateTime now() {
        return new PersianDateTime(DateTime.now());
    }

    
    // Getters
    public int getDay() {
        return day;
    }
    public int getHour() {
        return hour;
    }
    public int getMinute() {
        return minute;
    }
    public int getMonth() {
        return month;
    }
    public int getSecond() {
        return second;
    }
    public int getYear() {
        return year;
    }
    public long getTimeStamp() {
        return timestamp;
    }

    // Setters
    public void setDay(int day) {
        this.day = day;
    }
    public void setHour(int hour) {
        this.hour = hour;
    }
    public void setMinute(int minute) {
        this.minute = minute;
    }
    public void setMonth(int month) {
        this.month = month;
    }
    public void setSecond(int second) {
        this.second = second;
    }
    public void setYear(int year) {
        this.year = year;
    }
    public void setTimeStamp(long timestamp) {
        this.timestamp = timestamp;
    }

    
    @Override
    public String toString() {
        return this.toDateString() + " " + this.toTimeString();
    }

    public String toDateString() {
        return String.format("%d/%d/%d", year, month, day);
    }

    public String toTimeString() {
        return String.format("%d:%d:%d", hour, minute, second);
    }

    
    public boolean after(PersianDateTime dt) {
        return this.getTimeStamp() > dt.getTimeStamp();
    }
    public boolean before(PersianDateTime dt) {
        return this.getTimeStamp() < dt.getTimeStamp();
    }


    public DateTime toGregorianDate() {
        DateTime dt = null;
        int miladiYear, i, dayCount, remainDay, marchDayDiff;
        // this buffer has day count of Miladi month from April to January for a none year.
        int[] miladiMonth = {30, 31, 30, 31, 31, 30, 31, 30, 31, 31, 28, 31};
        miladiYear = this.year + 621;
        //Detemining the Farvardin the First
        //this is a Miladi leap year so Shamsi is leap too so the 1st of Farvardin is March 20 (3/20)
        //this is not a Miladi leap year so Shamsi is not leap too so the 1st of Farvardin is March 21 (3/21)
        // If next year is leap we will add one day to Feb.
        int milady_leap_year = miladiYear + 1;
        if (((miladiYear % 100) != 0 && (miladiYear % 4) == 0) || ((miladiYear % 100) == 0 && (miladiYear % 400) == 0)) {
            marchDayDiff = 12;
        } else {
            marchDayDiff = 11;
        }
        if (((milady_leap_year % 100) != 0 && (milady_leap_year % 4) == 0) || ((milady_leap_year % 100) == 0 && (milady_leap_year % 400) == 0)) {
            miladiMonth[10] = miladiMonth[10] + 1; //Adding one day to Feb
        }
        //Calculate the day count for input shamsi date from 1st Farvadin
        if ((this.month >= 1) && (this.month <= 6)) {
            dayCount = ((this.month - 1) * 31) + this.day;
        } else {
            dayCount = (6 * 31) + ((this.month - 7) * 30) + this.day;
        }
        //Finding the correspond miladi month and day
        if (dayCount <= marchDayDiff) //So we are in 20(for leap year) or 21for none leap year) to 31 march
        {
            dt = new DateTime(miladiYear, 3, dayCount + (31 - marchDayDiff), this.hour, this.minute, this.second);
        } else {
            remainDay = dayCount - marchDayDiff;
            i = 0; //starting from April
            while ((remainDay > miladiMonth[i])) {
                remainDay = remainDay - miladiMonth[i];
                i++;
            }
            //  miladiDate.setDate(remainDay);
            if (i > 8) // We are in the next Miladi Year
            {
                dt = new DateTime(miladiYear + 1, i - 8, remainDay, this.hour, this.minute, this.second);
            } else {
                dt = new DateTime(miladiYear, i + 4, remainDay, this.hour, this.minute, this.second);
            }
        }
        return dt;
    }
    
    
}
