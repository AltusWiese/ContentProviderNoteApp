package com.awiese.contentprovider.provider;


import android.net.Uri;
import android.provider.BaseColumns;

public final class ContentProviderContract {

    public static final String AUTHORITY = "com.awiese.contentprovider";
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY);

    public static final class Notes implements Columns {

        public static final Uri CONTENT_URI = Uri.withAppendedPath(
                ContentProviderContract.CONTENT_URI, "notes");

    }


    public interface Columns extends BaseColumns {

        String _ID = "_id";
        String NOTE_TITLE = "note_title_text";
        String NOTE_BODY_TEXT = "note_body_text";
    }
}
