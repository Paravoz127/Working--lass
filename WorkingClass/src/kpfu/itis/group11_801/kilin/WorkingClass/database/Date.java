package kpfu.itis.group11_801.kilin.workingClass.database;

public class Date {
    private int month;
    private int day;
    private int year;

    public Date(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public Date(String date) {
        String [] res = date.split("-");
        this.day = Integer.parseInt(res[2]);
        this.month = Integer.parseInt(res[1]);
        this.year = Integer.parseInt(res[0]);
    }

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    @Override
    public String toString() {
        return year + "-" + month + "-" + day;
    }
}