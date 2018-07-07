package com.example.liruk.taskorganizer;

import java.util.Date;

public class Task {
    private int priority;
    private String name;
    private int day;
    private int month;
    private int year;
    private int hour;
    private int minutes;
    private int estimatedTime;

    public Task(String name, int day, int month, int year, int hour, int minutes, int estimatedTime, int priority) {
        this.name = name;
        this.day = day;
        this.month = month;
        this.year = year;
        this.hour = hour;
        this.minutes = minutes;
        this.priority = priority;
        this.estimatedTime = estimatedTime;
    }


    public String getName() {
        return name;
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

    public int getHour() {
        return hour;
    }

    public int getMinutes() {
        return minutes;
    }

    public int getPriority() {
        return priority;
    }

    public long getScore(){
        Date now = new Date();
        long diff = (now.getTime());
        diff = diff / (1000 * 60 * 60);
        if(diff == 0){
            return 0;
        } else {
            return (priority*estimatedTime)/diff;
        }
    }

    public int getEstimatedTime() {
        return estimatedTime;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public void setEstimatedTime(int estimatedTime) {
        this.estimatedTime = estimatedTime;
    }
}
