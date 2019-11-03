package com.example.asus.reminder;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.IBinder;
import android.os.Vibrator;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;

public class AlarmService extends Service {



    MediaPlayer player;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }



    @Override
    public void onCreate() {
        super.onCreate();
        MainActivity.speak=new Speak(getApplicationContext());
        //player=MediaPlayer.create(this, Settings.System.DEFAULT_RINGTONE_URI);


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        player.stop();
    }



    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {




        String s=intent.getStringExtra("alarm");




        if(s!=null&&s.equals("alarm_on")) {


            boolean virbrate=intent.getBooleanExtra("vibrate",false);
            boolean ringtone=intent.getBooleanExtra("ringtone",false);
            boolean speak=intent.getBooleanExtra("speak",false);
            boolean voice=intent.getBooleanExtra("voice",false);

           if(virbrate){

                setVibration();

            }
            if(ringtone){

                int ringNumber=intent.getIntExtra("ringNumber",0);
                setRingTone(ringNumber);
            }
            if(speak){

               String text=intent.getStringExtra("speakText");

                speak(text);

            }
            if(voice){

                int voicenumber=intent.getIntExtra("ringNumber",0);

                if(voicenumber!=0){

                    voice();
                }

            }



            NotificationCompat.Builder notification = new NotificationCompat.Builder(getApplicationContext());
            notification.setContentTitle("Alarm On");
            notification.setContentText("azaz,sorry for disturbing.You have an exam today at 10 AM");
            notification.setSmallIcon(R.drawable.alarmclock);

            int id = (int) System.currentTimeMillis();
            NotificationManager notificationManager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
            notification.setAutoCancel(true);
            notificationManager.notify(0, notification.build());



        }



        return super.onStartCommand(intent, flags, startId);


    }


    public void setVibration(){

        Vibrator vibrator = (Vibrator) getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate(2000);

    }

    public void setRingTone(int ringNumber){

            Uri ring= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);

            Ringtone r=RingtoneManager.getRingtone(getApplicationContext(),ring);
            r.play();

    }

    public void speak(String text){

            MainActivity.speak.toSpeak(text);

    }

    public void voice(){

    }


}
