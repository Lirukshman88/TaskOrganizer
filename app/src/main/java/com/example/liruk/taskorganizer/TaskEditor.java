package com.example.liruk.taskorganizer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class TaskEditor extends AppCompatActivity {
    EditText taskName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_editor);

        Spinner spinner = findViewById(R.id.durationTensDigit);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.digits_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        Spinner spinner1 = findViewById(R.id.durationOnesDigit);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,
                R.array.digits_array, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter1);

        taskName = findViewById(R.id.name);
    }

    public void Submit(View view) {
        Intent i = new Intent();
        String name = taskName.getText().toString();
        i.putExtra("Data", name);
        setResult(RESULT_OK, i);
        finish();
    }
}
