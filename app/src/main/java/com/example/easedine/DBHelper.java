package com.example.easedine;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context) {
        super(context, "LoginDetails.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase Db) {
        Db.execSQL("CREATE TABLE Userdetails(uname TEXT PRIMARY KEY, pass TEXT, cpass TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase Db, int oldVersion, int newVersion) {
        Db.execSQL("DROP TABLE IF EXISTS Userdetails");
        onCreate(Db);
    }

//    public boolean insertuserdata(String uname, String pass, String cpass) {
//        SQLiteDatabase Db = this.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put("uname", uname);
//        contentValues.put("pass", pass);
//        contentValues.put("cpass", cpass);
//        long result = Db.insert("Userdetails", null, contentValues);
//        return result != -1; // return true if insertion is successful, false otherwise
//    }

    public boolean checkdetails(String uname, String pass) {
        SQLiteDatabase Db = this.getReadableDatabase();
        Cursor cursor = Db.rawQuery("SELECT * FROM Userdetails WHERE uname = ? AND pass = ?", new String[]{uname, pass});
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        return exists;
    }
    public Boolean checkUsername(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM users WHERE username = ?", new String[]{username});
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        return exists;
    }

    // Method to update user's password
    public Boolean updateUserPassword(String username, String newPassword) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("password", newPassword);
        int result = db.update("users", contentValues, "username = ?", new String[]{username});
        return result != -1;
    }

    // Modify insertuserdata method if necessary
    public Boolean insertuserdata(String uname, String pass, String copass) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", uname);
        contentValues.put("password", pass);
        long result = db.insert("users", null, contentValues);
        return result != -1;
    }
}

