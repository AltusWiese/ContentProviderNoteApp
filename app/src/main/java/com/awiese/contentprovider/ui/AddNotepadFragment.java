package com.awiese.contentprovider.ui;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.awiese.contentprovider.R;
import com.awiese.contentprovider.provider.DataQueries;

public class AddNotepadFragment extends Fragment {

    private EditText notepadAddTitleText, notepadBodyAddText;
    private Button addNewNoteButton;


    public static AddNotepadFragment newInstance() {
        Bundle args = new Bundle();
        AddNotepadFragment fragment = new AddNotepadFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.add_new_note_fragment_view, container, false);
        setupViews(view);
        setupClickListeners();
        return view;
    }

    private void setupViews(View view) {
        notepadAddTitleText = view.findViewById(R.id.title_add_text);
        notepadBodyAddText = view.findViewById(R.id.body_add_text);
        addNewNoteButton = view.findViewById(R.id.button_add_note);

    }

    private void setupClickListeners() {
        addNewNoteButton.setOnClickListener(v -> {
            new DataQueries(getContext()).addNote(notepadAddTitleText, notepadBodyAddText);
        notepadAddTitleText.getText().clear();
        notepadBodyAddText.getText().clear();
        });
    }
}
