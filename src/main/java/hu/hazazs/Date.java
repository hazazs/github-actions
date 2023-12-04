package hu.hazazs;

import java.util.Objects;

final class Date implements Cloneable {

    private int year;
    private int month;
    private int day;

    public Date(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    @Override
    protected Date clone() {
        try {
            return (Date) super.clone();
        } catch (CloneNotSupportedException x) {
            return null;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(year, month, day);
    }

    @Override
    public String toString() {
        return "Date [" +
                "year=" + year +
                ", month=" + month +
                ", day=" + day +
                ']';
    }

}