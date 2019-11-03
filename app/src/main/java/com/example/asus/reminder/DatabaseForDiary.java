package com.example.asus.reminder;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseForDiary extends SQLiteOpenHelper {

    SQLiteDatabase database;
    Context context;
    public final static String databaseName = "DairyDatabase";
    public String insert = "INSERT INTO DairyTable VALUES";
    public String delete = "DELETE FROM DairyTable WHERE";
    public String update = "UPDATE DairyTable SET";
    public String selectAll = "SELECT * FROM DairyTable";
    public String createTable = "CREATE TABLE IF NOT EXISTS DairyTable(id INTEGER,title VARCHAR,description VARCHAR,time VARCHAR,date VARCHAR,day VARCHAR);";


    public DatabaseForDiary(Context context) {

        super(context,databaseName,null,1);
        this.context=context;



    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(createTable);
        database=db;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP  TABLE IF  EXISTS AlarmTable");
    }


    public void createTable() {


    }

    public void insertData(int id,String title,String description, String time, String date, String day) {


        database=this.getWritableDatabase();
        database.execSQL(insert + "('" + id + "','"+ title +"','" + description + "','" + time + "','" + date + "','" + day + "');");

    }

    public void deleteData(int id) {

        database=this.getWritableDatabase();
        database.execSQL(delete + "WHERE id='" + id + "'", null);


    }

    public void updateData(int id, String time,String date, String status) {

        database=this.getWritableDatabase();
        database.execSQL(update + "id='" + id + "',time='" + time + "',date='" + date + "',status='" + status + "'WHERE rollno='" + id + "'");

    }

    public Cursor showAllData() {

        database=this.getWritableDatabase();
        if(database!=null){
            Cursor c = database.rawQuery(selectAll, null);

            return c;
        }

        return null;
    }


}

