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
import com.awiese.contentprovider.provider.DataQueries;


public class EditFragment extends Fragment {

    private static String oldNoteTitleText, oldNoteBodyText;
    private EditText notepadTitleEditText, notepadBodyEditText;
    private Button EditNoteButton;

    public static EditFragment newInstance(String noteTitleText, String noteBodyText) {
        oldNoteTitleText = noteTitleText;
        oldNoteBodyText = noteBodyText;
        Bundle args = new Bundle();
        EditFragment fragment = new EditFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.edit_note_fragment_view, container, false);
        setupViews(view);
        setupClickListeners();
        return view;
    }

    private void setupViews(View view) {
        notepadTitleEditText = view.findViewById(R.id.title_edit_text);
        notepadBodyEditText = view.findViewById(R.id.body_edit_text);
        EditNoteButton = view.findViewById(R.id.button_edit_note);
        notepadTitleEditText.setText(oldNoteTitleText);
        notepadBodyEditText.setText(oldNoteBodyText);

    }

    private void setupClickListeners() {
        EditNoteButton.setOnClickListener(v -> {
            String updatedTitleText, updatedBodyText;
            updatedTitleText = notepadTitleEditText.getText().toString();
            updatedBodyText = notepadBodyEditText.getText().toString();
            new DataQueries(getContext()).updateSelectedNote(oldNoteTitleText, updatedTitleText, updatedBodyText);
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