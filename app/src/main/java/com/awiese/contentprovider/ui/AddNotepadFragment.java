package com.awiese.contentprovider.ui;

import android.app.Fragment;
import android.content.ContentValues;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.awiese.contentprovider.R;
import com.awiese.contentprovider.contentProvider.NotepadContentProvider;

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
        notepadTitleEditText = view.findViewById(R.id.title_text);
        notepadBodyEditText = view.findViewById(R.id.body_of_text);
        addNewNoteButton = view.findViewById(R.id.button_add_note);

    }

    private void setupClickListeners() {
        addNewNoteButton.setOnClickListener(v -> addNote());
    }

    private void addNote() {
        ContentValues values = new ContentValues();
        values.put(NotepadContentProvider.NOTE_TITLE,
                notepadTitleEditText.getText().toString());
        values.put(NotepadContentProvider.NOTE_BODY_TEXT,
                notepadBodyEditText.getText().toString());
        Uri uri = getContext().getContentResolver().insert(
                NotepadContentProvider.CONTENT_URI, values);
        if (uri != null) {
            Toast.makeText(getContext(), uri.toString(), Toast.LENGTH_LONG).show();
        }
    }
}
