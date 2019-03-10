package com.example.aaa;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import calendar.NewCalendar;

public class SecondActivity extends AppCompatActivity implements NewCalendar.NewCalendarListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        NewCalendar calendar = (NewCalendar)findViewById(R.id.newCalendar);
        calendar.listener = this;
    }


    @Override
    public void onItemLongPress(Date day) {
        //显示长按的结果
        DateFormat df = SimpleDateFormat.getDateInstance();
        Toast.makeText(this,df.format(day),Toast.LENGTH_LONG).show();
    }
}
