package com.awiese.contentprovider.ui;


import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.awiese.contentprovider.R;
import com.awiese.contentprovider.contentProvider.NotepadContentProvider;
import com.awiese.contentprovider.model.NotepadModel;

import java.util.ArrayList;
import java.util.List;

public class DisplayNotepadFragment extends Fragment {

    private Button displayNotesButton;

    @Override

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.display_notepad_fragment_view, container, false);
        setupViews(view);
        setupRecyclerView(view);
        setupClickListeners(view);
        return view;
    }

    private void setupRecyclerView(View view) {
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view_list_notes);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        NoteDisplayAdapter noteDisplayAdapter = new NoteDisplayAdapter(new ArrayList<>());
        recyclerView.setAdapter(noteDisplayAdapter);

        final DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                layoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);
    }

    private void setupViews(View view) {
        displayNotesButton = view.findViewById(R.id.display_notes_button);
    }


    private void setupClickListeners(View view) {
        displayNotesButton.setOnClickListener(v -> setViewModelListOfNotes(view));
    }

    private void setViewModelListOfNotes(View view) {
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view_list_notes);
        NoteDisplayAdapter noteDisplayAdapter = new NoteDisplayAdapter(getListOfNotes());
        recyclerView.setAdapter(noteDisplayAdapter);
    }

    private List<NotepadModel> getListOfNotes() {
        List<NotepadModel> notepadModels = new ArrayList<>();
        Uri notes = Uri.parse(NotepadContentProvider.URL);
        Cursor c = getContext().getContentResolver().query(notes, null, null, null, "note_title_text");
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
}
