package com.example.aircraftwar2024.DAO;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbOpenHelper extends SQLiteOpenHelper {
    public DbOpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE easyRecords(name VARCHAR(50), score INT NOT NULL, time VARCHAR(100))");
        db.execSQL("CREATE TABLE mediumRecords(name VARCHAR(50), score INT NOT NULL, time VARCHAR(100))");
        db.execSQL("CREATE TABLE hardRecords(name VARCHAR(50), score INT NOT NULL, time VARCHAR(100))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
