package com.example.abjtty;

import android.provider.BaseColumns;

public class CollectionContract {
    public static final String TABLE_NAME = "records";
    public static class CollectionEntry implements BaseColumns {
        public static final String COLUMN_NAME_ARTIST = "artist";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_FORMAT = "format";
        public static final String COLUMN_NAME_VALUE = "value";
    }
    public static final String SQL_CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    CollectionEntry._ID + " INTEGER PRIMARY KEY," +
                    CollectionEntry.COLUMN_NAME_ARTIST + " TEXT, " +
                    CollectionEntry.COLUMN_NAME_TITLE + " TEXT, " +
                    CollectionEntry.COLUMN_NAME_FORMAT + " TEXT, " +
                    CollectionEntry.COLUMN_NAME_VALUE + " INT);";
    public static final String SQL_DELETE_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
}
