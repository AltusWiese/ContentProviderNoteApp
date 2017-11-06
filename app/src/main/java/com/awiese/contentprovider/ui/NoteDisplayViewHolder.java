package com.awiese.contentprovider.ui;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.awiese.contentprovider.R;

public class NoteDisplayViewHolder extends RecyclerView.ViewHolder{

    final TextView noteTitleText;
    final TextView noteBodyText;

    public NoteDisplayViewHolder(View noteView) {
        super(noteView);

    noteTitleText = noteView.findViewById(R.id.title_display_note);
    noteBodyText = noteView.findViewById(R.id.body_display_note);
    }
}
