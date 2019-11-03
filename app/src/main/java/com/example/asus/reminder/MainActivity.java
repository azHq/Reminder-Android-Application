package com.example.asus.reminder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;




public class MainActivity extends AppCompatActivity {

    static Speak speak;
    Toolbar toolbar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        speak=new Speak(getApplicationContext());
        startService(new Intent(this,AlarmService.class));
        toolbar=(Toolbar) findViewById(R.id.app_bar);

        toolbar.setTitle("Reminder");








    }

    public void writeDiary(View view){

        Intent tnt=new Intent(this,Diary.class);
        startActivity(tnt);

    }

    public void toStudy(View v){

        Intent tnt=new Intent(this,Study.class);
        startActivity(tnt);
    }
    public void toRemined(View v){

        Intent tnt=new Intent(this,Reminder.class);
        startActivity(tnt);

    }

    public void speak(View v){
        speak.toSpeak("azaz.,sry. for. disturbing.You have an exam. today. at 10 AM");

    }
}
