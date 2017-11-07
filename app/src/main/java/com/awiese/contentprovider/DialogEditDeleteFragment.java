package com.awiese.contentprovider;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v4.app.DialogFragment;

/**
 * Created by AWiese on 2017/11/07.
 */


public class DialogEditDeleteFragment extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(R.string.dialog_header)
                .setPositiveButton(R.string.edit_note_button, (dialog, id) -> {

                })
                .setNegativeButton(R.string.delete_note_button, (dialog, id) -> {
                    // User cancelled the dialog
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }
}