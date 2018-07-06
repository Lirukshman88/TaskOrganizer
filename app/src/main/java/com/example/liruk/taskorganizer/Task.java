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
