package com.example.liruk.taskorganizer;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.Date;

public class TaskEditor extends AppCompatActivity {
    EditText taskName;
    DatePicker datePicker;
    TimePicker timePicker;
    Spinner onesDuration;
    Spinner tenDuration;
    RatingBar ratingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_editor);

        onesDuration = findViewById(R.id.durationTensDigit);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.digits_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        onesDuration.setAdapter(adapter);

        tenDuration = findViewById(R.id.durationOnesDigit);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,
                R.array.digits_array, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tenDuration.setAdapter(adapter1);

        taskName = findViewById(R.id.name);
        datePicker = findViewById(R.id.date);
        timePicker = findViewById(R.id.time);
            timePicker.setIs24HourView(true);
        ratingBar = findViewById(R.id.priorityRating);
    }

    public void Submit(View view) {
        Intent i = new Intent();
        String name = taskName.getText().toString();

        int day = datePicker.getDayOfMonth();
        int month = datePicker.getMonth();
        int year =  datePicker.getYear();

        int duration = Integer.parseInt(onesDuration.getSelectedItem().toString()) * 10 +
                Integer.parseInt(tenDuration.getSelectedItem().toString());

        int hour = timePicker.getCurrentHour();

        int minutes = timePicker.getCurrentMinute();

        float rating = ratingBar.getRating();

        i.putExtra("Name", name);
        i.putExtra("Day", day);
        i.putExtra("Month",month);
        i.putExtra("Year", year);
        i.putExtra("Hour", hour);
        i.putExtra("Minutes", minutes);
        i.putExtra("Duration", duration);
        i.putExtra("Rating", rating);
        setResult(RESULT_OK, i);

        finish();
    }
}
