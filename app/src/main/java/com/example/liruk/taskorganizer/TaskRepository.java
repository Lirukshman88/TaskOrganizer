package com.example.liruk.taskorganizer;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.List;

public class TaskRepository {
    private TaskDao mTaskDao;
    private LiveData<List<Task>> mTasks;

    TaskRepository(Application application){
        TaskRoomDatabase db = TaskRoomDatabase.getDatabase(application);
        mTaskDao = db.taskDao();
        mTasks = mTaskDao.getAllTasks();
    }

    LiveData<List<Task>> getAllTasks(){
        return mTasks;
    }

    public void insert(Task task){
        new insertAsyncTask(mTaskDao).execute(task);
    }

    public void delete(Task task){
        new deleteAsyncTask(mTaskDao).execute(task.getName());
    }

    private static class insertAsyncTask extends AsyncTask<Task, Void, Void> {
        private TaskDao mAsyncTaskDao;

        insertAsyncTask(TaskDao dao){
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Task... params){
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    private static class deleteAsyncTask extends AsyncTask<String, Void, Void>{
        private TaskDao mAsyncTaskDao;

        deleteAsyncTask(TaskDao dao){ mAsyncTaskDao = dao;}

        @Override
        protected Void doInBackground(final String... params){
            mAsyncTaskDao.delete(params[0]);
            return null;
        }
    }
}
