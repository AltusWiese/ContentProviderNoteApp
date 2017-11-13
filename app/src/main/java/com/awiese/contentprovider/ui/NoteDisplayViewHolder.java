package com.awiese.contentprovider.ui;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.awiese.contentprovider.R;
import com.awiese.contentprovider.RecycleViewClickListener;
import com.awiese.contentprovider.model.NotepadModel;

public class NoteDisplayViewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener {

    final TextView noteId;
    final TextView noteTitleText;
    final TextView noteBodyText;
    private final RecycleViewClickListener mListener;

    NoteDisplayViewHolder(View noteView, RecycleViewClickListener listener) {
        super(noteView);
        mListener = listener;
        noteView.setOnLongClickListener(this);
        noteId = noteView.findViewById(R.id.note_ref_id);
        noteTitleText = noteView.findViewById(R.id.title_display_note);
        noteBodyText = noteView.findViewById(R.id.body_display_note);

    }

    @Override
    public boolean onLongClick(View view) {
        mListener.onClick(view, (NotepadModel) view.getTag());
        return true;
    }
}
