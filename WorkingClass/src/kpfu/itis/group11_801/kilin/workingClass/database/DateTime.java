package kpfu.itis.group11_801.kilin.workingClass.database;

import java.util.Calendar;
import java.time.YearMonth;

public class DateTime extends Date{
    private int hours;
    private int minutes;
    private int seconds;

    public DateTime(int day, int month, int year, int hours, int minutes, int seconds) {
        super(day, month, year);
        this.hours = hours;
        this.minutes = minutes;
        this.seconds = seconds;
    }

    public DateTime(String date) {
        super(date.split(" ")[0]);
        String [] dateAndTime = date.split(" ");
        String [] res = dateAndTime[1].split(":");
        this.hours = Integer.parseInt(res[0]);
        this.minutes = Integer.parseInt(res[1]);
        this.seconds = Integer.parseInt(res[2]);
    }

    public int getHours() {
        return hours;
    }

    public int getMinutes() {
        return minutes;
    }

    public int getSeconds() {
        return seconds;
    }

    @Override
    public String toString() {
        return super.toString() + " " + hours + ":" + minutes + ":" + seconds;
    }

    public static DateTime getCurrentDateTime() {
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH) + 1;
        int year = calendar.get(Calendar.YEAR);
        int hours = calendar.get(Calendar.HOUR_OF_DAY);
        int minutes = calendar.get(Calendar.MINUTE);
        int seconds = calendar.get(Calendar.SECOND);

        return new DateTime(day, month, year, hours, minutes, seconds);
    }

    public int getSec() {
        return ((((getYear() - 2018) * 360 + YearMonth.of(getYear(), getMonth()).lengthOfMonth() + getDay()) * 24 + hours) * 60 + minutes) * 60 + seconds;
    }

    public static void main(String [] args) {
        System.out.println(getCurrentDateTime());
    }
}
