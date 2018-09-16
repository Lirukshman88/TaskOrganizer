package com.example.liruk.taskorganizer;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder> {
    private List<Task> tasks;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        private TextView name;
        private TextView date;
        private TextView time;
        private RelativeLayout parentLayout;

        private ViewHolder(View v) {
            super(v);
            name = v.findViewById(R.id.taskName);
            date = v.findViewById(R.id.dateLeft);
            time = v.findViewById(R.id.timeRemaining);
            parentLayout = v.findViewById(R.id.parent_layout);
        }
    }

    public TaskAdapter(List<Task> list) {
        tasks = list;
    }

    @NonNull
    @Override
    public TaskAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // create a new view
        View v =  LayoutInflater.from(parent.getContext()).inflate(R.layout.task_item, parent, false);
        return new ViewHolder(v);
    }

    // Setup for how Task will be displayed
    @Override
    public void onBindViewHolder(@NonNull TaskAdapter.ViewHolder holder, int position) {
        Task task = tasks.get(position);
        String date;
        if(task.getMinutes() < 10){
            date = task.getMonth() + "/" + task.getDay() + "/" + task.getYear() + "   " + task.getHour() + ":0" + task.getMinutes();
        } else {
            date = task.getMonth() + "/" + task.getDay() + "/" + task.getYear() + "   " + task.getHour() + ":" + task.getMinutes();
        }

        String time = Integer.toString(tasks.get(position).getEstimatedTime()) + " hours";

        holder.name.setText(task.getName());
        holder.date.setText(date);
        holder.time.setText(time);
        holder.parentLayout.setId(position);
    }

    void setTasks(List<Task> mTasks){
        tasks=mTasks;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    public void sortTasks(){
        Collections.sort(tasks);
    }


}

