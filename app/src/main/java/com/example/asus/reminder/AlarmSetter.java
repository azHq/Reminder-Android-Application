package com.example.asus.reminder;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.database.DataSetObserver;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.TimePicker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class AlarmSetter extends AppCompatActivity {



    Calendar canlander= Calendar.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_setter);




    }

    public void setTime(View v){


        int mHour=canlander.get(Calendar.HOUR);
        int mMinute=canlander.get(Calendar.MINUTE);

        TimePickerDialog tpd = new TimePickerDialog(this,
                new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay,
                                          int minute) {
                        // Display Selected time in textbox
                        /*if (hourOfDay < 10 && minute < 10) {
                            txtTime.setText("0" + hourOfDay + ":" + "0" + minute);
                        } else if (minute < 10) {
                            txtTime.setText(hourOfDay + ":" + "0" + minute);
                        } else {
                            txtTime.setText(hourOfDay + ":" + minute);
                        }*/
                        Log.e("H", String.valueOf(hourOfDay));
                        Log.e("M", String.valueOf(minute));
                    }
                }, mHour, mMinute, true);
        tpd.show();

    }

    public void setDate(View v){

        int year=canlander.get(Calendar.YEAR);
        int month=canlander.get(Calendar.MONTH);
        int day=canlander.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog t=new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

            }
        },year,month,day);
        t.show();

    }

    public void setRingtone(View v){

    }

    public void setVibration(View v){

    }

    public void setSpeechText(View v){

    }

    public void setRecording(View v){

    }



}
