package com.example.liruk.taskorganizer;

import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;

    static final int request = 1;
    ArrayList<Task> tasks = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mRecyclerView = findViewById(R.id.my_recycler_view);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Makes a Tasks
                startActivityForResult(new Intent(MainActivity.this, NewTask.class), request);
            }
        });

        mRecyclerView.setHasFixedSize(true);
        // Set up RecyclerView with current task list
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        mAdapter = new TaskAdapter(tasks);
        mRecyclerView.setAdapter(mAdapter);

        //Swipe to delete functionality
        final ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                return makeMovementFlags(0, 1 << 2);
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
                // Remove item from backing list here
                final int position = viewHolder.getAdapterPosition();
                tasks.remove(position);
                mAdapter.notifyDataSetChanged();
            }


            @Override
            public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
                // view the background view
                if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE ) {
                    // Get RecyclerView item from the ViewHolder
                    View itemView = viewHolder.itemView;

                    Paint p = new Paint();
                    p.setColor(Color.RED);
                    // Draw Rect with varying left side, equal to the item's right side plus negative displacement dX
                    c.drawRect((float) itemView.getRight() + dX, (float) itemView.getTop(),
                            (float) itemView.getRight(), (float) itemView.getBottom(), p);
                    super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
                }
            }
        });

        itemTouchHelper.attachToRecyclerView(mRecyclerView);

    }

    // Receives Tasks and adds it to the list and notifies the data set changed
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == request){
            if(resultCode == RESULT_OK){
                String name = data.getStringExtra("Name");

                int day = data.getIntExtra("Day", 0);
                int month = data.getIntExtra("Month", 0);
                int year = data.getIntExtra("Year", 0);

                int hour = data.getIntExtra("Hour", 0);
                int minutes = data.getIntExtra("Minutes", 0);

                int duration = data.getIntExtra("Duration", 0);

                float rating = data.getFloatExtra("Rating", 0);

                Task task = new Task(name, day, month, year, hour, minutes, duration, (int) rating);

                tasks.add(task);
                mAdapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void openEditor(View view){
        Task clickedTask = tasks.get(view.getId());

        Intent i = new Intent(MainActivity.this, TaskEditor.class);

        i.putExtra("Name", clickedTask.getName());
        i.putExtra("Day", clickedTask.getDay());
        i.putExtra("Month", clickedTask.getMonth());
        i.putExtra("Year", clickedTask.getYear());
        i.putExtra("Hour", clickedTask.getHour());
        i.putExtra("Minutes", clickedTask.getMinutes());
        i.putExtra("Duration", clickedTask.getEstimatedTime());
        i.putExtra("Rating", clickedTask.getPriority());
        tasks.remove(view.getId());
        startActivityForResult(i, request);
        }
}
