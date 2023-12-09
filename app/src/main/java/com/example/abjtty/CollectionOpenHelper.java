package com.example.abjtty;

import static com.example.abjtty.CollectionContract.SQL_CREATE_TABLE;
import static com.example.abjtty.CollectionContract.TABLE_NAME;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class CollectionOpenHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "CollectionDatabase.db";
    public static final int DB_VERSION = 2;

    public CollectionOpenHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    public ArrayList<RecordCollection> ReadCollection(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        ArrayList<RecordCollection> collectionArrayList = new ArrayList<>();
        if (cursor.moveToFirst()){
            do {
                collectionArrayList.add(new RecordCollection(
                   cursor.getInt(0),
                   cursor.getString(1),
                   cursor.getString(2),
                   cursor.getString(3),
                   cursor.getInt(4)));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return collectionArrayList;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(CollectionContract.SQL_DELETE_TABLE);
        //db.execSQL(SQL_CREATE_TABLE);
        onCreate(db);
    }
}
