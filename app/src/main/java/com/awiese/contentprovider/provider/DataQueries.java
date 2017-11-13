package com.awiese.contentprovider.provider;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.widget.EditText;
import android.widget.Toast;

public class DataQueries {

    private final Context mContext;

    public DataQueries(Context context) {
        this.mContext = context;
    }


    public void addNote(EditText notepadTitleEditText, EditText notepadBodyEditText) {
        ContentValues values = new ContentValues();
        String response = "New note " + notepadTitleEditText.getText().toString() + " was added.";
        values.put(ContentProviderContract.Columns.NOTE_TITLE,
                notepadTitleEditText.getText().toString());
        values.put(ContentProviderContract.Columns.NOTE_BODY_TEXT,
                notepadBodyEditText.getText().toString());
        Uri uri = mContext.getContentResolver().insert(
                ContentProviderContract.Notes.CONTENT_URI, values);
        if (uri != null) {
            Toast.makeText(mContext, response, Toast.LENGTH_LONG).show();
        }
    }

    public void updateSelectedNote(String noteId, String noteTitleText, String noteBodyText) {
        ContentValues mUpdateValues = new ContentValues();
        String mSelectionClause = ContentProviderContract.Columns._ID + " = ?";
        String[] mSelectionArgs = {noteId};

        mUpdateValues.put(ContentProviderContract.Columns.NOTE_TITLE, noteTitleText);
        mUpdateValues.put(ContentProviderContract.Columns.NOTE_BODY_TEXT, noteBodyText);
        mContext.getContentResolver().update(
                ContentProviderContract.Notes.CONTENT_URI,
                mUpdateValues,
                mSelectionClause,
                mSelectionArgs
        );
        String response = "Note " + noteId +
                " was changed.";
        Toast.makeText(mContext, response, Toast.LENGTH_LONG).show();
    }

    public void deleteSelectedNote(String noteId) {

        String mSelectionClause = ContentProviderContract.Columns._ID + " = ?";
        String[] mSelectionArgs = {noteId};
        ContentResolver cr = mContext.getContentResolver();
        cr.delete(ContentProviderContract.Notes.CONTENT_URI, mSelectionClause, mSelectionArgs);
        String response = noteId + " was deleted.";
        Toast.makeText(mContext, response, Toast.LENGTH_LONG).show();
    }
}
