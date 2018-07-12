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

public class TaskEditor extends AppCompatActivity {
    EditText taskName;
    DatePicker datePicker;
    TimePicker timePicker;
    Spinner onesDuration;
    Spinner tensDuration;
    RatingBar ratingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_editor);
        Intent intent = getIntent();

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.digits_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        tensDuration = findViewById(R.id.durationTensDigit);
        tensDuration.setAdapter(adapter);
        tensDuration.setSelection(intent.getIntExtra("Duration",0)/10);

        onesDuration = findViewById(R.id.durationOnesDigit);
        onesDuration.setAdapter(adapter);
        onesDuration.setSelection(intent.getIntExtra("Duration",0)%10);

        taskName = findViewById(R.id.name);
        taskName.setText(intent.getStringExtra("Name"));

        datePicker = findViewById(R.id.date);
        datePicker.updateDate(intent.getIntExtra("Year",0),
                intent.getIntExtra("Month",0)-1,intent.getIntExtra("Day",0));
        //-1 because month starts at 0
        timePicker = findViewById(R.id.time);
        timePicker.setIs24HourView(true);
        timePicker.setHour(intent.getIntExtra("Hour", 0));
        timePicker.setMinute(intent.getIntExtra("Minutes", 0));

        ratingBar = findViewById(R.id.priorityRating);
        ratingBar.setRating(intent.getIntExtra("Rating",0));
    }

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
