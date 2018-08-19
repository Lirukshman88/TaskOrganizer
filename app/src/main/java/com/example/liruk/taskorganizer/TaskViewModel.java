package com.example.liruk.taskorganizer;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.ArrayList;
import java.util.List;

public class TaskViewModel extends AndroidViewModel implements AsyncResponse {
    private TaskRepository mRepository;
    private LiveData<List<Task>> mAllTasks;

    public TaskViewModel(Application application){
        super(application);
        mRepository = new TaskRepository(application);
        mAllTasks = mRepository.getAllTasks();
    }

    LiveData<List<Task>> getAllTasks(){
        return mAllTasks;
    }

    public void insert(Task task){
        mRepository.insert(task);
    }

    public void delete(Task task){
        mRepository.delete(task);
    }

    @Override
    public void processResult(Task task){

    }
}
