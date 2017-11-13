package com.awiese.contentprovider.provider;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.widget.Toast;

import com.awiese.contentprovider.model.NotepadModel;

public class DataQueries {

    private final Context mContext;

    public DataQueries(Context context) {
        this.mContext = context;
    }


    public void addNote(NotepadModel notepadModel) {
        ContentValues values = new ContentValues();
        String title = notepadModel.getNotepadTitleText();
        String body = notepadModel.getNotepadBodyText();
        String response = "New note " + title + " was added.";
        values.put(ContentProviderContract.Columns.NOTE_TITLE,
                title);
        values.put(ContentProviderContract.Columns.NOTE_BODY_TEXT,
                body);
        Uri uri = mContext.getContentResolver().insert(
                ContentProviderContract.Notes.CONTENT_URI, values);
        if (uri != null) {
            Toast.makeText(mContext, response, Toast.LENGTH_LONG).show();
        }
    }

    public void updateSelectedNote(NotepadModel notepadModel) {
        ContentValues mUpdateValues = new ContentValues();
        String mSelectionClause = ContentProviderContract.Columns._ID + " = ?";
        String[] mSelectionArgs = {notepadModel.getNoteId()};

        mUpdateValues.put(ContentProviderContract.Columns.NOTE_TITLE, notepadModel.getNotepadTitleText());
        mUpdateValues.put(ContentProviderContract.Columns.NOTE_BODY_TEXT, notepadModel.getNotepadBodyText());
        mContext.getContentResolver().update(
                ContentProviderContract.Notes.CONTENT_URI,
                mUpdateValues,
                mSelectionClause,
                mSelectionArgs
        );
        String response = "Note " + notepadModel.getNoteId() +
                " was changed.";
        Toast.makeText(mContext, response, Toast.LENGTH_LONG).show();
    }

    public void deleteSelectedNote(NotepadModel notepadModel) {

        String mSelectionClause = ContentProviderContract.Columns._ID + " = ?";
        String[] mSelectionArgs = {notepadModel.getNoteId()};
        ContentResolver cr = mContext.getContentResolver();
        cr.delete(ContentProviderContract.Notes.CONTENT_URI, mSelectionClause, mSelectionArgs);
        String response = notepadModel.getNoteId() + " was deleted.";
        Toast.makeText(mContext, response, Toast.LENGTH_LONG).show();
    }
}
