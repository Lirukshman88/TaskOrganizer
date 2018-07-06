package com.example.liruk.taskorganizer;

import java.util.Date;

public class Task {
    private int priority;
    private String name;
    private Date dueDate;
    private int estimatedTime;
    private boolean recurrence;

    public Task(String name, Date dueDate, int estimatedTime, int priority, boolean recurrence){
        this.name = name;
        this.dueDate = dueDate;
        this.priority = priority;
        this.estimatedTime = estimatedTime;
        this.recurrence = recurrence;
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
}
