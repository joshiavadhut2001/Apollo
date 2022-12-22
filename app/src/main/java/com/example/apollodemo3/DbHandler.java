package com.example.apollodemo3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHandler extends SQLiteOpenHelper {

    public static final String DBNAME="Login.db";
    public DbHandler( Context context) {
        super(context ,"Login.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("create table users (email TEXT primary key, username TEXT,password TEXT)");
        MyDB.execSQL("create table project (email TEXT primary key, project_name TEXT, start_date date, end_date date)");

    }


    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {

        MyDB.execSQL("drop Table if exists users");
    }

    public Boolean insertData( String email, String username,String password)
    {

        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("email",email);
        contentValues.put("username",username);
        contentValues.put("password",password);


        long result = MyDB.insert("users",null,contentValues);
        if(result == -1)return false;
        else
            return  true;
    }

    public  Boolean checkusername(String email)
    {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("select * from users where email=?", new String[] {email});

        if(cursor.getCount()>0)
            return true;
        else
            return false;

    }

    public Boolean checkusernamepassword(String email,String password)
    {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("select * from users where email=?  and password=?",new String[]{email,password});
        if(cursor.getCount()>0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
