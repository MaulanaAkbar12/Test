package com.example.projektodo;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "TODO.db";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "Email";
    public static final String COL_3 = "Username";
    public static final String COL_4 = "Password";
    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table Users(ID integer primary key autoincrement, Email text, Username Text, Password text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists Users");
        onCreate(db);
    }

    public boolean inserData(String Email, String Username, String Password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, Email);
        contentValues.put(COL_3, Username);
        contentValues.put(COL_4, Password);
        long result = db.insert("Users", null, contentValues);
        return result != -1;
    }

    @SuppressLint("Range")
    public String checkLogin (String Username, String Password) {
        SQLiteDatabase db = this.getWritableDatabase();
        String[] columns = {COL_2};
        String selecetion = "Username = ? and Password = ?";
        String[] selectionArgs = {Username, Password};
        Cursor cursor = db.query("Users", columns, selecetion, selectionArgs, null, null, null);
        String result = null;
        if (cursor != null && cursor.moveToFirst());
        {
            result = cursor.getString(cursor.getColumnIndex(COL_2));
            cursor.close();
        }
        return result;
    }
}
