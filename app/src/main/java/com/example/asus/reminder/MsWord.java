package com.example.asus.reminder;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.Layout;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import petrov.kristiyan.colorpicker.ColorPicker;

public class MsWord extends AppCompatActivity {

    EditText text,titleText;
    TextView timeTextView,dateTextView,dayTextView;
    public int id=0;

    ImageView textColorChange,textFontChange,newPage,save;
    int coloredAlready=0;
    boolean cursor=true;
    public int textColor=Color.RED,beforeCount=0;

    SpannableStringBuilder stringBuilder=null;
    DatabaseForDiary databaseForDiary;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ms_word);

        databaseForDiary=new DatabaseForDiary(getApplicationContext());



        timeTextView=(TextView) findViewById(R.id.time);
        dateTextView=(TextView) findViewById(R.id.date);
        dayTextView=(TextView) findViewById(R.id.day);

        Date date=new Date();
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(date);

        dateTextView.setText(calendar.get(Calendar.DAY_OF_MONTH)+":"+calendar.get(Calendar.MONTH)+":"+calendar.get(Calendar.YEAR));


        String day=calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault());
        dayTextView.setText(day);

        String s="";
        int am_pm=calendar.get(Calendar.AM_PM);
        if(Calendar.AM==am_pm) s="AM";
        else s="PM";
        timeTextView.setText(calendar.get(Calendar.HOUR_OF_DAY)+":"+calendar.get(Calendar.MINUTE)+s);

        text=(EditText) findViewById(R.id.editText2);
        titleText=(EditText) findViewById(R.id.titleText);
        stringBuilder=new SpannableStringBuilder();

        titleText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                cursor=false;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        text.addTextChangedListener(new TextWatcher() {

            private String oldContent = "";

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) { }
            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                final String newContent = text.getText().toString();

                cursor=true;
                if((text.getText().toString().length()-beforeCount)==1){

                    beforeCount=text.getText().toString().length();

                    String s=text.getText().toString().substring(text.getText().toString().length()-1,text.getText().toString().length());
                   /* SpannableString modifiedText = new SpannableString(s);
                    modifiedText.setSpan(new ForegroundColorSpan(textColor),s.length()-1,s.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);*/
                    stringBuilder.append(s);
                    text.setText(stringBuilder);
                    text.setSelection(text.getText().toString().length());
                }
                else if((text.getText().toString().length()-beforeCount)==-1){

                    beforeCount=text.getText().toString().length();
                    stringBuilder.delete(text.getText().toString().length(),text.getText().toString().length()+1);
                }






            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (null != text.getLayout() && text.getLayout().getLineCount() > 18) {
                    text.getText().delete(text.getText().length() - 1, text.getText().length());
                }





            }
        });



        textColorChange=(ImageView) findViewById(R.id.textColor);
        textFontChange=(ImageView) findViewById(R.id.font);
        newPage=(ImageView) findViewById(R.id.newpage);
        save=(ImageView) findViewById(R.id.save);

       textColorChange.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               openColorPicker();

           }
       });


        textFontChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder dialog=new AlertDialog.Builder(MsWord.this);
                View layoutInflater=getLayoutInflater().inflate(R.layout.font_style,null);
                final View title=getLayoutInflater().inflate(R.layout.title,null);
                dialog.setView(layoutInflater);
                dialog.setCustomTitle(title);
                final AlertDialog alertDialog=dialog.show();

                Button sensSerif=(Button) layoutInflater.findViewById(R.id.button9);
                Button serif=(Button) layoutInflater.findViewById(R.id.button12);
                Button casual=(Button) layoutInflater.findViewById(R.id.button16);
                Button cursive=(Button) layoutInflater.findViewById(R.id.button17);

                sensSerif.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if(cursor)text.setTypeface(Typeface.SANS_SERIF);
                        else titleText.setTypeface(Typeface.SANS_SERIF);
                        alertDialog.dismiss();
                    }
                });

                serif.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if(cursor)text.setTypeface(Typeface.SERIF);
                        else titleText.setTypeface(Typeface.SERIF);
                        alertDialog.dismiss();
                    }
                });
                casual.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if(cursor)text.setTypeface(Typeface.MONOSPACE);
                        else titleText.setTypeface(Typeface.MONOSPACE);
                        alertDialog.dismiss();
                    }
                });
                cursive.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if(cursor)text.setTypeface(null,Typeface.ITALIC);
                        else titleText.setTypeface(null,Typeface.ITALIC);
                        alertDialog.dismiss();
                    }
                });


                //text.setTypeface(Typeface.ITALIC);
               // text.setTypeface(null, Typeface.ITALIC);

            }
        });


        newPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                text.setText("");


            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences preferencesOutput = getPreferences(MODE_PRIVATE);
                int id=preferencesOutput.getInt("PageId",1);

                String title=titleText.getText().toString();
                String day=dayTextView.getText().toString();
                String date=dateTextView.getText().toString();
                String time=timeTextView.getText().toString();

                databaseForDiary.insertData(id,title,text.getText().toString(),"Time:"+time,"Date"+date,"Day"+day);

                SharedPreferences preferencesInput = getPreferences(MODE_PRIVATE);
                SharedPreferences.Editor editor =preferencesInput.edit();
                editor.putInt("PageId",id+1);
                editor.commit();
                Toast.makeText(MsWord.this,"Save successfully",Toast.LENGTH_LONG).show();


            }
        });



    }

    public void  openColorPicker(){
        final ColorPicker colorPicker=new ColorPicker(this);
        ArrayList<String> arrayList=new ArrayList<>();
        arrayList.add("#FF3418C1");
        arrayList.add("#951676");
        arrayList.add("#C70039");
        arrayList.add("#9506D3");
        arrayList.add("#AAD306");
        arrayList.add("#060107");
        arrayList.add("#DCD70B");
        arrayList.add("#79DC0B");
        arrayList.add("#CE6B11");
        arrayList.add("#CE11CC");

        colorPicker.setColors(arrayList)
                .setColumns(5)
                .setRoundColorButton(true)
                .setOnChooseColorListener(new ColorPicker.OnChooseColorListener() {
                    @Override
                    public void onChooseColor(int position, int color) {

                        textColor=color;
                        text.setSelection(text.getText().toString().length());

                        if(!cursor) titleText.setTextColor(color);



                    }

                    @Override
                    public void onCancel() {

                    }
                }).show();

    }


}
