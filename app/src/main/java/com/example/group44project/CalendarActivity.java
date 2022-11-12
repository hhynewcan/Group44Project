package com.example.group44project;

import android.content.Intent;
import android.os.Bundle;
import android.widget.CalendarView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class CalendarActivity extends AppCompatActivity
{
    CalendarView calendarView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar);

        calendarView = (CalendarView) findViewById(R.id.calendarView);

        Intent groceryListJump = new Intent(this, GroceryActivity.class);

        // Jump and pass the information to grocery list
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                        groceryListJump.putExtra("YEAR", String.valueOf(i));
                        groceryListJump.putExtra("MONTH", String.valueOf(i1));
                        groceryListJump.putExtra("DAY", String.valueOf(i2));
                        startActivity(groceryListJump);
            }
        });
    }
}
