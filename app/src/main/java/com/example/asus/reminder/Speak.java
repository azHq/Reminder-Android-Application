package com.example.asus.reminder;

import android.content.Context;
import android.speech.tts.TextToSpeech;
import android.widget.Toast;

import java.util.Locale;

public class Speak {

    TextToSpeech text;
    Context context;

    public Speak(Context context){

        this.context=context;


        text = new TextToSpeech(context, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {

                if (status != TextToSpeech.ERROR) {

                    text.setLanguage(Locale.ENGLISH);
                }

            }
        });

    }

    public void toSpeak(String s){

        text.speak(s, TextToSpeech.QUEUE_FLUSH,null);

        Toast.makeText(context,"hello how are you????",Toast.LENGTH_LONG).show();

    }
}
