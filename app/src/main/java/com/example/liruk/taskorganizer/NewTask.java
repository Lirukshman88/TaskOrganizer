package com.example.liruk.taskorganizer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TimePicker;

public class NewTask extends AppCompatActivity {
    EditText taskName;
    DatePicker datePicker;
    TimePicker timePicker;
    Spinner onesDuration;
    Spinner tensDuration;
    RatingBar ratingBar;

    //Receive all the information a Task
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.digits_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        tensDuration = findViewById(R.id.durationTensDigit);
        tensDuration.setAdapter(adapter);

        onesDuration = findViewById(R.id.durationOnesDigit);
        onesDuration.setAdapter(adapter);

        taskName = findViewById(R.id.name);
        datePicker = findViewById(R.id.date);
        timePicker = findViewById(R.id.time);
        timePicker.setIs24HourView(true);
        ratingBar = findViewById(R.id.priorityRating);
    }

    //Send task back to main page
    public void Submit(View view) {
        Intent i = new Intent();
        String name = taskName.getText().toString();

        int day = datePicker.getDayOfMonth();
        int month = datePicker.getMonth()+1; //January == 0
        int year =  datePicker.getYear();

        int duration = Integer.parseInt(tensDuration.getSelectedItem().toString()) * 10 +
                Integer.parseInt(onesDuration.getSelectedItem().toString());

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
