package com.example.medpal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.room.Delete;
import androidx.room.Update;

public class MedPalDatabase extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "MedPal.db";


    public MedPalDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /*Creating tables to store user data, emergency contact info, medicine reminders and medicine info*/
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create table user(username text primary key, phone text, password text, conPassword text, logged text)");
        db.execSQL("Create table practitioner(username text primary key, practitionerNumber text, practitionerName text, practitionerEmail text, practitionerAddress text)");
        db.execSQL("Create table emergencyContact(username text primary key, contactNumber text, contactName text, contactEmail text, contactAddress text,contactRelation text)");
        db.execSQL("Create table medicine(medicineID integer primary key, name text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists user");
        db.execSQL("drop table if exists practitioner");
        db.execSQL("drop table if exists emergencyContact");
        db.execSQL("drop table if exists medicine");
    }

    public boolean insertUserData(String username, String phone, String password, String conPassword, String logged) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("user_id", username);
        contentValues.put("username", username);
        contentValues.put("phone", phone);
        contentValues.put("password", password);
        contentValues.put("conPassword", conPassword);
        contentValues.put("logged", logged);
        long insertValues = db.insert("user", null, contentValues);
        if (insertValues == -1) return false;
        else return true;
    }

    /*Checks if username is already registered
     * Will change to phone number*/
    public Boolean checkUsername(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from user where username=?", new String[]{username});
        if (cursor.getCount() > 0) return false;
        else return true;
    }

    /*Checks if username and password are stored in the database*/
    public Boolean checkLoginDetails(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from user where username = ? and password = ?", new String[]{username, password});
        if (cursor.getCount() > 0) return true;
        else return false;
    }

    public Contact retrievePractitioner() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select practitionerNumber,practitionerName,practitionerEmail,practitionerAddress from practitioner where username=?", new String[]{this.getLoggedUser()});
        if (cursor != null && cursor.moveToFirst()) {
            String practitionerName = cursor.getString(cursor.getColumnIndex("practitionerName"));
            String practitionerNumber = cursor.getString(cursor.getColumnIndex("practitionerNumber"));
            String practitionerEmail = cursor.getString(cursor.getColumnIndex("practitionerEmail"));
            String practitionerAddress = cursor.getString(cursor.getColumnIndex("practitionerAddress"));
            return new Contact(practitionerName, practitionerNumber, practitionerEmail, practitionerAddress);

        } else {
            return null;
        }
    }

    public Contact retrieveEmergencyContact() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select contactNumber,contactName,contactEmail,contactAddress,contactRelation from emergencyContact where username=?", new String[]{this.getLoggedUser()});
        if (cursor != null && cursor.moveToFirst()) {
            String contactNumber = cursor.getString(cursor.getColumnIndex("contactNumber"));
            String contactName = cursor.getString(cursor.getColumnIndex("contactName"));
            String contactEmail = cursor.getString(cursor.getColumnIndex("contactEmail"));
            String contactAddress = cursor.getString(cursor.getColumnIndex("contactAddress"));
            String contactRelation = cursor.getString(cursor.getColumnIndex("contactRelation"));

            return new Contact(contactName, contactNumber, contactEmail, contactAddress, contactRelation);
        } else {
            return null;
        }

    }

    //Inserting data into the table practitioner
    public boolean insertPractitionerData(String practitionerName, String practitionerNumber, String practitionerAddress, String practitionerEmail) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("practitioner", "username = ?", new String[]{this.getLoggedUser()});
        ContentValues contentValues = new ContentValues();
        contentValues.put("practitionerNumber", practitionerNumber);
        contentValues.put("practitionerName", practitionerName);
        contentValues.put("practitionerAddress", practitionerAddress);
        contentValues.put("practitionerEmail", practitionerEmail);
        contentValues.put("username", this.getLoggedUser());
        long insertValues = db.insert("practitioner", null, contentValues);
        if (insertValues == -1) return false;
        else return true;
    }

    public boolean insertEmergencyContactData(String contactName, String contactNumber, String contactAddress, String contactEmail, String contactRelation) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("emergencyContact", "username = ?", new String[]{this.getLoggedUser()});
        ContentValues contentValues = new ContentValues();
        contentValues.put("contactName", contactName);
        contentValues.put("contactNumber", contactNumber);
        contentValues.put("contactAddress", contactAddress);
        contentValues.put("contactEmail", contactEmail);
        contentValues.put("contactRelation", contactRelation);
        contentValues.put("username", this.getLoggedUser());
        long insertValues = db.insert("emergencyContact", null, contentValues);
        if (insertValues == -1) return false;
        else return true;
    }

    public void connectUser(String logInUsername) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("logged", "1");
        db.update("user", contentValues, "username = ?", new String[]{logInUsername});
    }

    public void disconnectUser() {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("logged", "0");
        db.update("user", contentValues, null, null);
    }

    public String getLoggedUser() {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select username from user where logged='1'", null);
        cursor.moveToFirst();
        return null;
    }

}