package com.example.asus.reminder;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Reminder extends AppCompatActivity {

    RecyclerView recyclerView;
    ViewAdapter viewAdapter;
    Calendar canlander;
    List<AlarmInfo> list=new ArrayList<>();

    public int hour,minute,month,day,year;
    AlarmManager alarmManager;
    PendingIntent pi,pi2;

    DatabaseManagement databaseManagement;

    Switch ringtone;
    Switch vibration;
    Switch speech;
    Switch record;

    String textSpeech;

    boolean isRingtone=false,isVibration=true,isSpeech=false,isRecord=false;

    boolean stRingtone=false,stVibrate=false,stSpeech=false,stRecord=false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder);
        recyclerView=(RecyclerView) findViewById(R.id.alarmInfo);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        viewAdapter=new ViewAdapter(list);
        recyclerView.setAdapter(viewAdapter);

        canlander = Calendar.getInstance();

        year = canlander.get(Calendar.YEAR);
        month = canlander.get(Calendar.MONTH);
        day = canlander.get(Calendar.DAY_OF_MONTH);

        databaseManagement=new DatabaseManagement(getApplicationContext());


        preparedData();

    }



    public void preparedData(){

        AlarmInfo postDetails=new AlarmInfo("9:20PM","12.1.2018","how are you ");
        list.add(postDetails);
        postDetails=new AlarmInfo("10:20AM","12.1.2018","what are you doing ggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggg hhhkkkkkkkkkkkkkkkkkkkk");
        list.add(postDetails);
        postDetails=new AlarmInfo("10:20pm","12.1.2018","how");
        list.add(postDetails);
        postDetails=new AlarmInfo("10:20am","12.1.2018","whare are you going hggljfsss hello");
        list.add(postDetails);
        postDetails=new AlarmInfo("10:20","12.1.2018","how are you");
        list.add(postDetails);
        postDetails=new AlarmInfo("10:20","12.1.2018","i love you");
        list.add(postDetails);

        Cursor cursor=databaseManagement.showAllData();

        if(cursor!=null) {

            while (cursor.moveToNext()) {

                postDetails = new AlarmInfo(cursor.getString(1), cursor.getString(2), cursor.getString(3));
                list.add(postDetails);

            }
        }

        viewAdapter.notifyDataSetChanged();



    }

    public  class ViewAdapter extends RecyclerView.Adapter<ViewAdapter.MyViewHolder>{

        List<AlarmInfo> list;

        public class MyViewHolder extends RecyclerView.ViewHolder{


            View mView;
            public TextView time,date,text;
            public ImageView circleImageForOnline;

            public MyViewHolder(View itemView) {
                super(itemView);


                time=(TextView) itemView.findViewById(R.id.time);
                date=(TextView) itemView.findViewById(R.id.date);
                text=(TextView) itemView.findViewById(R.id.text);


            }

            public void setUserName(String time1){


                time.setText(time1);
            }

            public void setProfileImage(String image){



            }

            public void setActivity(boolean online){


                if(online){

                    circleImageForOnline.setVisibility(View.VISIBLE);
                }
                else {

                    circleImageForOnline.setVisibility(View.INVISIBLE);
                }
            }

        }


        public ViewAdapter(List<AlarmInfo> list) {

            this.list=list;
        }



        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);

            return new MyViewHolder(itemView);

        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {


            AlarmInfo alarmInfo=list.get(position);
            holder.time.setText(alarmInfo.time);
            holder.date.setText(alarmInfo.date);
            holder.text.setText(alarmInfo.text);


        }


        @Override
        public int getItemCount() {

            return list.size();
        }




    }


    public void add(View v){

        Intent tnt=new Intent(this,AlarmSetter.class);
        startActivity(tnt);



    }

    public void add2(View v) {


        int mHour = canlander.get(Calendar.HOUR_OF_DAY);
        final int mMinute = canlander.get(Calendar.MINUTE);






        TimePickerDialog tpd = new TimePickerDialog(this,
                new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minuteOfDay) {



                        hour=hourOfDay;
                        minute=minuteOfDay;
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
                        takeDate();

                    }
                }, mHour, mMinute, true);
        tpd.show();










    }

    public void takeDate(){

        int mYear = canlander.get(Calendar.YEAR);
        int mMonth = canlander.get(Calendar.MONTH);
        int mDay = canlander.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog t = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int Tyear, int Tmonth, int dayOfMonth) {


                setStatus();

                day=dayOfMonth;
                month=Tmonth;
                year=Tyear;


            }
        }, mYear, mMonth, mDay);


        t.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {

                Toast.makeText(getApplicationContext(),"You didn,t select any date",Toast.LENGTH_LONG).show();

                setAlarmTime(minute,hour,day,month,year);


            }
        });



        t.show();



    }


    public void setStatus(){

        LayoutInflater layoutInflater=getLayoutInflater();
       // LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view=layoutInflater.inflate(R.layout.set_status,null);

        final View tittle_view=layoutInflater.inflate(R.layout.dialog_tittle,null);

        AlertDialog.Builder dialog=new AlertDialog.Builder(this);
        dialog.setView(view);
        dialog.setCustomTitle(tittle_view);

        ringtone=(Switch) view.findViewById(R.id.setRingtone) ;
        vibration=(Switch) view.findViewById(R.id.vibrate) ;
        speech=(Switch) view.findViewById(R.id.speech) ;
        record=(Switch) view.findViewById(R.id.record) ;


        ringtone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(ringtone.isChecked()){

                    isRingtone=true;

                }
                else{

                    isRingtone=false;

                }
            }
        });

        vibration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(vibration.isChecked()){

                    isVibration=true;
                }
                else{

                    isVibration=false;

                }
            }
        });


        speech.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(speech.isChecked()){


                    isSpeech=true;

                    LayoutInflater layoutInflater2=getLayoutInflater();
                    // LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    View view2=layoutInflater2.inflate(R.layout.input_textview,null);

                    View tittle_view2=layoutInflater2.inflate(R.layout.dialog_tittle,null);

                    tittle_view2.setBackgroundColor(Color.BLUE);

                    TextView  textView=(TextView) tittle_view2.findViewById(R.id.textview);
                    final EditText input=(EditText ) view2.findViewById(R.id.editText);
                    textView.setBackgroundColor(Color.BLUE);
                    textView.setText("Write your text:");



                    AlertDialog.Builder dialog=new AlertDialog.Builder(Reminder.this);
                    dialog.setView(view2);
                    dialog.setCustomTitle(tittle_view2);

                    dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {


                            textSpeech=input.getText().toString();

                        }
                    });
                    dialog.create();
                    dialog.show();





                }
                else{

                    isSpeech=false;
                }
            }
        });

        record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(record.isChecked()){

                    isRecord=true;

                }
                else{

                    isRecord=false;

                }
            }
        });

        dialog.setPositiveButton("Save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {


                setAlarmTime(minute,hour,day,month,year);

            }
        });

        dialog.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                setAlarmTime(minute,hour,day,month,year);

            }
        });

        dialog.create();
        dialog.show();


    }


    public void setAlarmTime(int Minute,int Hour,int day,int month,int year ){



        Date date=new Date();

        Calendar call_alarm=Calendar.getInstance();
        Calendar call_now=Calendar.getInstance();

        call_now.setTime(date);
        call_alarm.setTime(date);

        call_alarm.set(Calendar.DAY_OF_MONTH,day);
        call_alarm.set(Calendar.MONTH,month);
        call_alarm.set(Calendar.YEAR,year);

        call_alarm.set(Calendar.HOUR_OF_DAY,hour);
        call_alarm.set(Calendar.MINUTE,minute);

       if(call_alarm.before(call_now)){

            call_alarm.add(Calendar.DATE,1);

        }


        alarmManager=(AlarmManager) getSystemService(Context.ALARM_SERVICE);
        SharedPreferences preferencesOutput = getPreferences(MODE_PRIVATE);
        int id=preferencesOutput.getInt("id",1);

        Intent tnt=new Intent(this,AlarmMake.class);

        tnt.putExtra("ringtone",isRingtone);
        tnt.putExtra("ringNumber",0);

        tnt.putExtra("vibrate",isVibration);

        tnt.putExtra("speak",isSpeech);
        tnt.putExtra("speakText",textSpeech);

        tnt.putExtra("voice",isRecord);
        tnt.putExtra("voiceNumber",0);






        pi=PendingIntent.getBroadcast(this,id,tnt,0);
        alarmManager.set(AlarmManager.RTC_WAKEUP,call_alarm.getTimeInMillis(),pi);


        SharedPreferences preferencesInput = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor =preferencesInput.edit();
        editor.putInt("id",id+1);
        editor.commit();




        //alarmManager=(AlarmManager) getSystemService(Context.ALARM_SERVICE);

        /*Date date=new Date();

        Calendar calNow = Calendar.getInstance();
        Calendar calSet = (Calendar) calNow.clone();
        calSet.set(Calendar.HOUR_OF_DAY, hour);
        calSet.set(Calendar.MINUTE, minute);
        calSet.set(Calendar.SECOND, 0);
        calSet.set(Calendar.MILLISECOND, 0);

        if(calSet.compareTo(calNow) <= 0){
            calSet.add(Calendar.DATE, 1);

            Toast.makeText(this,"Alarm set successfully",Toast.LENGTH_LONG).show();
        }*/


        Toast.makeText(this,"Alarm set successfully",Toast.LENGTH_LONG).show();



        databaseManagement.insertData(id,hour+":"+minute,day+"."+month+"."+year,textSpeech+(id+1));





    }


}
