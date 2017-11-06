package com.awiese.contentprovider.ui;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.awiese.contentprovider.R;
import com.awiese.contentprovider.model.NotepadModel;

import java.util.List;

class NoteDisplayAdapter extends RecyclerView.Adapter<NoteDisplayViewHolder> {

    private final List<NotepadModel> notepadModelList;


    NoteDisplayAdapter(List<NotepadModel> notepadModelList) {
        this.notepadModelList = notepadModelList;
    }

    @Override
    public NoteDisplayViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.display_single_note_in_recyclerview, parent, false);
        return new NoteDisplayViewHolder(v);
    }

    @Override
    public void onBindViewHolder(NoteDisplayViewHolder holder, int position) {
        NotepadModel notepadModel = notepadModelList.get(position);
        holder.noteTitleText.setText(notepadModel.getNotepadTitleText());
        holder.noteBodyText.setText(notepadModel.getNotepadBodyText());

    }

    @Override
    public int getItemCount() {
        return notepadModelList.size();
    }

}

