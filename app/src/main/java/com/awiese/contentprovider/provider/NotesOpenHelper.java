package com.awiese.contentprovider.provider;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class NotesOpenHelper extends SQLiteOpenHelper{

    private static final String DATABASE_NAME = "NotesStash";
    private static final int DATABASE_VERSION = 1;

    public NotesOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DbSchema.CREATE_DB_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DbSchema.DROP_NOTES_TABLE);
        onCreate(db);
    }
}
