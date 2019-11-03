package com.example.asus.reminder;


import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Vibrator;
import android.support.v4.app.NotificationCompat;

public class AlarmMake extends BroadcastReceiver {

    Speak speak;



    @Override
    public void onReceive(Context context, Intent intent) {



        boolean virbrate=intent.getBooleanExtra("vibrate",false);
        boolean ringtone=intent.getBooleanExtra("ringtone",false);
        boolean speak=intent.getBooleanExtra("speak",false);
        boolean voice=intent.getBooleanExtra("voice",false);
        int ringNumber=intent.getIntExtra("ringNumber",0);
        String speakText=intent.getStringExtra("speakText");
        int voiceNumber=intent.getIntExtra("ringNumber",0);


        Intent tnt=new Intent(context,AlarmService.class);
        tnt.putExtra("alarm","alarm_on");
        tnt.putExtra("ringtone",ringtone);
        tnt.putExtra("vibrate",virbrate);
        tnt.putExtra("speak",speak);
        tnt.putExtra("voice",voice);
        tnt.putExtra("ringNumber",ringNumber);
        tnt.putExtra("speakText",speakText);
        tnt.putExtra("voiceNumber",voiceNumber);



        context.startService(tnt);





       /*Vibrator vibrator=(Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate(2000);



        NotificationCompat.Builder notification=new NotificationCompat.Builder(context);
        notification.setContentTitle("Alarm On");
        notification.setContentText("azaz,sry for disturbing.You have an exam today at 10 AM");
        notification.setSmallIcon(R.drawable.ic_launcher_background);
        int id=(int)System.currentTimeMillis();
        NotificationManager notificationManager=(NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notification.setAutoCancel(true);
        notificationManager.notify(0,notification.build());

        Uri ring= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);

        Ringtone r=RingtoneManager.getRingtone(context,ring);
        r.play();*/







    }


}
