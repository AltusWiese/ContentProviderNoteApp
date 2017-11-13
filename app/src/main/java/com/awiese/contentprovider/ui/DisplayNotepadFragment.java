package com.awiese.contentprovider.ui;


import android.app.AlertDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.awiese.contentprovider.R;
import com.awiese.contentprovider.RecycleViewClickListener;
import com.awiese.contentprovider.model.NotepadModel;
import com.awiese.contentprovider.provider.DataQueries;

import static com.awiese.contentprovider.provider.ContentProviderContract.CONTENT_URI;

public class DisplayNotepadFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {

    private RecyclerView recyclerView;
    private RecycleViewClickListener listener;
    private static final int LOADER_ID = 853;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_display_notepad, container, false);
        recyclerView = view.findViewById(R.id.recycler_view_list_notes);
        listener = (deleteView, noteId) -> editDeleteAlertDialog(noteId);
        setupRecyclerView();
        getLoaderManager().initLoader(LOADER_ID, null, this);
        return view;
    }

    private void setupRecyclerView() {

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        NoteDisplayAdapter noteDisplayAdapter = new NoteDisplayAdapter(listener);
        recyclerView.setAdapter(noteDisplayAdapter);

        final DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                layoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);

    }

    private void editDeleteAlertDialog(NotepadModel notepadModel) {
        Bundle editTextBundle = new Bundle();
        editTextBundle.putParcelable("notepadModel", notepadModel);
        EditFragment newFragment = new EditFragment();
        newFragment.setArguments(editTextBundle);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(R.string.dialog_body)
                .setTitle(R.string.dialog_header);
        builder.setPositiveButton(R.string.edit_note_button, (dialog, which) -> initFragments(newFragment.newInstance(notepadModel)));

        builder.setNegativeButton(R.string.delete_note_button, (dialog, which) -> {
            DataQueries dataQueries = new DataQueries(getContext());
            dataQueries.deleteSelectedNote(notepadModel);

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


    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(getActivity(), CONTENT_URI, null, null, null, "note_title_text");
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        data.moveToFirst();
        NoteDisplayAdapter noteDisplayAdapter = new NoteDisplayAdapter(data, listener);
        recyclerView.setAdapter(noteDisplayAdapter);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}
