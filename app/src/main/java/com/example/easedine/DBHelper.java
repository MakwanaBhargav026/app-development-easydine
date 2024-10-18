package com.example.easedine;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    // Constructor to create the database helper
    public DBHelper(Context context) {
        super(context, "LoginDetails.db", null, 1);
    }

    // Create table with three columns: uname (primary key), pass, cpass
    @Override
    public void onCreate(SQLiteDatabase Db) {
        Db.execSQL("CREATE TABLE Userdetails(uname TEXT PRIMARY KEY, pass TEXT, cpass TEXT)");
    }

    // Upgrade the database by dropping the old table and creating a new one
    @Override
    public void onUpgrade(SQLiteDatabase Db, int oldVersion, int newVersion) {
        Db.execSQL("DROP TABLE IF EXISTS Userdetails");
        onCreate(Db);
    }

    // Method to check user details by comparing username and password
    public boolean checkdetails(String uname, String pass) {
        SQLiteDatabase Db = this.getReadableDatabase();
        Cursor cursor = Db.rawQuery("SELECT * FROM Userdetails WHERE uname = ? AND pass = ?", new String[]{uname, pass});
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        return exists;
    }

    // Method to check if the username already exists in the database
    public Boolean checkUsername(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Userdetails WHERE uname = ?", new String[]{username});
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        return exists;
    }

    // Method to update user's password
    public Boolean updateUserPassword(String username, String newPassword) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("pass", newPassword);  // Updating the password column
        int result = db.update("Userdetails", contentValues, "uname = ?", new String[]{username});
        return result != -1; // Return true if update was successful, false otherwise
    }

    // Method to insert user data into the Userdetails table
    public Boolean insertuserdata(String uname, String pass, String copass) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("uname", uname);  // Insert username
        contentValues.put("pass", pass);    // Insert password
        contentValues.put("cpass", copass); // Insert confirm password
        long result = db.insert("Userdetails", null, contentValues);
        return result != -1;  // Return true if insertion was successful, false otherwise
    }

    // Method to clear all data from the Userdetails table
    public void clearDatabase() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM Userdetails"); // Delete all entries
        db.close();
    }
}
