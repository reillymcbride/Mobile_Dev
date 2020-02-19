package com.example.a2019rmcbride.collegeapphelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "college_database";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(Database.College.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Database.College.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    //public void update(int id, String name, String decision, String deadline, String platform, String interview, String major){
    public void updateName(String id, String newName, String oldName){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE " + Database.College.TABLE_NAME + " SET " + Database.College.COLUMN_NAME  +
                " = '" + newName + "' WHERE " + Database.College._ID + " = '" + id + "'" + " AND " +
                Database.College.COLUMN_NAME + " = '" + oldName + "'";
        db.execSQL(query);
    }

    public void updateDecision(String id, String newName, String oldName){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE " + Database.College.TABLE_NAME + " SET " + Database.College.COLUMN_DECISION  +
                " = '" + newName + "' WHERE " + Database.College._ID + " = '" + id + "'" + " AND " +
                Database.College.COLUMN_DECISION + " = '" + oldName + "'";
        db.execSQL(query);
    }

    public void updateDeadline(String id, String newName, String oldName){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE " + Database.College.TABLE_NAME + " SET " + Database.College.COLUMN_DEADLINE  +
                " = '" + newName + "' WHERE " + Database.College._ID + " = '" + id + "'" + " AND " +
                Database.College.COLUMN_DEADLINE + " = '" + oldName + "'";
        db.execSQL(query);
    }

    public void updatePlatform(String id, String newName, String oldName){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE " + Database.College.TABLE_NAME + " SET " + Database.College.COLUMN_PLATFORM  +
                " = '" + newName + "' WHERE " + Database.College._ID + " = '" + id + "'" + " AND " +
                Database.College.COLUMN_PLATFORM + " = '" + oldName + "'";
        db.execSQL(query);
    }

    public void updateInterview(String id, String newName, String oldName){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE " + Database.College.TABLE_NAME + " SET " + Database.College.COLUMN_INTERVIEW  +
                " = '" + newName + "' WHERE " + Database.College._ID + " = '" + id + "'" + " AND " +
                Database.College.COLUMN_INTERVIEW + " = '" + oldName + "'";
        db.execSQL(query);
    }

    public void updateMajor(String id, String newName, String oldName){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE " + Database.College.TABLE_NAME + " SET " + Database.College.COLUMN_MAJOR  +
                " = '" + newName + "' WHERE " + Database.College._ID + " = '" + id + "'" + " AND " +
                Database.College.COLUMN_MAJOR + " = '" + oldName + "'";
        db.execSQL(query);
    }

    public void deleteEntry(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = " DELETE FROM " + Database.College.TABLE_NAME + " WHERE " + Database.College._ID
                + " = '" + id + "'";
        db.execSQL(query);

    }








}
