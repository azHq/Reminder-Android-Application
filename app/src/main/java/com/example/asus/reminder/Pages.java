package com.example.asus.reminder;

public class Pages {

    String description,title,date,day,time;
    public Pages(){

    }

    public Pages(String description, String title, String date, String day, String time) {
        this.description = description;
        this.title = title;
        this.date = date;
        this.day = day;
        this.time = time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
