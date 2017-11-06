package com.awiese.contentprovider.contentProvider;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import com.awiese.contentprovider.model.NotepadModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AWiese on 2017/11/06.
 */

public class DataQueries {

    Context mContext;

    public DataQueries(Context context) {
        this.mContext = context;
    }

    public List<NotepadModel> getListOfNotes() {
        List<NotepadModel> notepadModels = new ArrayList<>();
        Uri notes = Uri.parse(NotepadContentProvider.URL);
        Cursor c = mContext.getContentResolver().query(notes, null, null, null, "note_title_text");
        if (c != null) {
            if (c.moveToFirst()) {
                do {
                    int intId = c.getColumnIndex(NotepadContentProvider._ID);
                    String noteTitle = c.getString(c.getColumnIndex(NotepadContentProvider.NOTE_TITLE));
                    String noteBody = c.getString(c.getColumnIndex(NotepadContentProvider.NOTE_BODY_TEXT));
                    NotepadModel notepadModel = new NotepadModel(intId, noteTitle, noteBody);
                    notepadModels.add(notepadModel);
                } while (c.moveToNext());
            }
            c.close();
            return notepadModels;
        }

        throw new NullPointerException();
    }
}
