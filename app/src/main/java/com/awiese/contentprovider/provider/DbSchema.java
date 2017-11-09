package com.awiese.contentprovider.provider;


public interface DbSchema {

    String DATABASE_NAME = "NotesStash";
    String NOTES_TABLE_NAME = "notes";
    int DATABASE_VERSION = 1;
    String CREATE_DB_TABLE =
            " CREATE TABLE " + NOTES_TABLE_NAME +
                    " (_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    " note_title_text TEXT NOT NULL, " +
                    " note_body_text TEXT NOT NULL);";
    String DROP_NOTES_TABLE =
            "DROP TABLE IF EXISTS" + NOTES_TABLE_NAME;
}
