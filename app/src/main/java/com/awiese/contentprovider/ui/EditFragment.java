package com.awiese.contentprovider.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.awiese.contentprovider.R;
import com.awiese.contentprovider.model.NotepadModel;
import com.awiese.contentprovider.provider.DataQueries;


public class EditFragment extends Fragment {

    private String noteTitle, noteBody, noteId;
    private EditText notepadTitleEditText, notepadBodyEditText;
    private Button EditNoteButton;

    public EditFragment newInstance(NotepadModel notepadModel) {
        Bundle args = new Bundle();
        args.putParcelable("notepadModel", notepadModel);
        EditFragment fragment = new EditFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit_note, container, false);
        GetBundleArguments();
        setupViews(view);
        setupClickListeners();
        return view;
    }

    private void GetBundleArguments() {
        Bundle retrievedArguments = getArguments();
        NotepadModel note = retrievedArguments.getParcelable("notepadModel");
        if (note != null) {
            noteId = note.getNoteId();
            noteTitle = note.getNotepadTitleText();
            noteBody = note.getNotepadBodyText();
        }
    }

    private void setupViews(View view) {
        notepadTitleEditText = view.findViewById(R.id.title_edit_text);
        notepadBodyEditText = view.findViewById(R.id.body_edit_text);
        EditNoteButton = view.findViewById(R.id.button_edit_note);
        notepadTitleEditText.setText(noteTitle);
        notepadBodyEditText.setText(noteBody);

    }

    private void setupClickListeners() {
        EditNoteButton.setOnClickListener(v -> {
            String updatedTitleText, updatedBodyText;
            updatedTitleText = notepadTitleEditText.getText().toString();
            updatedBodyText = notepadBodyEditText.getText().toString();
            NotepadModel notepadModel = new NotepadModel(noteId, updatedTitleText, updatedBodyText);
            new DataQueries(getContext()).updateSelectedNote(notepadModel);
            initFragments(AddNotepadFragment.newInstance());
        });
    }

    private void initFragments(Fragment fragment) {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.commit();
    }
}