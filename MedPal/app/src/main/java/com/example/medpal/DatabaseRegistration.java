package com.example.medpal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseRegistration extends SQLiteOpenHelper {
    public DatabaseRegistration(Context context) {
        super(context, "Register.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create table user(username text primary key, phone text, password text, conPassword text)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists user");
    }

    //Inserting data into the table user
    public boolean insertUserData(String username, String phone, String password, String conPassword) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("phone", phone);
        contentValues.put("password", password);
        contentValues.put("conPassword", conPassword);
        long insertValues = db.insert("user", null, contentValues);
        if (insertValues == -1) return false;
        else return true;
    }

    public Boolean checkUsername(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from user where username=?", new String[]{username});
        if (cursor.getCount()>0) return false;
        else return true;
    }

    public Boolean checkLoginDetails(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from user where username = ? and password = ?", new String[]{username, password});
        if(cursor.getCount()>0) return true;
        else return false;
    }

}