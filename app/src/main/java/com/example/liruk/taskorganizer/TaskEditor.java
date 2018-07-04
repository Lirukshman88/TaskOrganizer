package com.example.liruk.taskorganizer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class TaskEditor extends AppCompatActivity {
    EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_editor);
        editText = findViewById(R.id.name);
    }

    public void Submit(View view) {
        Intent i = new Intent();
        String name = editText.getText().toString();
        i.putExtra("Data", name);
        setResult(RESULT_OK, i);
        finish();
    }
}
