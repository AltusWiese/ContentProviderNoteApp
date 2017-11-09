package com.awiese.contentprovider.provider;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.widget.EditText;
import android.widget.Toast;

import com.awiese.contentprovider.model.NotepadModel;

import java.util.ArrayList;
import java.util.List;

public class DataQueries {

    private final Context mContext;

    public DataQueries(Context context) {
        this.mContext = context;
    }


    public void addNote(EditText notepadTitleEditText, EditText notepadBodyEditText) {
        ContentValues values = new ContentValues();
        String response = "New note " + notepadTitleEditText.getText().toString() + " was added.";
        values.put(NotepadContentProvider.NOTE_TITLE,
                notepadTitleEditText.getText().toString());
        values.put(NotepadContentProvider.NOTE_BODY_TEXT,
                notepadBodyEditText.getText().toString());
        Uri uri = mContext.getContentResolver().insert(
                NotepadContentProvider.CONTENT_URI, values);
        if (uri != null) {
            Toast.makeText(mContext, response, Toast.LENGTH_LONG).show();
        }
    }

    public List<NotepadModel> getListOfNotes() {
        List<NotepadModel> notepadModels = new ArrayList<>();
        Uri notes = NotepadContentProvider.CONTENT_URI;
        Cursor c = mContext.getContentResolver().query(notes, null, null, null, "note_title_text");
        if (c != null) {
            if (c.moveToFirst()) {
                do {
                    String noteTitle = c.getString(c.getColumnIndex(NotepadContentProvider.NOTE_TITLE));
                    String noteBody = c.getString(c.getColumnIndex(NotepadContentProvider.NOTE_BODY_TEXT));
                    NotepadModel notepadModel = new NotepadModel(noteTitle, noteBody);
                    notepadModels.add(notepadModel);
                } while (c.moveToNext());
            }
            c.close();
            return notepadModels;
        }

        throw new NullPointerException();
    }

    public void updateSelectedNote(String oldNoteTitleText, String noteTitleText, String noteBodyText) {
        ContentValues mUpdateValues = new ContentValues();
        String mSelectionClause = NotepadContentProvider.NOTE_TITLE + " LIKE ?";
        String[] mSelectionArgs = {oldNoteTitleText};
        int mRowsUpdated;
        mUpdateValues.put(NotepadContentProvider.NOTE_TITLE, noteTitleText);
        mUpdateValues.put(NotepadContentProvider.NOTE_BODY_TEXT, noteBodyText);
        mRowsUpdated = mContext.getContentResolver().update(
                NotepadContentProvider.CONTENT_URI,
                mUpdateValues,
                mSelectionClause,
                mSelectionArgs
        );
        String response = mRowsUpdated + " rows were updated. " + oldNoteTitleText +
                " was changed.";
        Toast.makeText(mContext, response, Toast.LENGTH_LONG).show();
    }

    public void deleteSelectedNote(String noteTitle) {

        String mSelectionClause = NotepadContentProvider.NOTE_TITLE + " LIKE ?";
        String[] mSelectionArgs = {noteTitle};
        ContentResolver cr = mContext.getContentResolver();
        cr.delete(NotepadContentProvider.CONTENT_URI, mSelectionClause, mSelectionArgs);
        String response = noteTitle + " was deleted.";
        Toast.makeText(mContext, response, Toast.LENGTH_LONG).show();
    }
}
