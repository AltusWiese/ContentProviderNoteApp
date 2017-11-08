package com.awiese.contentprovider.ui;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.awiese.contentprovider.R;
import com.awiese.contentprovider.RecycleViewClickListener;

public class NoteDisplayViewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener {


    final TextView noteTitleText;
    final TextView noteBodyText;
    private final  RecycleViewClickListener mListener;

    public NoteDisplayViewHolder(View noteView, RecycleViewClickListener listener) {
        super(noteView);
        mListener = listener;
        noteView.setOnLongClickListener(this);
        noteTitleText = noteView.findViewById(R.id.title_display_note);
        noteBodyText = noteView.findViewById(R.id.body_display_note);

    }

    @Override
    public boolean onLongClick(View view) {
        mListener.onClick(view, noteTitleText.getText().toString(), noteBodyText.getText().toString());
        return true;
    }
}
