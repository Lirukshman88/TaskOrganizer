package com.example.liruk.taskorganizer;

import java.util.Date;

public class Task {
    private int priority;
    private String name;
    private Date dueDate;
    private int estimatedTime;

    public Task(String name, Date dueDate, int estimatedTime, int priority){
        this.name = name;
        this.dueDate = dueDate;
        this.priority = priority;
        this.estimatedTime = estimatedTime;
    }

    public String getName() {
        return name;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public int getPriority() {
        return priority;
    }

    public long getScore(){
        Date now = new Date();
        long diff = Math.abs(dueDate.getTime() - now.getTime());
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

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public void setEstimatedTime(int estimatedTime) {
        this.estimatedTime = estimatedTime;
    }
}
