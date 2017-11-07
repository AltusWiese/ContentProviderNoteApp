package com.awiese.contentprovider.ui;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.awiese.contentprovider.R;
import com.awiese.contentprovider.contentProvider.DataQueries;

public class AddNotepadFragment extends Fragment {

    private EditText notepadTitleEditText, notepadBodyEditText;
    private Button addNewNoteButton;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.add_new_note_fragment_view, container, false);
        setupViews(view);
        setupClickListeners();
        return view;
    }

    private void setupViews(View view) {
        notepadTitleEditText = view.findViewById(R.id.title_edit_text);
        notepadBodyEditText = view.findViewById(R.id.body_edit_text);
        addNewNoteButton = view.findViewById(R.id.button_add_note);

    }

    private void setupClickListeners() {
        addNewNoteButton.setOnClickListener(v -> {
            new DataQueries(getContext()).addNote(notepadTitleEditText, notepadBodyEditText);
        notepadTitleEditText.getText().clear();
        notepadBodyEditText.getText().clear();
        });
    }
}
