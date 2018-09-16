package com.example.liruk.taskorganizer;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.util.Date;

@Entity(tableName = "task_table")
public class Task implements Comparable<Task> {

    @ColumnInfo(name="priority")
    private int priority;

    @PrimaryKey
    @NonNull
    @ColumnInfo(name="task")
    private String name;

    @ColumnInfo(name="day")
    private int day;

    @ColumnInfo(name="month")
    private int month;

    @ColumnInfo(name="year")
    private int year;

    @ColumnInfo(name="hour")
    private int hour;

    @ColumnInfo(name="minutes")
    private int minutes;

    @ColumnInfo(name="estimatedTime")
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

    //Calculates the score based on estimatedTime, timeLeft, priority
    public long getScore(){
        Date now = new Date();
        Date due = new Date(year, month, day);
        long diff = due.getTime() - now.getTime();
        double timeLeft = diff/(1000*60*60);
        if (timeLeft <= 0){
            return 0;
        }
        if(priority == 5){
            return Integer.MAX_VALUE;
        }
        if(estimatedTime > timeLeft){
            return 0;
        } else {
            int extra = 0;
            if(timeLeft < 6){
                extra = 15;
            }
            if(timeLeft < 12){
                extra = 10;
            }
            if(timeLeft < 24){
                extra = 5;
            }
            return (long)(extra + (priority*estimatedTime)/timeLeft);
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

    @Override
    public int compareTo(@NonNull Task task) {
        if (task.getScore() > getScore()){
            return 1;
        } else {
            return -1;
        }
    }
}
