package com.example.asus.reminder;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseManagement extends SQLiteOpenHelper {

    SQLiteDatabase database;
    Context context;
    public final static String databaseName = "AlarmDatabse";
    public String insert = "INSERT INTO AlarmTable VALUES";
    public String delete = "DELETE FROM AlarmTable WHERE";
    public String update = "UPDATE AlarmTable SET";
    public String selectAll = "SELECT * FROM AlarmTable";
    public String createTable = "CREATE TABLE IF NOT EXISTS AlarmTable(id INTEGER,time VARCHAR,date VARCHAR,status VARCHAR);";


    public DatabaseManagement(Context context) {

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

    public void insertData(int id, String time, String date, String status) {


        database=this.getWritableDatabase();
        database.execSQL(insert + "('" + id + "','" + time + "','" + date + "','" + status + "');");

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
