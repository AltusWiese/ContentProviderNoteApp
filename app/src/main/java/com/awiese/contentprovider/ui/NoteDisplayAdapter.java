package com.awiese.contentprovider.ui;

import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.awiese.contentprovider.R;
import com.awiese.contentprovider.RecycleViewClickListener;
import com.awiese.contentprovider.model.NotepadModel;
import com.awiese.contentprovider.provider.ContentProviderContract;

class NoteDisplayAdapter extends RecyclerView.Adapter<NoteDisplayViewHolder> {

    private Cursor cursor;
    private final RecycleViewClickListener mListener;


    NoteDisplayAdapter(RecycleViewClickListener listener) {
        mListener = listener;
    }

    NoteDisplayAdapter(Cursor cursor, RecycleViewClickListener listener) {
        this.cursor = cursor;
        mListener = listener;
    }

    @Override
    public NoteDisplayViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_view_display_single_note, parent, false);
        return new NoteDisplayViewHolder(v, mListener);
    }

    @Override
    public void onBindViewHolder(NoteDisplayViewHolder holder, int position) {
        cursor.moveToPosition(position);
        String noteId = cursor.getString(cursor.getColumnIndex(ContentProviderContract.Columns._ID));
        String noteTitleText = cursor.getString(
                cursor.getColumnIndex(ContentProviderContract.Columns.NOTE_TITLE));
        String noteBodyText = cursor.getString(
                cursor.getColumnIndex(ContentProviderContract.Columns.NOTE_BODY_TEXT));

        NotepadModel notepadModel = new NotepadModel(noteId, noteTitleText, noteBodyText);

        holder.noteId.setText(String.valueOf(notepadModel.getNoteId()));
        holder.noteTitleText.setText(notepadModel.getNotepadTitleText());
        holder.noteBodyText.setText(notepadModel.getNotepadBodyText());
        holder.itemView.setTag(notepadModel);
    }

    @Override
    public int getItemCount() {
        return cursor.getCount();
    }

}

