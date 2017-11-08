package com.awiese.contentprovider.ui;


import android.app.AlertDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.awiese.contentprovider.EditFragment;
import com.awiese.contentprovider.R;
import com.awiese.contentprovider.RecycleViewClickListener;
import com.awiese.contentprovider.contentProvider.DataQueries;

import java.util.ArrayList;

public class DisplayNotepadFragment extends Fragment {


    private RecycleViewClickListener listener;
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

        NoteDisplayAdapter noteDisplayAdapter = new NoteDisplayAdapter(new ArrayList<>(), listener);
        recyclerView.setAdapter(noteDisplayAdapter);

        final DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                layoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);

    }


    private void setupViews(View view) {
        displayNotesButton = view.findViewById(R.id.display_notes_button);
    }

    private void setupClickListeners(View view) {
        displayNotesButton.setOnClickListener(v -> setAdapterDisplayListOfNotes(view));


        listener = (deleteView, noteTitle, noteBody) -> editDeleteAlertDialog(noteTitle, noteBody);

    }

    private void setAdapterDisplayListOfNotes(View view) {
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view_list_notes);
        NoteDisplayAdapter noteDisplayAdapter = new NoteDisplayAdapter(new DataQueries(getContext()).getListOfNotes(), listener);
        recyclerView.setAdapter(noteDisplayAdapter);
    }

    private void editDeleteAlertDialog (String noteTitle, String noteBody) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(R.string.dialog_body)
                .setTitle(R.string.dialog_header);
        builder.setPositiveButton(R.string.edit_note_button, (dialog, which) -> initFragments(EditFragment.newInstance(noteTitle, noteBody)));

        builder.setNegativeButton(R.string.delete_note_button, (dialog, which) -> {
            DataQueries dataQueries = new DataQueries(getContext());
            dataQueries.deleteSelectedNote(noteTitle);

        });
        builder.setNeutralButton(R.string.cancel_note_button, (dialog, which) -> dialog.cancel());

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void initFragments(Fragment fragment) {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.commit();
    }

}
