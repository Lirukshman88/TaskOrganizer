package com.example.liruk.taskorganizer;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface TaskDao {

    @Insert
    void insert(Task task);

    @Query("DELETE FROM task_table")
    void deleteAll();

    @Query("SELECT * from task_table ORDER BY priority ASC")
    LiveData<List<Task>> getAllTasks();

    @Query("DELETE FROM task_table WHERE task LIKE :taskName")
    void delete(String taskName);
}
