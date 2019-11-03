package com.example.asus.reminder;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.provider.CalendarContract;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.Set;

public class AlarmServiceManager extends AppCompatActivity {

    TimePicker timePicker;
    int hours,minutes;
    AlarmManager alarmManager;
    PendingIntent pi,pi2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_manager);

        timePicker=(TimePicker)findViewById(R.id.timePicker);
        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {

                hours=hourOfDay;
                minutes=minute;
            }
        });
    }

    public void setTime(View v){



        alarmManager=(AlarmManager) getSystemService(Context.ALARM_SERVICE);

        Date date=new Date();



        Calendar calNow = Calendar.getInstance();
        Calendar calSet = (Calendar) calNow.clone();
        calSet.set(Calendar.HOUR_OF_DAY, hours);
        calSet.set(Calendar.MINUTE, minutes);
        calSet.set(Calendar.SECOND, 0);
        calSet.set(Calendar.MILLISECOND,0);

        if(calSet.compareTo(calNow) <= 0){
            calSet.add(Calendar.DATE, 1);

            Toast.makeText(this,"Alarm set successfully",Toast.LENGTH_LONG).show();
        }





        SharedPreferences preferencesOutput = getPreferences(MODE_PRIVATE);
        int id=preferencesOutput.getInt("id",1);

        Intent tnt=new Intent(this,AlarmMake.class);
        pi=PendingIntent.getBroadcast(this,id,tnt,0);
        alarmManager.set(AlarmManager.RTC_WAKEUP,calSet.getTimeInMillis(),pi);


        SharedPreferences preferencesInput = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor =preferencesInput.edit();
        editor.putInt("id",id+1);
        editor.commit();





    }

    public void alarmOFF(View v){

        alarmManager.cancel(pi);
    }
}
